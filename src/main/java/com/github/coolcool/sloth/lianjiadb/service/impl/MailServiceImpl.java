package com.github.coolcool.sloth.lianjiadb.service.impl;

import com.github.coolcool.sloth.lianjiadb.service.MailService;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by dee on 2016/11/5.
 */
@Service
public class MailServiceImpl implements MailService {

    Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${com.github.coolcool.sloth.lianjiadb.mail.account}")
    String mailAccount;

    @Value("${com.github.coolcool.sloth.lianjiadb.mail.password}")
    String mailPassword;

    @Value("${com.github.coolcool.sloth.lianjiadb.mail.target}")
    String targetMail;

    @Bean
    public Properties getPro(){
        Properties props = new Properties();
        // 开启debug调试
        //props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.from", mailAccount);
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        return props;
    }



    @Override
    public void send(String subject, String content){
        // 设置环境信息
        Session session = Session.getInstance(getPro());

        // 创建邮件对象
        Message msg = new MimeMessage(session);
        Transport transport = null;
        try {
            msg.setSubject(subject);
            // 设置邮件内容
            msg.setContent(content,"text/html;charset=utf8");
            // 设置发件人
            msg.setFrom(new InternetAddress(mailAccount));    //注意:此处设置SMTP发件人
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(targetMail));
            transport = session.getTransport();
            // 连接邮件服务器
            transport.connect(mailAccount, mailPassword);
            // 发送邮件
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
