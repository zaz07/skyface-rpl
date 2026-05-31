# Skyface

Spring Boot 2.7 / JoinFaces 4 / PrimeFaces JSF web app, Java 17, Maven.

## Commands

```bash
./mvnw spring-boot:run                                   # dev (H2)
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod   # prod (PostgreSQL)
./mvnw test                                               # all tests
./mvnw test -Dtest=SkyfaceApplicationTests                # single test class
./mvnw clean package                                      # build jar
```

## Structure

| Path | Role |
|---|---|
| `src/main/java/com/oumous/skyface/SkyfaceApplication.java` | Entrypoint (`@SpringBootApplication`) |
| `src/main/java/com/oumous/skyface/entity/` | JPA entities / POJOs |
| `src/main/java/com/oumous/skyface/repository/` | Spring Data repositories |
| `src/main/java/com/oumous/skyface/service/` | `@Service` business logic |
| `src/main/java/com/oumous/skyface/bean/` | JSF backing beans (`@Component` + scope) |
| `src/main/java/com/oumous/skyface/controller/` | Spring MVC `@Controller` |
| `src/main/java/com/oumous/skyface/config/` | `@Configuration` classes |
| `src/main/resources/application.properties` | Config: PrimeFaces saga theme, .xhtml suffix |
| `src/main/resources/META-INF/resources/` | Facelets `.xhtml` views |
| `pom.xml` | Single module, no multi-module |

## Stack

- **Spring Boot 2.7.17** – web starter
- **JoinFaces 4.7.17** – PrimeFaces Spring Boot integration (JSF)
- **Views**: Facelets `.xhtml` files served via `javax.faces.DEFAULT_SUFFIX=.xhtml`
- **Test**: JUnit 5 + `@SpringBootTest` (context-loads test only)
- **Build**: Maven Wrapper (`./mvnw`), no Gradle

## Conventions

- Use Eclipse or any IDE; Eclipse project metadata (`.classpath`, `.project`, `.settings`) is committed
- `.gitignore` ignores IDE dirs (`.idea`, `.vscode/`, `.sts4-cache`) and `target/`
- No Lombok, no custom codegen, no migrations
- JPA entities in `entity/`, Spring Data repositories in `repository/`
- `data.sql` seeds H2 on startup; `spring.jpa.hibernate.ddl-auto=update` for dev
