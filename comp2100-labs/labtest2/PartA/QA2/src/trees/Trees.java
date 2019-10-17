package trees;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import trees.properties.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of trees.
 */
public class Trees extends ArrayList<Tree> {

    final static String TREES = "trees";
    final static String TREE = "tree";
    final static String KIND = "kind";
    final static String AGE = "age";
    final static String DIMENSIONS = "dimensions";
    final static String DIAMETER = "diameter";
    final static String HEIGHT = "height";
    final static String COLOR = "color";

    /**
     * Saves this collection to the specified file.
     *
     * @param file The file to save to.
     * @throws Exception Any errors that occur during execution.
     */
    public void saveToFile(File file) throws Exception {
        // TODO: You need to implement this function using some XML exporter!

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();


            Element docNode = doc.createElement(TREES);
            doc.appendChild(docNode);

            for (Tree tree : this) {
                Element treeEl = doc.createElement(TREE);
                treeEl.setAttribute(KIND, tree.getKind());

                Property[] arrProperty = tree.getProperties();

                for (Property prop: arrProperty) {
                    String name = prop.getName();

                    if (name.equals(AGE)) {
                        Element propertyEl = doc.createElement(AGE);

                        int age = ((Age) prop).getAge();
                        propertyEl.setAttribute(name, String.valueOf(age));
                        treeEl.appendChild(propertyEl);

                    } else if (name.equals(COLOR)) {
                        Element propertyEl = doc.createElement(COLOR);
                        String color = ((Color) prop).getColor();
                        propertyEl.setAttribute(name, color);
                        treeEl.appendChild(propertyEl);

                    } else if (name.equals(DIMENSIONS)) {
                        Element propertyEl = doc.createElement(DIMENSIONS);
                        int diameter = ((Dimensions) prop).getDiameter();
                        propertyEl.setAttribute(DIAMETER, String.valueOf(diameter));
                        int height = ((Dimensions) prop).getHeight();
                        propertyEl.setAttribute(HEIGHT, String.valueOf(height));
                        treeEl.appendChild(propertyEl);
                    }
                }

                docNode.appendChild(treeEl);
            }

            // Transform document into file with stream.
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult fOut = new StreamResult(file);

            tf.transform(source, fOut);

        } catch (TransformerConfigurationException e) {
            System.out.println("[Transformer configuration saving exception]");
            e.printStackTrace();
        } catch (TransformerException e) {
            System.out.println("[Transformer saving exception]");
            e.printStackTrace();
        }
    }

    /**
     * Loads this collection from the specified file.
     *
     * @param file The file to read from.
     * @throws Exception Any errors that occur during execution.
     */
    public static Trees loadFromFile(File file) throws Exception {
        // TODO: You need to implement this function using some XML importer!

        Trees trees = new Trees();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            Node root = doc.getFirstChild();
            NodeList nl = root.getChildNodes();

            for (int i = 0; i < nl.getLength(); i++) {
                Node treeNode = nl.item(i);

                Tree tree;

                if (treeNode.getNodeType() == Node.ELEMENT_NODE) {
                    String kind = treeNode.getAttributes().getNamedItem(KIND).getNodeValue();

                    NodeList propNl = treeNode.getChildNodes();

                    int length = propNl.getLength();
                    Property[] arrProp = new Property[length];

                    for (int j = 0; j < length; j++) {
                        Node propNode = propNl.item(j);
                        String nodeName = propNode.getNodeName();

                        if (nodeName.equals(AGE)) {
                            int ageVal = Integer.parseInt(propNode.getAttributes().getNamedItem(AGE).getNodeValue());
                            Age age = new Age(ageVal);

                            arrProp[j] = age;

                        } else if (nodeName.equals(DIMENSIONS)) {
                            int diameterVal = Integer.parseInt(propNode.getAttributes().getNamedItem(DIAMETER).getNodeValue());
                            int heightVal = Integer.parseInt(propNode.getAttributes().getNamedItem(HEIGHT).getNodeValue());

                            Dimensions dimensions = new Dimensions(diameterVal, heightVal);

                            arrProp[j] = dimensions;

                        } else if (nodeName.equals(COLOR)) {
                            String colorStr = propNode.getAttributes().getNamedItem(COLOR).getNodeValue();
                            Color color = new Color(colorStr);

                            arrProp[j] = color;
                        }
                    }

                    tree = new Tree(kind, arrProp);
                    trees.add(tree);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return trees;
    }
}
