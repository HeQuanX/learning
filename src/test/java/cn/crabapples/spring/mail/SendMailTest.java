package cn.crabapples.spring.mail;

import cn.crabapples.spring.system.common.utils.MailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * TODO
 *
 * @author Mr.He
 * 2020/3/21 0:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class SendMailTest {
    @Autowired
    MailUtils mailUtils;

    @Test
    public void sendTest() throws MessagingException {
        String title = "第一封邮件";
        String content = "这是第一封邮件";
        String filePath = "d:/123.txt";
        String fileName = "喜欢你1.txt";
        String fileName1 = "喜欢你2.txt";
        String[] targets = {"162165436@qq.com"};
        Session session = mailUtils.initSession();
        MimeMessage message = mailUtils.initMessage(session, title, targets);
        Multipart multipart = new MimeMultipart();
        mailUtils.addMailContentText(multipart, content);
        mailUtils.addMailContentFile(multipart, filePath, fileName);
        mailUtils.addMailContentFile(multipart, filePath, fileName1);
        assert message != null;
        message.setContent(multipart);
        message.saveChanges();
        mailUtils.sendEmail(session, message);
    }
}
