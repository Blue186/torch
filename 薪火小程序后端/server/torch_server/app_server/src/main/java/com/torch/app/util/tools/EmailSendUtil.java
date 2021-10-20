package com.torch.app.util.tools;


import com.torch.app.entity.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

//邮件发送类
@Service
public class EmailSendUtil {
    @Resource
    JavaMailSenderImpl mailSender;

    /**
     * 简易样式邮箱
     * @param from 发送者
     * @param user 接受者
     * @param subject 邮件标题
     * @param text 文本类容
     */
    public void simpleEmail(String from, User user, String subject, String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        mailSender.send(simpleMailMessage);
    }

    /**
     * html样式邮件
     * @param from 发送者
     * @param subject 标题
     * @param user 接受者
     */
    public void htmlEmail(String from,String subject,User user){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(from);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);

            String Text = "HTML文本";

            helper.setText(Text,true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
