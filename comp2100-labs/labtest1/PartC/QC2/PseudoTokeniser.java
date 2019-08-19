// A way to provide tokens without having to include tokeniser code
public class PseudoTokeniser {

	Token[] tokens;
	int count;

	public PseudoTokeniser(Token[] tokens) {
		this.tokens = tokens;
		count = 0;
	}
	
	public Token current() {
		return tokens[count];
	}

	public void next() {
		count += 1;
	}

	public boolean hasNext() {
		return count < tokens.length;
	}

	public void showTokens() {
		for (Token t : tokens)
			System.out.print(t.show());
	}

}