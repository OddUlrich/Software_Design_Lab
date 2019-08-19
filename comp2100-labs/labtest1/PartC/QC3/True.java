public class True extends Bool {

	boolean value = true;
	
	public True() {
	}

	public boolean evaluate() {
		return value;
	}
	public String show() {
		return "TRUE";
	}
}