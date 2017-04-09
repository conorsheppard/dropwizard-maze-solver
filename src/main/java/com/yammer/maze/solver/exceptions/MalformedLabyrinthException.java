package com.yammer.maze.solver.exceptions;

public class MalformedLabyrinthException extends Exception {
    public MalformedLabyrinthException() {
        super("MalformedLabyrinthException: Labyrinth rows must be of equal length");
    }
}