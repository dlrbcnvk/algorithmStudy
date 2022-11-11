package javajungsuk;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ex15_20 {
    public static void main(String[] args) {
        try {
            String fileName = "UserInfo.ser";
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            ObjectOutputStream out = new ObjectOutputStream(bos);

            UserInfo user1 = new UserInfo("Javaman", "1234", 30);
            UserInfo user2 = new UserInfo("JavaWoman", "4321", 26);

            ArrayList<UserInfo> list = new ArrayList<>();
            list.add(user1);
            list.add(user2);

            out.writeObject(user1);
            out.writeObject(user2);
            out.writeObject(list);
            out.close();
            System.out.println("직렬화 끝");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
