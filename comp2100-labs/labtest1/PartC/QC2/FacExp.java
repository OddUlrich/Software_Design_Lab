public class FacExp extends Exp {

	Exp value;

	public FacExp(Exp value) {
		this.value = value;
	}

	public Integer factorial(int x) {
		return x < 1 ? 1 : (x * factorial(x - 1));
	}

	public Integer evaluate() {
		return factorial(value.evaluate());

	}

	public String show() {
		return "!( " + value.show() + " )";
	}
}