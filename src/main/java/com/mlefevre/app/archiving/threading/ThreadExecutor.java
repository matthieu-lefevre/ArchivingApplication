package com.mlefevre.app.archiving.threading;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreadExecutor implements ThreadCompleteListener {

    private List<NotifyingThread> threads = new ArrayList<NotifyingThread>();

    public ThreadExecutor(List<NotifyingThread> threads) {
        this.threads = threads;
    }


    public void execute(int maxSimultaneousThreads) {
        for(NotifyingThread thread : this.threads) {
            System.out.println(this.threads.indexOf(thread));
            thread.addListener(this);
            thread.start();
        }
    }


    @Override
    public void notifyThreadCompleted(Thread thread) {
        System.out.println(thread.getName() + " finished!");
    }

}
