package comp1110.exam;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

/**
 * COMP1110 Final Exam, Question 3ii
 */
public class Q3TotalStockValueTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    // FIXME add one ore more JUnit unit tests that test the totalStockValue() method of the Q3SimpleStockManager class.


    @Test
    public void testNoneTotal() {
        Q3SimpleStockManager manager1 = new Q3SimpleStockManager();
        assertEquals(manager1.totalStockValue(), 0.0, 0.01);

        Q3SimpleStockManager manager2 = new Q3SimpleStockManager();
        manager2.newItem("abc", "none", 18.7);
        assertEquals(manager2.totalStockValue(), 18.7 * 0, 0.01);
    }

    @Test
    public void testOneTotal() {
        Q3SimpleStockManager manager1 = new Q3SimpleStockManager();
        manager1.newItem("aabbcc", "one", 43.0);
        manager1.addStock("aabbcc", 27);
        assertEquals(manager1.totalStockValue(), 43.0 * 27, 0.01);

        Q3SimpleStockManager manager2 = new Q3SimpleStockManager();
        manager2.newItem("abc", "one2", 18.7);
        manager2.addStock("abc", 2);
        assertEquals(manager2.totalStockValue(), 18.7 * 2, 0.01);
    }


    @Test
    public void testTwoTotal() {
        Q3SimpleStockManager manager1 = new Q3SimpleStockManager();
        manager1.newItem("aabbcc", "one", 11.5);
        manager1.addStock("aabbcc", 6);
        manager1.newItem("abc", "two", 5.0);
        manager1.addStock("abc", 3);
        assertEquals(manager1.totalStockValue(), 11.5 * 6 + 5.0 * 3, 0.01);
    }
}
