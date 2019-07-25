/**
 * The calculateMark method must return the MarkGrade based on the following:
 * 
 * + the "lab", "assignment1" and "assignment2" marks are between 0 and 10 (inclusive).
 * + the "finalexam" is between 0 and 100 (inclusive).
 * 
 * If any of these components are not within the expected range 
 * then an ComponentOutOfRangeException is thrown.
 * 
 * If a student does not attend the final exam (attendedFinal will be false) 
 * then a grade of NCN should be given and
 * the final mark must be set to null.
 * 
 * The lab mark is worth 10% of the final grade and marked out of 10, 
 * the assignments are worth 15% each of the final grade and marked out of 10, and
 * the final exam is worth 60% and marked out of 100.  These marks are combined EXACTLY 
 * and then rounded to produce an integer out of 100 (round up for values exactly 
 * half way between integer values).   Once this is done
 * the grade can be determined using the following ranges:
 *  + 0 ~ 44 : N
 *  + 45 ~ 49 : PX
 *  + 50 ~ 59 : P
 *  + 60 ~ 69 : C
 *  + 70 ~ 79 : D
 *  + 80 ~ 100 : HD
 *  
 * Note that there will also be some scaling involved in calculating the final mark in this actual course, 
 * however, lets not worry about this for this lab exercise. 
 *  
 */
public interface MarkCalculator {
    MarkGrade calculateMark(int lab, int assignment1, int assignment2, int finalexam, boolean attendedFinal) throws ComponentOutOfRangeException; 
}
