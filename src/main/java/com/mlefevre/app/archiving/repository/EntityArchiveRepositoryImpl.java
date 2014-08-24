package com.mlefevre.app.archiving.repository;

import com.mlefevre.app.archiving.domain.entity.EntityClass;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EntityArchiveRepositoryImpl implements EntityArchiveRepository {

    @Autowired
    @Qualifier(value = "archiveSessionFactory")
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

    @Override
    public void save(EntityClass entity) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
}
