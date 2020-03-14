
import com.google.api.client.googleapis.util.Utils;
import java.util.Date;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguye
 */
public class NewClass {
    public static void main(String[] args) {
        String dateExpired = "2020-02-20";
        Date asd = new Date("2020/02/20");
        String dateExpired1 = "02/20/2020";
        
        Date dad= new Date(dateExpired1);
        System.out.println(dad.after(asd));
            
//        String date = taint.utils.Utils.formatStringDate2(dateExpired);
//        Date d = new Date(date);
//        
//        System.out.println(d);
        
        
//        java.sql.Date now =  java.sql.Date.valueOf("2020-02-13");
//        System.out.println(now);
//                System.out.println(d.compareTo(now));

    }
}
