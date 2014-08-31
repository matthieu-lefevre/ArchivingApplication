package com.mlefevre.app.archiving.threading;

import com.mlefevre.app.archiving.service.ArchiveService;
import org.springframework.context.ApplicationContext;

import java.util.Date;

public class ArchivingThread extends NotifyingThread {


    public ArchivingThread(String name, ArchivingThreadContext context) {
        this.setName(name);
        this.context = context;
    }


    @Override
    public void execute() {
        try {
            final ApplicationContext applicationContext = this.context.getSpringContext();
            if(applicationContext == null) {
                return;
            }
            final ArchiveService archiveService = (ArchiveService) applicationContext.getBean("archiveService");
            archiveService.archiveDocuments(this.context.getDocumentIds());

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
