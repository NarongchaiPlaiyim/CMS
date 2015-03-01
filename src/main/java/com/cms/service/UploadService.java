package com.cms.service;

import com.cms.model.dao.AssignmentDAO;
import com.cms.model.dao.ClassDAO;
import com.cms.model.dao.FileUploadDAO;
import com.cms.model.dao.SubjectDAO;
import com.cms.model.db.ClassEntity;
import com.cms.model.db.FileUploadModel;
import com.cms.utils.Utils;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@Component
@Transactional
public class UploadService extends Service {
    protected enum FileType {FILE_CLASS,FILE_SUBJECT,FILE_ASSIGNMENT}

    @Resource
    private FileUploadDAO fileUploadDAO;

    @Resource
    private ClassDAO classDAO;
    @Resource
    private SubjectDAO subjectDAO;
    @Resource
    private AssignmentDAO assignmentDAO;

    public void processUpload(FileUploadModel model , UploadedFile file,FileType fileType,int idType) throws Exception{
        log.debug("processUpload()");
        String generateFileName = generateFileName(file.getFileName(),fileType);
        log.debug("generateFilename : {}",generateFileName);
        upLoadFile(file,generateFileName);

        switch (fileType){
            case FILE_CLASS : model.setClassModel(classDAO.findByID(idType));   break;
            case FILE_ASSIGNMENT : model.setAssignmentModel(assignmentDAO.findByID(idType));   break;
            case FILE_SUBJECT : model.setSubjectModel(subjectDAO.findByID(idType));   break;
            default: break;
        }
        model.setLocation(generateFileName);
        fileUploadDAO.persist(model);
    }

    public void processDownLoad()throws Exception{
        log.debug("processDownLoad()");
    }

    public void processDelete()throws Exception{
        log.debug("processDelete()");
    }

    private void upLoadFile(UploadedFile file ,String fileName) throws Exception {

        if(null == file){
            throw new Exception("file is null!");
        }
        try {
            log.debug("upLoadFile()");
            log.debug("fileName : {}",file.getFileName());
            log.debug("contentType : {}",file.getContentType());
            log.debug("fileSize (M) : {}",file.getSize() / 60);
            InputStream in = file.getInputstream();
            String path = "C:\\tmp\\" + fileName;
            System.out.println(path);
            File resultFile = new File(path);
            OutputStream out = new FileOutputStream(resultFile);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("", e);
            throw new Exception("fileupload failed!");
        }
    }

    private String generateFileName(String oldFileName,FileType fileType){
        return String.format("%s_%s.%s", Utils.getDocumentNo(),fileType.toString(), FilenameUtils.getExtension(oldFileName));
    }


}