
public class Parser {

    PseudoTokeniser _tokenizer;
    
    public Parser(PseudoTokeniser tokeniser) {
        _tokenizer = tokeniser;
    }

    /**
     *  <exp> ::= <integer literal> | (<exp>*<exp>) | (<exp>/<exp>) | !(<exp>)
     *
     *
     *  <exp> ::= <term>
     *  <term> ::=  <factor> |  <factor> * <term> | <factor> / <term>
     *  <factor> ::= <integer literal> | (<exp>) | !(<exp>)
     *
     */

    public Exp parseExp() {
        // TODO: Implement this method
        // You can use any standard library if you want
        // You can add any method if it helps implementation

        return parseTerm();
    }

    public Exp parseTerm() {
        Exp factor = parseFactor();

        if (_tokenizer.hasNext() && _tokenizer.current().type == TokenType.MULTIPLY) {
            _tokenizer.next();
            Exp term = parseTerm();
            return new MultExp(factor, term);

        } else if (_tokenizer.hasNext() && _tokenizer.current().type == TokenType.DIVIDE) {
            _tokenizer.next();
            Exp term = parseTerm();
            return new DivExp(factor, term);
        } else {
            return factor;
        }
    }

    public Exp parseFactor() {
        Exp exp = null;

        if (_tokenizer.hasNext() && _tokenizer.current().type == TokenType.INT) {
            exp = new IntExp(_tokenizer.current().value);
            _tokenizer.next();
            return exp;
        }
        // Factorial.
        else if (_tokenizer.hasNext() && _tokenizer.current().type == TokenType.EXCLAMATION) {
            _tokenizer.next();  // The next "(".
            while (_tokenizer.hasNext() && _tokenizer.current().type != TokenType.RIGHTBRACKET) {
                _tokenizer.next();
                exp = parseExp();
            }
            _tokenizer.next();
            return new FacExp(exp);

        } else if (_tokenizer.current().type == TokenType.LEFTBRACKET) {
            while (_tokenizer.hasNext() && _tokenizer.current().type != TokenType.RIGHTBRACKET) {
                _tokenizer.next();
                exp = parseExp();
            }
            _tokenizer.next();
            return exp;
        } else {
            return null;
        }
    }
}
