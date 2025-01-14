---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: home
nav_order: 1
---

# Home

This is the wiki for mastermind, the ICS4U culminating project.

The game being implemented is the standard variant of the game, where the solver
has to guess a secret code of length 4 in 10 attempts. The game is played on a
grid, with 6 possible colors and 4 pegs to provide hints. The operator can
choose to make a code and ask the program to guess, or they can ask the program
to make a code and guess themselves.

## Supported Features

### Required

- [x] Gameplay with GUI
  - Algorithm Solvers (Program Guesses)
    - Donald Knuth 5-Guess Algorithm
    - Medium Algorithm
    - Basic Algorithm
  - Human Solver (User Guesses)
- [x] File Handling
  - Logging
  - Different Code Maker Levels

### Additional

- Gameplay
  - User Response Validation (All Hints Provided Are Correct)
  - Different Code Maker Levels
- GUI
  - Help Button (Instructions & Tutorial)
  - Home Button

### More to Come

- [See bug tracker](https://github.com/powersagitar/ics4u/issues?q=is%3Aissue+is%3Aopen+has%3Amastermind)
