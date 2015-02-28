package com.cms.model.dao;

import com.cms.model.db.EnrollModel;
import com.cms.utils.Utils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnrollDAO extends GenericDAO<EnrollModel, Integer> {

    public List<EnrollModel> findBySubjectId(int subjectId){
        List<EnrollModel> enrollModelList = Utils.getEmptyList();
        try {
            Criteria criteria = getCriteria();
            criteria.add(Restrictions.eq("subjectModel.id", subjectId));
            enrollModelList = criteria.list();
        } catch (Exception e) {
            log.debug("Exception error findBySubjectId : ", e);
        }

        return enrollModelList;
    }
}