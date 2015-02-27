package com.cms.service;

import com.cms.model.dao.EnrollDAO;
import com.cms.model.dao.SubjectDAO;
import com.cms.model.dao.UserDAO;
import com.cms.model.db.EnrollModel;
import com.cms.model.db.SubjectModel;
import com.cms.model.db.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class SubjectService extends Service {
    private static final long serialVersionUID = 4112578630941123456L;
    @Resource private SubjectDAO subjectDAO;
    @Resource private UserDAO userDAO;
    @Resource private EnrollDAO enrollDAO;

    public List<SubjectModel> getSubject(int userId){
        return subjectDAO.findByUserId(userId);
    }

    public void save(SubjectModel subject, int user){
        try {
            UserModel model = userDAO.findByID(user);
            subject.setUserModel(model);
            log.debug("save subject : [{}]", subject.toString());
            subjectDAO.persist(subject);
        } catch (Exception e) {
            log.debug("Exception error save : ", e);
        }
    }

    public void remove(int subjectId){
        try {
            subjectDAO.delete(subjectDAO.findByID(subjectId));
        } catch (Exception e) {
            log.debug("Exception error remove : ", e);
        }
    }

    public List<EnrollModel> getStudent(int subjectId){
        return enrollDAO.findBySubjectId(subjectId);
    }
}
