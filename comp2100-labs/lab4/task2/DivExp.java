/**
 * DivExp: it is extended from the abstract class Exp.
 * 		    This class is used to represent the expression of division
 *
 * You are not required to implement any function inside this class.
 * Please do not change anything inside this class as well.
 *
 */

public class DivExp extends Exp {
	private Exp term;
	private Exp factor;

	public DivExp(Exp term, Exp factor) {
		this.term = term;
		this.factor = factor;
	}

	@Override
	public String show() {
		return "(" + term.show() + " / " + factor.show() + ")";
	}

	@Override
	public int evaluate() {
		return (term.evaluate() / factor.evaluate());
	}


}