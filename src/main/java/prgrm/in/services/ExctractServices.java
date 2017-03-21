package prgrm.in.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import prgrm.in.models.ExtractLinkModel;

/**
 * Created by archit on 22/3/17.
 */
public class ExctractServices {

    public ExtractLinkModel extractLinks(String html){
        ExtractLinkModel extractLinkModel = new ExtractLinkModel();

        try {
            Document doc = Jsoup.parse(html);
            Elements links = doc.select("a[href]");
            Elements media = doc.select("[src]");
            Elements imports = doc.select("link[href]");
            for (Element src : media) {
                if (src.tagName().equals("img"))
                    extractLinkModel.images.add(src.attr("abs:src"));

                else
                    extractLinkModel.media.add(src.attr("abs:src"));
            }

            for (Element link : imports) {
                extractLinkModel.imports.add(link.attr("abs:href"));
            }

            for (Element link : links) {
                extractLinkModel.links.add(link.attr("abs:href"));
            }
        }
        catch (Exception e){
        }
        return extractLinkModel;

    }


}
