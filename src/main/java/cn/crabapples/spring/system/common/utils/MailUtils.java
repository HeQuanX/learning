package cn.crabapples.spring.system.common.utils;

import ch.ethz.ssh2.crypto.Base64;
import cn.crabapples.spring.system.common.config.MailConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * TODO SMTP发送邮件演示
 *
 * @author Mr.He
 * 2020/3/20 21:15
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Component
public class MailUtils {
    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);
    private final MailConfigure mailConfigure;

    public MailUtils(MailConfigure mailConfigure) {
        this.mailConfigure = mailConfigure;
    }

    /**
     * 初始化session
     *
     * @return session
     */
    public Session initSession() {
        logger.info("开始初始化session");
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", mailConfigure.getProtocol());
        properties.put("mail.smtp.host", mailConfigure.getHost());
        properties.put("mail.smtp.auth", mailConfigure.isAuth());
        properties.put("mail.smtp.port", mailConfigure.getPort());
        properties.put("mail.smtp.socketFactory.class", mailConfigure.getSocketFactory());
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(mailConfigure.isDebug());
        logger.info("初始化session完成:[{}]", session);
        return session;
    }

    /**
     * 初始化邮件数据
     *
     * @param session session连接
     * @param title   邮件标题
     * @param targets 收件人
     * @return 邮件数据
     */
    public MimeMessage initMessage(Session session, String title, String[] targets) {
        try {
            logger.info("开始初始化message");
            MimeMessage message = new MimeMessage(session);
            message.setSubject(title);
            message.setFrom(new InternetAddress(mailConfigure.getSource()));
            logger.debug("发件人设置为:[{}]", mailConfigure.getSource());
            /*
             * ↑ ↑ ↑ ↑ ↑ ↑ ↑
             * 设置发件人地址
             *
             * 设置收件人地址
             * ↓ ↓ ↓ ↓ ↓ ↓ ↓
             */
            InternetAddress[] target = new InternetAddress[targets.length];
            for (int i = 0; i < targets.length; i++) {
                logger.debug("添加收件人:[{}]", targets[i]);
                target[i] = new InternetAddress(targets[i]);
            }
            message.addRecipients(Message.RecipientType.TO, target);
            logger.info("初始化message完成:[{}]", message);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送邮件
     *
     * @param session 连接信息
     * @param message 邮件信息
     */
    public void sendEmail(Session session, MimeMessage message) {
        try {
            logger.info("开始发送邮件");
            Transport transport = session.getTransport(mailConfigure.getProtocol());
            transport.connect(mailConfigure.getHost(), mailConfigure.getUsername(), mailConfigure.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            logger.info("邮件发送完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置正文
     *
     * @param multipart   邮件数据信息
     * @param contentText 邮件正文
     * @throws MessagingException 邮件相关异常
     */
    public void addMailContentText(Multipart multipart, String contentText) throws MessagingException {
        /*
         * BodyPart类型对象用来存储邮件Body相关信息
         */
        BodyPart content = new MimeBodyPart();
        logger.debug("设置邮件正文:[{}]", contentText);
        content.setText(contentText);
        multipart.addBodyPart(content);
    }

    /**
     * 添加附件
     *
     * @param multipart 邮件数据信息
     * @param filePath  附件在本地文件路径
     * @param fileName  附件邮件中文件名
     * @throws MessagingException 邮件相关异常
     */
    public void addMailContentFile(Multipart multipart, String filePath, String fileName) throws MessagingException {
        BodyPart filePart = new MimeBodyPart();
        logger.debug("设置邮件附件:[{}]", filePath);
        filePart.setDataHandler(new DataHandler(new FileDataSource(filePath)));
        filePart.setFileName("=?UTF-8?B?" + new String(Base64.encode(fileName.getBytes())) + "?=");
        multipart.addBodyPart(filePart);
    }
}
