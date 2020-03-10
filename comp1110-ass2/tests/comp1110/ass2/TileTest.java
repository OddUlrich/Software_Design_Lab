package comp1110.ass2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TileTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: rotate90Degree()
     */
    @Test
    public void testRotate90Degree() throws Exception {
        Tile tile_A1 = new Tile("A1B70");    // {RAIL, BLANK, RAIL, BLANK}
        int[] beforeRotate0 = {1, 0, 1, 0};
        int[] afterRotate0 = {0, 1, 0, 1};
        Assert.assertArrayEquals("A1B70 should be {1,0,1,0}",
                tile_A1.getCurrentEdgesStatus(), beforeRotate0);
        tile_A1.rotate90Degree();
        Assert.assertArrayEquals("A1B70 {1,0,1,0} rotate 90 degree to be {0,1,0,1}",
                tile_A1.getCurrentEdgesStatus(), afterRotate0);

        Tile tile_A2 = new Tile("A1B70");    // {RAIL, BLANK, RAIL, BLANK}
        int[] beforeRotate1 = {1, 0, 1, 0};
        int[] afterRotate1 = {1, 0, 1, 0};
        Assert.assertArrayEquals("A1B70 should be {1,0,1,0}",
                tile_A2.getCurrentEdgesStatus(), beforeRotate1);
        tile_A2.rotate90Degree();
        tile_A2.rotate90Degree();
        Assert.assertArrayEquals("A1B70 {1,0,1,0} rotate 180 degree to be {1, 0, 1, 0}",
                tile_A2.getCurrentEdgesStatus(), afterRotate1);

        Tile tile_A3 = new Tile("A1B70");    // {RAIL, BLANK, RAIL, BLANK}
        int[] beforeRotate2 = {1, 0, 1, 0};
        int[] afterRotate2 = {0, 1, 0, 1};
        Assert.assertArrayEquals("A1B70 should be {1,0,1,0}",
                tile_A3.getCurrentEdgesStatus(), beforeRotate2);
        for(int i=0;i<3;i++)
            tile_A3.rotate90Degree();
        Assert.assertArrayEquals("A1B70 {1,0,1,0} rotate 270 degree to be {0, 1, 0, 1}",
                tile_A3.getCurrentEdgesStatus(), afterRotate2);

        Tile tile_A4 = new Tile("A1B70");    // {RAIL, BLANK, RAIL, BLANK}
        int[] beforeRotate3 = {1, 0, 1, 0};
        int[] afterRotate3 = {1, 0, 1, 0};
        Assert.assertArrayEquals("A1B70 should be {1,0,1,0}",
                tile_A4.getCurrentEdgesStatus(), beforeRotate3);
        for(int i=0;i<4;i++)
            tile_A4.rotate90Degree();
        Assert.assertArrayEquals("A1B70 {1,0,1,0} rotate 360 degree to be {1, 0, 1, 0}",
                tile_A4.getCurrentEdgesStatus(), afterRotate3);


        Tile tile_S1 = new Tile("S1B70");//{HIGH, RAIL, RAIL, RAIL}
        int[] beforeRotate4 = {2,1,1,1}; //{HIGH, RAIL, RAIL, RAIL}
        int[] afterRotate4 = {1,2,1,1};//{RAIL, HIGH, RAIL, , RAIL}
        Assert.assertArrayEquals("S1B70 should be {2,1,1,1}",
                tile_S1.getCurrentEdgesStatus(), beforeRotate4);
        for(int i=0;i<5;i++)
            tile_S1.rotate90Degree();
        Assert.assertArrayEquals("S1B70 {2,1,1,1} rotate 450 degree to be {1,2,1,1}",
                tile_S1.getCurrentEdgesStatus(), afterRotate4);

        Tile tile_S2 = new Tile("S1B70");//{HIGH, RAIL, RAIL, RAIL}
        int[] beforeRotate5 = {2,1,1,1};//{HIGH, RAIL, RAIL, RAIL}
        int[] afterRotate5 = {1,1,2,1};//{RAIL, RAIL, HIGH, RAIL}
        Assert.assertArrayEquals("S1B70 should be {2,1,1,1}",
                tile_S2.getCurrentEdgesStatus(), beforeRotate5);
        for(int i=0;i<6;i++)
            tile_S2.rotate90Degree();
        Assert.assertArrayEquals("S1B70 {2,1,1,1} rotate 540 degree to be {1, 1,2,1}",
                tile_S2.getCurrentEdgesStatus(), afterRotate5);

        Tile tile_S3 = new Tile("S1B70");//{HIGH, RAIL, RAIL, RAIL}
        int[] beforeRotate6 = {2,1,1,1};//{HIGH, RAIL, RAIL, RAIL}
        int[] afterRotate6 = {1,1,1,2};//{RAIL, RAIL, RAIL, HIGH}
        Assert.assertArrayEquals("S1B70 should be {2,1,1,1}",
                tile_S3.getCurrentEdgesStatus(), beforeRotate6);
        for(int i=0;i<7;i++)
            tile_S3.rotate90Degree();
        Assert.assertArrayEquals("S1B70 {2,1,1,1} rotate 630 degree to be {1,1,1,2}",
                tile_S3.getCurrentEdgesStatus(), afterRotate6);


        Tile tile_S4 = new Tile("S1B70");//{HIGH, RAIL, RAIL, RAIL}
        int[] beforeRotate7 = {2,1,1,1};//{HIGH, RAIL, RAIL, RAIL}
        int[] afterRotate7 = {2,1,1,1};//{HIGH, RAIL, RAIL, RAIL}
        Assert.assertArrayEquals("S1B70 should be {2,1,1,1}",
                tile_S4.getCurrentEdgesStatus(), beforeRotate7);
        for(int i=0;i<8;i++)
            tile_S4.rotate90Degree();
        Assert.assertArrayEquals("S1B70 {2,1,1,1} rotate 720 degree to be {2,1,1,1}",
                tile_S4.getCurrentEdgesStatus(), afterRotate7);
    }
    /**
     * Method: rotate270Degree()
     *
     * @author Wyman
     */
    @Test
    public void Rotate270DegreeTest() throws Exception {
        Tile tile_A3 = new Tile("A3B40");    // {HIGH, HIGH, HIGH, BLANK}
        int[] beforeRotate0 = {2, 2, 2, 0};
        int[] afterRotate0 = {2, 2, 0, 2};
        Assert.assertArrayEquals("A3B40 should be {2, 2, 2, 0}",
                tile_A3.getCurrentEdgesStatus(), beforeRotate0);
        tile_A3.rotate270Degree();
        Assert.assertArrayEquals("A3B40 {2, 2, 2, 0} rotate 270 degree to be {2, 2, 0, 2}",
                tile_A3.getCurrentEdgesStatus(), afterRotate0);

        Tile tile_B0 = new Tile("B0C40");    // {HIGH, BLANK, RAIL, BLANK}
        int[] beforeRotate1 = {2, 0, 1, 0};
        int[] afterRotate1 = {0, 1, 0, 2};
        Assert.assertArrayEquals("B0B40 should be {2, 0, 1, 0}",
                tile_B0.getCurrentEdgesStatus(), beforeRotate1);
        tile_B0.rotate270Degree();
        Assert.assertArrayEquals("B0B40 {2, 0, 1, 0} rotate 270 degree to be {0, 1, 0, 2}",
                tile_B0.getCurrentEdgesStatus(), afterRotate1);

        Tile tile_S1 = new Tile("S1D10");    // {HIGH, RAIL, RAIL, RAIL}
        int[] beforeRotate2 = {2, 1, 1, 1};
        int[] afterRotate2 = {1, 1, 1, 2};
        Assert.assertArrayEquals("A1B70 should be {2, 1, 1, 1}",
                tile_S1.getCurrentEdgesStatus(), beforeRotate2);
        tile_S1.rotate270Degree();
        Assert.assertArrayEquals("A1B70 {2, 1, 1, 1} rotate 270 degree to be {1, 1, 1, 2}",
                tile_S1.getCurrentEdgesStatus(), afterRotate2);
    }

    /**
     * Method: getRowPos()
     * Letters from 'A' to 'G' represent the row number from 0 to 6.
     */
    @Test
    public void testGetRowPos() throws Exception {
        Tile tile0 = new Tile("A4A10");
        Assert.assertEquals("A4A10 should be in row 0", tile0.getRowPos(), 0);

        Tile tile1 = new Tile("A4B10");
        Assert.assertEquals("A4B10 should be in row 1", tile1.getRowPos(), 1);

        Tile tile2 = new Tile("A4C10");
        Assert.assertEquals("A4C10 should be in row 2", tile2.getRowPos(), 2);

        Tile tile3 = new Tile("A4D10");
        Assert.assertEquals("A4D10 should be in row 3", tile3.getRowPos(), 3);

        Tile tile4 = new Tile("A4E10");
        Assert.assertEquals("A4E10 should be in row 4", tile4.getRowPos(), 4);

        Tile tile5 = new Tile("A4F10");
        Assert.assertEquals("A4E10 should be in row 5", tile5.getRowPos(), 5);

        Tile tile6 = new Tile("A4G10");
        Assert.assertEquals("A4E10 should be in row 6", tile6.getRowPos(), 6);
    }

    /**
     * Method: getColPos()
     */
    @Test
    public void testGetColPos() throws Exception {
        Tile tile0 = new Tile("A4A00");
        Assert.assertEquals("A4A00 is in column 0", tile0.getColPos(), 0);

        Tile tile1 = new Tile("A4A10");
        Assert.assertEquals("A4B10 is in column 1", tile1.getColPos(), 1);

        Tile tile2 = new Tile("A4A20");
        Assert.assertEquals("A4C20 is in column 2", tile2.getColPos(), 2);

        Tile tile3 = new Tile("A4A30");
        Assert.assertEquals("A4D30 is in column 3", tile3.getColPos(), 3);

        Tile tile4 = new Tile("A4A40");
        Assert.assertEquals("A4E40 is in column 4", tile4.getColPos(), 4);

        Tile tile5 = new Tile("A4A50");
        Assert.assertEquals("A4E40 is in column 5", tile5.getColPos(), 5);

        Tile tile6 = new Tile("A4A60");
        Assert.assertEquals("A4E40 is in column 6", tile6.getColPos(), 6);
    }

    /**
     * Method: isMirrorOverY()
     * The orientation of tile indicates its property of flipping over y-axis.
     *     0 - 3: not mirrored over y-axis.
     *     4 - 7: mirrored over y-axis.
     */
    @Test
    public void testIsMirrorOverY() throws Exception {
        Tile tile1 = new Tile("A6D04");
        Assert.assertTrue("A6D04 should be mirrored over y-axis.", tile1.isMirrorOverY());

        Tile tile2 = new Tile("A6D05");
        Assert.assertTrue("A6D05 should be mirrored over y-axis.", tile2.isMirrorOverY());

        Tile tile3 = new Tile("A6D06");
        Assert.assertTrue("A6D06 should be mirrored over y-axis.", tile3.isMirrorOverY());

        Tile tile4 = new Tile("A6D07");
        Assert.assertTrue("A6D07 should be mirrored over y-axis.", tile4.isMirrorOverY());

        Tile tile5 = new Tile("A6D00");
        Assert.assertFalse("A6D00 should not be mirrored over y-axis.", tile5.isMirrorOverY());

        Tile tile6 = new Tile("A6D01");
        Assert.assertFalse("A6D01 should not be mirrored over y-axis.", tile6.isMirrorOverY());

        Tile tile7 = new Tile("A6D02");
        Assert.assertFalse("A6D02 should not be mirrored over y-axis.", tile7.isMirrorOverY());

        Tile tile8 = new Tile("A6D03");
        Assert.assertFalse("A6D03 should not be mirrored over y-axis.", tile8.isMirrorOverY());
    }

    /**
     * Method: getOrientation()
     * The value returned from method getOrientation() is used to rotate the image view of tile.
     *     0 - 3: same as it should be.
     *     4 - 7: transfer to 0 - 3.
     */
    @Test
    public void testGetOrientation() throws Exception {
        Tile tile1 = new Tile("A6D04");
        Assert.assertEquals("A6D04 orientation number should be 0.", tile1.getOrientation(), 0);

        Tile tile2 = new Tile("A6D05");
        Assert.assertEquals("A6D05 orientation number should be 1.", tile2.getOrientation(), 1);

        Tile tile3 = new Tile("A6D06");
        Assert.assertEquals("A6D06 orientation number should be 2.", tile3.getOrientation(), 2);

        Tile tile4 = new Tile("A6D07");
        Assert.assertEquals("A6D07 orientation number should be 3.", tile4.getOrientation(), 3);

        Tile tile5 = new Tile("A6D00");
        Assert.assertEquals("A6D00 orientation number should be 0.", tile5.getOrientation(), 0);

        Tile tile6 = new Tile("A6D01");
        Assert.assertEquals("A6D01 orientation number should be 1.", tile6.getOrientation(), 1);

        Tile tile7 = new Tile("A6D02");
        Assert.assertEquals("A6D02 orientation number should be 2.", tile7.getOrientation(), 2);

        Tile tile8 = new Tile("A6D03");
        Assert.assertEquals("A6D03 orientation number should be 3.", tile8.getOrientation(), 3);
    }

}