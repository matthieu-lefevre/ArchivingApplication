package com.mlefevre.app.archiving.threading;

import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class ArchivingThreadContext {

    private ApplicationContext springContext;

    private List<String> documentIds = new ArrayList<String>();


    public ArchivingThreadContext(ApplicationContext context) {
        this.springContext = context;
    }


    public ApplicationContext getSpringContext() {
        return this.springContext;
    }

    public List<String> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<String> documentIds) {
        this.documentIds = documentIds;
    }

    public void addDocumentId(String documentId) {
        if(this.documentIds == null) {
            this.documentIds = new ArrayList<String>();
        }
        this.documentIds.add(documentId);
    }
}
