package comp1110.lab3;

public class Bucket {
    protected double capacity;
    protected double contents;
    protected StackableBucket innerBucket = null;

    Bucket(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getContents() {
        return contents;
    }

    public double empty() {
        double tmp = contents;
        contents = 0;
        return tmp;
    }

    public void add(double amount) {
        contents += amount;
        if (contents > capacity) {
            contents = capacity;
        }
    }

    public static void main(String[] args) {
        Bucket big = new Bucket(10.0);
        Bucket small = new Bucket(1.0);

        big.add(20.0);
        small.add(20.0);
        System.out.println(big.getContents());
        System.out.println(small.getContents());

        big.empty();
        System.out.println(big.getContents());

        big.add(small.empty());
        System.out.println(small.getContents());
        System.out.println(big.getContents());
    }
}
