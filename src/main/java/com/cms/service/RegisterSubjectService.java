package com.cms.service;

import com.cms.model.dao.*;
import com.cms.model.db.*;
import com.cms.utils.Utils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Component
@Transactional
public class RegisterSubjectService extends Service{
    @Resource private SubjectDAO subjectDAO;
    @Resource private UserDAO userDAO;
    @Resource private EnrollDAO enrollDAO;
    @Resource private AssignmentDAO assignmentDAO;
    @Resource private StudentAssignmentDAO studentAssignmentDAO;
    @Resource private ExaminationDAO examinationDAO;
    @Resource private StudentExaminationDAO studentExaminationDAO;

    public List<SubjectModel> findByTeacherId(int teacherId){
        return subjectDAO.findByUserId(teacherId);
    }

    public void register(SubjectModel subjectId, int studentId){
        try {
            EnrollModel enrol = enrollDAO.findByStudentIdAndSubjectId(subjectId.getId(), studentId);
            UserModel model = userDAO.findByID(studentId);

            if (Utils.isNull(enrol)){
                EnrollModel enrollModel = new EnrollModel();
                enrollModel.setSubjectModel(subjectId);
                enrollModel.setUserModel(model);
                enrollDAO.persist(enrollModel);
            }

            assignmentSubject(subjectId.getId(), model);
        } catch (Exception e) {
            log.debug("Exception error register : ", e);
        }
    }

    private void assignmentSubject(int subjectId, UserModel model){
        List<AssignmentModel> assignmentModelList = assignmentDAO.findBySubjectId(subjectId);

        for (AssignmentModel assignmentModel : assignmentModelList){
            StudentAssignmentModel assignment = studentAssignmentDAO.findByAssignmentIdAndSubjectId(assignmentModel.getId(), model.getId());

            if (Utils.isNull(assignment)){
                StudentAssignmentModel studentAssignmentModel = new StudentAssignmentModel();
                studentAssignmentModel.setAssignmentModel(assignmentModel);
                studentAssignmentModel.setUserModel(model);
                studentAssignmentModel.setSubmitStatus(false);
                studentAssignmentModel.setScore(BigDecimal.ZERO);

                try {
                    studentAssignmentDAO.persist(studentAssignmentModel);
                } catch (Exception e) {
                    log.debug("Exception error assignmentSubject : ", e);
                }
            }
        }

        examinationSubject(subjectId, model);
    }

    private void examinationSubject(int subjectId, UserModel model){
        try {
            List<ExaminationModel> examinationModelList = examinationDAO.findBySubject(subjectId);

            for (ExaminationModel examinationModel : examinationModelList){
                StudentExaminationModel examination = studentExaminationDAO.findByExamIdAndStudentId(examinationModel.getId(), model.getId());

                if (Utils.isNull(examination)){
                    StudentExaminationModel studentExaminationModel = new StudentExaminationModel();
                    studentExaminationModel.setExaminationModel(examinationModel);
                    studentExaminationModel.setUserModel(model);
                    studentExaminationModel.setScore(BigDecimal.ZERO);

                    studentExaminationDAO.persist(studentExaminationModel);
                }
            }
        } catch (Exception e) {
            log.debug("Exception error examinationSubject : ", e);
        }
    }
}
