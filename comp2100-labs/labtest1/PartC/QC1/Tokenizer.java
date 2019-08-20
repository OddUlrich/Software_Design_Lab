public class Tokenizer {
    
    private String _buffer;
    private Token current;
    
    /**
     * Tokenizer constructor.
     * Note that the constructor will extract the first token from input buffer (text)
     * 
     * @param buffer String to be tokenized
     */
    public Tokenizer(String buffer) {
        _buffer = buffer;
        next();
    }
    
    /**
     * This method will extract token from _buffer and save it to {@code this.current}
     */
    public void next() {
        _buffer = _buffer.trim(); // Remove whitespace
        if(_buffer.isEmpty()) {
            current = null;
            return;
        }
        
        char firstChar = _buffer.charAt(0);
        int pos = 0;
        if (firstChar == '<') {
            // TODO: Finish this method

            // Get the length of tag.
            while (pos < _buffer.length() && _buffer.charAt(pos) != '>') {
                pos++;
            }

            if (pos == _buffer.length()) {
                current = new Token(TokenType.Value, _buffer.substring(0, pos));
            } else if (_buffer.charAt(1) == '/') {
                pos++;
                current = new Token(TokenType.CloseTag, _buffer.substring(0, pos));
            } else {
                pos++;
                current = new Token(TokenType.OpenTag, _buffer.substring(0, pos));
            }
        } else {
            while (pos < _buffer.length() && _buffer.charAt(pos) != '<') {
                pos++;
            }

            current = new Token(TokenType.Value, _buffer.substring(0, pos));
        }

        _buffer = _buffer.substring(pos);
    }

    /**
     *  Return the extracted token from next
     * @return current Token extracted from next()
     */
    public Token current() {
        return current;
    }

    /**
     * Check {@code current} return false if it's null.
     * @return
     */
    public boolean hasNext() {
        return current != null;
    }
}