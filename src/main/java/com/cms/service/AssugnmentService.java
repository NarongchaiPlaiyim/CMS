package com.cms.service;

import com.cms.model.dao.AssignmentDAO;
import com.cms.model.db.AssignmentModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class AssugnmentService extends Service{
    private static final long serialVersionUID = 4112578630941123456L;
    @Resource private AssignmentDAO assignmentDAO;

    public List<AssignmentModel> getAssignment(int subjectId){
        return assignmentDAO.findBySubjectId(subjectId);
    }
}
