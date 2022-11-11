package javajungsuk;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ex15_6 {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("123.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
            for (int i = '1'; i <= '9'; i++) {
                bos.write(i);
            }
//            fos.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
