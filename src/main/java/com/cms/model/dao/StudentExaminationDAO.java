package com.cms.model.dao;

import com.cms.model.db.StudentExaminationModel;
import com.cms.utils.Utils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentExaminationDAO extends GenericDAO<StudentExaminationModel, Integer>{

    public List<StudentExaminationModel> findByExamId(int examId){
        List<StudentExaminationModel> studentExaminationModels = Utils.getEmptyList();
        try {
            Criteria criteria = getCriteria();
            criteria.add(Restrictions.eq("examinationModel.id", examId));
            studentExaminationModels = criteria.list();
        } catch (Exception e) {
            log.debug("Exception error findByExamId : ", e);
        }

        return studentExaminationModels;
    }

    public List<StudentExaminationModel> findBySearch(String year, String semester, int examId){
        List<StudentExaminationModel> studentExaminationModelList = Utils.getEmptyList();
        try {
            Criteria criteria = getSession().createCriteria(StudentExaminationModel.class, "studentExam");
            criteria.createAlias("studentExam.examinationModel", "exam");
            criteria.createAlias("exam.subjectModel", "subject");
            if (!Utils.isZero(year.trim().length())){
                criteria.add(Restrictions.eq("subject.year", year.trim()));
            }

            if (!Utils.isZero(semester.trim().length())){
                criteria.add(Restrictions.eq("semester.year", semester.trim()));
            }

            if (!Utils.isZero(examId)){
                criteria.add(Restrictions.eq("examinationModel.id", examId));
            }

            studentExaminationModelList = criteria.list();
        } catch (Exception e) {
            log.debug("Exception error findBySearch");
        }
        return studentExaminationModelList;
    }

    public StudentExaminationModel findByExamIdAndStudentId(int examId, int studentId){
        StudentExaminationModel studentExaminationModel = null;
        try {
            Criteria criteria = getCriteria();
            criteria.add(Restrictions.eq("examinationModel.id", examId));
            criteria.add(Restrictions.eq("userModel.id", studentId));
            studentExaminationModel = (StudentExaminationModel) criteria.uniqueResult();
        } catch (Exception e) {
            log.debug("Exception error findByExamIdAndStudentId : ", e);
        }

        return studentExaminationModel;
    }
}
