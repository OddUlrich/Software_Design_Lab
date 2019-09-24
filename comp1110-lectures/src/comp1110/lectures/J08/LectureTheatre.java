package comp1110.lectures.J08;

public class LectureTheatre {
    String name;
    Projector projector;

    LectureTheatre(String name, String projectorBrand) {
        this.name = name;
        this.projector = new Projector(projectorBrand, this);
    }

    @Override
    public String toString() {
        return name + " has a projector: " + projector.toString();
    }
}
