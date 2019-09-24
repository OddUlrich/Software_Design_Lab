package comp1110.lectures.C04;

import java.io.IOException;

public class FilesStdio {
    public static void main(String[] args) {
        try {
            byte b = (byte) System.in.read();
            //System.out.println(b);
            System.out.write(b);
            System.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
