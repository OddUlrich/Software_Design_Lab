import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TokenizerTest {
    /**
     *  JUint Test for Tokenizer  -- 2019.8
     */

    private static Tokenizer tokenizer;
    private static final String testCase = "(100 + 2 - 40) / 10 * 4789";
    private static final String passCase = "+-";

    @Test
    public void testAddSub() {
    	tokenizer = new MyTokenizer(passCase);
    	
    	//check the type of the first token is LBRA
        assertEquals("wrong token type", Token.Type.ADD, tokenizer.current().type());
        //check the actual token is "("
        assertEquals("wrong token value", "+", tokenizer.current().token());
        
        
        //extract next token
        tokenizer.next();
        
    	//check the type of the first token is LBRA
        assertEquals("wrong token type", Token.Type.SUB, tokenizer.current().type());
        //check the actual token is "("
        assertEquals("wrong token value", "-", tokenizer.current().token());
    }
    
    @Test
    public void testFirst(){
    	tokenizer = new MyTokenizer(testCase);
    	
    	//check the type of the first token is LBRA
        assertEquals("wrong token type", Token.Type.LBRA, tokenizer.current().type());
        //check the actual token is "("
        assertEquals("wrong token value", "(", tokenizer.current().token());
    }

    @Test
    public void testTokenResult(){
    	tokenizer = new MyTokenizer(testCase);
    	
        // test first token (
        assertEquals(Token.Type.LBRA, tokenizer.current().type());
        
        // test second token 100
        tokenizer.next();
        assertEquals(Token.Type.INT, tokenizer.current().type());
        assertEquals("100", tokenizer.current().token());

        // test third token +
        tokenizer.next();
        assertEquals(Token.Type.ADD, tokenizer.current().type());
        assertEquals("+", tokenizer.current().token());

        // test fourth token 2
        tokenizer.next();
        assertEquals(Token.Type.INT, tokenizer.current().type());
        assertEquals("2", tokenizer.current().token());

        // test fourth token -
        tokenizer.next();
        assertEquals(Token.Type.SUB, tokenizer.current().type());
        assertEquals("-", tokenizer.current().token());
        
        // test sixth token ), note that we call next twice. 
        // Correct implementation of tokenizer.current() should return ')' this case (not 40)
        tokenizer.next();
        tokenizer.next();
        assertEquals(Token.Type.RBRA, tokenizer.current().type());
    }
}
