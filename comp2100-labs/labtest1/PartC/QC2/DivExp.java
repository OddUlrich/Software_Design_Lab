public class DivExp extends Exp {

	Exp left;
	Exp right;

	public DivExp(Exp left, Exp right) {
		this.left = left;
		this.right = right;
	}

	public Integer evaluate() {
		return left.evaluate() / right.evaluate();

	}

	public String show() {
		return "( " + left.show() + " / " + right.show() + " )";
	}
}