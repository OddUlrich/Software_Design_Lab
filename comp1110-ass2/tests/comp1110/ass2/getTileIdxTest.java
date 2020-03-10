package comp1110.ass2;

import org.junit.Assert;
import org.junit.Test;

public class getTileIdxTest {

    /**
     * Method: Tile.getTileIdx()
     */
    @Test
    public void testTileSIdx() throws Exception{
        for (int i = 0; i < 6; i++) {
            String str = "S" + i +"A13";
            Tile tile = new Tile(str);
            int index = i;
            Assert.assertEquals("Index of S" + index + " is " + index, index, tile.getTileIdx());
        }
    }

    @Test
    public void testTileAIdx() throws Exception{
        for (int i = 0; i < 6; i++) {
            String str = "A" + i +"A13";
            Tile tile = new Tile(str);
            int index = i + 6;
            Assert.assertEquals("Index of A" + index + " is " + index, index, tile.getTileIdx());
        }
    }

    @Test
    public void testTileBIdx() throws Exception{
        for (int i = 0; i < 3; i++) {
            String str = "B" + i +"A13";
            Tile tile = new Tile(str);
            int index = i + 12;
            Assert.assertEquals("Index of B" + index + " is " + index, index, tile.getTileIdx());
        }
    }
}
