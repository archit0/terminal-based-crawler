package prgrm.in.services.threads;

import prgrm.in.models.ExtractLinkModel;
import prgrm.in.services.CrawlServices;
import prgrm.in.services.MemoryQueue;
import prgrm.in.utils.RequestUtils;

/**
 * Created by archit on 22/3/17.
 */
public class SourceCodeThread implements Runnable {
    private final MemoryQueue memoryQueue;
    private final CrawlServices crawlServices;

    public SourceCodeThread(CrawlServices crawlServices,MemoryQueue memoryQueue){
        this.crawlServices=crawlServices;
        this.memoryQueue=memoryQueue;
    }
    public void run() {
        while (true){
            try {
                String url=this.memoryQueue.toDownloadUrlQueue.take();
                String id=this.crawlServices.projectModel.id;
                int total=this.memoryQueue.data.size();

                if(total>=this.crawlServices.projectModel.crawlLimit&&this.crawlServices.projectModel.crawlLimit!=-1) {
                    System.out.println("Crawl Limit Reached");
                    break;
                }
                else{
                    System.out.println((total+1)+" Downloaded:"+ url);
                    String sourceCode= RequestUtils.sourceCode(url);
                    this.memoryQueue.data.put(url,sourceCode);
                    this.memoryQueue.toProcessUrlQueue.add(url);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
