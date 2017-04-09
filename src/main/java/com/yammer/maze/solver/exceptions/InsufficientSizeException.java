package com.yammer.maze.solver.exceptions;

public class InsufficientSizeException extends Exception {
    public InsufficientSizeException() {
        super("InsufficientSizeException: Labyrinth must be at least of size 4x4");
    }
}
