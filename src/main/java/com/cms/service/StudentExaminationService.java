package com.cms.service;

import com.cms.model.dao.ExaminationDAO;
import com.cms.model.dao.StudentExaminationDAO;
import com.cms.model.dao.UserDAO;
import com.cms.model.db.ExaminationModel;
import com.cms.model.db.StudentExaminationModel;
import com.cms.model.db.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class StudentExaminationService extends Service{
    private static final long serialVersionUID = 4112578630941419914L;
    @Resource private ExaminationDAO examinationDAO;
    @Resource private UserDAO userDAO;
    @Resource private StudentExaminationDAO studentExaminationDAO;

    public List<StudentExaminationModel> getStudentAssignment(int subjectId, int studentId){
        List<StudentExaminationModel> studentExaminationModelList = new ArrayList<StudentExaminationModel>();

        try {
            List<ExaminationModel> examinationModelList = examinationDAO.findBySubject(subjectId);
            UserModel userModel = userDAO.findByID(studentId);

            for (ExaminationModel model : examinationModelList){
                studentExaminationModelList.add(studentExaminationDAO.findByExamIdAndStudentId(model.getId(), userModel.getId()));
            }
        } catch (Exception e) {
            log.debug("Exception error getStudentAssignment : ", e);
        }


        return studentExaminationModelList;
    }
}
