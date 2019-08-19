public enum TokenType {
	TRUE ("TRUE"),
	FALSE ("FALSE"),
	LEFTBRACKET ("("),
	RIGHTBRACKET (")"),
	AND ("&&"),
	OR ("||"),
	NOT ("!");

	String value;

	TokenType(String value) {
		this.value = value;
	}
}