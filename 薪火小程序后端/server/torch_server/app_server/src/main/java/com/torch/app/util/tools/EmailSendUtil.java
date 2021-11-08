package com.torch.app.util.tools;



import com.torch.app.entity.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

//邮件发送类
@Component
public class EmailSendUtil {
    @Resource
    private JavaMailSenderImpl mailSender;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private TokenUtil tokenUtil;

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

    public void sendMailVerify(String from, String mail,String cookie){
        String subject = "薪火邮箱验证码";
        Random r = new Random();
        StringBuffer sb =new StringBuffer();
        for(int i = 0;i < 6;i ++){
            int ran1 = r.nextInt(10);
            sb.append(String.valueOf(ran1));
        }
        String code = sb.toString();
        System.out.println("token+"+tokenUtil);


        String md5 = tokenUtil.generateMd5(mail, cookie,code);
        redisUtil.set(mail,md5,60);//将mail和cookie加密的md5上传redis。


        String text = "薪火邮箱验证码："+code+",请在60秒内完成注册。";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(mail);
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
