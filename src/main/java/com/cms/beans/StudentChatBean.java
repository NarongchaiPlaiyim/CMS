package com.cms.beans;

import com.cms.model.db.BoardModel;
import com.cms.model.db.ClassEntity;
import com.cms.model.db.FileUploadModel;
import com.cms.service.StudentChatService;
import com.cms.utils.FacesUtil;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import java.util.List;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "studentChatBean")
public class StudentChatBean extends Bean{
    private static final long serialVersionUID = 4112585632998374840L;
    @ManagedProperty("#{studentChatService}") private StudentChatService studentChatService;

    private ClassEntity classSelected;
    private List<FileUploadModel> fileUploadList;
    private List<BoardModel> boardModelList;
    private FileUploadModel fileUploadSelected;
    private BoardModel chatMessage;
    private UploadedFile uploadedFile;
    private int thisClassId;

    private StreamedContent fileDownload;
    @PostConstruct
    public void onCreation(){
        log.debug("onCreation().");
        if(preLoad() && isAuthorizeStudent()){
            init();
        }
    }

    private void init(){
        chatMessage = new BoardModel();
        onload();
    }

    private void onload(){
        HttpSession session = FacesUtil.getSession(true);
        thisClassId = (Integer) session.getAttribute("chatClassId");
        try {
            classSelected = studentChatService.findClassById(thisClassId);
            onSelectFileUploadByClassId(thisClassId);
            onSelectChatMessageByClassId(thisClassId);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

    public void onSelectFileUploadByClassId(int id){
        log.debug("onSelectFileUploadByClassId()");

        try {
            fileUploadList =  studentChatService.findListFileByClassId(id);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

    public void onSelectChatMessageByClassId(int id){
        log.debug("onSelectChatMessageByClassId()");

        try {
            boardModelList =  studentChatService.findListChatMessageByClassId(id);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

    public void onAddChatMessage(){
        log.debug("onAddChatMessage()");
        try {
            studentChatService.addChatMessage(chatMessage, getUser().getId(), thisClassId);
            onSelectChatMessageByClassId(thisClassId);
            chatMessage = new BoardModel();
//            chatMessage.setDetail("");
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
            studentChatService.uploadFile(fileUploadSelected, uploadedFile,thisClassId);
            onSelectFileUploadByClassId(thisClassId);
//            showDialogSaved();
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public StreamedContent onDownloadFile(int fileId){
        log.debug("onUploadFile ");
        try {

            fileDownload =  studentChatService.downloadFileById(fileId);
            System.out.println("**************  "+fileDownload.getName() +"  "+fileDownload.getContentType());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            log.error(e.getMessage());
        }
        return fileDownload;
    }

    public void onDeleteFile(){
        log.debug("onDeleteFile : [{}]", fileUploadSelected.toString());
        try {
            studentChatService.deleteFileById(fileUploadSelected.getId());
            onSelectFileUploadByClassId(thisClassId);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
