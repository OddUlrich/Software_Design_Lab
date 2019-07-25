
/**
 * Sample code with JUnit 4 for the parameterized test
 * @author dongwookim
 */

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class MarkCalculatorTest {
	
	/**
	 * Return a list of parameters which are different implementation of 
	 * interface {@linkplain MarkCalculator}. 
	*/
	@Parameters
	public static Iterable<? extends Object>  getImplementations() {
		return Arrays.asList(
				new MarkCalculator2(),
				new MarkCalculator5(),
				new MarkCalculator0(),
				new MarkCalculator4(),
				new MarkCalculator6(),
				new MarkCalculator3(),
				new MarkCalculator1()	
		   );
	}
	
	@Parameter
	public MarkCalculator calculator;
	/**
	 * Test for ComponentOutOfRangeException.
	 * @throws ComponentOutOfRangeException
	 */
	// Out of the down bound
	@Test(expected = ComponentOutOfRangeException.class)
	public void testExceptionLabDown() throws ComponentOutOfRangeException {
		calculator.calculateMark(-1, 0, 0, 0, true);
	}
	@Test(expected = ComponentOutOfRangeException.class)
	public void testExceptionA1Down() throws ComponentOutOfRangeException {
		calculator.calculateMark(0, -1, 0, 0, true);
	}
	@Test(expected = ComponentOutOfRangeException.class)
	public void testExceptionA2Down() throws ComponentOutOfRangeException {
		calculator.calculateMark(0, 0, -1, 0, true);
	}
	@Test(expected = ComponentOutOfRangeException.class)
	public void testExceptionFinalDown() throws ComponentOutOfRangeException {
		calculator.calculateMark(0, 0, 0, -1, true);
	}

	// Out of the up bound
	@Test(expected = ComponentOutOfRangeException.class)
	public void testExceptionLabUp() throws ComponentOutOfRangeException {
		calculator.calculateMark(11, 10, 10, 100, true);
	}
	@Test(expected = ComponentOutOfRangeException.class)  // MarkCal6 failed here.
	public void testExceptionA1Up() throws ComponentOutOfRangeException {
		calculator.calculateMark(10, 11, 10, 100, true);
	}
	@Test(expected = ComponentOutOfRangeException.class)  // MarkCal6 failed here.
	public void testExceptionA2Up() throws ComponentOutOfRangeException {
		calculator.calculateMark(10, 10, 11, 100, true);
	}
	@Test(expected = ComponentOutOfRangeException.class)
	public void testExceptionFinalUp() throws ComponentOutOfRangeException {
		calculator.calculateMark(10, 10, 10, 101, true);
	}

	/**
	 * Test on all grades levels including boundary.
	 * @throws ComponentOutOfRangeException
	 */
	@Test
	public void testGradeN() throws ComponentOutOfRangeException {
		// 0 ~ 44 : N
		// No rounding.
		assertEquals(new MarkGrade(0, Grade.N), calculator.calculateMark(0, 0, 0, 0, true));	  // 0
		assertEquals(new MarkGrade(13, Grade.N), calculator.calculateMark(1, 2, 2, 10, true));    // 13
		assertEquals(new MarkGrade(36, Grade.N), calculator.calculateMark(6, 4, 8, 20, true));    // 36
		assertEquals(new MarkGrade(44, Grade.N), calculator.calculateMark(8, 5, 3, 40, true));    // 44

		// Rounding up.
		assertEquals(new MarkGrade(7, Grade.N), calculator.calculateMark(2, 1, 2, 0, true));      // 6.5
		assertEquals(new MarkGrade(21, Grade.N), calculator.calculateMark(3, 3, 6, 7, true));     // 20.7
		assertEquals(new MarkGrade(33, Grade.N), calculator.calculateMark(5, 8, 7, 9, true));     // 32.9
		assertEquals(new MarkGrade(44, Grade.N), calculator.calculateMark(1, 9, 9, 26, true));    // 43.6
		
		// Rounding down.
		assertEquals(new MarkGrade(15, Grade.N), calculator.calculateMark(2, 2, 2, 12, true));    // 15.2
		assertEquals(new MarkGrade(28, Grade.N), calculator.calculateMark(5, 3, 5, 19, true));    // 28.4
		assertEquals(new MarkGrade(39, Grade.N), calculator.calculateMark(4, 6, 5, 31, true));    // 39.1
		assertEquals(new MarkGrade(44, Grade.N), calculator.calculateMark(2, 4, 7, 43, true));    // 44.3
	}
	
	@Test
	public void testGradeNCN() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(null, Grade.NCN), calculator.calculateMark(0, 0, 0, 0, false));    // MarkCal5 failed here.
		assertEquals(new MarkGrade(null, Grade.NCN), calculator.calculateMark(10, 0, 0, 0, false));
		assertEquals(new MarkGrade(null, Grade.NCN), calculator.calculateMark(0, 10, 0, 0, false));
		assertEquals(new MarkGrade(null, Grade.NCN), calculator.calculateMark(0, 0, 10, 0, false));
		assertEquals(new MarkGrade(null, Grade.NCN), calculator.calculateMark(0, 0, 0, 100, false));
	}
	
	@Test
	public void testGradePX() throws ComponentOutOfRangeException {
		// 45 ~ 49 : PX
		// No rounding.
		assertEquals(new MarkGrade(45, Grade.PX), calculator.calculateMark(2, 9, 9, 26, true));	   // 45. MarkCla2 failed here.
		assertEquals(new MarkGrade(47, Grade.PX), calculator.calculateMark(2, 5, 9, 40, true));    // 47
		assertEquals(new MarkGrade(49, Grade.PX), calculator.calculateMark(7, 4, 6, 45, true));    // 49

		// Rounding up.
		assertEquals(new MarkGrade(45, Grade.PX), calculator.calculateMark(4, 5, 6, 40, true));    // 44.5
		assertEquals(new MarkGrade(46, Grade.PX), calculator.calculateMark(3, 4, 5, 49, true));    // 45.9
		assertEquals(new MarkGrade(49, Grade.PX), calculator.calculateMark(0, 6, 4, 56, true));    // 48.6
		
		// Rounding down.
		assertEquals(new MarkGrade(45, Grade.PX), calculator.calculateMark(7, 8, 2, 39, true));    // 45.4
		assertEquals(new MarkGrade(48, Grade.PX), calculator.calculateMark(4, 8, 9, 31, true));    // 48.1
		assertEquals(new MarkGrade(49, Grade.PX), calculator.calculateMark(7, 4, 7, 43, true));    // 44.3
	}	
	
	@Test
	public void testGradeP() throws ComponentOutOfRangeException {
		// 50 ~ 59 : P
		// No rounding.
		assertEquals(new MarkGrade(50, Grade.P), calculator.calculateMark(7, 9, 9, 26, true));	  // 50. MarkCla2 failed here.
		assertEquals(new MarkGrade(53, Grade.P), calculator.calculateMark(8, 5, 9, 40, true));    // 53
		assertEquals(new MarkGrade(59, Grade.P), calculator.calculateMark(8, 4, 8, 55, true));    // 59

		// Rounding up.
		assertEquals(new MarkGrade(50, Grade.P), calculator.calculateMark(9, 5, 6, 40, true));    // 49.5
		assertEquals(new MarkGrade(56, Grade.P), calculator.calculateMark(7, 6, 7, 49, true));    // 55.9
		assertEquals(new MarkGrade(59, Grade.P), calculator.calculateMark(4, 6, 4, 66, true));    // 58.6
		
		// Rounding down.
		assertEquals(new MarkGrade(50, Grade.P), calculator.calculateMark(0, 4, 4, 64, true));    // 50.4
		assertEquals(new MarkGrade(58, Grade.P), calculator.calculateMark(2, 8, 9, 51, true));    // 58.1
		assertEquals(new MarkGrade(59, Grade.P), calculator.calculateMark(5, 4, 7, 63, true));    // 59.3
	}	
	
	@Test
    public void testGradeC() throws ComponentOutOfRangeException {
		// 60 ~ 69 : C
		// No rounding.
		assertEquals(new MarkGrade(60, Grade.C), calculator.calculateMark(5, 9, 9, 46, true));	  // 60. MarkCla2 failed here.
		assertEquals(new MarkGrade(64, Grade.C), calculator.calculateMark(7, 5, 9, 60, true));    // 64
		assertEquals(new MarkGrade(69, Grade.C), calculator.calculateMark(6, 4, 8, 75, true));    // 69

		// Rounding up.
		assertEquals(new MarkGrade(60, Grade.C), calculator.calculateMark(10, 5, 8, 50, true));   // 59.5
		assertEquals(new MarkGrade(66, Grade.C), calculator.calculateMark(8, 8, 7, 59, true));    // 65.9
		assertEquals(new MarkGrade(69, Grade.C), calculator.calculateMark(5, 7, 5, 76, true));    // 68.6
		
		// Rounding down.
		assertEquals(new MarkGrade(60, Grade.C), calculator.calculateMark(10, 4, 4, 64, true));   // 60.4
		assertEquals(new MarkGrade(68, Grade.C), calculator.calculateMark(6, 8, 9, 61, true));    // 68.1
		assertEquals(new MarkGrade(69, Grade.C), calculator.calculateMark(9, 4, 7, 73, true));    // 69.3
	}
	
	@Test
    public void testGradeD() throws ComponentOutOfRangeException {
		// 70 ~ 79 : D
		// No rounding.
		assertEquals(new MarkGrade(70, Grade.D), calculator.calculateMark(6, 7, 9, 66, true));	  // 70. MarkCla2 failed here.
		assertEquals(new MarkGrade(74, Grade.D), calculator.calculateMark(8, 6, 10, 70, true));   // 74
		assertEquals(new MarkGrade(79, Grade.D), calculator.calculateMark(7, 8, 10, 75, true));   // 79

		// Rounding up.
		assertEquals(new MarkGrade(70, Grade.D), calculator.calculateMark(8, 5, 8, 70, true));    // 69.5
		assertEquals(new MarkGrade(76, Grade.D), calculator.calculateMark(9, 8, 9, 69, true));    // 75.9
		assertEquals(new MarkGrade(79, Grade.D), calculator.calculateMark(6, 7, 7, 86, true));    // 78.6
		
		// Rounding down.
		assertEquals(new MarkGrade(70, Grade.D), calculator.calculateMark(8, 7, 9, 64, true));    // 70.4
		assertEquals(new MarkGrade(78, Grade.D), calculator.calculateMark(7, 8, 7, 81, true));    // 78.1
		assertEquals(new MarkGrade(79, Grade.D), calculator.calculateMark(10, 6, 7, 83, true));   // 79.3
	}
	
	@Test
    public void testGradeHD() throws ComponentOutOfRangeException {
	    // 80 ~ 100 : HD
		// No rounding.
		assertEquals(new MarkGrade(100, Grade.HD), calculator.calculateMark(10, 10, 10, 100, true));  // 100
		assertEquals(new MarkGrade(94, Grade.HD), calculator.calculateMark(7, 8, 10, 100, true));     // 94
		assertEquals(new MarkGrade(80, Grade.HD), calculator.calculateMark(7, 6, 8, 86, true));	      // 80. MarkCla2 and 3 failed here.

		// Rounding up.
		assertEquals(new MarkGrade(99, Grade.HD), calculator.calculateMark(10, 10, 9, 100, true));    // 98.5
		assertEquals(new MarkGrade(86, Grade.HD), calculator.calculateMark(7, 8, 9, 89, true));       // 85.9
		assertEquals(new MarkGrade(80, Grade.HD), calculator.calculateMark(6, 9, 7, 83, true));       // 79.8

		// Rounding down.
		assertEquals(new MarkGrade(99, Grade.HD), calculator.calculateMark(10, 10, 10, 99, true));    // 99.4
		assertEquals(new MarkGrade(98, Grade.HD), calculator.calculateMark(10, 10, 10, 96, true));     // 68.1
		assertEquals(new MarkGrade(80, Grade.HD), calculator.calculateMark(6, 7, 9, 84, true));       // 80.4
	}
	
	/**
	 * White-box test orienting MarkCalcu
	 * Test with specific score value.
	 * @throws ComponentOutOfRangeException
	 */
	@Test
	public void testSpecificScore() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(50, Grade.P), calculator.calculateMark(2, 7, 8, 43, true));  // 50.3. MarkCla4 failed here.
	}
	
	/**
	 * Test on all decimal values from .1 to .9 
	 * @throws ComponentOutOfRangeException
	 */
	@Test
	public void testAllDecimalValues() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(17, Grade.N), calculator.calculateMark(3, 2, 3, 11, true));     // 17.1
		assertEquals(new MarkGrade(31, Grade.N), calculator.calculateMark(4, 3, 6, 22, true));     // 30.7
		assertEquals(new MarkGrade(47, Grade.PX), calculator.calculateMark(5, 7, 8, 33, true));     // 47.3
		assertEquals(new MarkGrade(58, Grade.P), calculator.calculateMark(6, 8, 9, 44, true));     // 57.9
		assertEquals(new MarkGrade(68, Grade.C), calculator.calculateMark(6, 9, 10, 55, true));    // 67.5
		assertEquals(new MarkGrade(20, Grade.N), calculator.calculateMark(7, 2, 2, 11, true));     // 19.6
		assertEquals(new MarkGrade(33, Grade.N), calculator.calculateMark(8, 4, 4, 22, true));     // 33.2
		assertEquals(new MarkGrade(47, Grade.PX), calculator.calculateMark(9, 6, 6, 33, true));     // 46.8
		assertEquals(new MarkGrade(60, Grade.C), calculator.calculateMark(10, 8, 8, 44, true));    // 60.4
	}
}
