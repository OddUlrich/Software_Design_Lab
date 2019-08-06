/**
 * SubExp: it is extended from the abstract class Exp,
 * 		    This class is used to represent the expression of subtraction
 *
 * Please do not change anything else.
 */

public class SubExp extends Exp {
	private Exp exp;
	private Exp term;


	public SubExp(Exp exp, Exp term) {
		this.exp = exp;
		this.term = term;

	}

	@Override
	public String show() {
		// TODO: Implement show() function for displaying the expression
		return "(" + exp.show() + " - " + term.show() + ")";
	}

	@Override
	public int evaluate() {
		// TODO: Implement evaluate() function for evaluating the result
		return (exp.evaluate() - term.evaluate());
	}

}
