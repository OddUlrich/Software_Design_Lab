public class NotExp extends Bool {

	Bool value;

	public NotExp(Bool value) {
		this.value = value;
	}

	public boolean evaluate() {
		return !value.evaluate();

	}

	public String show() {
		return "!( " + value.show() + " )";
	}
}