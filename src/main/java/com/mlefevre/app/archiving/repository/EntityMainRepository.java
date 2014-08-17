package com.mlefevre.app.archiving.repository;


import com.mlefevre.app.archiving.entity.EntityClass;

import java.util.List;

public interface EntityMainRepository {

    EntityClass find(Integer id);

    List<EntityClass> findAll();

}
