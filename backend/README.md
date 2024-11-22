## Backend Architecture

The architecture of the `backend` artifact consists of three major parts:

- The [`application`](application/) directory contains the deployable artifact that builds the actual application binary (either as JAR file or as combined Docker container).
- The [`modules`](modules/) directory contains a set of separate and distinct functionalities that can be grouped together as distinct artifacts. All modules should be included as dependencies from within the [`application`](application/) artifact.
- The [`development`](development/) directory contains additional logics that are only relevant and must only be used during local development. It is designed to enable a convenient and efficient local development process. These logics never go into the resulting application itself.

### Include packages into `application` or create a separate `module`?

The distinction between the [`application`](application/) directory (and corresponding Maven artifact) and the artifacts in the [`modules`](modules/) directory is fluent.

In doubt, you should start by integrating new logics in the `application` artifact. If it becomes clear that the logics become larger and form a single and cohesive unit they should be extracted, refactored and placed into a distinct `module`.

Use common sense. Don't create a new module just to create a new module. Create a new module because it helps us to grasp and isolate the concept that is being implemented within the module.

Each new module should reserve resources according to its name. A module `foo` should reserve the following resources:

- The subdirectory `foo` below the [`backend/modules`](modules/) directory.
- The Maven artifact identifier `yavin-backend-module-foo`
- The Java package `de.perdian.yavin.backend.modules.foo`

As long as you start the new module within the `application` artifact the Java package name should also be `de.perdian.yavin.backend.modules.foo`. This way an extraction of the classes into a separate module can easily be done and doesn't change dependent classes within the `application` artifact.

### Dependencies

The `application` artifact must have dependencies to all the different `modules`.

A `module` is allowed to have dependencies to other `modules`, but the dependency graph must be directed. There must not be circular dependencies between modules.

The same rules are in place for package dependencies inside the `applications` artifact. A module package (e.g. `de.perdian.yavin.backend.modules.foo`) is allowed to have dependencies to other module packages (e.g. `de.perdian.yavin.backend.modules.bar`) but these dependencies must not form circular dependencies, neither direct nor transitive.

## Backend module structure

A module package (either located inside the `applications` artifact or a separate module) should be structured according to the following structure:

```
  de.perdian.yavin.backend.modules.foo
  - ports
  - adapters
    - controllers
    - services
  - repositories
  - domain
```

- `ports` should contain interfaces that other packages are allowed to reference and use. They represent the *public* API of the module. For larger modules with multiple ports the package may further be separated into an `incoming` and `outgoing` subpackages that signalize if data is coming into the application or leaves the application.
- `adapters` should contain the implementations of the interfaces in the `ports` package. Any code within the `adapters` package is considered part of the *private* API of the module and must not be directly referenced or used by any classes outside the module.
    - `controllers` should contain Spring controller implementations that are called from users.
    - `services` should contain any other port implementations.
- `repositories` should contain any JPA entities and repositories that are used to store data to external systems (e.g. databases). Most of the time they should not be referenced or used outside the package.
- `domain` should contain any domain specific classes that represent the model used by both the `incoming` and/or `outgoing` submodules. the `domain` is considered part of the *public* API of the module.

Use common sense. If there are good reasons for a different structure (and only then) don't force the classes into the structure above if it doesn't fit but try to stay close the idea layed out above.
