public class RubberDuck extends Duck {

    public RubberDuck(AttackBehaviour attack, FlyBehaviour fly) {
        this.attackBehaviour = attack;
        this.flyBehaviour = fly;
    }

    public String display() {
        return "Rubber lover duck duck";
    }
}