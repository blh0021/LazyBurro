package LazyBurro.Helper;

import org.jsoup.Jsoup;

public class XML {
    public static String prettyPrint(String xml, int indent) {
        return Jsoup.parse(xml).toString();
    }
}
