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

}
