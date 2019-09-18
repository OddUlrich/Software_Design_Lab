import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TokenizerMarking {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(60);

    private static final MyTokenizer emptyToken = new MyTokenizer("");
    private static MyTokenizer tokenFormula;
    private final static String fCase = "4789+10- 2 / 100 * 40+40 +  100 / 2";

    private static MyTokenizer tokenArithmetic;
    private final static String sCase = "((((1+2) + 3) + 4) + 5)";

    @Before
    public void beforeEachTestMethod(){
        tokenFormula = new MyTokenizer(fCase);
        tokenArithmetic = new MyTokenizer(sCase);
    }

    /**
     *    purpose: Test HasNext()
     *    point: 0.1
     */
    @Test
    public void testHasNext(){
        assertEquals(false, emptyToken.hasNext());
        assertEquals(true, tokenFormula.hasNext());
        assertEquals(true, tokenArithmetic.hasNext());
    }

    /**
     *    purpose: Test next()
     *    point: 0.1
     */
    @Test
    public void testNext(){
        Token re = null;
        // in case of error
        assertEquals(Token.class, tokenFormula.current().getClass());
        for (int i = 0; i <= 5; i ++) {
            // should unchanged
            re = tokenFormula.current();
            assertEquals(Token.Type.INT, re.type());
            assertEquals("4789", re.token());
        }
    }

    /**
     *    purpose: Test takeNext()
     *    point: 0.1
     */
    @Test
    public void testTakeNext(){
        assertEquals(Token.class, tokenFormula.current().getClass());
        assertEquals(Token.class, tokenArithmetic.current().getClass());

        tokenFormula.next();
        Token re = tokenFormula.current();
        assertEquals(Token.Type.ADD, re.type());
        assertEquals("+", re.token());
    }

    /**
     *   purpose: test token result of formula
     *   point: 0.1
     */
    @Test
    public void testFormula(){
        ArrayList<String> records = new ArrayList<>(10);
        try {
            while (tokenFormula.hasNext()) {
                Token t = tokenFormula.current();
                records.add(t.type() == Token.Type.INT ? t.token() : String.valueOf(t.type()));
            	tokenFormula.next();                
            }
            assertEquals("4789 ADD 10 SUB 2 DIV 100 MUL 40 ADD 40 ADD 100 DIV 2", String.join(" ", records));
        }
        catch (Exception e){
            fail("fail in Tokenizer test4");
        }
    }

    /**
     *   purpose: test token result of arithmetic
     *   point: 0.1
     */
    @Test
    public void testArithmetic() {
        ArrayList<String> records = new ArrayList<>(10);
        try {
            while (tokenArithmetic.hasNext()) {
                Token t = tokenArithmetic.current();
                records.add(t.type() == Token.Type.INT ? t.token() : String.valueOf(t.type()));
            	tokenArithmetic.next();                
            }
            assertEquals("LBRA LBRA LBRA LBRA 1 ADD 2 RBRA ADD 3 RBRA ADD 4 RBRA ADD 5 RBRA", String.join(" ", records));
        } catch (Exception e) {
            fail("fail in Tokenizer test5");
        }
    }
}
