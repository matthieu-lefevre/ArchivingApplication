package com.mlefevre.app.archiving.threading;

import java.util.Date;

public abstract class NotifyingThread extends Thread {

    protected Date startTime;
    protected Date endTime;

    public abstract Date getStartTime();

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public abstract Date getEndTime();

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    private NotifyingThreadListener listener;

    public void setListener(NotifyingThreadListener listener) {
        this.listener = listener;
    }


    @Override
    public void run() {
        try {
            this.listener.onStart(this);
            execute();
        } catch(Exception e) {
            this.listener.onFailure(this);
        } finally {
            this.listener.onComplete(this);
        }
    }

    public abstract void execute();

}
