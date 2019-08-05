package Mr.He;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.ConnectionInfo;
import ch.ethz.ssh2.Session;

import java.io.*;

/**
 * java连接linux
 * @author hequan@gogpay.cn
 * @date 2019/7/4 16:24
 */
public class LinuxSession {
    public static void main(String[] args) throws IOException {
        Connection connection = new Connection("192.168.89.131",22);
        connection.connect();
        connection.authenticateWithPassword("root","0705");
        Session session = connection.openSession();
        session.execCommand("ls");
        InputStream inputStream1 = session.getStdout();
        InputStream inputStream = session.getStderr();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.err.println(bufferedReader.readLine());
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1));
        System.err.println(bufferedReader1.readLine());
    }

}