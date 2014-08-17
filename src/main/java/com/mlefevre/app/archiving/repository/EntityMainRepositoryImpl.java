package com.mlefevre.app.archiving.repository;

import com.mlefevre.app.archiving.entity.EntityClass;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EntityMainRepositoryImpl implements EntityMainRepository {

    @Autowired
    @Qualifier(value = "mainSessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public EntityClass find(Integer id) {
        EntityClass entity = (EntityClass) this.sessionFactory.getCurrentSession()
                .createCriteria(EntityClass.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();

        return entity;
    }


    @Override
    public List<EntityClass> findAll() {
        List<EntityClass> entities = (List<EntityClass>) this.sessionFactory.getCurrentSession()
                .createCriteria(EntityClass.class)
                .list();

        return entities;
    }

}
