/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author nguye
 */
public class Utils {

    private static String code;

    public static String getCode() {
        return code;
    }

    public static void setCode(String code) {
        Utils.code = code;
    }
    
    public static String formatDateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateFormat = df.format(date);

        return dateFormat;
    }

    public static String formatStringDate(String strDate) {
        java.sql.Date date = java.sql.Date.valueOf(strDate);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");

        String dateFormat = df.format(date);

        return dateFormat;
    }

    public static String encodePassword(String password)
            throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        String sha256hex = Base64.getEncoder().encodeToString(hash);
        return sha256hex;
    }

    public static String sendEmail(String recepient) throws MessagingException {
        System.out.println("Preparing to send email...");

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccount = "nguyentutai12@gmail.com";
        String myPassword = "ngtutai1206";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, myPassword);
            }
        });

        Message message = prepareMessage(session, myAccount, recepient);

        Transport.send(message);
        System.out.println("Message sent successfully");
        return getCode();
    }

    private static Message prepareMessage(Session session, String myAccount,
            String recepient) {

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(myAccount));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));

            message.setSubject("Verify your email with Rental Car !!");

            Random random = new Random();
            int leng = 6;
            String code = "";

            while (leng > 0) {
                int a = random.nextInt(10);
                code += a;
                leng--;
            }

            String htmlStr = "<h1>Welcome to my system.</h1>"
                    + "<p>Your code to activate account: <b>" + code + "</b></p>";

            message.setContent(htmlStr, "text/html");

            setCode(code);
            
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
