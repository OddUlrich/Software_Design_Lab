package comp1110.ass2;

import org.junit.Assert;
import org.junit.Test;

public class ExitTest {

    /**
     * Method: Exit.fixPosition()
     */
    @Test
    public void testUpExit() throws Exception {
        //Test the left exit on upper edge
        Exit exit = new Exit(1, 0, Exit.UP);
        int[] exitPosition = {exit.getColPos(), exit.getRowPos()};
        int[] afterFix = {1, 0};
        Assert.assertArrayEquals(afterFix, exitPosition);
        //Test the middle exit on upper edge
        exit = new Exit(3, 0, Exit.UP);
        exitPosition = new int[] {exit.getColPos(), exit.getRowPos()};
        afterFix = new int[] {3, 0};
        Assert.assertArrayEquals(afterFix, exitPosition);
        //Test the right exit on upper edge
        exit = new Exit(5, 0, Exit.UP);
        exitPosition = new int[] {exit.getColPos(), exit.getRowPos()};
        afterFix = new int[] {5, 0};
        Assert.assertArrayEquals(afterFix, exitPosition);
    }

    @Test
    public void testLeftExit() throws Exception {
        //Test the upper exit on left edge
        Exit exit = new Exit(0, 1, Exit.LEFT);
        int[] exitPosition = {exit.getColPos(), exit.getRowPos()};
        int[] afterFix = {0, 1};
        Assert.assertArrayEquals(afterFix, exitPosition);
        //Test the middle exit on left edge
        exit = new Exit(0, 3, Exit.LEFT);
        exitPosition = new int[] {exit.getColPos(), exit.getRowPos()};
        afterFix = new int[] {0, 3};
        Assert.assertArrayEquals(afterFix, exitPosition);
        //Test the lower exit on left edge
        exit = new Exit(0, 5, Exit.LEFT);
        exitPosition = new int[] {exit.getColPos(), exit.getRowPos()};
        afterFix = new int[] {0, 5};
        Assert.assertArrayEquals(afterFix, exitPosition);
    }

    @Test
    public void testRightExit() throws Exception {
        //Test the upper exit on right edge
        Exit exit = new Exit(7, 1, Exit.RIGHT);
        int[] exitPosition = {exit.getColPos(), exit.getRowPos()};
        int[] afterFix = {6, 1};
        Assert.assertArrayEquals(afterFix, exitPosition);
        //Test the middle exit on right edge
        exit = new Exit(7, 3, Exit.RIGHT);
        exitPosition = new int[] {exit.getColPos(), exit.getRowPos()};
        afterFix = new int[] {6, 3};
        Assert.assertArrayEquals(afterFix, exitPosition);
        //Test the lower exit on right edge
        exit = new Exit(7, 5, Exit.RIGHT);
        exitPosition = new int[] {exit.getColPos(), exit.getRowPos()};
        afterFix = new int[] {6, 5};
        Assert.assertArrayEquals(afterFix, exitPosition);
    }

    @Test
    public void testDownExit() throws Exception {
        //Test the left exit on lower edge
        Exit exit = new Exit(1, 7, Exit.DOWN);
        int[] exitPosition = {exit.getColPos(), exit.getRowPos()};
        int[] afterFix = {1, 6};
        Assert.assertArrayEquals(afterFix, exitPosition);
        //Test the middle exit on lower edge
        exit = new Exit(3, 7, Exit.DOWN);
        exitPosition = new int[] {exit.getColPos(), exit.getRowPos()};
        afterFix = new int[] {3, 6};
        Assert.assertArrayEquals(afterFix, exitPosition);
        //Test the right exit on lower edge
        exit = new Exit(5, 7, Exit.DOWN);
        exitPosition = new int[] {exit.getColPos(), exit.getRowPos()};
        afterFix = new int[] {5, 6};
        Assert.assertArrayEquals(afterFix, exitPosition);
    }
}
