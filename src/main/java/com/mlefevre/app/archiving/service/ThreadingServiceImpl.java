package com.mlefevre.app.archiving.service;

import com.mlefevre.app.archiving.exception.ThreadingException;
import com.mlefevre.app.archiving.threading.ArchivingThread;
import com.mlefevre.app.archiving.util.math.Division;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThreadingServiceImpl implements ThreadingService {

    private static Logger LOG = LoggerFactory.getLogger(ThreadingService.class);


    private final static int MAX_DOCUMENTS_PER_THREAD = 200;
    private final static int MAX_SIMULTANEOUS_THREADS = 10;


    @Override
    public List<ArchivingThread> dispatch(List<String> documentIds) throws ThreadingException {
        Division division = new Division(documentIds.size(), MAX_DOCUMENTS_PER_THREAD);
        division.calculate();

        int threadsNb = division.getQuotient() + 1;
        LOG.info("Creating " + threadsNb + " threads.");
        List<ArchivingThread> threads = new ArrayList<ArchivingThread>(threadsNb);

        int documentIdsStartIndex = 0;

        for(int i = 1; i <= threadsNb; i++) {
            int documentIdsLastIndex = this.getLastDocumentIdIndexForThread(i, threadsNb, documentIdsStartIndex, documentIds.size());

            ArchivingThread thread = this.create(documentIds, "Thread" + i, documentIdsStartIndex, documentIdsLastIndex);
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
            thread = new ArchivingThread(name, threadDocumentIds);

        } catch(IndexOutOfBoundsException e) {
            throw new ThreadingException("An error occurred while building threads.", e);
        }

        return thread;
    }

    @Override
    public void execute(List<ArchivingThread> threads) throws ThreadingException {

        for(ArchivingThread thread : threads) {
            Thread.State state = thread.getState();
        }

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
