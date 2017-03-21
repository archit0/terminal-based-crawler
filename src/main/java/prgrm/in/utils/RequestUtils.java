package prgrm.in.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by archit on 21/3/17.
 */
public class RequestUtils {
    public static String sourceCode(String url){
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.html();
        } catch (IOException e) {
            return "";
        }
    }

}
