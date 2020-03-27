package cn.crabapples.spring.test;

import java.io.*;

/**
 * TODO
 *
 * @author Mr.He
 * 2020/3/8 9:53
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class Utils {
    public static void saveObj(Object obj, String f) {
        File file = new File("d:/" + f);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(obj);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObj(String f) {
        File file = new File("d:/" + f);
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
