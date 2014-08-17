package com.mlefevre.app.archiving.service;


import com.mlefevre.app.archiving.exception.ArchiveException;

import java.util.List;

public interface ArchiveService {


    List<String> getDocumentsToArchive();

    void archiveDocuments(List<String> documentIds) throws ArchiveException;

    void cleanArchivedDocuments(List<String> documentIds);

}
