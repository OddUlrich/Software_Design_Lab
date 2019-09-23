# COMP1110 Lab 2

## Before the Lab

* Complete your [personal journal](https://gitlab.cecs.anu.edu.au/comp1110/comp1110-labs/blob/master/journal.md) for week 2, commit it and push at least 5 minutes prior to the lab.

* Next week (week 3) will be the [first lab test](https://cs.anu.edu.au/courses/comp1110/assessments/labtest1/).You will still need to complete your personal journal for week 3, commit and push 5 minutes prior to the lab test.

* Make sure you've covered the material for modules [**J5**](https://cs.anu.edu.au/courses/comp1110/lectures/java/#J5) (Selection) and [**J6**](https://cs.anu.edu.au/courses/comp1110/lectures/java/#J6) (Iteration)

## Purpose

In this lab you will learn a little more about GitLab before you write,
test, and debug some small, *imperative* Java programs.   

All of the exercises are done with your labs
repo, and the Java exercises are done in IntelliJ.   You may work on your own
computer, but you will need to share your
work with your tutor and discuss it with them in your lab.

**It is essential that you complete this lab and have a tutor mark it off during the lab**.

## Introduce yourself to someone else in the lab!

If you have a question or a problem during a lab, chances are someone else is having the same issue. If you get stuck, we encourage you to discuss with other people in your lab as well as your tutor.
There will also be a few chances to discuss concepts and compare solutions with others.

Introduce yourself to someone else in your lab now-it'll help you later!
## GitLab Task

1.  **Pulling upstream commits in GitLab**

	Remember that your labs and assignment repos are *forks*; your own variation
	of some other repo.   Once you've made the fork, your repo is independent
	and won't see any subsequent changes to the repo from which your fork was
	taken.

	Sometimes it is valuable to be able to pull changes from the repo from which
	you originally forked, and apply those changes to your repo.   In the case
	of our class, this is true if the master repo is updated with corrections
	or improvements.  In such cases, it would be good if you could relatively
	easily merge those changes into your own repo.   In this lab exercise you
	will learn how to do that.

	The [terminology](http://stackoverflow.com/questions/2739376/definition-of-downstream-and-upstream)
	of an *upstream* repo is often used to refer to the one from which a repo
	is derived.   In your case, the *upstream* repo is the one from which your
	repo was forked (one created by your lecturer).  Git has [advanced](https://git-scm.com/book/en/v2/Git-Basics-Working-with-Remotes)
	mechanisms for pulling changes from an upstream repo; we'll just look at
	something quite specific in this lab.

	You should compete the following exercise.   In these steps you will update
	your *personal* comp1110-labs repo with some changes.

	1. Open your comp1110-labs repo in IntelliJ.
	2. If you have any uncommitted changes, commit them before starting the upstream pull (VCS -> Commit Changes...).
	3. Select VCS -> Git -> Remotes...
	4. Select the "+" button to add the comp1110 labs repo as a remote upstream repo
	(Name: `upstream` URL:`https://gitlab.cecs.anu.edu.au/comp1110/comp1110-labs.git`). Select OK.
	5. Prepare to pull changes (VCS -> Git -> Pull...).
	6. Use the drop-down menu to change the 'Remote' to be
	`upstream(https://gitlab.cecs.anu.edu.au/comp1110/comp1110-labs.git)`.
	7. Refresh the list of branches by clicking the refresh button to the right of the dropdown.
	8. Select the `upstream/master` branch.
	9. Click `Pull`.  This should behave just like a merge from your last lab
	exercise, and work without further interaction.   If not, you may need to
	merge, in which case you should consult your tutor if you're unsure what
	to do.
	10. Bring up the `Version Control` panel at the bottom of your IntelliJ
	window, and select `Log`, and notice that you now have new changes in your history.
	10. `Push` your merge (VCS -> Git -> Push...)

After you pull remote commits into your repo, commit, and push them, they will
be visible to all clones of your repo.   Go to the GitLab web page for your labs
repo, and use the `Commits` and `Graph` menu options (on the top-center menu) to
see how the changes you pulled have been integrated into your own repo.
 
You can also pull your changes into other clones of your labs repo (such as on your
home computer) using the process described above.

Once you have completed this task, close the associated issue (#9) with a
suitable comment.

You can **repeat the above exercise for the homework and assignment repos**.  The
steps are the same, but you need to change the repo name (in the obvious way)
in steps 1, 3, and 5 (using `comp1110-homework` and `comp1110-ass1` respectively, instead
of `comp1110-labs`).

## Imperative Coding Tasks

The following programming tasks exercise simple *imperative* programming,
demonstrating **sequence**, **selection**, and **repetition**.  We will start
*object-oriented* programming in the next lab.



1. **Countries**
    
    **A. Create a new class**
    
    Inside the package `comp1110.lab2` make a new class `Countries`. Add a main method to this class (Remember: use `psvm` as the shortcut to create the main method). 
    
    **B. Create an array**
    
    Inside your main method create an array of `String`s that has the names of the following
    countries: Germany, Argentina, Netherlands, Brazil.
   
    **C. Select elements of the sequence**

    Print out the elements of the array on separate lines (you do not need to use a loop for this,
    just use fixed indices into the array).
    
    **D. Test your program**
    
    Test your program using the `L2 Countries` test. 
    
    If it fails tests you'll have to debug your code. Try:
    
    Read through the tests you failed-the error will say exactly what failed (e.g. 'Did not print Germany')
    
    Read through your code- is everything labelled correctly? Are there any typos?
    
    Reread the instructions-are you doing exactly what the instructions ask?
    
    Try running the code on its own-without the tests. What are you expecting to happen? What actually happens? (Right click on the class and select `run` to run the main method of your class.)
    
    Add some print statements to your code (remember-the shortcut for the print function is `sout`). Try printing variables before and after you change something.
    What do you expect your code to be printing at each stage? What is actually printing? 
    
    Ask someone around you- how did they get the solution? What have they done differently?
    
    Ask your tutor!
    


**Discuss with someone else** If you were doing the same thing using a loop, how would you do this? You don't need to discuss the code exactly, but identify the **sequence**, **selection** and **iteration** steps.

2. **Reverse**

    **A. Create a new class**
    
    Make a new class `Reverse` within the `comp1110.lab2` package. Write your code within the main method of the class.
   
    **B. Iterate over sequence**

    Using a `while` loop, write a program that prints (on separate lines) the numbers
    between and including 10 and 30 in reverse order. i.e. the program should
    print on separate lines: 30 29 28 ... 10.
    
    **C. Test & debug your program**
    
    Test your program using the `L2 Reverse` test.


**Discuss with someone in lab** If you changed the `while` to a `do while` (Just added the word 'do' to your code), how would the output change? How many numbers would you expect to be printed?)
Is this the same for both your code and theirs? Have you solved the problem the same way?


3. **Triangle**
    
    **A.Create a class**

    Create a class `Triangle` within the `comp1110.lab2` package that draws
    simple triangles using ASCII characters and prints to standard output. 
    Write your code within the main method of the class.
    
    **B. Read integer from console**
    
    The program should read in from the console an integer which represents the
    height (in characters) of the triangle. 
    
    **C. Draw the triangle**
    
    Use the `*` character to draw the resulting triangle. 
    The first line should have one`*`, the second will have
    a `*` followed by one space, followed by a `*`, the third will have three
    spaces, the fourth will have five, etc. The last line will have (height x 2) - 1
    `*`'s. For example, for an input of 5, the output might look like this
    (here a '_' is drawn to represent a space character):
```
____*
___*_*
__*___*
_*_____*
*********
```
    **D. Test & debug your program**
    
    Test your program using the `L2 TriangleTest` test.
    

    
**Discuss with someone in your lab:** Have a look at your partner's code-without testing it first, discuss what you think will happen if you run the code with `-1` as the input?


4. **Commit and push your work**

    Commit your changes locally (navigate through `VCS` and select `Commit changes...` (*or* press `Ctrl + K`). Write a commit message that says: `Done with Lab 2!`. Leave everything else to its defaults.

    Push your changes, which will allow your tutor to see them (`VCS` -> `Git` -> `Push` (*or* `Crl + Shift + K`).

    Close the associated issue (#10) with a suitable comment.
    
    Get your tutor to mark off your work.
    


## Prepare for the lab test

If you finish early, use any spare time you have to do further preparation
for [lab test 1](https://cs.anu.edu.au/courses/comp1110/assessments/labtest1/). You are encouraged to ask your tutor
for help in understanding how to complete any of those questions.

## Extension

Note: Extension exercises are **not** assessable- if you need to prepare for a lab exam or work on a group assignment, do that first.
Extension exercises are just here to give you some extra problems to extend the lab work.


**Use the `break` keyword**

Create an array that contains {11,22,33,44,5,66,77,88,99,100} 

Using a loop containing the `break` keyword, write a program that will:

* print 5 and nothing else if the array contains the number 5.

* print the sum of the elements in the array and nothing else if the array doesn't contain the number 5.

When you run the code it should print `5`. Remove the 5 from the array-your program should now print `540`.

**Use the `continue` keyword**


Using the `continue` keyword, write a program to add all the numbers between 1 and 100 (inclusive) but skip multiples of 3 (3,6,9,12...)
So your code should calculate 1+2+4+5+7+8+10+11...+98+100.

Hint: If your code is working correctly, the sum should be `3367`.



