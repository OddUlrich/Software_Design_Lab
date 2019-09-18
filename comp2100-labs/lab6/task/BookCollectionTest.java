import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Some basic tests for the Book Collection class.
 */
public class BookCollectionTest {
    private BookCollection collection;

    @Before
    public void createCollection() {
        // Create a new collection
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Thinking in Java (4th ed.)", "Eckel, Bruce",
                2006, BookGenre.NON_FICTION));
        books.add(new Book("The Ultimate Hitchhiker's Guide to the Galaxy",
                "Adams, Douglas",1979, BookGenre.FICTION_COMEDY));
        books.add(new Book("The Hobbit",
                "Tolkien, J.R.R.",1937, BookGenre.FICTION_FANTASY));

        this.collection = new BookCollection(books);
    }

    @Test
    public void testBespokeLoad() throws Exception {
        System.out.println("Saving bespoke collection: \n" + collection.toString());

        // Save it to the specified file.
        File file = new File("my_book_collection.bin");

        // Ensure that there is nothing there right now:
        file.delete();

        collection.saveToBespokeFile(file);
        
        // Load it from the specified file.
        BookCollection comparisonCollection = BookCollection.loadFromBespokeFile(file);

        assertNotNull(comparisonCollection);
        compareCollections(collection, comparisonCollection);

        System.out.println("Read bespoke collection: \n" + comparisonCollection.toString());
        
        // Delete file after test
        file.delete();        
    }


    @Test
    public void testJSONLoad() throws Exception {
        System.out.println("Saving JSON collection: \n" + collection.toString());

        // Save it to the specified file.
        File file = new File("my_book_collection.json");

        // Ensure that there is nothing there right now:
        file.delete();

        collection.saveToJSONFile(file);

        // Check that this file looks like JSON.
        try (FileReader reader = new FileReader(file)) {
            char character = (char) reader.read();
            assertTrue(character == '[' || character == '{');
        }
        
        // Load it from the specified file.
        BookCollection comparisonCollection = BookCollection.loadFromJSONFile(file);

        assertNotNull(comparisonCollection);
        compareCollections(collection, comparisonCollection);

        System.out.println("Read JSON collection: \n" + comparisonCollection.toString());
        
        // Delete file after test
        file.delete();
    }


    @Test
    public void testXML() throws Exception {
        System.out.println("Saving XML collection: \n" + collection.toString());

        // Save it to the specified file.
        File file = new File("my_book_collection.xml");

        // Ensure that there is nothing there right now:
        file.delete();

        collection.saveToXMLFile(file);

        // Check that this file looks like XML.
        try (FileReader reader = new FileReader(file)) {
            char character = (char) reader.read();
            assertEquals(character, '<');
        }catch(Exception e) {
            e.printStackTrace();
        }
                
        // Load it from the specified file.
        BookCollection comparisonCollection = BookCollection.loadFromXMLFile(file);

        assertNotNull(comparisonCollection);
        compareCollections(collection, comparisonCollection);

        System.out.println("Read XML collection: \n" + comparisonCollection.toString());
        
        // Delete file after test
        file.delete();        
    }

    /**
     * Checks that two collections are identical.
     *
     * @param goodCollection The known good collection.
     * @param comparisonCollection The potentially bad collection.
     */
    private static void compareCollections(BookCollection goodCollection,
                                           BookCollection comparisonCollection) {
        // Check that they are of the same length
        assertEquals(comparisonCollection.getList().size(), goodCollection.getList().size());

        // Check that the fields of each book match
        for (int i = 0; i < comparisonCollection.getList().size(); i++) {
            Book knownGoodBook = goodCollection.getList().get(i);
            Book loadedBook = comparisonCollection.getList().get(i);

            assertEquals(knownGoodBook.title, loadedBook.title);
            assertEquals(knownGoodBook.authorName, loadedBook.authorName);
            assertEquals(knownGoodBook.yearReleased, loadedBook.yearReleased);
            assertEquals(knownGoodBook.bookGenre, loadedBook.bookGenre);
        }
    }

}
