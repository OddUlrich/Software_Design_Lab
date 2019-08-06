/**
 * AddExp: it is extended from the abstract class Exp.
 *         This class is used to represent the expression of addition
 *
 * You are not required to implement any function inside this class.
 * Please do not change anything inside this class as well.
 */

public class AddExp extends Exp {
	private Exp exp;
	private Exp term;

	public AddExp(Exp exp, Exp term) {
		this.exp = exp;
		this.term = term;
	}

	@Override
	public String show() {
		return "(" + exp.show() + " + " + term.show() + ")";
	}

	@Override
	public int evaluate() {
		return (exp.evaluate() + term.evaluate());
	}

}