package organization;

import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *   Java class Organization, including two attributes
 *      | String: organization name e.g. "CloudCoder"
 *      | List<Person>: a collection of Person class, representing all members in this organization  e.g.[Person1, Person2 ...]
 *
 */
public class Organization {
    public List<Person> members;

    /**
     *   constructor, create an empty organization
     */
    public Organization(){
        // create empty list
        members = new ArrayList<>();
    }

    /**
     *  constructor, crate an organization with specific name and members
     */
    public Organization(List<Person> members){
        this.members = members;
    }


    @Override
    public String toString() {
        return "Organization has " + members.size() + " members. They are " + members;
    }

// =========================== please implement loadFromJson and SaveToJson below ==============================
// Please follow the instructions and sample Json file to implement both save and load function below

    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";
    private final static String CHILDREN = "children";

    /**
     * Saves this organization information to the specified file.
     *
     * @param fileName The file to save to.
     */
    void saveToJson(String fileName){
        // TODO: You need to implement this function using JSON Simple library
        // You can define additional method if you need

        try {
            FileWriter fWriter = new FileWriter(fileName);
            JSONArray root;

            root = addChildrenToJsonObj(this.members);

            root.writeJSONString(fWriter);
            fWriter.close();

        } catch (IOException e) {
            System.err.println("[json file writer exception]");
            e.printStackTrace();
        }

        System.out.println(members.toString());
    }

    private JSONArray addChildrenToJsonObj(List<Person> personList) {
        JSONArray root = new JSONArray();

        for (Person person: personList) {
            JSONObject personNode = new JSONObject();

            personNode.put(FIRST_NAME, person.firstName);
            personNode.put(LAST_NAME, person.lastName);

            if (person.hasChildren()) {
                personNode.put(CHILDREN, addChildrenToJsonObj(person.children));
            }

            root.add(personNode);
        }

        return root;
    }

    /**
     *  Loads a specific organization from the  file.
     *
     * @param fileName The file to read from
     * @return Organization class
     */
    static Organization loadFromJson(String fileName){
        // TODO: You need to implement this function using JSON Simple library
        // You can define additional method if you need

        List<Person> members = new ArrayList<>();

        try {
            FileReader fReader = new FileReader(fileName);
            JSONParser jsonParser = new JSONParser();
            JSONArray root = (JSONArray) jsonParser.parse(fReader);

            members = loadChildrenFromObj(root);

        } catch (IOException e) {
            System.err.println("[json file reader exception]");
            e.printStackTrace();

        } catch (ParseException e) {
            System.err.println("[json file reader parse exception]");
            e.printStackTrace();
        }

        System.out.println(members.toString());

        return new Organization(members);
    }

    private static List<Person> loadChildrenFromObj(JSONArray root) {
        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < root.size(); i++) {
            JSONObject personNode = (JSONObject) root.get(i);
            Person person = new Person();

            person.firstName = (String) personNode.get(FIRST_NAME);
            person.lastName = (String) personNode.get(LAST_NAME);

            if (personNode.get(CHILDREN) != null) {
                person.children = loadChildrenFromObj((JSONArray) personNode.get(CHILDREN));
            }

            personList.add(person);
        }

        return personList;
    }

}
