package comp1110.lectures.O03;

public class Interfaces {
    public static void main(String[] args) {
        FunnelwebSpider charlotte = new FunnelwebSpider();
        Toxic somethingToxic = new HydrogenCyanide();
        Borax powder = new Borax();
        Dog leslie = new Dog();

        Toxic[] toxicThings = new Toxic[3];
        toxicThings[0] = charlotte;
        toxicThings[1] = somethingToxic;
        toxicThings[2] = powder;

        for (Toxic t : toxicThings) {
            System.out.println(t + " is deadly?: " + t.isLethalToHumans());
        }

    }
}
