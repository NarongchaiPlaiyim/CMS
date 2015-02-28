package com.cms.model.dao;

import com.cms.model.db.AssignmentModel;
import com.cms.utils.Utils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssignmentDAO  extends GenericDAO<AssignmentModel, Integer>{

    public List<AssignmentModel> findBySubjectId(int subjectId){
        List<AssignmentModel> assignmentModels = Utils.getEmptyList();
        try {
            Criteria criteria = getCriteria();
            criteria.add(Restrictions.eq("subjectModel.id", subjectId));
            criteria.add(Restrictions.eq("active", 1));
            assignmentModels = criteria.list();
        } catch (Exception e) {
            log.debug("Exceprion error findBySubjectId : ", e);
        }

        return assignmentModels;
    }
}
