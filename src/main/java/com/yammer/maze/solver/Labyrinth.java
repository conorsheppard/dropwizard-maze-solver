package com.yammer.maze.solver;

public class Labyrinth {
    public int startX, startY;
    public char[][] labyrinth;

    private static final char WALL = 'O';
    private static final char FREE = ' ';
    private static final char PATH = '•';
    private boolean[][] explored;

    public Labyrinth(char[][] lab) {
        labyrinth = lab;
        explored = new boolean[lab.length][lab[0].length];
    }

    public int getHeight() {
        return labyrinth.length;
    }

    public int getWidth() {
        return labyrinth[0].length;
    }

    public boolean isExplored(int row, int col) {
        return explored[row][col];
    }

    public boolean isWall(int row, int col) {
        return labyrinth[row][col] == WALL;
    }

    public void mark(int row, int col) {
        labyrinth[row][col] = PATH;
    }

    public void setExplored(int row, int col) {
        explored[row][col] = true;
    }
}
