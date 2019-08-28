public class MagicianDuck extends Duck {

    public MagicianDuck(AttackBehaviour attack, FlyBehaviour fly) {
        this.attackBehaviour = attack;
        this.flyBehaviour = fly;
    }

    public String display() {
        return "Feel the power of magic!";
    }
}