# Skyface — Guide de transformation en starter réutilisable

## 1. Réorganiser les packages

```
com.oumous.skyface/
├── entity/
├── repository/
├── service/
├── bean/
├── controller/
├── config/
└── SkyfaceApplication.java
```

## 2. JPA + H2 (persistance) ✅

- spring-boot-starter-data-jpa + h2 dans pom.xml
- AdminUser → @Entity
- AdminUserRepository (Spring Data JPA)
- data.sql (démo)
- Profil dev (H2) / prod (PostgreSQL) — TODO

## 3. Layout PrimeFaces (gabarit) ✅

```
META-INF/resources/
├── templates/layout.xhtml   (header, menu, footer, <ui:insert>)
├── index.xhtml
├── admin-users.xhtml
└── error/404.xhtml
```

## 4. Spring Security ✅

- spring-boot-starter-security
- Login page PrimeFaces (login.xhtml) — standalone, plain HTML form
- Roles in-memory (dev): admin/admin (ADMIN), user/user (USER)
- CSRF désactivé, H2 console accessible
- Logout lien dans la topbar

## 5. Profils Spring ✅

application.properties → common (PrimeFaces, JSF), default profile = dev
application-dev.properties  → H2 + data.sql seed
application-prod.properties → PostgreSQL

## 6. Logback ✅

logback-spring.xml avec rolling file (10MB per file, 30 day retention, 1GB cap)

## 7. RTL + Internationalisation (Arabe) ✅

### 7.1 LocaleBean (@Component @SessionScope)
- locale, isRtl(), getDir(), switchToFrench/Arabic/English()

### 7.2 Bundles de messages
- messages.properties (FR)
- messages_en.properties (EN)
- messages_ar.properties (AR)
- MessagesConfig.java (@Bean MessageSource)

### 7.3 Direction dynamique
- `<f:view locale="#{localeBean.locale}">`
- dir="#{localeBean.dir}" sur `<html>` et `<p:outputPanel>`
- Sélecteur de langue dans le layout

### 7.4 CSS RTL
- META-INF/resources/css/rtl-overrides.css

## 8. Tests ✅

- AdminUserRepositoryTest (@DataJpaTest) — 4 tests
- AdminUserServiceTest (@SpringBootTest) — 5 tests

## 9. Nettoyage final ✅

- Supprimer code démo (AdminDashboardBean, index.xhtml existant) ✅
- README.md avec démarrage rapide ✅
- Renommer groupId/artifactId — conservé com.oumous / skyface
