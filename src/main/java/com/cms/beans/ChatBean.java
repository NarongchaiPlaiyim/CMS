package com.cms.beans;

import com.cms.model.db.ClassEntity;
import com.cms.model.db.FileUploadModel;
import com.cms.model.db.SubjectModel;
import com.cms.service.ChatService;
import com.cms.service.ClassTutorialService;
import com.cms.utils.FacesUtil;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "chatBean")
public class ChatBean extends Bean {
    private static final long serialVersionUID = 4112585632998374840L;
    @ManagedProperty("#{chatService}") private ChatService chatService;

    private ClassEntity classSelected;
    private List<FileUploadModel> fileUploadList;
    private FileUploadModel fileUploadSelected;
    private UploadedFile uploadedFile;
    private int thisClassId;

    private StreamedContent fileDownload;
    @PostConstruct
    public void onCreation(){
        log.debug("onCreation().");
        if(preLoad() && isAuthorizeTeacher()){
            init();
        }
    }

    private void init(){
        onload();
    }

    private void onload(){
        HttpSession session = FacesUtil.getSession(true);
        thisClassId = (Integer) session.getAttribute("chatClassId");
        try {
            classSelected = chatService.findClassById(thisClassId);
            onSelectFileUploadByClassId(thisClassId);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

    public void onSelectFileUploadByClassId(int id){
        log.debug("onSelectFileUploadByClassId()");

        try {
            fileUploadList =  chatService.findListFileByClassId(id);

            System.out.println("cccccccccccccc : "+fileUploadList.size()+" id : "+id);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }


    public void onClickAdd(){
        log.debug("onClickAdd()");
        fileUploadSelected = new FileUploadModel();
//        fileUploadSelected.se
    }

    public void onUploadFile(){
        log.debug("onUploadFile : [{}]", fileUploadSelected.toString());
        try {
            chatService.uploadFile(fileUploadSelected, uploadedFile,thisClassId);
            onSelectFileUploadByClassId(thisClassId);
//            showDialogSaved();
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public void onDownloadFile(){
        log.debug("onUploadFile ");
        try {

           fileDownload =  chatService.downloadFileById(2);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            log.error(e.getMessage());
        }

    }

    public void onDeleteFile(){
        log.debug("onDeleteFile : [{}]", fileUploadSelected.toString());
        try {
            chatService.deleteFileById(fileUploadSelected.getId());
            onSelectFileUploadByClassId(thisClassId);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }


}
