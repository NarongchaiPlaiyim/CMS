package com.cms.model.dao;

import com.cms.model.db.StudentAssignmentModel;
import com.cms.utils.Utils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentAssignmentDAO extends GenericDAO<StudentAssignmentModel, Integer>{

    public List<StudentAssignmentModel> findByAssignmentId(int assignmentId){
        List<StudentAssignmentModel> assignmentModelList = Utils.getEmptyList();
        try {
            Criteria criteria = getCriteria();
            criteria.add(Restrictions.eq("assignmentModel.id", assignmentId));
            assignmentModelList = criteria.list();
        } catch (Exception e) {
            log.debug("Exception error findByAssignmentId : ", e);
        }

        return assignmentModelList;
    }
}
