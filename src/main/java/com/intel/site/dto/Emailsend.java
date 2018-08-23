package com.intel.site.dto;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Java Program to send text mail using default SMTP server and without authentication.
 * You need mail.jar, smtp.jar and activation.jar to run this program.
 *
 * @author Javin Paul
 */

public class Emailsend {

    private String name;
    private String phone;
    private String email;
    private String dategame;
    private String message;
    private String quest;

    public Emailsend(String name, String phone, String email, String dategame, String message, String quest){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dategame = dategame;
        this. message = message;
        this.quest = quest;
    }

    public Emailsend(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dategame = "none";
        this.quest = "none";
        this.message = "Certificate";
    }


    public void send() {
        String to = "Vad008@ukr.net";         // receiver email
        String from = "Vad008@ukr.net";       // sender email
        String host = "mail.2upost.com";            // mail server host

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "25");

        Session session = Session.getDefaultInstance(properties); // default session

        try {
            MimeMessage message = new MimeMessage(session); // email message

            message.setFrom(new InternetAddress(from)); // setting header fields

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            if (message.equals("Certificate")){
                message.setSubject("Сертификат"); // subject line
                // actual mail body
                message.setText("Имя: " + name + "\n"+
                        "Телефон: " + name + "\n"+
                        "Почта:: " + name + "\n"+
                        "Дата: " + name + "\n"+
                        "Сообщение: " + name + "\n"+
                        "Квест: " + name);
            } else {
                message.setSubject("Test Mail from Java Program"); // subject line
                // actual mail body
                message.setText("Олег лох!");
            }


            // Send message
            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }


    }
}
