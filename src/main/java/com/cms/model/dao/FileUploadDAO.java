package com.cms.model.dao;

import com.cms.model.db.ClassEntity;
import com.cms.model.db.FileUploadModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileUploadDAO extends GenericDAO<FileUploadModel, Integer>{

    public List<FileUploadModel> findByClassId(int id)throws Exception{
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("classModel.id", id));
        criteria.add(Restrictions.eq("active",1));
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }
}
