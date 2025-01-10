---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: page
nav_order: 2
---

# Project Structure

```tree
.
├── app
│   ├── bin
│   ├── build
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
│           ├── java
│           │   └── mastermind
│           └── resources
├── build
└── gradle
```

## Project Root

The repository this project resides in is a compilation of all coursework
throughout the semester.

Thus, the project root is [`/culminating-mastermind`](https://github.com/powersagitar/ics4u/tree/main/culminating-mastermind).

## Build Directories

- `app/bin`
- `app/build`
- `build`

## Gradle Directory

- `gradle`

## Project Sources

- `app/src/main/java/mastermind/core` core (non-gui) components of program
- `app/src/main/java/mastermind/core/solvers` algorithms for solving mastermind game
- `app/src/main/java/mastermind/gui` gui components of program
- `app/src/main/java/mastermind/gui/panels` shareable gui components
- `app/src/main/java/mastermind/gui/scenes` scenes/stages/pages/screens of the game
  - each page/stage/screen of the program is a scene
  - e.g., game mode selector, code breaker gameplay, code maker gameplay
- `app/src/main/java/mastermind/utils` general utilities that are not directly
  associated with program logic
  - i.e., components that can be reused by other projects
  - e.g., helper methods, data structures

## Unit Tests

- `app/src/test/java/mastermind`
