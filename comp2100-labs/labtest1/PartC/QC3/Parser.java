
public class Parser {

    PseudoTokeniser _tokenizer;
    
    public Parser(PseudoTokeniser tokeniser) {
        _tokenizer = tokeniser;
    }

    /**
     *  <BOOL> ::= NOT (<BOOL>) | (<BOOL> AND <BOOL>) | (<BOOL> OR <BOOL>) | TRUE | FALSE
     *
     *  <BOOL>   ::=  <factor> | <factor> AND <BOOL> | <factor> OR <BOOL>
     *  <factor> ::= TRUE | FALSE | ( <BOOL> ) | NOT ( <BOOL> )
     *
     */

    public Bool parseBool() {
        // TODO: Implement this method
        // You can use any standard library if you want
        // You can add any method if it helps implementation

        Bool bool = parseFactor();

        if (_tokenizer.hasNext() && _tokenizer.current().type == TokenType.AND) {
            _tokenizer.next();
            Bool factor = parseFactor();
            return new AndExp(bool, factor);
        } else if (_tokenizer.hasNext() && _tokenizer.current().type == TokenType.OR) {
            _tokenizer.next();
            Bool factor = parseFactor();
            return new OrExp(bool, factor);
        } else {
            return bool;
        }
    }

    /**
     *  <BOOL> ::= NOT (<BOOL>) | (<BOOL> AND <BOOL>) | (<BOOL> OR <BOOL>) | TRUE | FALSE
     *
     *  <BOOL>   ::=  <factor> | <factor> AND <BOOL> | <factor> OR <BOOL>
     *  <factor> ::= TRUE | FALSE | ( <BOOL> ) | NOT ( <BOOL> )
     *
     */

    public Bool parseFactor() {
        Bool factor = null;

        if (_tokenizer.hasNext() && _tokenizer.current().type == TokenType.TRUE) {
            factor = new True();
            _tokenizer.next();
            return factor;

        } else if (_tokenizer.hasNext() && _tokenizer.current().type == TokenType.FALSE) {
            factor = new False();
            _tokenizer.next();
            return factor;

        } else if (_tokenizer.hasNext() && _tokenizer.current().type == TokenType.NOT) {
            _tokenizer.next(); // Now the token is left brace.
            while (_tokenizer.hasNext() && _tokenizer.current().type != TokenType.RIGHTBRACKET) {
                _tokenizer.next();
                factor = parseBool();
            }
            _tokenizer.next();
            return new NotExp(factor);

        } else if (_tokenizer.hasNext() && _tokenizer.current().type == TokenType.LEFTBRACKET) {
            while (_tokenizer.hasNext() && _tokenizer.current().type != TokenType.RIGHTBRACKET) {
                _tokenizer.next();
                factor = parseBool();
            }
            _tokenizer.next();
            return factor;
        } else {
            return factor;
        }
    }

}
