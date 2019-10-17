
## JUnit Testing

> The goal of this assignment is to give students an idea on how assessable items will be evaluated throughout the remaining semester. Most of the lab-related activities and final exam will be auto-graded via test cases.

JUnit provides a simple way of creating unit tests for an application you are developing.  This assignment involves getting some practice at using JUnit.

7 versions of the a `calculateMark` method have been written, _only one is correct_.  The task for this part of the workshop is to write JUnit tests to determine which of these implementations are incorrect.
The code for this these is available via the testing project at: 
[calculatemark](https://gitlab.cecs.anu.edu.au/u1009226/comp2100-labs/tree/master/assignment1)

Noting this `calculateMark` function is used for some previous version of the course, the formula for calculating marks for this coruse this year are a little different. 

Now your job is to do the following:

* Download a copy of the code BUT DO NOT READ the code for the different implementations (i.e. the code in `MarkCalculator1.java, MarkCalculator2.java`, ....). 

* Read `MarkCalculator.java`, this includes an interface and a comment which gives a specification of exactly what the `calculateMark` method does. Identify test cases from the interface specification.

* Read `MarkCalculatorTest.java`, this implements base JUnit testing framework with `ParameterizedTest`. Implement a set of test methods and cases, identified in the previous step, that aims to uncover errors in the implementation (black box testing) into `MarkCalculatorTest.java`.

Which implementations passed your tests?

* NOW read over the code and see if you can spot any errors (code review).  Create some other test cases based on being able to view the code (white box testing).  What is the minimum number of tests case that are path complete for the different implementations?

* Which implementation now passes your tests? Did your code review spot any errors?  Which implementation is exactly correct?

Now which implementations passed your tests?

### Grading policy
* Full mark will be given if your test cases can identify all 6 wrong implementations of MarkCalculator.
* Otherwise, you will get 0.33 mark for each of wrong implementation identified by test cases.

## Assignment Submission

* Assignment deadline: **Friday, 9nd August, 23:59**
* Submission mode: Electronic, via Wattle ([`Individual Assignment`](https://wattlecourses.anu.edu.au/mod/assign/view.php?id=1721415))
* Submission format (**IMPORTANT**):
  1. Upload your final version of `MarkCalculatorTest.java` to Wattle. 
    * __Do not change file name.__ 
    * __Do not upload any other files.__
  2. __Violation of the submission format will__ have their assignment not evaluated by an autograder and __get zero marks.__

## Note

__This is an individual assignment.__ Please do not collaborate with other colleagues. This is very important, as any breach of this needs to be investigated and reported. You are much better off not doing this assignment then copying a small part of code your and risking academic misconduct.

