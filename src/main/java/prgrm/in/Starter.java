package prgrm.in;

import prgrm.in.services.MemoryQueue;
import prgrm.in.models.ProjectModel;
import prgrm.in.services.CrawlServices;
import prgrm.in.services.ExctractServices;
import prgrm.in.services.FormatServices;

/**
 * Created by archit on 22/3/17.
 */
public class Starter {
    public static void a1(){
        String url= "www.tutorialspoint.com";

        ExctractServices exctractServices=new ExctractServices();
        FormatServices formatServices=new FormatServices();
        ProjectModel projectModel=new ProjectModel();
        projectModel.baseUrl=formatServices.formatUrl(url);

        CrawlServices crawlServices=new CrawlServices(projectModel,exctractServices,formatServices);

        crawlServices.start();
    }
    public static void a2(){
        String url= "www.w3schools.com";

        ExctractServices exctractServices=new ExctractServices();
        FormatServices formatServices=new FormatServices();
        ProjectModel projectModel=new ProjectModel();
        projectModel.baseUrl=formatServices.formatUrl(url);

        CrawlServices crawlServices=new CrawlServices(projectModel,exctractServices,formatServices);

        crawlServices.start();
    }
    public static void main(String[] args){

        a1();






    }
}
