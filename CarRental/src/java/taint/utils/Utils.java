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

/**
 *
 * @author nguye
 */
public class Utils {

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
}
