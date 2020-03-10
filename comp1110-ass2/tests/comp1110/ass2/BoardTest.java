package comp1110.ass2;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {


    /**
     * Test the functionality among special tile placement and its counting.
     * Validate the counts are well-worked and can be perfectly applied into the game.
     * In this test, we ignore the connection rule of the game.
     * But all placements must be in different position.
     *
     * @author Wyman
     */
    @Test
    public void specialTileCountTest() throws Exception {
        Board board0 = new Board();

        // Place a normal tile before a special tile.
        Tile tile_A2 = new Tile("A3A34");
        board0.placeTileOnBoard(tile_A2);
        Assert.assertEquals("The number of special tile should be 0, but now is " + board0.getUsedSpeTileNum(),
                board0.getUsedSpeTileNum(), 0);
        Tile tile_S3 = new Tile("S3B45");
        board0.placeTileOnBoard(tile_S3);
        Assert.assertEquals("The number of special tile should be 1, but now is " + board0.getUsedSpeTileNum(),
                board0.getUsedSpeTileNum(), 1);

        // Continue to place one special tile and 1 normal tile.
        Tile tile_S5 = new Tile("S5E34");
        board0.placeTileOnBoard(tile_S5);
        Assert.assertEquals("The number of special tile should be 2, but now is " + board0.getUsedSpeTileNum(),
                board0.getUsedSpeTileNum(), 2);
        Tile tile_B0 = new Tile("B0B65");
        board0.placeTileOnBoard(tile_B0);
        Assert.assertEquals("The number of special tile should be 2, but now is " + board0.getUsedSpeTileNum(),
                board0.getUsedSpeTileNum(), 2);

        // Continuously place 3 special tiles.
        Board board1 = new Board();
        Tile S0 = new Tile("S0C21");
        Tile S4 = new Tile("S4D07");
        Tile S5 = new Tile("S5F62");

        Assert.assertEquals("The number of special tile should be 0, but now is " + board1.getUsedSpeTileNum(),
                board1.getUsedSpeTileNum(), 0);
        board1.placeTileOnBoard(S0);
        Assert.assertEquals("The number of special tile should be 1, but now is " + board1.getUsedSpeTileNum(),
                board1.getUsedSpeTileNum(), 1);
        board1.placeTileOnBoard(S4);
        Assert.assertEquals("The number of special tile should be 2, but now is " + board1.getUsedSpeTileNum(),
                board1.getUsedSpeTileNum(), 2);
        board1.placeTileOnBoard(S5);
        Assert.assertEquals("The number of special tile should be 3, but now is " + board1.getUsedSpeTileNum(),
                board1.getUsedSpeTileNum(), 3);
    }

    /**
     * Validate the equality between tile's position value and board's.
     *
     * @author Wyman
     */
    @Test
    public void getTilesFromBoardTest() throws Exception {
        Board board = new Board();

        Tile tile_A2 = new Tile("A3A34");
        Tile tile_S5 = new Tile("S5E34");
        Tile tile_S3 = new Tile("S3B45");
        Tile tile_B0 = new Tile("B0B65");

        board.placeTileOnBoard(tile_A2);
        board.placeTileOnBoard(tile_S3);
        board.placeTileOnBoard(tile_S5);
        board.placeTileOnBoard(tile_B0);

        Assert.assertEquals("A2 location error!",
                board.getTileObjByPosition(tile_A2.getRowPos(), tile_A2.getColPos()), tile_A2);
        Assert.assertEquals("S3 location error!",
                board.getTileObjByPosition(tile_S3.getRowPos(), tile_S3.getColPos()), tile_S3);
        Assert.assertEquals("S5 location error!",
                board.getTileObjByPosition(tile_S5.getRowPos(), tile_S5.getColPos()), tile_S5);
        Assert.assertEquals("B0 location error!",
                board.getTileObjByPosition(tile_B0.getRowPos(), tile_B0.getColPos()), tile_B0);
    }
}
