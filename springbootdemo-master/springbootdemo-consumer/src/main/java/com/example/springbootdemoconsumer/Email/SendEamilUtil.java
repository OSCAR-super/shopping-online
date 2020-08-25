package com.example.springbootdemoconsumer.Email;


import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

//@Component 将工具类注入spring容器中
@Component
public class SendEamilUtil {


    public int sendemail(HttpServletRequest request , HttpServletResponse response, String toEmail, String code) throws IOException, AddressException, MessagingException {

        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        String to = toEmail;
        String subject = "邮箱验证";
        String content = "<html><head></head><body><h1>这是一封激活邮件,激活码或激活地址为：</h1>"+code+"</body></html>";
        request.getSession().setAttribute("EmailCode",code);

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.qq.com");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");

        //发送者的邮箱和授权码
        Authenticator authenticator = new Email_Authenticator("3432771488@qq.com", "adtzslgccrmqchgg");
        Session sendMailSession = Session.getDefaultInstance(properties, authenticator);
        MimeMessage mailMessage = new MimeMessage(sendMailSession);
        //邮箱的发送者
        try {
            mailMessage.setFrom(new InternetAddress("3432771488@qq.com"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //邮箱接收
        // Message.RecipientType.TO属性表示接收者的类型为TO
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //发送邮件的标题
        mailMessage.setSubject(subject, "UTF-8");
        //发送邮件的日期
        mailMessage.setSentDate(new Date());

        //MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();

        //创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();
        //设置邮件的内容的格式和字节码
        html.setContent(content.trim(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        mailMessage.setContent(mainPart);
        Transport.send(mailMessage);
        return 1;
    }

}

