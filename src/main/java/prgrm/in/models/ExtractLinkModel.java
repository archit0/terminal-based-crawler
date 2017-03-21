package prgrm.in.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archit on 21/3/17.
 */
public class ExtractLinkModel {
    public List<String> links;
    public List<String> media;
    public List<String> imports;
    public List<String> images;
    public ExtractLinkModel(){
        links=new ArrayList<String>();
        media=new ArrayList<String>();
        imports=new ArrayList<String>();
        images=new ArrayList<String>();

    }

}
