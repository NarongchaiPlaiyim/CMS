package com.cms.service;

import com.cms.model.dao.ExaminationDAO;
import com.cms.model.dao.StudentExaminationDAO;
import com.cms.model.db.ExaminationModel;
import com.cms.model.db.StudentExaminationModel;
import com.cms.model.db.SubjectModel;
import com.cms.utils.Utils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class ExaminationService extends Service {
    @Resource private ExaminationDAO examinationDAO;
    @Resource private StudentExaminationDAO studentExaminationDAO;

    public List<ExaminationModel> getList(SubjectModel model){
        List<ExaminationModel> examinationModelList = Utils.getEmptyList();
        try {
            examinationModelList = examinationDAO.findBySubject(model.getId());
        } catch (Exception e) {
            log.debug("Exception error load : ", e);
        }
        return examinationModelList;
    }

    public void create(ExaminationModel examinationModel){
        try {
            examinationDAO.persist(examinationModel);
        } catch (Exception e) {
            log.debug("Exception error save : ", e);
        }
    }

    public List<StudentExaminationModel> getStudentExamByExam(int examId){
        return studentExaminationDAO.findByExamId(examId);
    }

    public void remove(int examId){
        try {
            ExaminationModel model = examinationDAO.findByID(examId);
            model.setActive(0);
            examinationDAO.update(model);
        } catch (Exception e) {
            log.debug("Exception error remove : ", e);
        }
    }

    public void saveScore(List<StudentExaminationModel> modelList){
        for (StudentExaminationModel model : modelList){
            try {
                studentExaminationDAO.update(model);
            } catch (Exception e) {
                log.debug("Exception error saveScore : ", e);
            }
        }
    }

    public List<StudentExaminationModel> search(String year, String semeter, int examId){
        return studentExaminationDAO.findBySearch(year, semeter, examId);
    }
}
