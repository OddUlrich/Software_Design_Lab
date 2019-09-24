package comp1110.lectures.J08;

public class LectureTheatreStaticNested {
    public static class Projector {
        String brand;
        LectureTheatreStaticNested theatre;

        Projector(String brand, LectureTheatreStaticNested theatre) {
            this.brand = brand;
            this.theatre = theatre;
        }

        @Override
        public String toString() {
            return brand + " in room " + theatre.name;
        }
    }

    String name;
    Projector projector;

    LectureTheatreStaticNested(String name, String projectorBrand) {
        this.name = name;
        this.projector = new Projector(projectorBrand, this);
    }

    @Override
    public String toString() {
        return name + " has a projector: " + projector.toString();
    }

}
