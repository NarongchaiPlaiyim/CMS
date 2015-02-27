package com.cms.service;

import com.cms.model.dao.SubjectDAO;
import com.cms.model.db.SubjectModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class SubjectService extends Service {
    private static final long serialVersionUID = 4112578630941123456L;
    @Resource private SubjectDAO subjectDAO;

    public List<SubjectModel> getSubject(int userId){
        return subjectDAO.findByUserId(userId);
    }
}
