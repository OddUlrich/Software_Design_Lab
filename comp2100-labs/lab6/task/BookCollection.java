import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// you need to import some xml libraries

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// import any standard library if needed
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A book collection holds 0 or more books in a collection.
 */
public class BookCollection {
    private List<Book> books;

    /**
     * Creates a new collection with no books by default.
     */
    public BookCollection() {
        books = new ArrayList<Book>();
    }

    /**
     * Creates a new book collection with the specified list of books pre-defined.
     *
     * @param books A books list.
     */
    public BookCollection(List<Book> books) {
        this.books = books;
    }

    /**
     * Returns the current list of books stored by this collection.
     *
     * @return A (mutable) list of books.
     */
    public List<Book> getList() {
        return books;
    }

    /**
     * Sets the list of books in this collection to the specified value.
     */
    public void setList(List<Book> books) {
        this.books = books;
    }

    /**
     * A simple human-readable toString implementation. Not particularly useful to save
     * to disk.
     *
     * @return A human-readable string for printing
     */
    @Override
    public String toString() {
        return books.stream()
                .map(book -> " - " + book.display() + "\n")
                .collect(Collectors.joining());
    }

    /**
     * Saves this collection to the specified "bespoke" file.
     *
     * @param file The path to a file.
     */
    public void saveToBespokeFile(File file) {
        // TODO: Implement this function yourself. The specific hierarchy is up to you,
        //       but it must be in a bespoke format and should match the
        //       load function.
        //
        // HINT: You may want to use serialisation on the list object. Serialize each
        //       field of book.
        //       You can add throws in method signature if needed

        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));

            for (Book book: books) {
                String line = book.title + ";"
                                    + book.authorName + ";"
                                    + book.yearReleased + ";"
                                    + book.bookGenre.name();

                bWriter.write(line);
                bWriter.newLine();
            }
            bWriter.close();

        } catch (IOException e) {
            System.err.println("Raise IO Exception.");
            e.printStackTrace();
        }
    }

    /**
     * Saves this collection to the specified JSON file.
     *
     * @param file The path to a file.
     */
    public void saveToJSONFile(File file) {
        // TODO: Implement this function yourself. The specific hierarchy is up to you,
        //       but it must be in a JSON format and should match the load function.
        //       You can add throws in method signature if needed

        final String TITLE = "title";
        final String AUTHOR_NAME = "author_ame";
        final String YEAR_RELEASED = "year_released";
        final String BOOK_GENRE = "book_genre";

        try {
            FileWriter fWriter = new FileWriter(file);
            JSONArray root = new JSONArray();

            for (Book book: books) {
                JSONObject bookNode = new JSONObject();

                bookNode.put(TITLE, book.title);
                bookNode.put(AUTHOR_NAME, book.authorName);
                bookNode.put(YEAR_RELEASED, Integer.toString(book.yearReleased));
                bookNode.put(BOOK_GENRE, book.bookGenre.name());

                root.add(bookNode);
            }

            root.writeJSONString(fWriter);
            fWriter.close();

        } catch (IOException e) {
            System.err.println("Raise IO Exception.");
            e.printStackTrace();
        }
    }

    /**
     * Saves this collection to the specified XML file.
     *
     * @param file The path to a file.
     */
    public void saveToXMLFile(File file) {
        // TODO: Implement this function yourself. The specific hierarchy is up to you,
        //       but it must be in an XML format and should match the
        //       load function.
        //       You can add throws in method signature if needed

        final String BOOKS = "books";
        final String BOOK = "book";
        final String TITLE = "title";
        final String AUTHOR_NAME = "author_name";
        final String YEAR_RELEASED = "year_released";
        final String BOOK_GENRE = "book_genre";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbf.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Create root element.
            Element docNode = doc.createElement(BOOKS);

            doc.appendChild(docNode);

            for (Book book: books) {
                Element bookEl = doc.createElement(BOOK);
                Element titleEl = doc.createElement(TITLE);
                Element authorEl = doc.createElement(AUTHOR_NAME);
                Element yearEl = doc.createElement(YEAR_RELEASED);
                Element genreEl = doc.createElement(BOOK_GENRE);

                // Set xml file attributes.
                bookEl.setAttribute("version", "1.0");
                bookEl.setAttribute("class", "BookCollection");

                // Set child node content.
                titleEl.appendChild(doc.createTextNode(book.title));
                authorEl.appendChild(doc.createTextNode(book.authorName));
                yearEl.appendChild(doc.createTextNode(Integer.toString(book.yearReleased)));
                genreEl.appendChild(doc.createTextNode(book.bookGenre.name()));

                // Add child node.
                bookEl.appendChild(titleEl);
                bookEl.appendChild(authorEl);
                bookEl.appendChild(yearEl);
                bookEl.appendChild(genreEl);

                docNode.appendChild(bookEl);
            }

            // Transform document into file with stream.
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();

            tf.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult fOut = new StreamResult(file);
            tf.transform(source, fOut);

        } catch (ParserConfigurationException e) {
            System.err.println("Raise Parser Configuration Exception.");
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            System.err.println("Raise Transformer Configuration Exception.");
            e.printStackTrace();
        } catch (TransformerException e) {
            System.err.println("Raise Transformer Exception.");
            e.printStackTrace();
        }
    }


    /**
     * Load a pre-existing book collection from a "bespoke" file.
     *
     * @param file The file to load from. This is guaranteed to exist.
     * @return An initialised book collection.
     */
    public static BookCollection loadFromBespokeFile(File file) {
        // TODO: Implement this function yourself.
        //       You can add throws in method signature if needed

        BookCollection collection = new BookCollection();
        List<Book> bookList = new ArrayList<>();

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(";");

                Book book = new Book(tokens[0], tokens[1],
                                        Integer.parseInt(tokens[2]),
                                        BookGenre.valueOf(tokens[3]));

                bookList.add(book);
            }

            collection.setList(bookList);

        } catch (FileNotFoundException e) {
            System.err.println("Raise File Not Found Exception.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Raise IO Exception.");
            e.printStackTrace();
        }

        return collection;
    }

    /**
     * Load a pre-existing book collection from a JSON file.
     *
     * @param file The file to load from. This is guaranteed to exist.
     * @return An initialised book collection.
     */
    public static BookCollection loadFromJSONFile(File file) {
        // TODO: Implement this function yourself.
        //       You can add throws in method signature if needed

        final String TITLE = "title";
        final String AUTHOR_NAME = "author_ame";
        final String YEAR_RELEASED = "year_released";
        final String BOOK_GENRE = "book_genre";

        BookCollection collection = new BookCollection();
        List<Book> bookList = new ArrayList<>();

        try {
            FileReader fReader = new FileReader(file);

            JSONParser jsonParser = new JSONParser();
            JSONArray root = (JSONArray) jsonParser.parse(fReader);

            for (int i = 0; i < root.size(); i++) {
                JSONObject bookNode = (JSONObject) root.get(i);
                Book book = new Book((String) bookNode.get(TITLE),
                                     (String) bookNode.get(AUTHOR_NAME),
                                     Integer.parseInt((String) bookNode.get(YEAR_RELEASED)),
                                     BookGenre.valueOf(((String) bookNode.get(BOOK_GENRE))));
                bookList.add(book);
            }

            collection.setList(bookList);
            fReader.close();

        } catch (IOException e) {
            System.err.println("Raise IO Exception.");
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("Raise Parse Exception.");
            e.printStackTrace();
        }

        return collection;
    }

    /**
     * Load a pre-existing book collection from an XML file.
     *
     * @param file The file to load from. This is guaranteed to exist.
     * @return An initialised book collection.
     */
    public static BookCollection loadFromXMLFile(File file) {
        // TODO: Implement this function yourself.
        //       You can add throws in method signature if needed

        final String TITLE = "title";
        final String AUTHOR_NAME = "author_name";
        final String YEAR_RELEASED = "year_released";
        final String BOOK_GENRE = "book_genre";

        BookCollection collection = new BookCollection();
        List<Book> bookList = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            Node root = doc.getFirstChild(); // Root node (Books).
            NodeList nl = root.getChildNodes();

            for (int i = 0; i < nl.getLength(); i++) {
                Node bookNode = nl.item(i);

                // Space can be considered as a node.
                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList bookEl = bookNode.getChildNodes();

                    Book book = new Book();

                    for (int j = 0; j < bookEl.getLength(); j++) {
                        Node node = bookEl.item(j);

                        String nodeName = node.getNodeName();
                        if (nodeName.equals(TITLE)) {
                            Node contentNode = node.getFirstChild();
                            book.title = contentNode.getTextContent();
                        } else if (nodeName.equals(AUTHOR_NAME)) {
                            Node contentNode = node.getFirstChild();
                            book.authorName = contentNode.getTextContent();
                        } else if (nodeName.equals(YEAR_RELEASED)) {
                            Node contentNode = node.getFirstChild();
                            book.yearReleased = Integer.parseInt(contentNode.getTextContent());
                        } else if (nodeName.equals(BOOK_GENRE)) {
                            Node contentNode = node.getFirstChild();
                            book.bookGenre = BookGenre.valueOf(contentNode.getTextContent());
                        }
                    }
                    bookList.add(book);
                }
            }
            collection.setList(bookList);

        } catch (ParserConfigurationException e) {
            System.err.println("Raise Parser Configuration Exception.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Raise IO Exception.");
            e.printStackTrace();
        } catch (SAXException e) {
            System.err.println("Raise SAX Exception.");
            e.printStackTrace();
        }

        return collection;
    }
}

