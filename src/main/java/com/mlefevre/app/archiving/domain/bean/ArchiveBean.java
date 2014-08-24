package com.mlefevre.app.archiving.domain.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class ArchiveBean {

    private List<String> documentIds = new ArrayList<String>();

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

    public void cleanDocumentIds() {
        this.documentIds.clear();
    }
}
