package comp1110.lectures.J08;

public class Projector {
    String brand;
    LectureTheatre theatre;

    Projector(String brand, LectureTheatre theatre) {
        this.brand = brand;
        this.theatre = theatre;
    }

    @Override
    public String toString() {
        return brand + " in room " + theatre.name;
    }
}
