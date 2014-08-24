package com.mlefevre.app.archiving.threading;


import com.mlefevre.app.archiving.service.ArchiveService;
import org.springframework.context.ApplicationContext;

import java.util.Date;

public class CleaningThread extends NotifyingThread {

    private ArchivingThreadContext context;

    public CleaningThread(String name, ArchivingThreadContext context) {
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
            archiveService.cleanArchivedDocuments(this.context.getDocumentIds());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Date getStartTime() {
        return this.startTime;
    }

    @Override
    public Date getEndTime() {
        return this.endTime;
    }

}
