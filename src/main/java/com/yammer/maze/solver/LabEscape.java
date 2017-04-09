package com.yammer.maze.solver;

import com.yammer.maze.solver.exceptions.InsufficientSizeException;
import com.yammer.maze.solver.exceptions.MalformedLabyrinthException;
import com.yammer.maze.solver.exceptions.NoEscapeException;

public class LabEscape {
    /**
     * @param labyrinth A labyrinth drawn on a matrix of characters. It's at least 4x4, can be a rectangle or a square.
     *                  Walkable areas are represented with a space character, walls are represented with a big O character.
     *                  The escape point is always on the border (see README)
     * @param startX    Starting row number for the escape. 0 based.
     * @param startY    Starting column number for the escape. 0 based.
     * @return          A char matrix with the same labyrinth and a path drawn from the starting point to the escape
     * @throws NoEscapeException when no path exists to the outside, from the selected starting point
     */
    public static char[][] drawPathForEscape(char[][] labyrinth, int startX, int startY)
            throws NoEscapeException, InsufficientSizeException, MalformedLabyrinthException {

        if (!rowsAreOfEqualSize(labyrinth)) throw new MalformedLabyrinthException();

        if (labyrinth[0].length < 4 || labyrinth.length < 4) throw new InsufficientSizeException();

        Labyrinth lab = new Labyrinth(labyrinth);
        solve(lab, startX, startY);
        return lab.labyrinth;
//        throw new UnsupportedOperationException("please implement"); // TODO
    }
    // Finds a pathway out of the given maze from the given start location.
    // Preconditions: maze != null and startRow/Col are within the maze
    private static void solve(Labyrinth lab, int startRow, int startCol) throws NoEscapeException {
        if (explore(lab, startRow, startCol));
        else throw new NoEscapeException();
    }

    // Private helper that finds a pathway out of the maze from the given start
    // using recursive backtracking. Marks each square it examines as having
    // been 'explored'. Returns true if a path out was found, and false if not.
    // If a pathway out is found, marks every step along that path with a '.'.
    private static boolean explore(Labyrinth labyrinth, int row, int col) {
        // unproductive path: wall or previously explored
        if (labyrinth.isWall(row, col) || labyrinth.isExplored(row, col)) {
            return false;
        } else if (row == 0 || row == labyrinth.getHeight()-1 || col == 0 || col == labyrinth.getWidth()-1) {
            // exit has been found
            labyrinth.mark(row, col);
            return true;
        } else {
            labyrinth.setExplored(row, col);

            if (explore(labyrinth, row,col-1) || // left
                    explore(labyrinth,row-1, col) || // up
                    explore(labyrinth,row+1, col) || // down
                    explore(labyrinth, row, col+1)) { // right

                labyrinth.mark(row, col);
                return true;
            }
        }
        return false;
    }

    private static boolean rowsAreOfEqualSize(char[][] labyrinth) {
        int length = labyrinth[0].length;
        for (char[] c : labyrinth)
            if (c.length != length) return false;

        return true;
    }
}