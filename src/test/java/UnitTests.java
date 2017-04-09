import com.yammer.maze.solver.LabEscape;
import com.yammer.maze.solver.exceptions.InsufficientSizeException;
import com.yammer.maze.solver.exceptions.MalformedLabyrinthException;
import com.yammer.maze.solver.exceptions.NoEscapeException;
import org.junit.Assert;
import org.junit.Test;

public class UnitTests {

    // Check all rows are the same length
    @Test
    public void testRowLengthCheck() {
        MalformedLabyrinthException malformedLabyrinthException = new MalformedLabyrinthException();
        int startX = 1, startY = 1;
        char[][] testLabyrinth = {{'O','O','O','O'},
                                  {'O',' ',' '},
                                  {'O',' ',' ','O'},
                                  {'O','O','O','O'}
                                 };

        try {
            LabEscape.drawPathForEscape(testLabyrinth, startX, startY);
        } catch (NoEscapeException e) {
            e.printStackTrace();
        } catch (InsufficientSizeException e) {
            e.printStackTrace();
        } catch (MalformedLabyrinthException e) {
            Assert.assertEquals(malformedLabyrinthException.getMessage(), e.getMessage());
        }
    }

    // Check unsolvable labyrinth throws NoEscapeException
    @Test
    public void testLabWithNoEscape() {
        NoEscapeException noEscapeException = new NoEscapeException();
        int startX = 1, startY = 1;
        char[][] testLabyrinth = {{'O','O','O','O'},
                                  {'O',' ',' ','O'},
                                  {'O',' ',' ','O'},
                                  {'O','O','O','O'}
                                 };
        try {
            LabEscape.drawPathForEscape(testLabyrinth, startX, startY);
        } catch (NoEscapeException e) {
            Assert.assertEquals(noEscapeException.getMessage(), e.getMessage());
        } catch (InsufficientSizeException e) {
            e.printStackTrace();
        } catch (MalformedLabyrinthException e) {
            e.printStackTrace();
        }
    }

    // Check labyrinth is at least 4x4
    @Test
    public void testLabOfInsufficientSize() {
        InsufficientSizeException insufficientSizeException = new InsufficientSizeException();
        int startX = 1, startY = 1;
        char[][] testLabyrinth = {{'O','O','O'},
                                  {'O',' ',' '},
                                  {'O',' ','O'}
                                 };
        try {
            LabEscape.drawPathForEscape(testLabyrinth, startX, startY);
        } catch (NoEscapeException e) {
            e.printStackTrace();
        } catch (InsufficientSizeException e) {
            Assert.assertEquals(insufficientSizeException.getMessage(), e.getMessage());
        } catch (MalformedLabyrinthException e) {
            e.printStackTrace();
        }
    }

    // Check labyrinth 1 was solved correctly
    @Test
    public void testPathForEscape1() throws InsufficientSizeException, NoEscapeException, MalformedLabyrinthException {
        int startX = 2, startY = 1;
        char[][] testLabyrinth = {{'O','O','O','O'},
                                  {'O',' ',' ',' '},
                                  {'O',' ','O','O'},
                                  {'O','O','O','O'}
                                 };

        char[][] expectedResult = {{'O','O','O','O'},
                                   {'O','•','•','•'},
                                   {'O','•','O','O'},
                                   {'O','O','O','O'}
                                  };

        Assert.assertArrayEquals(expectedResult, LabEscape.drawPathForEscape(testLabyrinth, startX, startY));

    }

    // Check labyrinth 2 was solved correctly
    @Test
    public void testPathForEscape2() throws InsufficientSizeException, NoEscapeException, MalformedLabyrinthException {
        int startX = 3, startY = 1;
        char[][] testLabyrinth = {{'O','O','O','O','O','O','O','O','O','O'},
                                  {'O',' ',' ',' ',' ','O',' ',' ',' ','O'},
                                  {'O',' ','O','O',' ','O',' ','O',' ','O'},
                                  {'O',' ',' ','O',' ','O',' ','O',' ','O'},
                                  {'O',' ','O','O',' ',' ',' ','O',' ',' '},
                                  {'O',' ','O','O','O','O','O','O','O','O'},
                                  {'O',' ',' ',' ',' ',' ',' ',' ',' ','O'},
                                  {'O','O','O','O','O','O','O','O','O','O'}};

        char[][] expectedResult = {{'O','O','O','O','O','O','O','O','O','O'},
                                   {'O','•','•','•','•','O','•','•','•','O'},
                                   {'O','•','O','O','•','O','•','O','•','O'},
                                   {'O','•',' ','O','•','O','•','O','•','O'},
                                   {'O',' ','O','O','•','•','•','O','•','•'},
                                   {'O',' ','O','O','O','O','O','O','O','O'},
                                   {'O',' ',' ',' ',' ',' ',' ',' ',' ','O'},
                                   {'O','O','O','O','O','O','O','O','O','O'}};

        Assert.assertArrayEquals(expectedResult, LabEscape.drawPathForEscape(testLabyrinth, startX, startY));
    }
}
