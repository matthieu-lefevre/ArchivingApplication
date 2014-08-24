package com.mlefevre.app.archiving.repository;


import com.mlefevre.app.archiving.domain.entity.EntityClass;

import java.util.List;

public interface EntityMainRepository {

    EntityClass find(Integer id);

    List<EntityClass> findAll();

    EntityClass findByDocumentId(String documentId);

    List<EntityClass> findAllDocumentsToArchive(String sqlQuery);

}
