package com.mlefevre.app.archiving.threading;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArchivingThreadExecutor implements NotifyingThreadListener {

    private List<NotifyingThread> threads = new ArrayList<NotifyingThread>();

    public ArchivingThreadExecutor(List<NotifyingThread> threads) {
        this.threads = threads;
    }


    public void execute(int maxSimultaneousThreads) {
        for(NotifyingThread thread : this.threads) {
            thread.setListener(this);
            thread.start();
        }
    }


    @Override
    public void onStart(NotifyingThread thread) {
        thread.setStartTime(new Date());
    }

    @Override
    public void onComplete(NotifyingThread thread) {
        thread.setEndTime(new Date());
    }

    @Override
    public void onFailure(NotifyingThread thread) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
