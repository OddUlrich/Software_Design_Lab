/**
 * Abstract class for duck.
 *
 * @author Wyman Wong
 * 
 */

public abstract class Duck {
	protected FlyBehaviour flyBehaviour;
	protected AttackBehaviour attackBehaviour;

	public void getInjured() {
		this.flyBehaviour = new NoFly();
	}

	public String performFly() {
		return this.flyBehaviour.fly();
	}

	public String performAttack(int power) {
		return this.attackBehaviour.attack(power);
	}

	public void changeWeapon(AttackBehaviour attackBehaviour) {
		this.attackBehaviour = attackBehaviour;
	}

    /**
     * Abstract method in duck class that need to be implement in subclasses.
     *
     * @author: Wyman Wong
     * @return String: the content to display.
     */
	public abstract String display();


}