/**
 * Name: Parser.java
 *
 *  The main objective of this class is to implement a simple parser.
 *  It should be able to parser the following grammar rule:
 *  <exp>    ::= <term> | <term> + <exp> | <term> - <exp>
 *  <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
 *  <factor> ::= <unsigned integer> | ( <exp> )
 *
 * @author:
 * @Uid:
 * @Date:
 */

public class Parser {

    MyTokenizer _tokenizer;
    
    public Parser(MyTokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    /*
    <exp>    ::= <term> | <term> + <exp> | <term> - <exp>
    <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
    <factor> ::= <unsigned integer> | ( <exp> )
     */
    public Exp parseExp() {
        // TODO: Implement parse function for <exp>
        // ########## YOUR CODE STARTS HERE ##########
        Exp term = parseTerm();

        if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.ADD) {
            _tokenizer.next();
            Exp exp = parseExp();
            return new AddExp(term, exp);

        } else if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.SUB) {
            _tokenizer.next();
            Exp exp = parseExp();
            return new SubExp(term, exp);

        }  else {
            return term;
        }
        // ########## YOUR CODE ENDS HERE ##########
    }

    public Exp parseTerm() {
        // TODO: Implement parse function for <term>
        // ########## YOUR CODE STARTS HERE ##########
        Exp factor = parseFactor();

        if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.MUL) {
            _tokenizer.next();
            Exp term = parseTerm();
            return new MultExp(factor, term);

        } else if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.DIV) {
            _tokenizer.next();
            Exp term = parseTerm();
            return new DivExp(factor, term);

        }  else {
            return factor;
        }
        // ########## YOUR CODE ENDS HERE ##########
    }

    public Exp parseFactor() {
        // TODO: Implement parse function for <factor>
        // ########## YOUR CODE STARTS HERE ##########
        if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.INT) {
            Exp uInt = new IntExp(Integer.parseInt(_tokenizer.current().token()));
            _tokenizer.next();
            return uInt;

        } else if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.LBRA) {
            _tokenizer.next();
            return parseExp();

        }  else if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.RBRA) {
            _tokenizer.next();
            if (_tokenizer.hasNext()
                && (_tokenizer.current().type() == Token.Type.ADD)
                    || (_tokenizer.current().type() == Token.Type.ADD)) {
                _tokenizer.next();
                return parseExp();

            } else if (_tokenizer.hasNext()
                && (_tokenizer.current().type() == Token.Type.MUL)
                    || (_tokenizer.current().type() == Token.Type.DIV)) {
                _tokenizer.next();
                return parseTerm();
            } else {
                return null;
            }
        } else {
            return null;
        }
        // ########## YOUR CODE ENDS HERE ##########
    }
}
