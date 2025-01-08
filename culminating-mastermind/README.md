# Mastermind Project Wiki

## Project Structure

```tree
.
├── culminating-mastermind.iml
├── out
├── src
│   └── mastermind
│       ├── Mastermind.java
│       ├── core
│       │   ├── Code.java
│       │   ├── Response.java
│       │   └── solvers
│       │       ├── DonaldKnuthAlgorithm.java
│       │       ├── EasyAlgorithm.java
│       │       ├── HumanSolver.java
│       │       ├── MastermindAlgorithm.java
│       │       ├── MastermindSolver.java
│       │       └── MediumAlgorithm.java
│       ├── gui
│       │   ├── panels
│       │   │   └── GameBoard.java
│       │   └── scenes
│       │       ├── CodeBreaker.java
│       │       ├── CodeBreakerSelector.java
│       │       ├── CodeMaker.java
│       │       ├── GameModeSelector.java
│       │       ├── Result.java
│       │       └── Scene.java
│       └── utils
│           ├── MathUtil.java
│           └── Tuple2.java
└── wiki.md
```

### Editor Settings

- `culminating-mastermind.iml` Intellij configuration file

### Binary Files

- `out` Intellij build directory

### Project Sources

- `src/mastermind/core` core (non-gui) components of program
- `src/mastermind/core/solvers` algorithms for solving mastermind game
- `src/gui` gui components of program
- `src/gui/panels` shareable gui components
- `src/gui/scenes` scenes/stages/pages/screens of the game
  - each page/stage/screen of the program is a scene
  - e.g., game mode selector, code breaker gameplay, code maker gameplay
- `src/utils` general utilities that are not directly associated with program logic
  - i.e., components that can be reused by other projects
  - e.g., helper methods, data structures

## Solvers

```mermaid
classDiagram
  MastermindSolver <|-- HumanSolver
  MastermindSolver <|-- MastermindAlgorithm
  MastermindAlgorithm <|-- EasyAlgorithm
  MastermindAlgorithm <|-- MediumAlgorithm
  MastermindAlgorithm <|-- DonaldKnuthAlgorithm

  class MastermindSolver {
    <<abstract>>
    +getattempts(): int
    #isInitialguess(): boolean
    #hasExeededMaxGuesses(): boolean
  }

  class MastermindAlgorithm {
    <<abstract>>
    +guess(): Code*
    +guess(response: Response): ~Status, Code~*
  }
```

## Scenes (GUI)

Each scene inherits `src/gui/scenes/Scene.java`.

### Transitions

Note that the node names correspond to classes in `src/gui/scenes`.

```mermaid
flowchart TB
  mode_select --> algorithm_select
  mode_select --> code_maker

  algorithm_select --> code_breaker

  code_maker --> result
  code_breaker --> result

  mode_select[GameModeSelector]
  result[Result]

  subgraph code breaker
    algorithm_select[CodeBreakerSelector]
    code_breaker[CodeBreaker]
  end

  subgraph code maker
    code_maker[CodeMaker]
  end
```

## Communications Between Solvers and GUI

### Algorithmic Solvers

```mermaid
sequenceDiagram
  solver ->> gui: first guess
  gui ->> solver: user response (hints)
  solver ->> gui: next guess
```

### Human Solver

```mermaid
sequenceDiagram
  gui ->> solver: user guess
  solver ->> gui: response (hints)
```
