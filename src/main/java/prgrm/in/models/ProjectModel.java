package prgrm.in.models;
import java.util.UUID;

/**
 * Created by archit on 21/3/17.
 */
public class ProjectModel {
    public String id;
    public String baseUrl;
    public boolean otherDomains;
    public int crawlLimit;
    public ProjectModel(){
        String uuid = UUID.randomUUID().toString();
        this.id=uuid;
        baseUrl="";
        otherDomains=false;
        crawlLimit=-1;
    }
}
