# Mastermind

The ICS4U culminating project.

## JavaFX

1. Go to [Install JavaFX](https://openjfx.io/openjfx-docs/#IDE-VSCode)
2. Follow Non-modular projects > IDE
3. As instructed, install JavaFX SDK to `/culminating-mastermind/lib`

## Launch

1. Open VS Code
2. Go to "Run and Debug" in the sidebar
3. Set launch task to "Launch Mastermind"
4. Launch

## Pseudocode

```mermaid
flowchart TD

start --> 1
1 --> 2
2 --> 3
3 --> 12
12 --> |yes| 13
12 --> |no| 14
14 --> |yes| 4
14 --> |no| 15
4 --> 5
5 --> 6
6 --> 7
7 --> 19
22 --> 8
24 --> 8
25 --> 8
8 --> 9
9 --> 10
10 --> 11
11 --> 3
13 --> exit
15 --> exit

start --> 16
16 --> 17
17 --> 18
18 --> 29
29 --> |yes| 26
29 --> |no| 30
26 --> 27
27 --> |yes| 28
27 --> |no| 19

19 --> 20
20 --> 21
21 --> |yes| 22
21 --> |no| 23
23 --> |yes| 24
23 --> |no| 25
22 --> 29
24 --> 29
25 --> 29
28 --> exit
30 --> exit

subgraph code maker

subgraph generate random code

16[for each of the 4 colors in the code]

17[generate a random number between 0 and 5, inclusive]

18[each random number represents a color in the code]

end

subgraph process guess

29@{ shape: diam, label: "number of attempts is less than or equal to 10" }

26[ask for guess]

27@{ shape: diam, label: "guess equals to secret code" }

end

subgraph code maker endgame

28[code breaker wins]

30[code breaker loses]

end

end

subgraph code breaker

1["`create set S of 1,296 (6^4) possible codes/permutations`"]

2[start with guess 1122, 1 means the first possible color, 2 means the second possible color...]

subgraph process response

3[get external response, parse to indices of black and white key pegs]

12@{ shape: diam, label: "response is 4 black key pegs" }

14@{ shape: diam, label: "number of attempts is less than or equal to 10" }

4[remove all permutations that don't have the correct colors at the correct indices, correct colors are indicated by black pegs]

5["`remove all permutations that have the incorrect colors at the incorrect indices, including both white pegs (misplacements) and misses`"]

end

subgraph find next guess

6[for each permutation, assume it's the correct secret code]

7["`for each assumption, find the response (indices of black and white key pegs) of itself with the rest of permutations`"]

8[filter out all permutations that remain possible based on the response]

9[the score of a response is the number of permutations that remain possible]

10[the score of an assumption is the maximum score of all its responses]

11[the best guess is the assumption with the lowest score]

end

subgraph code breaker endgame

13[previous guess was the correct secret code]

15[algorithm fails to guess the code]

end

end

subgraph generate response

19[secret code and guess both are arrays of length 4]

20[compare colors in both arrays of the same index]

21@{ shape: diam, label: "colors are the same" }

22[a black key peg is placed at the index]

23@{ shape: diam, label: "color is misplaced, i.e., color in guess appears in secret code but at a different index, and number of existing misplacements of this particular color is less than its total occurrences in the secret code" }

24[a white key peg is placed at the index]

25[no key pegs are placed at the index]

end
```
