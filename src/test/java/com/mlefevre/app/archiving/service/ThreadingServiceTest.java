package com.mlefevre.app.archiving.service;

import com.mlefevre.app.archiving.exception.ThreadingException;
import com.mlefevre.app.archiving.threading.ArchivingThread;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ThreadingServiceTest {

    @InjectMocks
    private ThreadingServiceImpl threadingService;


    @Test
    public void dispatchTest_Success() throws ThreadingException {
        List<String> documentIds = new ArrayList<String>();
        for(int i = 1; i <= 203; i++) {
            documentIds.add(new String("Id" + i));
        }

        List<ArchivingThread> threads = this.threadingService.dispatch(documentIds);

        assertEquals(2, threads.size());
        for(ArchivingThread thread : threads) {
            System.out.println(thread.getName() + ": " +thread.getDocumentIds().size());
        }

    }

}
