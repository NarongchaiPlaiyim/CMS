package com.cms.model.dao;

import com.cms.model.db.ClassEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassDAO extends GenericDAO<ClassEntity, Integer>{


    public List<ClassEntity> findClassBySubjectId(int id)throws Exception{

        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("subjectModel.id",id));
        criteria.add(Restrictions.eq("active",1));
        criteria.addOrder(Order.asc("classCode"));
        return criteria.list();
    }
}
