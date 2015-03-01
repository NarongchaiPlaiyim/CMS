package com.cms.service;

import com.cms.model.dao.AssignmentDAO;
import com.cms.model.dao.FileUploadDAO;
import com.cms.model.dao.StudentAssignmentDAO;
import com.cms.model.dao.SubjectDAO;
import com.cms.model.db.AssignmentModel;
import com.cms.model.db.FileUploadModel;
import com.cms.model.db.StudentAssignmentModel;
import com.cms.model.db.SubjectModel;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class AssignmentService extends Service{
    private static final long serialVersionUID = 4112578630941123456L;
    @Resource private AssignmentDAO assignmentDAO;
    @Resource private SubjectDAO subjectDAO;
    @Resource private StudentAssignmentDAO studentAssignmentDAO;
    @Resource private FileUploadDAO fileUploadDAO;
    @Resource private UploadService uploadService;

    public List<AssignmentModel> getAssignment(int subjectId){
        return assignmentDAO.findBySubjectId(subjectId);
    }

    public void save(AssignmentModel model, int subjectId){
        try {
            SubjectModel subjectModel = subjectDAO.findByID(subjectId);
            model.setSubjectModel(subjectModel);
            assignmentDAO.persist(model);
        } catch (Exception e) {
            log.debug("Exception error save : ", e);
        }
    }

    public void remove(int assignmentId){
        try {
            AssignmentModel model = assignmentDAO.findByID(assignmentId);
            model.setActive(0);
            assignmentDAO.update(model);
        } catch (Exception e) {
            log.debug("Exception error remove : ", e);
        }
    }

    public List<StudentAssignmentModel> getStudentAssignment(int assignmentId){
        return studentAssignmentDAO.findByAssignmentId(assignmentId);
    }

    public void saveScore(List<StudentAssignmentModel> modelList){
        for (StudentAssignmentModel model : modelList){
            try {
                studentAssignmentDAO.update(model);
            } catch (Exception e) {
                log.debug("Exception error saveScore : ", e);
            }
        }
    }

    public void uploadFile(FileUploadModel model , UploadedFile file,int classId)throws Exception{
        log.debug(" ClassTutorialService uploadFile() classId : {}",classId);

        uploadService.processUpload(model, file, UploadService.FileType.FILE_ASSIGNMENT,classId);

    }

    public List<FileUploadModel> findListFileByClassId(int id) throws Exception{
        log.debug("findListFileByClassId() id : {}",id);
        return fileUploadDAO.findByAssginmentId(id);
    }
}
