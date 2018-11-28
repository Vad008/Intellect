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

    public Emailsend(String name, String phone, String email, String dategame, String message, String quest) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dategame = dategame;
        this.message = message;
        this.quest = quest;
    }

    public Emailsend(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dategame = "none";
        this.quest = "none";
        this.message = "Certificate";
    }

    public Emailsend(String name, String phone, String email, String message) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dategame = "none";
        this.quest = "none";
        this.message = message;
    }


    public void send() {
        String to = "Vad008@ukr.net";         // receiver email
        String from = "Vad008@ukr.net";       // sender email
        String host = "localhost";            // mail server host

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "25");

        Session session = Session.getDefaultInstance(properties); // default session

        try {
            MimeMessage mimemessage = new MimeMessage(session); // email message

            mimemessage.setFrom(new InternetAddress(from)); // setting header fields

            mimemessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            if (message.equals("Certificate")) {
                mimemessage.setSubject("Сертификат"); // subject line
                // actual mail body
                mimemessage.setText("Имя: " + name + "\n" +
                        "Телефон: " + phone + "\n" +
                        "Почта: " + email);
            } else if (quest.equals("Клініка") || quest.equals("Куш")) {
                mimemessage.setSubject("Квест"); // subject line
                // actual mail body
                mimemessage.setText("Имя: " + name + "\n" +
                        "Телефон: " + phone + "\n" +
                        "Почта: " + email + "\n" +
                        "Дата: " + dategame + "\n" +
                        "Сообщение: " + message + "\n" +
                        "Квест: " + quest);
            } else {
                mimemessage.setSubject("Обратная связь квест"); // subject line
                // actual mail body
                mimemessage.setText("Имя: " + name + "\n" +
                        "Телефон: " + phone + "\n" +
                        "Почта: " + email + "\n" +
                        "Сообщение: " + message);
            }


            // Send message
            Transport.send(mimemessage);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }


    }
}
