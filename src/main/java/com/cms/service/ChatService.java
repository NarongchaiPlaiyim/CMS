package com.cms.service;

import com.cms.model.dao.ClassDAO;
import com.cms.model.dao.FileUploadDAO;
import com.cms.model.dao.SubjectDAO;
import com.cms.model.db.ClassEntity;
import com.cms.model.db.FileUploadModel;
import com.cms.model.db.SubjectModel;
import com.cms.service.security.UserDetail;
import com.cms.utils.Utils;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class ChatService extends Service {

    @Resource
    private ClassDAO classDao;
    @Resource
    private UploadService uploadService;
    @Resource
    private FileUploadDAO fileUploadDAO;

    public ClassEntity findClassById(int id)throws Exception{
        log.debug("ClassTutorialService findClassById() id : {}",id);

        return classDao.findByID(id);
    }

    public List<FileUploadModel> findListFileByClassId(int id) throws Exception{
        log.debug("findListFileByClassId() id : {}",id);
        return fileUploadDAO.findByClassId(id);
    }

    public void uploadFile(FileUploadModel model , UploadedFile file,int classId)throws Exception{
        log.debug(" ClassTutorialService uploadFile() classId : {}",classId);

        uploadService.processUpload(model, file, UploadService.FileType.FILE_CLASS,classId);

    }


    public void deleteClassById(int id)throws Exception{
        log.debug(" ClassTutorialService deleteClassById() id : {}",id);
        ClassEntity entity  = classDao.findByID(id);
        entity.setActive(0);
        classDao.update(entity);
    }


}
