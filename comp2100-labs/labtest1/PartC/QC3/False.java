public class False extends Bool {

	boolean value = false;
	
	public False() {
	}

	public boolean evaluate() {
		return value;
	}
	public String show() {
		return "FALSE";
	}
}