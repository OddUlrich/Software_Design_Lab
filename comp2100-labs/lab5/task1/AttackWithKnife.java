public class AttackWithKnife implements AttackBehaviour {

    public String attack(int power) {
        return "Attack with knife, Damage:" + Integer.toString(power*2);
    }

}