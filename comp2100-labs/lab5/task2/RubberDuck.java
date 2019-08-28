/**
 * @author huy.pham
 * @author dongwoo
 */
public class RubberDuck extends Duck {

	public void quack() {
		System.out.println("Squeak");

		// TODO: notify all registered observers
		// TODO: Add your implementation here

		notifyObservers();

		// End TODO
	}

	public String toString() {
		return "Rubber Duck";
	}
}
