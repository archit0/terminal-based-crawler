package prgrm.in.services.threads;

import prgrm.in.services.CrawlServices;
import prgrm.in.services.MemoryQueue;
import prgrm.in.utils.RequestUtils;

/**
 * Created by archit on 22/3/17.
 */
public class SourceCodeThread implements Runnable {
    private final MemoryQueue memoryQueue;
    private final CrawlServices crawlServices;

    public SourceCodeThread(CrawlServices crawlServices, MemoryQueue memoryQueue) {
        this.crawlServices = crawlServices;
        this.memoryQueue = memoryQueue;
    }

    public void run() {
        try {
            if(this.memoryQueue.toDownloadUrlQueue.size()!=0) {
                String url = this.memoryQueue.toDownloadUrlQueue.take();
                int total = this.memoryQueue.data.size();
                if (total >= this.crawlServices.projectModel.crawlLimit && this.crawlServices.projectModel.crawlLimit != -1) {
                    System.out.println("Crawl Limit Reached Can't Crawl "+url);
                } else {
                    if (!this.memoryQueue.data.containsKey(url)) {
                        System.out.println((total + 1) + " Downloaded:" + url);
                        String sourceCode = RequestUtils.sourceCode(url);
                        this.memoryQueue.data.put(url, sourceCode);
                        this.memoryQueue.toProcessUrlQueue.add(url);
                    }
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
