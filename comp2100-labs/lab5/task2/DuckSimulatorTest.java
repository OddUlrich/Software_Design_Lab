import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * NOTE: There might be more tests when marking.
 * 
 * @author dongwoo
 * @author huy.pham
 */
public class DuckSimulatorTest {
	List<Duck> duckList;
	Observer quackologist;
	Observer quackCounter;
	
	class QuackCounter implements Observer {
		int count = 0;

		public void update(Observable duck) {
			count += 1;
		}
	}

	@Before
	public void beforeEachTestMethod() {
		duckList = new ArrayList<Duck>();
		duckList.add(new KnightDuck());
		duckList.add(new MagicianDuck());
		duckList.add(new RubberDuck());
		
		quackologist = new Quackologist();
		quackCounter = new QuackCounter();
		
		for(Duck duck: duckList) {
			duck.registerObserver(quackCounter);
			duck.registerObserver(quackologist);
		}
	}

	@Test
	public void testQuack() {
		for(Duck duck: duckList) {
			duck.quack();
		}
		Assert.assertEquals(3, ((QuackCounter)quackCounter).count);
	}
}
