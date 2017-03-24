package prgrm.in.services;

/**
 * Created by archit on 21/3/17.
 */
public class FormatServices {
    public  boolean checkIsOtherDomain(String main,String toCheck){
            toCheck=formatUrl(toCheck);
        toCheck=toCheck.replace("https://","");
            main=main.replace("https://","");
        toCheck=toCheck.replace("http://","");
        main=main.replace("http://","");
            return !main.equalsIgnoreCase(toCheck);
    }
    public  String formatUrl(String url){
        url=url.trim();
        String newFormattedUrl="";
        boolean httpExists=url.toLowerCase().indexOf("http://")==0 || url.toLowerCase().indexOf("https://")==0;

        if(httpExists==false)
            newFormattedUrl="http://"+url;
        else
            newFormattedUrl=url;

        int doubleSlashIndex=newFormattedUrl.indexOf("://")+3;
        int lastSlash=newFormattedUrl.indexOf("/",doubleSlashIndex);
        if(lastSlash==-1){
            newFormattedUrl+="/";
        }
        else{
            newFormattedUrl=newFormattedUrl.substring(0,lastSlash+1);
        }
        return newFormattedUrl;
    }


}
