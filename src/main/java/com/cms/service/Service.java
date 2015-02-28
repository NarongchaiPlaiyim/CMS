package com.cms.service;

import com.cms.service.security.UserDetail;
import com.cms.utils.AttributeName;
import com.cms.utils.FacesUtil;
import com.cms.utils.Utils;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;

import javax.annotation.Resource;
import java.io.*;

public abstract class Service  implements Serializable {
    @Resource protected Logger log;
    @Resource protected Logger moLogger;
    @Resource protected Logger mtLogger;


    protected void upLoadFile(UploadedFile file ,UserDetail user) throws Exception {

        if(null == file){
            throw new Exception("file is null!");
        }
        try {
            log.debug("upLoadFile()");
            log.debug("fileName : {}",file.getFileName());
            log.debug("contentType : {}",file.getContentType());
            log.debug("fileSize (M) : {}",file.getSize() / 60);
            InputStream in = file.getInputstream();
            String path = "C:\\tmp\\" + generateFileName(file.getFileName(),user.getUserName());
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

    private String generateFileName(String oldFileName,String userName){
        return String.format("%s_%s.%s",userName, Utils.getDocumentNo(), FilenameUtils.getExtension(oldFileName));
    }

}
