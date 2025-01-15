---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: home
nav_order: 1
---

# Home

This is the wiki for Mastermind (Code Breaker), the ICS4U1 culminating project.

The game being implemented is the standard variant of the game, where the solver
has to guess a secret code of length 4 in 10 attempts. The game is played on a
grid, with 6 possible colors and 4 pegs (for each slot in the code) to provide
hints. The operator can choose to make a code and ask the program to guess, or
they can ask the program to make a code and guess themselves.

## Supported Features

### Required

- [x] Gameplay with GUI
  - Algorithm Solvers (Program Guesses), with Player Setting the Code
    - Donald Knuth 5-Guess Algorithm (Impossible Mode)
    - Medium Algorithm
    - Basic Algorithm
  - Human Solver (User Guesses)
- [x] File Handling
  - Logging (Stores Game History, Activities and Errors in a Log File, mastermind.log)
  - Different Code Maker Levels (one with pre-programmed codes, which is file-handling)

### Additional

- Gameplay
  - User Response Validation (All Hints Provided Are Correct)
  - Different Code Maker Levels (Using File Handling)
- GUI
  - Help Button (Which Includes Instructions & Tutorial)
  - Home Button
