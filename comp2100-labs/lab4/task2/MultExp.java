/**
 * MultExp: it is extended from the abstract class Exp,
 * 		    This class is used to represent the expression of multiplication
 *
 * You are not required to implement any function inside this class.
 * Please do not change anything inside this class as well.
 */

public class MultExp extends Exp {
	private Exp term;
	private Exp factor;


	public MultExp(Exp term, Exp factor) {
		this.term = term;
		this.factor = factor;

	}

	@Override
	public String show() {
		return "(" + term.show() + " * " + factor.show() + ")";
	}

	@Override
	public int evaluate() {
		return (term.evaluate() * factor.evaluate());
	}

}