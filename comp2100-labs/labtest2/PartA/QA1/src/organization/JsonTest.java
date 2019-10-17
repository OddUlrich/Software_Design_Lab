package organization;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JsonTest {

    /**
     *  Junit test for load and save json
     *
     *  Note: all the test case data is synthetic, any similarity to real persons or places is entirely coincidental
     */

    // global variables
    private final String SIMPLEJSON = "simpleOrg.json";
    private final String COMPLEXJSON = "complexOrg.json";

    private static Organization simpleOrg;
    private static Organization complexOrg;

    @BeforeClass
    public static void generateTestCase(){

        // generate test cases for simple json
        // | Jane Done
        // | Alice Smith
        // | Cody Anderson
        List<Person> members = new ArrayList<>();
        members.add(new Person("Jane", "Done"));
        members.add(new Person("Alice", "Smith"));
        members.add(new Person("Cody", "Anderson"));
        simpleOrg = new Organization(members);

        // generate test cases for difficult json
        // | Jane Done + child:
        //              | Anderson
        // | Cody Smith + children:
        //              | Bob Smith
        //              | Alice Smith + child
        //                           | Jame Smith
        members = new ArrayList<>();
        members.add(new Person("Jane", "Done", new Person("Anderson", "Done")));
        List<Person> children = new ArrayList<>();
        children.add(new Person("Alice", "Smith", new Person("Jame", "Smith")));
        children.add(new Person("Bob", "Smith"));
        members.add(new Person("Cody", "Smith", children));
        complexOrg = new Organization(members);
    }

    @Test
    public void testSimple(){
        // save to the specific file
        File outputFile = new File(SIMPLEJSON);

        // not exits file yet
        if (outputFile.exists()) {
            outputFile.delete();
        }

        simpleOrg.saveToJson(SIMPLEJSON);

        // test the Json is saved or not
        assertTrue(outputFile.exists());

        Organization simpleOrgFromFile = Organization.loadFromJson(SIMPLEJSON);
        assertTrue(equalOrg(simpleOrg, simpleOrgFromFile));
    }

    @Test
    public void testComplex(){
        // save to the specific file
        File outputFile = new File(COMPLEXJSON);

        // not exits file yet
        if (outputFile.exists()) {
            outputFile.delete();
        }

        complexOrg.saveToJson(COMPLEXJSON);

        // test the Json is saved or not
        assertTrue(outputFile.exists());

        Organization complexOrgFromFile = Organization.loadFromJson(COMPLEXJSON);
        assertTrue(equalOrg(complexOrg, complexOrgFromFile));
    }

    
    public static boolean equalOrg(Organization source, Organization target) {
    	boolean result;
    	for(int i=0; i<source.members.size(); i++) {
    		result = compareMember(source.members.get(i), target.members.get(i));
    		if(!result) {
    			return false;
    		}
    	}
		return true;
    }

	private static boolean compareMember(Person person, Person person2) {
		boolean result;
		if(!person.equals(person2)) {
			return false;
		}else {
			for(int i=0; i<person.numberOfChildren(); i++) {
				result = compareMember(person.children.get(i), person2.children.get(i));
	    		if(!result) {
	    			return false;
	    		}				
			}
		}
		return true;
	}
}
