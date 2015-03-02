package com.cms.service;

import com.cms.model.dao.EnrollDAO;
import com.cms.model.dao.FileUploadDAO;
import com.cms.model.dao.SubjectDAO;
import com.cms.model.dao.UserDAO;
import com.cms.model.db.EnrollModel;
import com.cms.model.db.FileUploadModel;
import com.cms.model.db.SubjectModel;
import com.cms.model.db.UserModel;
import com.cms.utils.Utils;
import org.primefaces.model.UploadedFile;
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
    @Resource private FileUploadDAO fileUploadDAO;
    @Resource private FileManagementService uploadService;

    public List<SubjectModel> getSubject(int userId){
        return subjectDAO.findByUserId(userId);
    }

    public void save(SubjectModel subject, int user){
        try {
            if (Utils.isZero(subject.getId())){
                UserModel model = userDAO.findByID(user);
                subject.setUserModel(model);
                subjectDAO.persist(subject);
            } else {
                subjectDAO.update(subject);
            }
        } catch (Exception e) {
            log.debug("Exception error save : ", e);
        }
    }

    public void remove(int subjectId){
        try {
            SubjectModel model = subjectDAO.findByID(subjectId);
            model.setActive(0);
            subjectDAO.update(model);
        } catch (Exception e) {
            log.debug("Exception error remove : ", e);
        }
    }

    public List<EnrollModel> getStudent(int subjectId){
        return enrollDAO.findBySubjectId(subjectId);
    }

    public SubjectModel edit(int subjectId){
        try {
            return subjectDAO.findByID(subjectId);
        } catch (Exception e) {
            log.debug("Exception error edit : ", e);
            return new SubjectModel();
        }
    }

    public void uploadFile(FileUploadModel model , UploadedFile file,int classId)throws Exception{
        log.debug(" ClassTutorialService uploadFile() classId : {}",classId);

        uploadService.processUpload(model, file, FileManagementService.FileType.FILE_SUBJECT,classId);

    }

    public List<FileUploadModel> findListFileByClassId(int id) throws Exception{
        log.debug("findListFileByClassId() id : {}",id);
        return fileUploadDAO.findBySubjectId(id);
    }

    public void deleteFileById(int id)throws Exception{
        log.debug(" ClassTutorialService deleteFileById() id : {}",id);
        uploadService.processDelete(id);

    }
}
