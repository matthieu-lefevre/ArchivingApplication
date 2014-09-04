package com.mlefevre.app.archiving.domain.model;

import com.mlefevre.app.archiving.domain.enumeration.Status;
import com.mlefevre.app.archiving.util.math.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThreadReport {

    private Status status;

    private String message;

    private List<String> documentIds = new ArrayList<String>();

    private Date startTime;

    private Date endTime;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<String> documentIds) {
        this.documentIds = documentIds;
    }

    public void addDocumentId(String documentId) {
        this.documentIds.add(documentId);
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getExecutionTime() {
        long diff = DateUtils.timeStampDiff(this.endTime, this.startTime);
        int seconds = DateUtils.getSeconds(diff);
        int minutes = DateUtils.getMinutes(diff);

        StringBuilder builder = new StringBuilder();
        builder.append(minutes + "min");
        builder.append(" ");
        builder.append(seconds + "sec");

        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ThreadReport {");
        builder.append("\n");
        builder.append("\tExecution Time: " + this.getExecutionTime());
        builder.append("\n");
        builder.append("\tStatus: " + status);
        builder.append("\n");
        builder.append("\tDocument Ids: ");
        for(String documentId : this.documentIds) {
            builder.append("\n\t\t" + documentId + " ");
        }
        if(this.message != null) {
            builder.append("\n");
            builder.append("\tMessage: " + message);
        }
        builder.append("\n");
        builder.append("}");

        return builder.toString();
    }
}
