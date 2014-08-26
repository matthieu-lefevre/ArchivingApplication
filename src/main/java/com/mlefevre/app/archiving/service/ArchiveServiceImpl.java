package com.mlefevre.app.archiving.service;

import com.mlefevre.app.archiving.domain.bean.ArchiveBean;
import com.mlefevre.app.archiving.domain.entity.EntityClass;
import com.mlefevre.app.archiving.exception.ArchiveException;
import com.mlefevre.app.archiving.exception.ThreadingException;
import com.mlefevre.app.archiving.repository.EntityArchiveRepository;
import com.mlefevre.app.archiving.repository.EntityMainRepository;
import com.mlefevre.app.archiving.threading.ArchivingThread;
import com.mlefevre.app.archiving.threading.ArchivingThreadExecutor;
import com.mlefevre.app.archiving.threading.NotifyingThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "archiveService")
@Transactional
public class ArchiveServiceImpl implements ArchiveService {

    private static Logger LOG = LoggerFactory.getLogger(ArchiveService.class);

    @Autowired
    private ArchiveBean archiveBean;

    @Autowired
    private ArchiveThreadingService threadingService;

    @Autowired
    private EntityMainRepository entityMainRepository;

    @Autowired
    private EntityArchiveRepository entityArchiveRepository;


    @Override
    public List<EntityClass> getDocumentsToArchive(String archivingSqlRequest) throws ArchiveException {
        List<EntityClass> documents = this.entityMainRepository.findAllDocumentsToArchive(archivingSqlRequest);
        this.fillArchiveBean(documents);

        return documents;
    }

    private void fillArchiveBean(List<EntityClass> documents) throws ArchiveException {
        if(documents == null) {
            throw new ArchiveException("No documents to fill archive bean.");
        }
        List<String> documentIds = new ArrayList<String>(documents.size());
        for(EntityClass document : documents) {
            documentIds.add(document.getName());
        }
        this.archiveBean.setDocumentIds(documentIds);
    }



    @Override
    public void archiveDocuments() throws ArchiveException {
        try {
            List<NotifyingThread> threads = this.threadingService.dispatch(this.archiveBean.getDocumentIds());
            ArchivingThreadExecutor<NotifyingThread> executor = new ArchivingThreadExecutor<NotifyingThread>(threads);
            executor.execute(2);

            System.out.println("Number od threads played: " + executor.getNumberOfThreadsPlayed());

        } catch (ThreadingException e) {
            throw new ArchiveException(e.getMessage(), e);
        }
    }

    @Override
    public void archiveDocuments(List<String> documentIds) throws ArchiveException {
        for(String documentId : documentIds) {
            this.archiveDocument(documentId);
        }
    }

    @Override
    public void archiveDocument(String documentId) throws ArchiveException {
        EntityClass entity = this.entityMainRepository.findByDocumentId(documentId);
        this.entityArchiveRepository.save(entity);
    }



    @Override
    public void cleanArchivedDocuments() {

    }

    @Override
    public void cleanArchivedDocuments(List<String> documentIds) throws ArchiveException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
