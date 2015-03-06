package android_app.gg.peter.madklub;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pg on 06/03/15.
 */
public class Utils {
    public static String formatDateToString(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    public static Date parseToDate(String dateString){
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
