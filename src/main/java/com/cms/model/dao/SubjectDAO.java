package com.cms.model.dao;

import com.cms.model.db.SubjectModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubjectDAO extends GenericDAO<SubjectModel, Integer> {

    public List<SubjectModel> findByUserId(int userId){
        List<SubjectModel> subjectModelList = new ArrayList<SubjectModel>();

        try {
            Criteria criteria = getCriteria();
            criteria.add(Restrictions.eq("userModel.id", userId));
            criteria.addOrder(Order.asc("subjectCode"));
            subjectModelList = criteria.list();
        } catch (Exception e) {
            log.debug("Exception error findByUserId : ", e);
        }

        return subjectModelList;
    }
}
