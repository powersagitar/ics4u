/*
    Authors: Mohan Dong, Kenneth Chen
    Date: 01/15/2024
    Title: Status.java
 */

package mastermind.core.solvers;

/**
 * The status of the solver after guessing the code.
 */
public enum Status {
    /**
     * The solver has guessed the code correctly.
     */
    Win,

    /**
     * The solver failed to guess the code.
     */
    Lose,

    /**
     * The solver has made a guess, but the game is not over yet.
     */
    Continue
}
