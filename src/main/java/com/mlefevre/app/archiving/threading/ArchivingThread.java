package com.mlefevre.app.archiving.threading;


import java.util.ArrayList;
import java.util.List;

public class ArchivingThread extends NotifyingThread {

    private List<String> documentIds = new ArrayList<String>();


    public ArchivingThread(String name, List<String> documentsIds) {
        this.setName(name);
        this.documentIds = documentsIds;
    }

    @Override
    public void doRun() {

    }


    public List<String> getDocumentIds() {
        return this.documentIds;
    }

}
