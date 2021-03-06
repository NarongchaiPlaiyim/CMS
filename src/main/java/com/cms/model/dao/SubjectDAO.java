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
            criteria.add(Restrictions.eq("active", 1));
            criteria.addOrder(Order.asc("subjectCode"));
            subjectModelList = criteria.list();
        } catch (Exception e) {
            log.debug("Exception error findByUserId : ", e);
        }

        return subjectModelList;
    }

    public SubjectModel findByDup(SubjectModel subjectModel){
        SubjectModel model = null;

        try {
            Criteria criteria = getCriteria();
            criteria.add(Restrictions.eq("subjectCode", subjectModel.getSubjectCode()));
            criteria.add(Restrictions.eq("semester", subjectModel.getSemester()));
            criteria.add(Restrictions.eq("year", subjectModel.getYear()));
            model = (SubjectModel) criteria.uniqueResult();
        } catch (Exception e) {
            log.debug("Exception error findByDup : ", e);
        }

        return model;
    }
}
