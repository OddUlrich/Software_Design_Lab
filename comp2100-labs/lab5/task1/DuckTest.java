import static org.junit.Assert.*;

import java.lang.reflect.Modifier;
import org.junit.Test;

public class DuckTest {

	@Test
	public void testKnightDuck() {
		Duck duck = new KnightDuck(new AttackWithKnife(), new FlyWithWings());
		assertEquals("I can fly!", duck.performFly());
		
		// damage is doubled if you attack with the knife
		assertEquals("Attack with knife, Damage:6", duck.performAttack(3));
		assertEquals("Attack with knife, Damage:10", duck.performAttack(5));
		assertEquals("Attack with knife, Damage:30", duck.performAttack(15));
		
		duck.getInjured();
		assertEquals("I cannot fly :(", duck.performFly());
		
		assertEquals("I'm a mighty knight duck", duck.display());
	}
	
	@Test
	public void testMagicianDuck() {
		Duck duck = new MagicianDuck(new AttackWithStaff(), new FlyWithWings());
		assertEquals("I can fly!", duck.performFly());
		
		assertEquals("Attack with staff, Damage:3", duck.performAttack(3));
		assertEquals("Attack with staff, Damage:7", duck.performAttack(7));
		
		duck.changeWeapon(new AttackWithKnife());
		assertEquals("Attack with knife, Damage:6", duck.performAttack(3));		
		
		assertEquals("Feel the power of magic!", duck.display());
	}

	@Test
	public void testRubberDuck() {
		Duck duck = new RubberDuck(new AttackWithKnife(), new NoFly());
		assertEquals("Attack with knife, Damage:6", duck.performAttack(3));
		
		assertEquals("Rubber lover duck duck", duck.display());
	}
	
    @Test
    public void testAbstractClass() {
        // Duck class should be an abstract class
        assertTrue(Modifier.isAbstract(Duck.class.getModifiers()));
    }

}
