public class AttackWithStaff implements AttackBehaviour {

    public String attack(int power) {
        return "Attack with staff, Damage:" + Integer.toString(power);
    }

}