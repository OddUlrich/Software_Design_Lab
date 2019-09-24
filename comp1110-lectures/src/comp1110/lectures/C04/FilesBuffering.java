package comp1110.lectures.C04;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Demonstrates the performance benefits of using buffered streams for
 * reading and writing files.
 */
public class FilesBuffering {
    public static void main(String[] args) {
        // test buffers of different sizes 1..2^20
        for (int j=0; j<20; j++) {
            InputStream in = null;
            OutputStream out = null;
            int bufferSize = 1<<j;

            long start = 0;
            try {
                if (false) {
                    in = new FileInputStream("resources/words/hamlet.txt");
                    out = new FileOutputStream("resources/words/hamlet-copy.txt");
                } else {
                    in = new BufferedInputStream(new FileInputStream("resources/words/hamlet.txt"), bufferSize);
                    out = new BufferedOutputStream(new FileOutputStream("resources/words/hamlet-copy.txt"), bufferSize);
                }
                start = System.nanoTime();
                int i;
                while ((i = in.read()) != -1) {
                    out.write(i);
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    long end = System.nanoTime();
                    System.out.println("Buffer size " + bufferSize + " took " + (end - start) / 1000000 + " ms");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
