
public class MarkGrade {
	Grade grade;
	Integer mark;

	public MarkGrade(Integer mark, Grade grade) {
		this.grade = grade;
		this.mark = mark;
	}
	
	@Override
	public boolean equals(Object obj) {
		MarkGrade mg = (MarkGrade) obj;
		return mg.grade == grade && mg.mark == mark;
	}
	
	@Override
	public String toString() {
		return mark + " " + grade;
	}
}
