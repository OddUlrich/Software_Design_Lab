/**
 * @author huy.pham
 */
public class Quackologist implements Observer {

	public void update(Observable duck) {
		System.out.println("[Quackologist observed]: " + duck + " just quacked.");
	}

}
