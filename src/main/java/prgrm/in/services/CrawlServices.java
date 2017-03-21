package prgrm.in.services;

import prgrm.in.models.ProjectModel;
import prgrm.in.services.threads.ProcessingThread;
import prgrm.in.services.threads.SourceCodeThread;

/**
 * Created by archit on 21/3/17.
 */
public class CrawlServices {

    public final ProjectModel projectModel;
    public final FormatServices formatServices;
    public final ExctractServices extractServices;

    public CrawlServices(ProjectModel projectModel,ExctractServices exctractServices, FormatServices formatServices
                         ){

        this.projectModel=projectModel;
        this.extractServices=exctractServices;
        this.formatServices=formatServices;
    }
    public void start(){
        MemoryQueue memoryQueue=new MemoryQueue();
        memoryQueue.toDownloadUrlQueue.add(this.projectModel.baseUrl);

        Runnable sourceCodeThread=new SourceCodeThread(this,memoryQueue);
        Runnable processingThread=new ProcessingThread(this,memoryQueue);
        new Thread(sourceCodeThread).start();
        new Thread(processingThread).start();
    }


}
