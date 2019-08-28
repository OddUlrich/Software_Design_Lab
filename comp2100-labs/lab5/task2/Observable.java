/**
 * 
 * @author dongwoo
 *
 */
public interface Observable {
	public void registerObserver(Observer observer);
	public void notifyObservers();
}
