package com.mlefevre.app.archiving.service;

import com.mlefevre.app.archiving.domain.model.ThreadReport;
import com.mlefevre.app.archiving.exception.ThreadingException;
import com.mlefevre.app.archiving.threading.ArchiveThreadPoolExecutor;
import com.mlefevre.app.archiving.threading.ArchivingThread;
import com.mlefevre.app.archiving.threading.ArchivingThreadContext;
import com.mlefevre.app.archiving.threading.NotifyingThread;
import com.mlefevre.app.archiving.util.math.Division;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArchiveThreadingServiceImpl implements ArchiveThreadingService {

    private static Logger LOG = LoggerFactory.getLogger(ArchiveThreadingService.class);

    @Autowired
    private ApplicationContext context;


    private final static int MAX_DOCUMENTS_PER_THREAD = 3;
    private final static int THREAD_POOL_SIZE = 5;


    @Override
    public List<NotifyingThread> dispatch(List<String> documentIds) throws ThreadingException {
        Division division = new Division(documentIds.size(), MAX_DOCUMENTS_PER_THREAD);
        division.calculate();

        int threadsNb = division.getQuotient() + 1;
        LOG.info("Creating " + threadsNb + " threads.");
        List<NotifyingThread> threads = new ArrayList<NotifyingThread>(threadsNb);

        int documentIdsStartIndex = 0;

        for(int i = 1; i <= threadsNb; i++) {
            int documentIdsLastIndex = this.getLastDocumentIdIndexForThread(i, threadsNb, documentIdsStartIndex, documentIds.size());

            NotifyingThread thread = this.create(documentIds, "Thread" + i, documentIdsStartIndex, documentIdsLastIndex);
            threads.add(thread);

            documentIdsStartIndex = documentIdsLastIndex;
        }

        return threads;
    }


    @Override
    public ArchivingThread create(List<String> documentIds, String name, int startIndex, int stopIndex) throws ThreadingException {
        ArchivingThread thread = null;
        try {
            List<String> threadDocumentIds = documentIds.subList(startIndex, stopIndex);
            ArchivingThreadContext ctx = new ArchivingThreadContext(this.context);
            ctx.setDocumentIds(threadDocumentIds);
            thread = new ArchivingThread(name, ctx);

        } catch(IndexOutOfBoundsException e) {
            throw new ThreadingException("An error occurred while building threads.", e);
        }

        return thread;
    }

    @Override
    public void execute(List<NotifyingThread> threads) throws ThreadingException {
        ArchiveThreadPoolExecutor<NotifyingThread> executor = new ArchiveThreadPoolExecutor(threads);
        executor.setStartTime(new Date());
        executor.execute(THREAD_POOL_SIZE);
        executor.setEndTime(new Date());

        for(ThreadReport report : executor.getReports()) {
            System.out.println(report);
        }
        System.out.println("Archiving Execution Time: " + executor.getExecutionTime());
    }

    private int getLastDocumentIdIndexForThread(int currentThread, int threadsNb, int documentIdStartIndex, int documentIdsNb) {
        int documentIdLastIndex;
        if(currentThread == threadsNb) {
            documentIdLastIndex = documentIdsNb;
        } else {
            documentIdLastIndex = documentIdStartIndex + MAX_DOCUMENTS_PER_THREAD; // to get exactly MAX_DOCUMENTS_PER_THREAD into thread
        }

        return documentIdLastIndex;
    }


}
