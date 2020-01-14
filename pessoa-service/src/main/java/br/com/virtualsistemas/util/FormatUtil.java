package br.com.virtualsistemas.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class FormatUtil {

    public static String formataData(Timestamp date){
        if(date == null){
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}
