/**
 * Name: Parser.java
 *
 *  The main objective of this class is to implement a simple parser.
 *  It should be able to parser the following grammar rule:
 *  <exp>    ::= <term> | <exp> + <term> | <exp> - <term>
 *  <term>   ::=  <factor> | <term> * <factor> | <term> / <factor>
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
    <exp>    ::= <term> | <exp> + <term> | <exp> - <term>
    <term>   ::=  <factor> | <term> * <factor> | <term> / <factor>
    <factor> ::= <unsigned integer> | ( <exp> ) 
     */
    public Exp parseExp() {
        // TODO: Implement parse function for <exp>
        // ########## YOUR CODE STARTS HERE ##########

        // ########## YOUR CODE ENDS HERE ##########
    }

    public Exp parseTerm() {
        // TODO: Implement parse function for <term>
        // ########## YOUR CODE STARTS HERE ##########

        // ########## YOUR CODE ENDS HERE ##########
    }

    public Exp parseFactor() {
        // TODO: Implement parse function for <factor>
        // ########## YOUR CODE STARTS HERE ##########

        // ########## YOUR CODE ENDS HERE ##########
    }    
}
