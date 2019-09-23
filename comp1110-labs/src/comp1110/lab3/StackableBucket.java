package comp1110.lab3;

public class StackableBucket extends Bucket {
    private String name;

    StackableBucket(double capacity, String name) {
        super(capacity);
        this.capacity = capacity;
        this.name = name;
    }

    public String getInnerBucket() {
        if (innerBucket == null) {
            return null;
        } else {
            return innerBucket.name;
        }
    }

    public void setInnerBuckets(StackableBucket smallerBucket) {
        if (smallerBucket.capacity > capacity) {
            System.out.println("Too large to stack!");
        } else {
            if (innerBucket == null) {
                innerBucket = smallerBucket;
            } else {
                if (innerBucket.capacity > smallerBucket.capacity) {
                    innerBucket.innerBucket = smallerBucket;
                }
            }
        }
    }

    public void unstackBuckets() {
        if (innerBucket != null) {
            innerBucket.unstackBuckets();
            innerBucket = null;
        }
    }

    public static void main(String[] args) {
        StackableBucket big = new StackableBucket(10.0, "big");
        StackableBucket small = new StackableBucket(1.0, "small");
        StackableBucket medium = new StackableBucket(5.0, "medium");

        medium.setInnerBuckets(big);

        big.setInnerBuckets(medium);
        System.out.println(big.getInnerBucket());

        big.setInnerBuckets(small);
        System.out.println(big.getInnerBucket());
        System.out.println(medium.getInnerBucket());

        big.unstackBuckets();
        System.out.println(big.getInnerBucket());
        System.out.println(medium.getInnerBucket());
    }
}
