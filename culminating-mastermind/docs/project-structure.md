---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: page
nav_order: 2
---

# Project Structure

## Project Root

The repository this project resides in is a compilation of all coursework
throughout the semester.

Thus, the project root is [`/culminating-mastermind`](https://github.com/powersagitar/ics4u/tree/main/culminating-mastermind).

## Worktree

```tree
.
├── app
│   ├── build
│   │   └── docs
│   │       └── javadoc
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── mastermind
│       │   │       ├── core
│       │   │       │   └── solvers
│       │   │       ├── gui
│       │   │       │   ├── panels
│       │   │       │   └── scenes
│       │   │       └── utils
│       │   └── resources
│       └── test
├── docs
└── utils
```

- **app**: Primary source code and assets
  - **app/build/docs/javadoc**: Output directory of [`./gradlew
javadoc`](/ics4u/building/#building-javadoc)
  - **app/src/main/java/mastermind**: See [Javadoc](https://powersagitar.github.io/ics4u/javadoc/)
    and [design](/ics4u/design)
  - **app/src/test**: Unit tests
- **docs**: project documentation
- **utils**: utility scripts and resources
