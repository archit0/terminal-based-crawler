package prgrm.in.services.threads;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import prgrm.in.models.ExtractLinkModel;
import prgrm.in.services.CrawlServices;
import prgrm.in.services.MemoryQueue;

/**
 * Created by archit on 22/3/17.
 */
public class ProcessingThread implements Runnable {
    private final MemoryQueue memoryQueue;
    private final CrawlServices crawlServices;
    DBCollection coll;

    public ProcessingThread(CrawlServices crawlServices, MemoryQueue memoryQueue) {
        this.crawlServices = crawlServices;
        this.memoryQueue = memoryQueue;
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        DB db = mongoClient.getDB("crawler");
        coll = db.getCollection("data");
    }

    public void run() {
        try {
            if (this.memoryQueue.toProcessUrlQueue.size() != 0) {
                String url = this.memoryQueue.toProcessUrlQueue.take();
                String source = this.memoryQueue.data.get(url);
                this.memoryQueue.data.put(url, "");

                ExtractLinkModel extractLinkModel = this.crawlServices.extractServices.extractLinks(source);
                Gson gson = new Gson();
                BasicDBObject doc = new BasicDBObject("projectId", this.crawlServices.projectModel.id).
                        append("url", url).
                        append("sourceCode", source)
                        .append("tag", (DBObject) JSON.parse(gson.toJson(extractLinkModel)));
                coll.insert(doc);
                for (String x : extractLinkModel.links) {
                    if (!this.memoryQueue.data.containsKey(x) &&
                            !this.memoryQueue.toDownloadUrlQueue.contains(x) &&
                            !x.trim().equals("")) {

                        boolean allowed = this.crawlServices.projectModel.otherDomains;
                        String mainUrl = this.crawlServices.projectModel.baseUrl;
                        boolean isOther = this.crawlServices.formatServices.checkIsOtherDomain(mainUrl, x);
                        if (!allowed)
                            if (isOther)
                                continue;
                        this.memoryQueue.toDownloadUrlQueue.add(x);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
