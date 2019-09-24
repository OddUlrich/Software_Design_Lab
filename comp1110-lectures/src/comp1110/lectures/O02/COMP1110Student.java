package comp1110.lectures.O02;

public class COMP1110Student extends Student implements Comparable<Student> {

    private int ass1;
    private int ass2;
    private int ce;
    private int lab1;
    private int lab2;
    private int mse;
    private int exam;


    public COMP1110Student(String name, int age, String uid, int ass1, int ass2, int ce, int lab1, int lab2, int mse, int exam) {
        super(name, age, uid);
        this.ass1 = ass1;
        this.ass2 = ass2;
        this.ce = ce;
        this.lab1 = lab1;
        this.lab2 = lab2;
        this.mse = mse;
        this.exam = exam;
    }

    private int redeem(int mark, int max) {
        return Math.max(mark, (max * exam) / 100);
    }

    public int mark() {
        return redeem(ass1, 5) + ass2 + redeem(ce, 5) + redeem(lab1, 5) + redeem(lab2, 5) + redeem(mse, 5) + exam;
    }

    public Grade getGrade() {
        int finalMark = mark();
        Grade finalGrade = Grade.fromMark(finalMark);
        return finalGrade;
    }

    @Override
    public int compareTo(Student o) {
        int myUid = Integer.parseInt(this.getUID().substring(1));
        int otherUid = Integer.parseInt(o.getUID().substring(1));

        return myUid - otherUid;
    }
}
