package com.mlefevre.app.archiving.threading;

import com.mlefevre.app.archiving.domain.model.ThreadReport;

public abstract class NotifyingThread extends Thread {

    protected ArchivingThreadContext context;

    ThreadReport report;

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
            this.listener.onFailure(this, e);
        } finally {
            this.listener.onComplete(this);
        }
    }

    public abstract void execute();


    public ThreadReport getReport() {
        return report;
    }

    public void setReport(ThreadReport report) {
        this.report = report;
    }

    public ArchivingThreadContext getContext() {
        return context;
    }
}
