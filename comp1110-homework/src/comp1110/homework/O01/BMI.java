package comp1110.homework.O01;

public class BMI {
    private String name;
    private double height;
    private double weight;

    BMI(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public double getBMI() {
        double bmi;
        bmi = this.weight / Math.pow(this.height, 2.0);
        return bmi;
    }

    public String toString() {
        return (this.name + " is "
                          + this.height + "m tall and is "
                          + this.weight + "Kg and has a BMI of "
                          + getBMI() + "Kg/m^2");
    }
}
