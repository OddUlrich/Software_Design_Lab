public enum TokenType {
	INT (""),
	LEFTBRACKET ("("),
	RIGHTBRACKET (")"),
	MULTIPLY ("*"),
	DIVIDE ("/"),
	EXCLAMATION ("!");

	String value;

	TokenType(String value) {
		this.value = value;
	}
}