---
name: jsf-primefaces-crud
description: Build JSF+PrimeFaces CRUD features with Spring-managed beans, in-memory service layer, and Facelets views
---
## Layers

| Layer | Annotation | Scope |
|---|---|---|
| Entity POJO | Plain class in `com.oumous.skyface` | None (DTO) |
| Service | `@Service` | Singleton (injected) |
| Backing Bean | `@Component` + `@Scope("view")` | View scope (per-tab) |
| Facelets View | `.xhtml` in `src/main/resources/META-INF/resources/` | — |

## Pattern

1. **Entity** – Simple POJO with fields, getters/setters, no annotations.
2. **Service** – `@Service` class with thread-safe in-memory `CopyOnWriteArrayList`, `AtomicLong` ID counter, methods: `findAll`, `findById`, `save` (upsert by null id), `delete`.
3. **Backing Bean** – `@Component @Scope("view")` with injected service, `selectedUser` field for dialog binding, `users` list cached per view, `prepareNew`/`prepareEdit` for dialog prep, `save`/`delete` actions that flush the cached list.
4. **View** – PrimeFaces `p:dataTable` with paginator, `p:dialog` for form, `p:commandButton` for actions. Use `update=":listForm"` on save/delete to refresh the table.

## Key conventions

- `@Scope("view")` keeps bean state across AJAX requests within the same view
- Service uses `synchronized` on write methods for thread safety
- Backing bean nulls the cached list after write operations so next read re-fetches
- Dialog form is in a separate `<h:form>` from the list table to avoid nested forms
- Use `process="@this" update=":dialogForm"` on edit/new buttons to prepare the dialog without submitting the whole page
