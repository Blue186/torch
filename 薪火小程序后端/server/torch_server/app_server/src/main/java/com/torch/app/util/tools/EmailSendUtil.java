package com.torch.app.util.tools;



import com.torch.app.entity.User;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    private JavaMailSenderImpl mailSender;

    private RedisUtil redisUtil;

    private TokenUtil tokenUtil;

    private RedissonClient redissonClient;

    @Autowired
    public EmailSendUtil(JavaMailSenderImpl mailSender, RedisUtil redisUtil, TokenUtil tokenUtil, RedissonClient redissonClient) {
        this.mailSender = mailSender;
        this.redisUtil = redisUtil;
        this.tokenUtil = tokenUtil;
        this.redissonClient = redissonClient;
    }

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
        String subject = "薪火志愿者邮箱验证码";
        Random r = new Random();
        StringBuffer sb =new StringBuffer();
        for(int i = 0;i < 6;i ++){
            int ran1 = r.nextInt(10);
            sb.append(String.valueOf(ran1));
        }
        String code = sb.toString();
        System.out.println("token+"+tokenUtil);


        String md5 = tokenUtil.generateMd5(mail, cookie,code);
//        ---------------------------,就不用布隆过滤器了吧
        redisUtil.set(mail,md5,60);//将mail和cookie加密的md5上传redis。


        String text = "邮箱验证码："+code+",注意请在60秒内完成注册。";
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
