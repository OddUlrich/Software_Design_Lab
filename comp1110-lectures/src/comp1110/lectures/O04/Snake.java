package comp1110.lectures.O04;

public class Snake extends Reptile implements Poisonous {
    public Snake(String name) {
        super(name);
    }

    @Override
    public boolean isLethalToHumans() {
        return true;
    }
}
