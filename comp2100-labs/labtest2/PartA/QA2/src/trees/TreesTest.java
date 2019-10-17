package trees;

import org.junit.Before;
import org.junit.Test;

import trees.properties.Age;
import trees.properties.Color;
import trees.properties.Dimensions;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;

public class TreesTest {
    private Trees simpleData;
    private Trees complexData;
    
    // global variables
    private final String SIMPLEXML = "simple.xml";
    private final String COMPLEXXML = "complex.xml";    

    @Before
    public void createTrees() {
        simpleData = new Trees();

        Property[] treeAProperties = {
        	new Age(21),
            new Dimensions(2, 20),
            new Color("red")
        };

        Tree treeA = new Tree("Cedar", treeAProperties);
        simpleData.add(treeA);

        Property[] treeBProperties = {
            new Age(30),
            new Dimensions(1, 15),
            new Color("green")
        };

        Tree treeB = new Tree("Fir", treeBProperties);
        simpleData.add(treeB);
    }
    

    @Before
    public void createComplexTrees() {
        complexData = new Trees();

        Property[] treeAProperties = {
            new Dimensions(2, 20),
            new Color("red")
        };

        Tree treeA = new Tree("Cedar", treeAProperties);
        complexData.add(treeA);

        Property[] treeBProperties = {
            new Age(30),
            new Color("green")
        };

        Tree treeB = new Tree("Fir", treeBProperties);
        complexData.add(treeB);
    }    

    /**
     * Tests that the implementation is successfully able to save
     * information to a file.
     *
     * @throws Exception Some user-generated exceptions.
     */
    @Test
    public void saveToFile() throws Exception {
        File outputFile = new File("test.xml");
        if (outputFile.exists()) {
            assertTrue("Unable to delete temporary file", outputFile.delete());
        }

        simpleData.saveToFile(outputFile);

        assertTrue(outputFile.exists());

        // Verify this is XML
        try (FileInputStream input = new FileInputStream(outputFile)) {
            // NB. This isn't unicode-compatible, but the header will always be ASCII here.
            char firstChar = (char) input.read();
            char secondChar = (char) input.read();

            assertEquals('<', firstChar);
            assertEquals('?', secondChar);
        }
    }

    /**
     * Tests that the implementation is successfully able to load
     * from the previously designed file.
     *
     * @throws Exception Some user-generated exceptions.
     */
    @Test
    public void loadFromFile() throws Exception {
        // Ensure that saving still occurs regardless of JUnit's ordering
        saveToFile();

        File outputFile = new File("test.xml");
        assertTrue(outputFile.exists());

        Trees userObject = Trees.loadFromFile(outputFile);
        assertNotNull(userObject);

        assertEquals(simpleData.size(), userObject.size());
        assertEquals(simpleData, userObject);
        
        assertTrue(compareTrees(simpleData, userObject));

        System.out.println("Saved data: " + simpleData);
        System.out.println("Loaded data: " + userObject);
    }
        
    /**
     * Compare two Trees and return false if different.
     */
    public static boolean compareTrees(Trees source, Trees target) {
    	for(int i=0; i < source.size(); i++) {
    		if(!source.get(i).equals(target.get(i))) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Test simple case
     * @throws Exception
     */
    @Test
    public void testSimple() throws Exception{
        // save to the specific file
        File outputFile = new File(SIMPLEXML);

        // not exits file yet
        if (outputFile.exists()) {
            outputFile.delete();
        }

        simpleData.saveToFile(outputFile);

        assertTrue(outputFile.exists());

        Trees trees = Trees.loadFromFile(outputFile);
        assertTrue(compareTrees(simpleData, trees));    	
    }
    
    /**
     * Test complex case
     * @throws Exception
     */
    @Test
    public void testComplex() throws Exception{
        // save to the specific file
        File outputFile = new File(COMPLEXXML);

        // not exits file yet
        if (outputFile.exists()) {
            outputFile.delete();
        }

        complexData.saveToFile(outputFile);

        assertTrue(outputFile.exists());

        Trees trees = Trees.loadFromFile(outputFile);
        assertTrue(compareTrees(complexData, trees));    	    	
    }
}
