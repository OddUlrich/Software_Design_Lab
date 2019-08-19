
public class Token {

	TokenType type;
	Integer value;
    
    public Token(TokenType type) {
    	this.type = type;
    	this.value = null;
    }

    public Token(int value) {
    	this.type = TokenType.INT;
    	this.value = value;
    }

    public String show() {
    	return type == TokenType.INT ? "" + value : type.value;
    }
}
