package com.mlefevre.app.archiving.threading;


import com.mlefevre.app.archiving.domain.enumeration.Status;
import com.mlefevre.app.archiving.domain.model.ThreadReport;
import com.mlefevre.app.archiving.exception.ThreadingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class ArchiveThreadPoolExecutor<T extends NotifyingThread> implements NotifyingThreadListener<T> {

    List<ThreadReport> reports;
    List<T> threads;

    public ArchiveThreadPoolExecutor(List<T> threads) {
        this.threads = threads;
        reports = new ArrayList<ThreadReport>(threads.size());
    }


    public void execute(int threadPoolSize) throws ThreadingException {
        try {
            ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolSize);
            for(NotifyingThread thread : threads) {
                thread.setListener(this);
                Future<String> result = threadPool.submit(thread, "Done");
                result.get();
            }

            threadPool.awaitTermination(15, TimeUnit.SECONDS);
            threadPool.shutdownNow();

        } catch(InterruptedException e) {
            throw new ThreadingException("", e);
        } catch (ExecutionException e) {
            throw new ThreadingException("", e);
        }
    }


    @Override
    public void onStart(T thread) {
        ThreadReport report = new ThreadReport();
        report.setStartTime(new Date());
        report.setDocumentIds(thread.getContext().getDocumentIds());

        thread.setReport(report);
    }

    @Override
    public void onComplete(T thread) {
        ThreadReport report = thread.getReport();
        report.setEndTime(new Date());
        report.setStatus(Status.SUCCESS);

        thread.setReport(report);
    }

    @Override
    public <U extends Exception> void onFailure(T thread, U exception) {
        ThreadReport report = thread.getReport();
        report.setEndTime(new Date());
        report.setStatus(Status.FAIL);
        report.setMessage(exception.getMessage());

        thread.setReport(report);
    }

}
