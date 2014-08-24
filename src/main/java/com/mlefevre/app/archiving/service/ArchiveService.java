package com.mlefevre.app.archiving.service;


import com.mlefevre.app.archiving.domain.entity.EntityClass;
import com.mlefevre.app.archiving.exception.ArchiveException;

import java.util.List;

/**
 * Archive service
 */
public interface ArchiveService {

    /**
     * Retrieve the documents to archive from the given SQL query
     *
     * @param archivingSqlRequest
     * @return the list of documents to archive
     * @throws ArchiveException
     */
    List<EntityClass> getDocumentsToArchive(String archivingSqlRequest) throws ArchiveException;


    /**
     * Archive the given documents
     * (copy from main db to archive db)
     *
     * @throws ArchiveException
     */
    void archiveDocuments() throws ArchiveException;

    /**
     * Archive the given documents
     * (copy from main db to archive db)
     *
     * @param documentIds (document to archive identifiers)
     * @throws ArchiveException
     */
    void archiveDocuments(List<String> documentIds) throws ArchiveException;

    /**
     * Archive the document from the given id
     *
     * @param documentId
     * @throws ArchiveException
     */
    void archiveDocument(String documentId) throws ArchiveException;



    /**
     * Clean archived documents
     * (delete documents from main db)
     *
     * @throws ArchiveException
     */
    void cleanArchivedDocuments() throws ArchiveException;

    /**
     * Clean archived documents
     * (delete documents from main db)
     *
     * @param documentIds (document to archive identifiers)
     * @throws ArchiveException
     */
    void cleanArchivedDocuments(List<String> documentIds) throws ArchiveException;

}
