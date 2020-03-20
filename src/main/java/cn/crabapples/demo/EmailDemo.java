package cn.crabapples.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @author hequan@gogpay.cn
 * @date 2020/3/20 14:20
 */
public class EmailDemo {
    private static final Logger logger = LoggerFactory.getLogger(EmailDemo.class);
//    private static final String HOST = "smtp.gmail.com";
//    private static final String USERNAME = "crabapples.cn@gmail.com";
//    private static final String PASSWORD = "BestLoveBaby!";
//    private static final String FROM = "crabapples.cn@gmail.com";


    private static final String HOST = "smtp.qq.com";
    private static final String USERNAME = "294046317@qq.com";
    private static final String PASSWORD = "LILI5201";
    private static final String FROM = "294046317@qq.com";


    private static final String PORT = "465";
    private static final String[] TOS = {"294046317@qq.com"};
    private static final String TITLE = "邮件标题";
    private static final String AFFIX = "";
    private static final String AFFIX_NAME = "";


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", PORT);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(FROM));// 加载发件人地址
            InternetAddress[] sendTo = new InternetAddress[TOS.length]; // 加载收件人地址
            for (int i = 0; i < TOS.length; i++) {
                sendTo[i] = new InternetAddress(TOS[i]);
            }
            message.addRecipients(Message.RecipientType.TO, sendTo);
            message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(FROM));//设置在发送给收信人之前给自己（发送方）抄送一份，不然会被当成垃圾邮件，报554错
            message.setSubject(TITLE);//加载标题
            Multipart multipart = new MimeMultipart();//向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            BodyPart contentPart = new MimeBodyPart();//设置邮件的文本内容
            contentPart.setText("测试邮件");
            multipart.addBodyPart(contentPart);
            if (!AFFIX.isEmpty()) {//添加附件
                BodyPart messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(AFFIX);
                messageBodyPart.setDataHandler(new DataHandler(source));//添加附件的内容
                sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();//添加附件的标题
                messageBodyPart.setFileName("=?GBK?B?" + enc.encode(AFFIX_NAME.getBytes()) + "?=");
                multipart.addBodyPart(messageBodyPart);
            }
            message.setContent(multipart);//将multipart对象放到message中
            message.saveChanges(); //保存邮件
            Transport transport = session.getTransport("smtp");//发送邮件
            transport.connect(HOST, USERNAME, PASSWORD);//连接服务器的邮箱
            transport.sendMessage(message, message.getAllRecipients());//把邮件发送出去
            transport.close();//关闭连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//        System.err.println(message);
//    }
}
