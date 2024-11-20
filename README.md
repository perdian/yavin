## Assumptions

- The application has both a separate [backend](backend/) and [frontend](frontend/) module.

### Backend

- The backend is implemented as a Spring Boot application.
- The Spring Boot application itself is deployed via the [`backend/application`](backend/application/) Maven artifact.
- Separate modules for distinct functionalities can be created as Maven artifacts in the [`backend/modules/`](backend/modules/) folder.
- The distinct modules are included from within the [`backend/application`](backend/application/) artifact.
- The [`backend/development`](backend/development/) artifact is designed to be used only during local development. It is based upon the [`backend/application`](backend/application/) artifact.

### Frontend

- To be defined... no template defined here yet.

## Packaging

Both the [`backend/application`](backend/application/) as well as the [`frontend/application`](frontend/application/) artifacts will (also) create a Docker container if the Maven profile `docker` is active.

## Naming

The dummy application is named `yavin`. Despite a nice reference to master Yoda it's a name that is easy to find in search and replace operations and isn't used by any external framework or library so the actual renamings shouldn't have any side effects.
