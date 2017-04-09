package com.yammer.maze.solver.exceptions;

public class NoEscapeException extends Exception {
    public NoEscapeException() {
        super("Error: No Escape From Labyrinth");
    }
}
