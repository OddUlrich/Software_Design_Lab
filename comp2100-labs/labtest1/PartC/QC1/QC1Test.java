public class QC1Test {
    
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static void test1(){
        String sentence = "<HELLO>happy</WORLD>banana";
        Tokenizer tokenizer = new Tokenizer(sentence);

        System.out.println("Test: " + sentence);
        System.out.println((TokenType.OpenTag == tokenizer.current().type)?"Pass":"Fail");
        tokenizer.next();
        System.out.println((TokenType.Value == tokenizer.current().type)?"Pass":"Fail");
        tokenizer.next();
        System.out.println((TokenType.CloseTag == tokenizer.current().type)?"Pass":"Fail");
        tokenizer.next();
        System.out.println((TokenType.Value == tokenizer.current().type)?"Pass":"Fail");
    }

    public static void test2(){
        String sentence = "<empty></empty>";
        Tokenizer tokenizer = new Tokenizer(sentence);

        System.out.println("Test: " + sentence);
        System.out.println((TokenType.OpenTag == tokenizer.current().type)?"Pass":"Fail");
        tokenizer.next();
        System.out.println((TokenType.CloseTag == tokenizer.current().type)?"Pass":"Fail");
    }

    public static void test3(){
        String sentence = "don'tParseThese>>>Brackets</alright>";
        Tokenizer tokenizer = new Tokenizer(sentence);

        System.out.println("Test: " + sentence);
        System.out.println((TokenType.Value == tokenizer.current().type)?"Pass":"Fail");
        tokenizer.next();
        System.out.println((TokenType.CloseTag == tokenizer.current().type)?"Pass":"Fail");
    }
}
