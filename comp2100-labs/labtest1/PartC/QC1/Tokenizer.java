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
        
        // TODO: Finish this method
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