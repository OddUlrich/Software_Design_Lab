---
title: "Lab 6 - Persistent data"
summary: Persistent data
layout: page
---

Partial code for this lab is available at [here](https://gitlab.cecs.anu.edu.au/u1009226/comp2100-labs/tree/master/lab6).

## Task 1 - Save data to a file [0.5 marks]

This stage aims to save the data of a class to a file. Open your Book Collection implementation (`task/BookCollection.java`) and fill in the "save" functions.

1. Directly save the class using bespoke or serialization method. For bespoke, you could define your own format for saving a book collection. Implement `saveToBespokeFile` method once you define your own format.
2. Using JSON to save the structure of the book collection. Implement `saveToJSONFile` in `BookCollection.java`.
  - There are multiple ways to write JSON documents as well. In this task, we will only use simple.json library, which is not the part of the standard Java library. 
  - Please add `json-simple-1.1.1.jar` file to your classpath to import the library properly. 
  - The jar file is available in the lab repository as well.
  - Here's a simple explanation on how to use simple.json library to read/write json files [link](https://www.mkyong.com/java/json-simple-example-read-and-write-json/)
3. Using XML to save the structure of the book collection. Implement `saveToXMLFile` in `BookCollection.java`.
  - There are many different ways to write XML documents. During the class, we have discussed DOM-based approach. You can use DOM-based approach, or you may want to use an alternative.
  - In this case, please make sure that your implementation is compilable with Java 8+ (Use standard library).

## Task 2 - Load data from a file [0.5 marks]

Similarly, now implement the "load" methods for these files. Note that the format for these has already been specified, and each of these load methods should be capable of reading the corresponding file saved in Task 1.

1. Implement `loadFromBespokeFile` method in `BookCollection.java` to load a book collection which is stored by `saveToBespokeFile` method.
2. Implement `loadFromJSONFile` method in `BookCollection.java` to load a book collection which is stored by `saveToJSONFile` method.
3. Implement `loadFromXMLFile` method in `BookCollection.java` to load a book collection which is stored by `saveToXMLFile` method.

For both task 1 and 2, you can add throw statements in each of methood signature if needed.

Please use the given test file to check the correctness of your implementation. Note that the test cases do not validate the correctness of your JSON or XML files. You may want to validate the correctness of file format with additional test cases.

## Submission Guideline

* This is an individual task. You can discuss with your colleagues how to solve the tasks, but never copy their solutions.
* Assignment deadline: Friday, 27 September 2019, 11:55 PM
* Submission mode: Electronic, via Wattle (Lab 6)
* Submission format (IMPORTANT):
  * Upload your final version of java files to Wattle.
  * You should upload `BookCollection.java` to Wattle submission page (1 java file).
  * Do not change the structure of the partial code including class names and package structure.
  * You may use standard libraries to complete the tasks, but it is not allowed to use external libraries.
    * Do not import packages outside of the standard java SE package. The list of available packages can be found here: [https://docs.oracle.com/en/java/javase/12/docs/api/index.html](https://docs.oracle.com/en/java/javase/12/docs/api/index.html)
    * Some IDEs (such as NetBeans) will automatically import some libraries. Please remove these libraries before submit.
  * Violation of the submission format will have their assignment not evaluated by an autograder and get zero marks.
