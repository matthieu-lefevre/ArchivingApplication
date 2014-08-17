package com.mlefevre.app.archiving.service;

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



    public List<ArchivingThread> dispatch(List<String> documentIds) {
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


    public ArchivingThread create(List<String> documentIds, String name, int startIndex, int stopIndex) {
        List<String> threadDocumentIds = documentIds.subList(startIndex, stopIndex);
        ArchivingThread thread = new ArchivingThread(name, threadDocumentIds);

        return thread;
    }

    public int getLastDocumentIdIndexForThread(int currentThread, int threadsNb, int documentIdStartIndex, int documentIdsNb) {
        int documentIdLastIndex;
        if(currentThread == threadsNb) {
            documentIdLastIndex = documentIdsNb - 1;
        } else {
            documentIdLastIndex = documentIdStartIndex + MAX_DOCUMENTS_PER_THREAD - 1; // to get exactly MAX_DOCUMENTS_PER_THREAD into thread
        }

        return documentIdLastIndex;
    }


}
