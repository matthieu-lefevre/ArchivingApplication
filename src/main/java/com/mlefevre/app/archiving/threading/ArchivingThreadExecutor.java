package com.mlefevre.app.archiving.threading;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ArchivingThreadExecutor<T extends NotifyingThread> implements NotifyingThreadListener<T> {

    private int numberOfThreadsPlayed = 0;
    private int lastIndex = 0;


    LinkedBlockingQueue<T> queue = null;

    List<T> threads = new ArrayList<T>();

    public ArchivingThreadExecutor(List<T> threads) {
        this.threads = threads;
    }


    public void execute(int maxSimultaneousThreads) {
        this.lastIndex = maxSimultaneousThreads - 1;
        this.queue = new LinkedBlockingQueue<T>(maxSimultaneousThreads);
        this.queue.addAll(threads.subList(0, this.lastIndex));

        Iterator<T> iterator = this.queue.iterator();
        while(iterator.hasNext()) {
            T thread = iterator.next();
            thread.setListener(this);
            thread.start();
        }
    }


    @Override
    public void onStart(T thread) {
        thread.setStartTime(new Date());
        this.numberOfThreadsPlayed++;
    }

    @Override
    public void onComplete(T thread) {
        thread.setEndTime(new Date());
        this.queue.remove(thread);
        this.lastIndex++;
        this.queue.add(this.threads.get(this.lastIndex));
    }

    @Override
    public void onFailure(T thread) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public int getNumberOfThreadsPlayed() {
        return numberOfThreadsPlayed;
    }
}
