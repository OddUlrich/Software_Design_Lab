package organization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *   Java Class Person, with 3 attributes
 *      | String firstName: first name of a person  (e.g Charlie)
 *      | String lastName: last name of a person  (e.g. Smith)
 *      | List<Person> children: child(ren) of a person
 *                     | Empty list: do not have children  e.g. []
 *                     | Else: has at least one child   e.g. [Person1, Person2, ..]
 *
 *   Note: please do not modify any component in this class
 */
public class Person {

    public String firstName;
    public String lastName;
    public List<Person> children;

    /**
     *  java class Person constructor
     */
    public Person(){
        firstName = null;
        lastName = null;
        // empty list
        this.children=new ArrayList<>();
    }

    /**
     *  java class Person constructor:
     *      represents a person does not have children
     */
    public Person(String firstname, String lastname) {
        this.firstName = firstname;
        this.lastName = lastname;
        // empty list
        this.children=new ArrayList<>();
    }

    /**
     *  java class Person constructor:
     *      represents a person has one child
     */
    public Person(String firstname, String lastname, Person child) {
        this.firstName = firstname;
        this.lastName = lastname;
        // convert it into list type
        this.children = Collections.singletonList(child);
    }

    /**
     *  java class Person constructor:
     *      represents a person has more than one children
     */
    public Person(String firstname, String lastname, List<Person> children) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.children=children;
    }

    /**
     * return whether this person has children or not
     *     | true: this person has at least one child
     *     | false: this person does not have children
     */
    public boolean hasChildren(){
        return this.children.size() > 0;
    }

    /**
     * return the number of child(ren) with this person
     */
    public int numberOfChildren(){
        return this.children.size();
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof Person &&
                ((Person) obj).firstName.equals(this.firstName) &&
                ( this.lastName == null || ((Person) obj).lastName.equals(this.lastName)) &&
                ( this.children == null || ((Person) obj).children.equals(this.children));
    }

    @Override
    public String toString() {
        StringBuilder re = new StringBuilder();

        re.append("\"");

        if (this.firstName != null){
            re.append(this.firstName);
            if (this.lastName != null){
                re.append(" ");
            }
        }
        if (this.lastName != null){
            re.append(this.lastName);
        }
        
        if (this.hasChildren()){
            re.append(" with ").append(this.numberOfChildren()).append(" child(ren):");
            re.append(this.children);
        }

        re.append("\"");
        return re.toString();
    }

}
