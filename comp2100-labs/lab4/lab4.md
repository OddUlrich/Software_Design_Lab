---
title: "Lab 4 - Parser"
summary: Tokenizer and Parser
layout: page
---

The partial code for this lab can be found [here](https://gitlab.cecs.anu.edu.au/u1009226/comp2100-labs/lab4).

## Task 1 - Tokenizer [0.5 marks]

The main objective of this part is to implement a simple tokenizer. The input of the tokenizer is a string. Your goal is to break down the character stream and return a sequence of tokens.

First, take a look at 'Token.java' and understand the types of tokens we want to extract from a string. There are 7 types of tokens:

1. INT: a decimal 32-bit unsigned integer (e.g. `4869`)
2. ADD: a single add character (`+`)
3. SUB: a single minus character (`-`)
4. MUL: a single star character (`*`)
5. DIV: a single slash character (`/`)
6. LBRA: a single left round bracket (`(`)
7. RBRA: a single right round bracket (`)`)

Our main tokenizer class, `MyTokenizer.java`, inherits abstract class `Tokenizer.java` which has three methods in it: `hasNext()`, `current()`, and `next()`. `MyTokenizer.java` implements `Tokenizer.java`. Specifically, `MyTokenizer.java` defines two additional private fields: `_buffer` and `currentToken`. `_buffer` will keep the string that we want to tokenize, and `currentToken` keeps a Token instance which is extracted by `next()` method. Read the comments to understand the details of how these function should work.

- Check the constructor of `MyTokenizer`, which will initially save the input string to `_buffer` and extract the first token using `next()`. 
- Check the implementation of `hasNext()` and `current()` in `MyTokenizer`.

__Now your job is to implement the missing part of `next()` method.__ Initially, the tokenizer provided only works with tokens that are: "+" token and the "-" token. It is also able to consume white space, which normally a tokenizer would do. Modify the `nextToken()` implementation such that it deals with the tokens "\*", "/", "(",  ")" and unsigned integer. Before implementing the actual method, please go and check the test cases in `TokenizerTest.java`. Test cases are one of the best ways to define the requirements of the code.

You may want to check the [example code](https://gitlab.cecs.anu.edu.au/u1009226/comp2100-lecture/tree/master/src/comp2100/parsing/tokenizer) used in the classroom lecture. The implementation is slightly different from the example code since we introduced `Token` class in this task. Therefore, instead of returning a string from `current()` now the tokenizer will return `Token` class which contains the type information as well as the surface form (string) or the token. This way of implementation is more object-oriented.

Remind: Please implement your code within a block indicated by comments; 'YOUR CODE STARTS HERE' and 'YOUR CODE ENDS HERE' for each task.

## Task 2 - Parser [0.5 marks]

The main objective of this part is to implement a simple parser. To complete the second task, you first need to complete Task 1. Once you've implemented tokenizer, copy your tokenizer implementations into `task2` to import the tokenizer inside of the parser.

Now the goal of this task is to implement a parser for the following grammar:

```
<exp> ::= <term> | <exp> + <term> | <exp> - <term>
<term> ::=  <factor> | <term> * <factor> | <term> / <factor>
<factor> ::= <unsigned integer> | ( <exp> ) 
```

Try to understand the grammar and implement a parser which could parse this grammar. 

1. Go through `Exp.java` , `AddExp.java` , `IntExp.java` , `SubExp.java`, `DivExp.java`, `MultExp.java`  files:

   Check the implementations of `show()` and `evaluate()` methods. `show()` method is designed to return the content of parsed expression, and `evaluate()` method will evaluate and execute the expression and return the result.

2. Go through `ParserTest.java`:
   
   `ParserTest` is a JUnit test class, which defines the proper behaviour of the parser through a set of examples. Read the code and try to understand what are the requirements of the parser.

3. Implement missing parts of `Parser.java`:

   `Parser` class contains three parsing methods: `parseExp()`, `parseTerm()`, and `parseFactor()`. Now your job is to implement these three methods. If you don't know what you need to implement, please go and check the code that we used in the classroom [here](https://gitlab.cecs.anu.edu.au/u1009226/comp2100-lecture/tree/master/src/comp2100/parsing). The grammar used in the classroom was slightly simpler than the grammar above, but the structure of the code is almost similar.

If you are still unconfident about the parser, please go over the lecture and ask help from tutors.

Remind: Please implement your code within a block indicated by comments; 'YOUR CODE STARTS HERE' and 'YOUR CODE ENDS HERE' for each task.


## Submission Guideline

* Assignment deadline: Friday, 23 August 2019, 11:55 PM
* Submission mode: Electronic, via Wattle (Lab 4)
* Submission format (IMPORTANT):
  * Upload your final version of MyTokenizer.java (for task 1) and Parser.java (for task 2) to Wattle.
    * Do not change the file names.
    * Do not upload any other files.
  * Do not change the structure of the source code including class name, package structure, etc. You are only allowed to edit the part indicated by comments (See source code files for details).
  * You may use standard libraries to complete the tasks, but it is not allowed to use external libraries.
  * Violation of the submission format will have their assignment not evaluated by an autograder and get zero marks.
