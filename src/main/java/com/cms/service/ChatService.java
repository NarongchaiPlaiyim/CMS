package com.cms.service;

import com.cms.model.dao.BoardDAO;
import com.cms.model.dao.ClassDAO;
import com.cms.model.dao.FileUploadDAO;
import com.cms.model.dao.UserDAO;
import com.cms.model.db.BoardModel;
import com.cms.model.db.ClassEntity;
import com.cms.model.db.FileUploadModel;
import org.primefaces.model.StreamedContent;
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
    private FileManagementService fileManagementService;
    @Resource
    private FileUploadDAO fileUploadDAO;
    @Resource
    private BoardDAO boardDAO;
    @Resource
    private UserDAO userDAO;

    public ClassEntity findClassById(int id)throws Exception{
        log.debug("ClassTutorialService findClassById() id : {}",id);

        return classDao.findByID(id);
    }

    public List<FileUploadModel> findListFileByClassId(int id) throws Exception{
        log.debug("findListFileByClassId() id : {}",id);
        return fileUploadDAO.findByClassId(id);
    }

    public List<BoardModel> findListChatMessageByClassId(int id) throws Exception{
        log.debug("findListFileByClassId() id : {}",id);
        return boardDAO.findByClassId(id);
    }

    public void addChatMessage(BoardModel boardModel ,int userId ,int classId) throws Exception{
        log.debug("addChatMessage() userId : {} , classId : {}",userId,classId);

        boardModel.setClassModel(classDao.findByID(classId));
        boardModel.setUserModel(userDAO.findByID(userId));
        boardDAO.persist(boardModel);
    }

    public void uploadFile(FileUploadModel model , UploadedFile file,int classId)throws Exception{
        log.debug(" ClassTutorialService uploadFile() classId : {}",classId);
        fileManagementService.processUpload(model, file, FileManagementService.FileType.FILE_CLASS,classId);
    }

    public StreamedContent downloadFileById(int id)throws Exception{
        log.debug(" ClassTutorialService downloadFileById() id : {}",id);
        return fileManagementService.processDownLoad(id, 0);
    }

    public void deleteFileById(int id)throws Exception{
        log.debug(" ClassTutorialService deleteFileById() id : {}",id);
        fileManagementService.processDelete(id);

    }





}
