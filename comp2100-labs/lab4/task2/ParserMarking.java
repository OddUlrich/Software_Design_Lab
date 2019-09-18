import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class ParserMarking {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(60);

    private static Random rand;

    @BeforeClass
    public static void beforeTest(){
        rand = new Random();
    }


    /**
     *  Purpose:  test for single unsigned integer value
     *  TestCase: a, (a)
     *  Point:    0.1
     */
    @Test
    public void testSingleton(){
        // testcase: 3
        MyTokenizer case1 = new MyTokenizer("3");
        try{
            Exp exp = new Parser(case1).parseExp();
            assertEquals("3", exp.show());
            assertEquals(3, exp.evaluate());

        }catch (Exception e){
            fail("fail in test testSingleton");
        }

        // testcase: (4869)
        MyTokenizer case2 = new MyTokenizer("(4869)");
        try{
            Exp exp = new Parser(case2).parseExp();
            assertEquals("4869", exp.show());
            assertEquals(4869, exp.evaluate());

        }catch (Exception e){
            fail("fail in test testSingleton");
        }
    }

    /**
     *  Purpose:  test for fundamental calculation with binary objects
     *  TestCase: a + b, a - b, a * b, a / b (with WhiteSpace)
     *  Point:    0.1
     */
    @Test
    public void testfundamentalCalculation1(){
        int a = (5 + rand.nextInt(5)) * 2;  //even number
        int b = 2;
        String[] base = new String[]{ String.valueOf(a), "", String.valueOf(b)};

        try{
            // addition
            base[1] = "+";
            Exp addExp = new Parser(new MyTokenizer(String.join(" ", base))).parseExp();
            assertEquals(a + b, addExp.evaluate());
            assertEquals("("+String.join(" ", base)+")", addExp.show());

            // subtract
            base[1] = "-";
            Exp subExp = new Parser(new MyTokenizer(String.join(" ", base))).parseExp();
            assertEquals(a - b, subExp.evaluate());
            assertEquals("("+String.join(" ", base)+")", subExp.show());

            // multiply
            base[1] = "*";
            Exp mulExp = new Parser(new MyTokenizer(String.join(" ", base))).parseExp();
            assertEquals(a * b, mulExp.evaluate());
            assertEquals("("+String.join(" ", base)+")", mulExp.show());

            // divide
            base[1] = "/";
            Exp divExp = new Parser(new MyTokenizer(String.join(" ", base))).parseExp();
            assertEquals(a / b, divExp.evaluate());
            assertEquals("("+String.join(" ", base)+")", divExp.show());

        }catch (Exception e){
            fail("fail in test fundamentalCalculation with binary objects");
        }
    }

    /**
     * purpose: test for fundamental calculation
     * TestCase: (a . ( b . ( c . d))) with some operator .
     * Point: 0.1
     */
    @Test
    public void testfundamentalCalculation2(){
        int a = 198, b = 0, c = 11, d = 28;
        String input = null;

        try{
            input = a + " + (" + b + " - (" + c + " + " + d + "))";
            Exp nary1 = new Parser(new MyTokenizer(input)).parseExp();
            assertEquals(a+(b-(c+d)), nary1.evaluate());

            input = a + " * (" + b + " / (" + c + " * " + d + "))";
            Exp nary2 = new Parser(new MyTokenizer(input)).parseExp();
            assertEquals(a*(b/(c*d)), nary2.evaluate());

            input = a + " + (" + b + " + (" + c + " * " + d + "))";
            Exp nary3 = new Parser(new MyTokenizer(input)).parseExp();
            assertEquals(a+(b+(c*d)), nary3.evaluate());
        }
        catch (Exception e){
            fail("fail in test fundamentalCalculation with n-ary objects");
        }
    }

    /**
     * purpose: test for prior calculation
     * TestCase: (a + b) - (c + d); (a * b) / (c * d); a + (b + c) * d
     * Point: 0.1
     */
    @Test
    public void testPriorCalculation(){
        int a = 198, b = 602, c = 6622, d = 18;
        String input = null;

        try{
            input = "(" + a + " + " + b + ")" + " - " + "(" + c + " + " + d + ")";
            Exp prior1 = new Parser(new MyTokenizer(input)).parseExp();
            assertEquals((a+b)-(c+d), prior1.evaluate());

            input = "(" + a + " * " + b + ")" + " / " + "("  + c + " * " + d + ")";
            Exp prior2 = new Parser(new MyTokenizer(input)).parseExp();
            assertEquals((a*b)/(c*d), prior2.evaluate());

            input = a + " + " + "(" + b + " + " + c + ")" + " * " + d;
            Exp prior3 = new Parser(new MyTokenizer(input)).parseExp();
            assertEquals(a+(b+c)*d, prior3.evaluate());
        }
        catch (Exception e){
            fail("fail in test prior calculation");
        }
    }

    /**
     * purpose: advanced test
     * TestCase: arithmetic progression; fake factorial
     * Point: 0.1
     *
     *
     * arithmetic
     * "100 + 111 + 122 + 133 + 144 + 155 + 166 + 177 + 188 + 199"
     * "((100 + 199) * 10) /  2"
     *
     * fake factorial
     * "((((15 * 13) * 11) * 9) * 7)"
     */
    @Test
    public void advancedTest(){
        // ========  arithmetic progression ========
        int begin = 100, gap = 11, num = 10;
        ArrayList<String> arithmetic = new ArrayList<>();
        int result = 0;
        // feed in arithmetic
        for (int i = 0; i < num; i ++){
            result += begin + gap * i;
            arithmetic.add(String.valueOf(begin + gap * i));
        }
        String formulaExp1 = String.join(" + ", arithmetic);
        String formulaExp2 = "((" + arithmetic.get(0) + " + " + arithmetic.get(arithmetic.size()-1) + ")" +
                                    " * " + arithmetic.size() + ")" + " / " + " 2 ";

//        System.out.println(formulaExp1);
//        System.out.println(formulaExp2);
        try {
            Exp arithmetic1 = new Parser(new MyTokenizer(formulaExp1)).parseExp();
            Exp arithmetic2 = new Parser(new MyTokenizer(formulaExp2)).parseExp();

            assertEquals(result, arithmetic1.evaluate());
            assertEquals(arithmetic1.evaluate(), arithmetic2.evaluate());
        }
        catch (Exception e){
            fail("fail in advanced test");
        }

        // ========  fake factorial ========
        begin = 15; gap = 2; num = 5; result = 1;
        StringBuilder factorial = new StringBuilder();
        factorial.append("((((");

        for (int i = 0; i < num; i ++){
            result *= (begin - gap * i);
            factorial.append(String.valueOf(begin - gap * i));

            if (i == 0){
                factorial.append(" * ");
            }else if(i != num -1){
                factorial.append(") * ");
            }
        }
        factorial.append(")");
//        System.out.println(factorial.toString());

        try {
            Exp fac = new Parser(new MyTokenizer(factorial.toString())).parseExp();

            assertEquals(result, fac.evaluate());
            assertEquals(factorial.toString(), fac.show());
        }
        catch (Exception e){
            fail("fail in advanced test");
        }
    }
}
