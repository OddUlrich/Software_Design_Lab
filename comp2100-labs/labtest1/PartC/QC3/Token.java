
public class Token {

	TokenType type;
    
    public Token(TokenType type) {
    	this.type = type;
    }

    public String show() {
    	return type.value;
    }
}
