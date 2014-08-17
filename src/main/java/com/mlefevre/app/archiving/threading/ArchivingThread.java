package com.mlefevre.app.archiving.threading;


import java.util.ArrayList;
import java.util.List;

public class ArchivingThread extends Thread {

    private List<String> documentIds = new ArrayList<String>();


    public ArchivingThread(String name, List<String> documentsIds) {
        super(name);
        System.out.println("Creating archiving thread (" + this.getName() + ")...");
        this.documentIds = documentsIds;
    }


    @Override
    public void run() {
        for(String documentId : this.documentIds) {
            System.out.println(documentId);
        }
        System.out.println("End of archiving thread (" + this.getName() + ")...");
    }


}
