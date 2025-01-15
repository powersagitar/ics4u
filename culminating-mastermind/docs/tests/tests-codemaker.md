---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: page
parent: Tests
---

# Code Maker

Program makes a secret code and user tries to guess.

{: .important }
The documented results are produced from revision [`55a4fe`](https://github.com/powersagitar/ics4u/tree/55a4febedb461ceb75bab67da6acb3bddd9ef4c1). In these test 
cases, the computer generates a random code and provides correct feedback to 
the user in the form of white and black key pegs. 

## Random Code Maker
The computer will generate a completely random code of length 4 based on the 6 
possible colors.

### Test Case 1

Secret Code: Red Yellow Blue Red

| Number of Attempts |         Attempt          |       Hints        |
|:------------------:|:------------------------:|:------------------:|
|         1          |   Green Red Green Red    | Black: 1, White: 1 |
|         2          | Green Blue Orange Yellow | Black: 0, White: 2 |
|         3          |  Green Blue Red Yellow   | Black: 0, White: 3 |
|         4          | Green Purple Red Yellow  | Black: 0, White: 2 |
|         5          |   Green Blue Red Green   | Black: 0, White: 2 |
|         6          | Green Purple Red Yellow  | Black: 0, White: 2 |
|         7          | Yellow Green Yellow Red  | Black: 1, White: 1 |
|         8          | Yellow Red Orange Green  | Black: 0, White: 2 |
|         9          | Yellow Yellow Yellow Red | Black: 2, White: 0 |
|         10         | Purple Yellow Purple Red | Black: 2, White: 0 |

Result: The computer generated a random code and the user was unable to guess 
it. After manually comparing, the computer provided correct feedback to the 
guesses.

### Test Case 2

Secret Code: Green Orange Green Red

| Number of Attempts |         Attempt          |       Hints        |
|:------------------:|:------------------------:|:------------------:|
|         1          | Purple Purple Green Red  | Black: 2, White: 0 |
|         2          |  Blue Orange Green Red   | Black: 3, White: 0 |
|         3          |   Blue Blue Green Red    | Black: 2, White: 0 |
|         4          |  Green Orange Green Red  | Black: 4, White: 0 |

Result: The computer generated a random code and the user was able to guess
it within 4 tries. The computer provided correct feedback to the guesses.

### Test Case 3

Secret Code: Purple Yellow Yellow Yellow

| Number of Attempts |           Attempt           |       Hints        |
|:------------------:|:---------------------------:|:------------------:|
|         1          |   Green Green Red Orange    | Black: 0, White: 0 |
|         2          | Purple Purple Purple Purple | Black: 1, White: 0 |
|         3          |   Yellow Purple Blue Blue   | Black: 0, White: 2 |
|         4          | Purple Yellow Yellow Yellow | Black: 4, White: 0 |

Result: The computer generated a random code and the user was able to guess
it within 4 tries. The computer provided correct feedback to the guesses.

## Pre-Programmed Code Maker

The computer will select a code from a list of pre-programmed codes. The list of
pre-programmed codes contains a list of all the codes that only have one color.

### Test Case 1

Secret Code: Purple Purple Purple Purple

| Number of Attempts |           Attempt           |       Hints        |
|:------------------:|:---------------------------:|:------------------:|
|         1          |    Green Red Blue Yellow    | Black: 0, White: 0 |
|         2          | Orange Orange Orange Purple | Black: 1, White: 0 |
|         3          | Purple Purple Purple Purple | Black: 4, White: 0 |

Result: The computer selected a pre-programmed code and the user was able to
guess it within 3 tries. The computer provided correct feedback to the guesses.

### Test Case 2

Secret Code: Red Red Red Red

| Number of Attempts |           Attempt           |       Hints        |
|:------------------:|:---------------------------:|:------------------:|
|         1          |     Green Red Green Red     | Black: 2, White: 0 |
|         2          |  Green Blue Orange Yellow   | Black: 0, White: 0 |
|         3          |  Purple Purple Green Green  | Black: 0, White: 0 |
|         4          |   Green Purple Red Yellow   | Black: 1, White: 0 |
|         5          |  Purple Purple Yellow Red   | Black: 1, White: 0 |
|         6          |   Red Green Orange Orange   | Black: 1, White: 0 |
|         7          | Orange Purple Purple Purple | Black: 0, White: 0 |
|         8          |    Red Red Purple Purple    | Black: 2, White: 0 |
|         9          |    Purple Purple Red Red    | Black: 2, White: 0 |
|         10         |     Red Red Red Orange      | Black: 3, White: 0 |

Result: The computer selected a pre-programmed code and the user was not able 
to guess it within 10 tries. The computer provided correct feedback to the 
guesses.