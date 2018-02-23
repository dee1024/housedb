package com.github.coolcool.sloth.lianjiadb.common;

import com.sun.mail.util.MailSSLSocketFactory;

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
public abstract class MailUtil {


    static Properties props = new Properties();
    static {
        // 开启debug调试
        //props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
    }


    public static void send(String subject, String content){

        String targetMail = "2394134029@qq.com";    //注意:此处设置接受邮件通知的邮箱地址

        // 设置环境信息
        Session session = Session.getInstance(props);

        // 创建邮件对象
        Message msg = new MimeMessage(session);
        Transport transport = null;
        try {
            msg.setSubject(subject);
            // 设置邮件内容
            msg.setContent(content,"text/html;charset=utf8");
            // 设置发件人
            msg.setFrom(new InternetAddress("xxxxxxxx@qq.com"));    //注意:此处设置SMTP发件人
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(targetMail));
            transport = session.getTransport();
            // 连接邮件服务器
            transport.connect("xxxxxxxxx@qq.com", "xxxxxxxxx");     //注意:此处设置SMTP发件服务器的账号、密码
            // 发送邮件
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



}
