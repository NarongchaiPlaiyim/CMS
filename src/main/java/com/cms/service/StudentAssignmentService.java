package com.cms.service;

import com.cms.model.dao.*;
import com.cms.model.db.*;
import com.cms.utils.Utils;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class StudentAssignmentService extends Service{
    @Resource private SubjectDAO subjectDAO;
    @Resource private AssignmentDAO assignmentDAO;
    @Resource private StudentAssignmentDAO studentAssignmentDAO;
    @Resource private UserDAO userDAO;
    @Resource private FileManagementService uploadService;
    @Resource private FileUploadDAO fileUploadDAO;

    public List<SubjectModel> findByTeacherId(int teacherId){
        return subjectDAO.findByUserId(teacherId);
    }

    public List<StudentAssignmentModel> getStudentAssignment(int subjectId, int studentId){
        List<StudentAssignmentModel> studentAssignmentModelList = new ArrayList<StudentAssignmentModel>();

        try {
            List<AssignmentModel> assignmentModelList = assignmentDAO.findBySubjectId(subjectId);
            UserModel userModel = userDAO.findByID(studentId);
            for (AssignmentModel model : assignmentModelList){
                studentAssignmentModelList.add(studentAssignmentDAO.findByAssignmentIdAndStudent(model.getId(), userModel.getId()));
            }
        } catch (Exception e) {
            log.debug("Exception error getStudentAssignment : ", e);
        }


        return studentAssignmentModelList;
    }

    public void save(List<StudentAssignmentModel> studentAssignmentModelList){
        for(StudentAssignmentModel model : studentAssignmentModelList){
            try {
                studentAssignmentDAO.update(model);
            } catch (Exception e) {
                log.debug("Exception error save : ", e);
            }
        }
    }

    public void uploadFile(String fileName , UploadedFile file,int studentAssignId)throws Exception{
        log.debug(" ClassTutorialService uploadFile() classId : {}",studentAssignId);
        StudentAssignmentModel model = studentAssignmentDAO.findByID(studentAssignId);
        String name =  Utils.getDocumentNo() + "_" + file.getFileName();
        uploadService.upLoadFile(file, name);
        uploadService.upLoadFile(file, name);
        model.setFilename(fileName);
        model.setLocation(name);
        model.setSubmitStatus(true);
        studentAssignmentDAO.update(model);
    }

    public StudentAssignmentModel getById(int studentAssignId){
        try {
            return studentAssignmentDAO.findByID(studentAssignId);
        } catch (Exception e) {
            log.debug("Exception error getById : ", e);
            return null;
        }
    }

    public List<FileUploadModel> findListFileByClassId(int id) throws Exception{
        log.debug("findListFileByClassId() id : {}",id);
        return fileUploadDAO.findByAssginmentId(id);
    }

    public StreamedContent downloadFileById(int id)throws Exception{
        log.debug(" ClassTutorialService downloadFileById() id : {}",id);
        return uploadService.processDownLoad(id, 0);
    }
}
