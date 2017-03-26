package prgrm.in;

import prgrm.in.services.MemoryQueue;
import prgrm.in.models.ProjectModel;
import prgrm.in.services.CrawlServices;
import prgrm.in.services.ExctractServices;
import prgrm.in.services.FormatServices;

import java.util.Scanner;

/**
 * Created by archit on 22/3/17.
 */
public class Starter {
    public static void a1(){
        String url= "http://www.cricbuzz.com/";

        ExctractServices exctractServices=new ExctractServices();
        FormatServices formatServices=new FormatServices();
        ProjectModel projectModel=new ProjectModel();
        projectModel.baseUrl=formatServices.formatUrl(url);
        projectModel.crawlLimit=15;
        CrawlServices crawlServices=new CrawlServices(projectModel,exctractServices,formatServices);

        crawlServices.start();
    }
    public static void a2(String url){

        ExctractServices exctractServices=new ExctractServices();
        FormatServices formatServices=new FormatServices();
        ProjectModel projectModel=new ProjectModel();
        projectModel.crawlLimit=-1;
        projectModel.baseUrl=formatServices.formatUrl(url);
        CrawlServices crawlServices=new CrawlServices(projectModel,exctractServices,formatServices);
        crawlServices.start();
    }
    public static void main(String[] args){


        Scanner sc=new Scanner(System.in);
        System.out.print("Enter domain: ");
        String t=sc.nextLine();

        a2(t);



    }
}
