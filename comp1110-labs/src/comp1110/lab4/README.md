# COMP1110 Lab 4

## Before the Lab

* Complete your personal journal for week 5, commit and push at least 5 minutes before your lab.

* Make sure you've covered the modules [**S2**](https://cs.anu.edu.au/courses/comp1110/lectures/se/#S2)(Branches and Merging) and [**J4**](https://cs.anu.edu.au/courses/comp1110/lectures/java/#J4)(Random)

## Purpose

In this lab you will complete a git exercise in which you will practice using git in a group setting, and you will develop a more complex Java program.

**It is essential that you complete this lab and have a tutor mark it off during the lab**.

## GitLab Upstream Pulls for Assignement

**One** group member should do the following.   Once complete, each of the
other group members can pull the changes.

0. Quit IntelliJ if you have it open.
1. Start a terminal (use the menu at top left)
2. Change directory (`cd`) into the folder that contains your intelliJ
projects (`cd IdeaProjects`)
3. Change directory (`cd`) into your assignment repo (`cd comp1110-ass2-<yourgroup>`
or `cd comp1140-ass2-<yourgroup>`).
4. Display the currently known remote repos for the project (`git remote -v`).
5. Add the comp1110 assignment2 repo as a remote upstream repo (`git remote
add upstream https://gitlab.cecs.anu.edu.au/comp1110/comp1110-ass2.git` or
`git remote add upstream https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2.git`).
6. Now open your comp1110-ass2 (or comp1140-ass2) repo in IntelliJ
7. Prepare to pull changes (VCS -> Git -> Pull...)
8. Use the drop-down menu to change the 'Remote' to be
`upstream(https://gitlab.cecs.anu.edu.au/comp1110/comp1110-ass2.git)'
or 'upstream(https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2.git)`.
9. Refresh the list of branches by clicking the refresh button to the right
of the dropdown.
10. Select the `upstream/master` branch.
11. Click `Pull`.  This should behave just like a merge from your last lab
exercise, and work without further interaction.   If not, you may need to
merge, in which case you should consult your tutor if you're unsure what to
do.
12. Bring up the `Version Control` panel at the bottom of your IntelliJ
window, and select `Log`, and notice that you now have some new changes in
your history.
13. Commit and push your change.
14. Your group members can now pull your change to their clones of the group
repo.

You can repeat these steps to pull further changes into your forks of class
repos.   You only need to perform the steps on one of your clones and then
the changes will be visible (after pulling) at each clone of your repo.
You can do this on the lab computers (as in this exercise) or on your home
computer.   If you have linux or MacOS on your computer, the process is
identical to the above.   If you have windows on your computer, you will
need to use [git bash](https://git-for-windows.github.io) rather than a
terminal to set your upstream remote (steps 2-6).

## Git groupwork

This is a simple exercise that explores most of the git skills that you are
expected to use in COMP1110. This exercise was demonstrated in a [howto video](https://cs.anu.edu.au/courses/comp1110/help/mp4/gitexercise.mp4).
If you have not seen the video, I strongly recommend you watch it. If you get
yourself into trouble doing this exercise, you may find it helpful to look at
the video again. In the [video](https://cs.anu.edu.au/courses/comp1110/help/mp4/gitexercise.mp4) I performed the role of all three characters
(Alice, Bob and Cindy). You should do this exercise in the lab with your group
members. If there are only two people in the group, you should be able to adapt
the exercise by delegating Cindy's activities to Alice (so you just have Alice
and Bob).

1. Before you start, **everyone** in the group must have a clone of your group
repo and an associated project in IntelliJ.  This will work best if you're
sitting nearby to each other.
	 
2.  **Alice** does the following (with the others watching...):
    
    **Create a new package and 2 new classes**
    * Create a new package, `gittest`.
	* Create two new Java classes, `Main` (which has an empty `main()` method), and `A`, which just has a `toString()`.  When IntelliJ prompts you to add the new classes to git, say yes.
    * If you neglected to add the new files when they were created, add them now.  You will notice that IntelliJ colors the classes red if you've not added them.  You can add them via `VCS` -> `git` -> `Add`, or via the keyboard shortcut `Ctrl+Alt+A`.  The files will appear green once added.
    
    **Commit and push new files classes to group repo**
    * Commit (`VCS` -> `Commit Changes...` or `Ctrl+K`), being sure to set the author's name to `Alice <u1234567@anu.edu.au>` (option at top right, replacing u1234567 with your UID).
    * Click `Commit`, choosing to `Commit and Push`.
	* You should see a little green notification dialogue at the bottome of the screen confirming that the push was successful.
		
3. **Bob** does the following (*without co-ordinating with Cindy*):
    
    **Pull changes from group repo**
    * Pull Alice's changes `VCS` -> `git` -> `Pull...`, leave the defaults and click `Pull`.
	* In the `Version Control` tool window (activate by clicking the little icon at the very bottom left of the IntelliJ window, and select `Version Control`), you should see Alice's changes.
    
    **Add a new class to group project**
    * Create a new Java class in the `gittest` package, `B` which just has a `toString()` method.  When prompted to add the new class to git, choose `Yes`.
	
    **Commit changes but do not push to group repo**
    * Commit (`Ctrl+K`), set the author to `Bob <u1234567@anu.edu.au>`, but when you click `Commit`, just choose commit, **do not** choose `Commit and Push...`
	* Do **not** push the change yet (we want to imagine Bob gets interrupted before pushing).
	
4. **Cindy** does the following (*without co-ordinating with Bob*):
    
    **Pull changes from group repo**
    * Pull Alice's changes `VCS` -> `git` -> `Pull...`, leave the defaults and click `Pull`.
	* In the `Version Control` tool window (activate by clicking the little icon at the very bottom left of the IntelliJ window, and select `Version Control`), you should see Alice's changes.
	
    **Add a new class to group project**
    * Create a new Java class in the `gittest` package, `C` which just has a `toString()` method.  When prompted to add the new class to git, choose `Yes`.
	
    **Commit changes and push to group repo**
    * Commit (`Ctrl+K`), set the author to `Cindy <u1234567@anu.edu.au>`, and when you click `Commit`, choose `Commit and Push...`
	* You should see a little green notification dialogue at the bottome of the screen confirming that the push was successful.
	
5. **Bob** now does the following:
    
    **Try to push your commit to group repo**
    * Try to push `Ctrl+Shift+K`, then click `Push`, and see an error message giving you the option to either intentionally push a new head, or merge. We will merge in the following steps.
	
    **Merge your changes with group repo**
    * Click `Merge`.
	* You should see a green notification saying that the Push was successful and that two commits were pushed.  The first of the two commits is the one that Bob did in step 3.   The second is an automatically generated one that captures the merge of Cindy and Bob's work.
    * View the log by clicking on the `Log` tab of the Version Control tool window.  You can see a graph showing the merge of Cindy's work with Bob's.

6. **Alice** and **Cindy** update (`Ctrl+T`, select `Merge` as the update type).  Now everyone should be in the same state. View history to ensure that this is so (`Team...`, `Show History`).

7. **Bob** now conducts an experiment, but doesn't want to mess things up for Alice and Cindy (because his idea may not necessarily work out), so he used a *branch*:
	
    **Create a new branch of your group repo**
    * Add a new branch named `BobsExperiment` (`VCS`, `Git`, `Branches...`, `New Branch...`).   Rules for branch names can be found [here](https://www.kernel.org/pub/software/scm/git/docs/git-check-ref-format.html).
	
    **Make changes to the group project, commit and push to your branch of group repo**
    * Make a simple change to class `B`, commit that change (`Ctrl+K`), being sure to set the author to Bob.
    * Make another simple change to class `B`, commit that change.
    * Push your changes.

8. **Everyone** does the following (*at the same time*):
    * Update project (`Ctrl+T`).
    * Look at the `Log` tab of the Version Control tool window.
    * Ensure that you are on the head of the master branch (i.e. not on Bob's experimental branch). The changeset you are on will be marked with the word `HEAD` in a yellow box. If you followed all of the steps, Alice and Cindy should have been the changeset where Bob did a merge (right before he made the branch). Bob will be on the wrong changeset, so he should move to the `master`: `VCS`, `Git`, `Branches`, `Local Branches`, and select `origin/master`.
	* Now (ignoring Bob's experiment), everyone should edit the main method of `Main` and add a line such as `A a = new A();`, changed suitably to reflect the author (eg `B` for Bob and `C` for Cindy).
	* Each person should now save their change and commit (**but don't yet push**) your change.  Remember to set your author name and a commit message.

9. **Alice** pushes her changes. There should be no error. 
	
10. **Bob** now tries to push his change, but will find a problem that he must now resolve. [Why can't you push? What's causing the problem?]
    * Try to push (`Ctrl+Shift+K`).
	
    **Manually merge changes with group repo**
    * You should get an error message, which you should read (you will resolve the problem by merging, so click `Merge`).
	* You will see a dialogue which presents you with three choices (`Accept Yours`, which would throw away Alice's work, `Accept Theirs`, which would throw away your work, or `Merge`, which we will do).  Click `Merge`.
	* You will see a window with three panes, the center shows the result, the left shows yours (Bob's), and the right shows theirs (Alice's).
	* Click the little `<<` icon next to Alice's change to bring it into the merged result.
	* Click the little `>>` icon next to your (Bob's) change to bring it into the merged result.
	* You will be asked whether you want to finish.  Click `Save and Finish`.
	* Push your result (`Ctrl+Shift+K`).

11.  **Cindy** now tries to push her change, but will run into the same problem as Bob.
    * Try to push (`Ctrl+Shift+K`).
	
    **Manually merge changes with group repo**
    * You should get the same error message as Bob, which you should read (you will resolve the problem by merging, so click `Merge`).
	* You will see a dialogue which presents you with three choices (`Accept Yours`, which would throw away Alice and Bob's work, `Accept Theirs`, which would throw away your work, or `Merge`, which we will do).  Click `Merge`.
	* You will see a window with three panes, the center shows the result, the left shows yours (Cindy's), and the right shows theirs (Alice and Bob's).
	* Click the little `<<` icon next to Alice and Bob's change to bring it into the merged result.
	* Click the little `>>` icon next to your (Cindy's) change to bring it into the merged result.
	* You will be asked whether you want to finish.  Click `Save and Finish`.
	* Push your result (`Ctrl+Shift+K`).

12. **Everyone** pulls and updates and should see the `Main` file with all three contributions.

That completes the group exercise.  Close the associated issue (#13).  The next exercise is an individual exercises.


## Random numbers

This exercise creates a simple imperative class (not object-oriented), that
prints out random numbers.

**Add a new class**

In the Java package `comp1110.lab4`, within your labs repo, create a new Java
class, `Dice`, that prints 10 randomly-generated integers. 

**Print randomly generated integers.**

Each time you run
the program it should, with very high probability, print a different set of
integers. The integers should be strictly between 9 and 16. That is, each
of the numbers will be in the set of numbers {10, 11, 12, 13, 14, 15}.  You 
should make use of the `java.util.Random` library, as discussed in lectures.

**Test your code**

Test your code using the provided test class `L4 DiceTest`.

Commit, push to git,close the issue (#14), and show your tutor.
	
## Prepare for lab test 2

Use any spare time you have to work on preparing for [lab test 2](https://cs.anu.edu.au/courses/comp1110/assessments/labtest2/) and working on your group assignment.

## Extension


**Background**

Professor [Richard P. Brent](https://researchers.anu.edu.au/researchers/brent-rp)(Emeritus professor at ANU!) made significant contributions to the field of random number generators, and created some algorithms to produce pseudo-random numbers.
[Further reading for those interested.](https://arxiv.org/abs/1004.3115)
**Write your own 'random' code**

As discussed in lectures, java.util.random gives you *pseudo-random* numbers- it looks random, but is created using a formula.

Without using java.util.random, write a code that will 'randomly' return an int in [1,2,3] every time you run it.

**Test your own 'random' code using collections**

Write some code to run your program 150 times, and collect the results. Read through the next steps and work out which collection type will be most useful here. (Array,list,set,map?)

To be *psuedo-random*, your code should:

- Print each number roughly the same amount of times. Using your collection, print how often each number came up-the closer to (50,50,50) the better!

- It should look random- if your code runs 1,2,3,1,2,3,1,2,3... that won't look random. Seperate the results into the first 50 (1-50), second 50 (51-100) and third 50 results (101-150).
How often is the same result in the same place- (e.g. Does the 1st result = 51st result = 101st result? How often does this happen?)

- Should contain all small sequences of numbers. Check that your results contain the following sets: (1,1,1), (1,2,3),(2,3,2),(3,2,1),(3,1,2).



How many different ways can you think of to create a 'random' number generator? You don't have to program more than one, but if you do try testing them against each other.
