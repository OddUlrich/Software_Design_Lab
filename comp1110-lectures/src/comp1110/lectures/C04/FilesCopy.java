package comp1110.lectures.C04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesCopy {
    public static void main(String[] args) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("resources/words/hamlet.txt");
            out = new FileOutputStream("resources/words/hamlet-copy.txt");
            int i = 0;
            while ((i = in.read()) >= 0) {
                out.write(i);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Files.copy(Paths.get("resources/words/hamlet.txt"), Paths.get("resources/words/hamlet-nio.txt"));
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
}
