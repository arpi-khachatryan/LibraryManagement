package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        }
        return sdf.format(date);
    }

    public static Date stringToDate(String strDate) {
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            System.out.println("wrong format");
        }
        return null;
    }
}