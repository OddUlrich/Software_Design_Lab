import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    /**
     *  JUint Test for *Exp && Parser  -- 2019.8
     */

    private static MyTokenizer tokenizer;
    
    private static final String SIMPLECASE = "1+2";
    private static final String MIDCASE = "12 * 5 - 3";
    private static final String COMPLEXCASE = "(10 - 2) * (10 / 2) + 1";

    private static final String[] testExample = new String[]{"2+1", "2-1", "2*1", "2/1"};
    
    
    @Test
    public void testSimleAdd() {
        MyTokenizer mathTokenizer = new MyTokenizer(testExample[0]);
        Exp t1 = new Parser(mathTokenizer).parseExp();
        assertEquals(3, t1.evaluate());            
    }
    
    @Test
    public void testSimleSub() {
        MyTokenizer mathTokenizer = new MyTokenizer(testExample[1]);
        Exp t1 = new Parser(mathTokenizer).parseExp();
        assertEquals(1, t1.evaluate());            
    }
    
    @Test
    public void testSimleMul() {
        MyTokenizer mathTokenizer = new MyTokenizer(testExample[2]);
        Exp t1 = new Parser(mathTokenizer).parseExp();
        assertEquals(2, t1.evaluate());            
    }
    
    @Test
    public void testSimleDiv() {
        MyTokenizer mathTokenizer = new MyTokenizer(testExample[3]);
        Exp t1 = new Parser(mathTokenizer).parseExp();
        assertEquals(2, t1.evaluate());            
    }

    @Test
    public void testSimpleCase(){
        tokenizer = new MyTokenizer(SIMPLECASE);
        try{
            Exp exp = new Parser(tokenizer).parseExp();
            assertEquals("incorrect display format", "(1 + 2)", exp.show());
            assertEquals("incorrect evaluate value", 3, exp.evaluate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testMidCase(){
        tokenizer = new MyTokenizer(MIDCASE);
        try{
            Exp exp = new Parser(tokenizer).parseExp();
            assertEquals("incorrect display format", "((12 * 5) - 3)", exp.show());
            assertEquals("incorrect evaluate value", 57, exp.evaluate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testComplexCase(){
        tokenizer = new MyTokenizer(COMPLEXCASE);
        try{
            Exp exp = new Parser(tokenizer).parseExp();
            assertEquals("incorrect display format","(((10 - 2) * (10 / 2)) + 1)", exp.show());
            assertEquals("incorrect evaluate value", 41, exp.evaluate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

}


