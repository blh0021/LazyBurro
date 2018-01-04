package LazyBurro.Helper;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class XML {
    public static String prettyPrint(String xml, int indent) {
        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.setIndentContent(true);
        tidy.setPrintBodyOnly(true);
        tidy.setTidyMark(false);

        // HTML to DOM
        Document htmlDOM = tidy.parseDOM(new ByteArrayInputStream(xml.getBytes()), null);

        // Pretty Print
        OutputStream out = new ByteArrayOutputStream();
        tidy.pprint(htmlDOM, out);

        return out.toString();
        //return xml;
    }
}
