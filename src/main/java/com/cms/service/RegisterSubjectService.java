package com.cms.service;

import com.cms.model.dao.SubjectDAO;
import com.cms.model.db.SubjectModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class RegisterSubjectService extends Service{
    @Resource private SubjectDAO subjectDAO;

    public List<SubjectModel> findByTeacherId(int teacherId){
        return subjectDAO.findByUserId(teacherId);
    }
}
