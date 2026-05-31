# Skyface

Spring Boot 2.7 / JoinFaces 4 / PrimeFaces 11 — JSF web app starter with RTL/Arabic support.

## Quick Start

```bash
./mvnw spring-boot:run
```

Open `http://localhost:8080` and login with `admin` / `admin`.

## Profiles

| Profile | Database | Command |
|---|---|---|
| `dev` (default) | H2 in-memory | `./mvnw spring-boot:run` |
| `prod` | PostgreSQL | `./mvnw spring-boot:run -Dspring-boot.run.profiles=prod` |
| `mysql` | MySQL | `./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql` |

## Default Users

| Username | Password | Role |
|---|---|---|
| `admin` | `admin` | ADMIN |
| `user` | `user` | USER |

## Features

- PrimeFaces layout with header, sidebar, content
- JPA + H2/PostgreSQL/MySQL
- Spring Security login
- RTL / Arabic / French / English i18n
- Rolling log file

## Build

```bash
./mvnw clean package
java -jar target/skyface-0.0.1-SNAPSHOT.jar
```
