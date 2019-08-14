/**
 * SubExp: it is extended from the abstract class Exp,
 * 		    This class is used to represent the expression of subtraction
 *
 * Please do not change anything else.
 */

public class SubExp extends Exp {
	private Exp exp;
	private Exp term;


	public SubExp(Exp term, Exp exp) {
		this.term = term;
		this.exp = exp;

	}

	@Override
	public String show() {
		// TODO: Implement show() function for displaying the expression
		return "(" + term.show() + " - " + exp.show() + ")";
	}

	@Override
	public int evaluate() {
		// TODO: Implement evaluate() function for evaluating the result
		return (term.evaluate() - exp.evaluate());
	}

}
