/**
 * @author dongwoo
 * @author huy.pham
 */
public class MagicianDuck extends Duck{

	public void quack() {
		System.out.println("Kwak");

		// TODO: notify all registered observers
		// TODO: Add your implementation here

		notifyObservers();

		// End TODO
	}

	public String toString() {
		return "Magician Duck";
	}
}
