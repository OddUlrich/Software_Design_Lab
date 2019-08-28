/**
 * @author dongwoo
 * @author huy.pham
 */
public class KnightDuck extends Duck{

	public void quack() {
		System.out.println("Quack");

		// TODO: notify all registered observers
		// TODO: Add your implementation here

		notifyObservers();

		// End TODO
	}

	public String toString() {
		return "Knight Duck";
	}
}
