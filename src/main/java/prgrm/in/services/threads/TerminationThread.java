package prgrm.in.services.threads;

import prgrm.in.services.CrawlServices;
import prgrm.in.services.MemoryQueue;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by archit on 25/3/17.
 */
public class TerminationThread implements Runnable {
    private  ScheduledExecutorService sourceCodeThreadExecutor;
    private  ScheduledExecutorService processingThreadExecutor;
    private  ScheduledExecutorService currentExec;

    MemoryQueue memoryQueue;
    public TerminationThread(MemoryQueue memoryQueue, ScheduledExecutorService sourceCodeThreadExecutor,
                             ScheduledExecutorService processingThreadExecutor,ScheduledExecutorService currentExec){
        this.memoryQueue=memoryQueue;
        this.sourceCodeThreadExecutor=sourceCodeThreadExecutor;
        this.processingThreadExecutor=processingThreadExecutor;
        this.currentExec=currentExec;
    }
    int waitedtime=0;
    int timeoutTime=60000/4;
    public void run() {
          if(this.memoryQueue.toDownloadUrlQueue.size()==0&&this.memoryQueue.toProcessUrlQueue.size()==0)
              waitedtime+=100;

          if(waitedtime==timeoutTime){

              System.out.println("Terminating");

              sourceCodeThreadExecutor.shutdownNow();
              processingThreadExecutor.shutdownNow();
              currentExec.shutdownNow();


          }
    }
}
