package comp1110.lectures.O04;

public class Toad extends Amphibian implements Poisonous {
    public Toad(String name) {
        super(name);
    }

    @Override
    public boolean isLethalToHumans() {
        return false;
    }
}
