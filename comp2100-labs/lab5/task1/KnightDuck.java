public class KnightDuck extends Duck {

    public KnightDuck(AttackBehaviour attack, FlyBehaviour fly) {
        this.attackBehaviour = attack;
        this.flyBehaviour = fly;
    }

    public String display() {
        return "I'm a mighty knight duck";
    }
}