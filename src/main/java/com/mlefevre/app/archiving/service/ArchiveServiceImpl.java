package com.mlefevre.app.archiving.service;

import com.mlefevre.app.archiving.exception.ArchiveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArchiveServiceImpl implements ArchiveService {

    private static Logger LOG = LoggerFactory.getLogger(ArchiveService.class);

    @Autowired
    private ThreadingService threadingService;


    @Override
    public List<String> getDocumentsToArchive() {
        return null;
    }

    @Override
    public void archiveDocuments(List<String> documentIds) throws ArchiveException {

    }

    @Override
    public void cleanArchivedDocuments(List<String> documentIds) {

    }
}
