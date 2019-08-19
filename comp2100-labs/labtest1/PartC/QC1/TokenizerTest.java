import static org.junit.Assert.*;

import org.junit.Test;

public class TokenizerTest {
    private static Tokenizer tokenizer;

    @Test
    public void test0() {
    	String sentence = "<hi>";
        tokenizer = new Tokenizer(sentence);
        assertEquals(TokenType.OpenTag, tokenizer.current().type);    	
    }
    
    @Test
    public void test1() {
    	String sentence = "<hi>happy</world>banana";
        tokenizer = new Tokenizer(sentence);
        assertEquals(TokenType.OpenTag, tokenizer.current().type);
        tokenizer.next();
        assertEquals(TokenType.Value, tokenizer.current().type);
        tokenizer.next();
        assertEquals(TokenType.CloseTag, tokenizer.current().type);
        tokenizer.next();
        assertEquals(TokenType.Value, tokenizer.current().type);
    }
    
    @Test
    public void test2() {
    	String sentence = "<emp></emp>";
        tokenizer = new Tokenizer(sentence);
        assertEquals(TokenType.OpenTag, tokenizer.current().type);
        tokenizer.next();
        assertEquals(TokenType.CloseTag, tokenizer.current().type);
        tokenizer.next();
    }
    
    @Test
    public void test3() {
    	String sentence = "don'tParseThese>>Brackets </okay>";
        tokenizer = new Tokenizer(sentence);
        assertEquals(TokenType.Value, tokenizer.current().type);
        tokenizer.next();
        assertEquals(TokenType.CloseTag, tokenizer.current().type);
    }
}
