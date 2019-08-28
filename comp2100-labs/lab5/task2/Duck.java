import java.util.ArrayList;
import java.util.List;

/**
 * abstract class Duck implements two interfaces: Quackable and Observable
 * 
 * @author dongwoo
 * @author huy.pham
 */
public abstract class Duck implements Quackable, Observable {
	// observerList should keep the list of observers watching this duck 
	List<Observer> observerList;

	public Duck() {
		observerList = new ArrayList<Observer>();
	}
	
	public void registerObserver(Observer observer) {
		// TODO: add observer to observerList
		// TODO: Add your implementation here

		observerList.add(observer);

		// End TODO
	}

	public void notifyObservers() {
		// TODO: Call update for each observer in observerList
		// TODO: Add your implementation here

		for (Observer obs: observerList) {
			obs.update(this);
		}

		// End TODO
	}
}
