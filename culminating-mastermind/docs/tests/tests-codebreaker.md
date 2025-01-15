---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: page
parent: Tests
---

# Code Breaker

User makes a secret code and program tries to guess.

{: .important }
The documented results are produced from revision [`6b74221`](https://github.com/powersagitar/ics4u/tree/6b74221daadd0736fbe0d4e98eed56f35cc9199f).

## Donald Knuth 5-Guess Algorithm

Donald Knuth algorithm always finds the secret code in at most 5 attempts.

It is the best algorithm among the 3.

### Test Case 1

Secret Code: Red Purple Yellow Orange

| Number of Attempts |         Attempt          |       Hints        |
|:------------------:| :----------------------: | :----------------: |
|         1          |   Green Green Red Red    | Black: 0, White: 1 |
|         2          | Blue Purple Green Yellow | Black: 1, White: 1 |
|         3          |  Red Purple Orange Blue  | Black: 2, White: 1 |
|         4          |  Red Purple Blue Purple  | Black: 2, White: 0 |
|         5          | Red Purple Yellow Orange | Black: 4, White: 0 |

### Test Case 2

Secret Code: Yellow Purple Green Blue

| Number of Attempts |         Attempt          |       Hints        |
|:------------------:| :----------------------: | :----------------: |
|         1          |   Green Green Red Red    | Black: 0, White: 1 |
|         2          |   Red Blue blue Purple   | Black: 0, White: 2 |
|         3          | Orange Red Orange Black  | Black: 1, White: 0 |
|         4          | Purple Purple Green Blue | Black: 3, White: 0 |
|         5          | Yellow Purple Green Blue | Black: 4, White: 0 |

## Medium Algorithm

Medium algorithm finds the secret code in 7 attempts in both tests.

It's slightly less optimal than Donald Knuth's algorithm.

### Test Case 3

Secret Code: Red Purple Yellow Orange

| Number of Attempts |           Attempt           |       Hints        |
|:------------------:| :-------------------------: | :----------------: |
|         1          |   Green Green Green Green   | Black: 0, White: 0 |
|         2          |     Blue Blue Blue Blue     | Black: 0, White: 0 |
|         3          | Yellow Yellow Yellow Yellow | Black: 1, White: 0 |
|         4          | Purple Yellow Purple Purple | Black: 0, White: 2 |
|         5          |  Yellow Purple Orange Red   | Black: 1, White: 3 |
|         6          |  Orange Purple Red Yellow   | Black: 1, White: 3 |
|         7          |  Red Purple Yellow Orange   | Black: 4, White: 0 |

### Test Case 4

Secret Code: Yellow Purple Green Blue

| Number of Attempts |           Attempt           |       Hints        |
|:------------------:| :-------------------------: | :----------------: |
|         1          |   Green Green Green Green   | Black: 1, White: 0 |
|         2          |     Blue Blue Blue Blue     | Black: 1, White: 0 |
|         3          | Yellow Yellow Yellow Yellow | Black: 1, White: 0 |
|         4          |  Yellow Blue Purple Green   | Black: 1, White: 3 |
|         5          |  Purple Yellow Blue Green   | Black: 0, White: 4 |
|         6          |  Green Blue Yellow Purple   | Black: 0, White: 4 |
|         7          |  Yellow Purple Green Blue   | Black: 4, White: 0 |

## Basic Algorithm

Basic algorithm would almost never find the secret code (did not find the secret code in this case).

This is intended behavior.

### Test Case 5

Secret Code: Red Purple Yellow Orange

| Number of Attempts |          Attempt           |       Hints        |
|:------------------:| :------------------------: | :----------------: |
|         1          |  Purple Green Green Blue   | Black: 0, White: 1 |
|         2          |   Green Orange Red Blue    | Black: 0, White: 2 |
|         3          |   Red Orange Purple Red    | Black: 1, White: 2 |
|         4          |  Yellow Green Yellow Red   | Black: 1, White: 1 |
|         5          | Orange Green Yellow Yellow | Black: 1, White: 1 |
|         6          |   Orange Red Blue Purple   | Black: 0, White: 3 |
|         7          |  Green Purple Blue Purple  | Black: 1, White: 0 |
|         8          | Green Green Purple Orange  | Black: 1, White: 1 |
|         9          | Orange Green Yellow Purple | Black: 1, White: 2 |
|         10         |   Yellow Blue Blue Blue    | Black: 0, White: 1 |

### Test Case 6

Secret Code: Yellow Purple Green Blue

| Number of Attempts |         Attempt         |       Hints        |
|:------------------:| :---------------------: | :----------------: |
|         1          | Blue Orange Orange Blue | Black: 1, White: 0 |
|         2          | Purple Green Red Purple | Black: 0, White: 2 |
|         3          |  Blue Red Blue Orange   | Black: 0, White: 1 |
|         4          |   Blue Green Red Red    | Black: 0, White: 2 |
|         5          | Orange Blue Yellow Red  | Black: 0, White: 2 |
|         6          | Orange Green Red Green  | Black: 0, White: 1 |
|         7          | Orange Green Purple Red | Black: 0, White: 2 |
|         8          | Green yellow Red Green  | Black: 0, White: 2 |
|         9          | Blue Blue Green Orange  | Black: 1, White: 1 |
|         10         | Purple Red Purple Green | Black: 0, White: 2 |
