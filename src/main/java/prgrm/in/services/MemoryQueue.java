package prgrm.in.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by archit on 22/3/17.
 */
public class MemoryQueue {
    public BlockingQueue<String> toDownloadUrlQueue;
    public BlockingQueue<String> toProcessUrlQueue;

    public Map<String,String> data;

    public MemoryQueue(){
        toDownloadUrlQueue=new LinkedBlockingQueue<String>();
        toProcessUrlQueue=new LinkedBlockingQueue<String>();

        data=new ConcurrentHashMap<String, String>();
    }

    public static void main(String[] args) throws Exception{
        MemoryQueue memoryQueue=new MemoryQueue();
        memoryQueue.toDownloadUrlQueue.add("Archit");
        memoryQueue.toDownloadUrlQueue.add("Dwived");
        System.out.println(memoryQueue.toDownloadUrlQueue.take());
        System.out.println(memoryQueue.toDownloadUrlQueue.take());
        System.out.println(memoryQueue.toDownloadUrlQueue.take());
        memoryQueue.toDownloadUrlQueue.add("Dwived");

    }
}
