package comp1110.lectures.C03;

public class Person {
    String firstName;
    String familyName;
    int age;

    @Override
    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person p = (Person)o;
            return firstName.equals(p.firstName)
                    && familyName.equals(p.familyName)
                    && age == p.age;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int sum = 0;
        int index = 0;
        for (char c : firstName.toCharArray()) {
            // what if characters add to same value?
            sum += c * index++;
        }
        for (char c : familyName.toCharArray()) {
            // what if characters add to same value?
            sum += c * index++;
        }
        sum += age * index;
        return sum;
    }

    public static void main(String[] args) {
        Person josh = new Person();
        josh.firstName = "Josh";
        josh.familyName = "Milthorpe";
        josh.age = 40;
        Person josh2 = new Person();
        josh2.firstName = "Josh";
        josh2.familyName = "Milthorpe";
        josh2.age = 40;
        System.out.println(josh.equals(josh2));
        System.out.println(josh.hashCode());
        System.out.println(josh2.hashCode());
    }
}
