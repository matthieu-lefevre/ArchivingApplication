package com.mlefevre.app.archiving.repository;


import com.mlefevre.app.archiving.domain.entity.EntityClass;

import java.util.List;

public interface EntityArchiveRepository {

    EntityClass find(Integer id);

    List<EntityClass> findAll();

    void save(EntityClass entity);

}
