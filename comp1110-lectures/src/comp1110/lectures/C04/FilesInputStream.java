package comp1110.lectures.C04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FilesInputStream {
    public static void main(String[] args) {
        FileInputStream in = null;
        try {
            in = new FileInputStream("resources/words/hamlet.txt");
            for (int i = 0; i < 100; i++) {
                byte b = (byte) in.read();
                System.out.write(b);
            }
            System.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            ;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
