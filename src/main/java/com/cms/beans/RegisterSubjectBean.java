package com.cms.beans;

import com.cms.model.db.FileUploadModel;
import com.cms.model.db.SubjectModel;
import com.cms.service.RegisterSubjectService;
import com.cms.service.security.UserDetail;
import com.cms.utils.AttributeName;
import com.cms.utils.FacesUtil;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import java.util.List;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "registerSubjectBean")
public class RegisterSubjectBean extends Bean{
    private static final long serialVersionUID = 4112579994998374840L;
    @ManagedProperty("#{registerSubjectService}") private RegisterSubjectService registerSubjectService;
    private List<SubjectModel> subjectModelList;

    private SubjectModel subjectId;
    private String teacher;
    private UserDetail studentId;

    private int teacherId;
    private HttpSession httpSession;
    private List<FileUploadModel> fileUploadList;
    private StreamedContent fileDownload;

    @PostConstruct
    public void onCreation(){
        log.debug("onCreation().");
        if(preLoad() && isAuthorizeStudent()){
            init();
        }
    }

    private void init(){
        getAttribute();
        getTeacherId();
        subjectInTeacher();
    }

    private void getAttribute(){
        httpSession = FacesUtil.getSession(true);
        teacher = (String) httpSession.getAttribute(AttributeName.TEACHER.getName());
        studentId = (UserDetail) httpSession.getAttribute(AttributeName.USER_DETAIL.getName());
    }

    private void getTeacherId(){
        teacherId = registerSubjectService.getByTeacherName(teacher);
//        httpSession.setAttribute("teacherId", teacherId);
    }

    private void subjectInTeacher(){
        subjectModelList =  registerSubjectService.findByTeacherId(teacherId);
        httpSession.setAttribute(AttributeName.STUDENT_SUBJECT.getName(), subjectModelList);
    }

    public void onRegister(){
        registerSubjectService.register(subjectId, studentId.getId());
        showDialogSaved();
    }

    public void onSelectFileUploadBySubjectId(){
        log.debug("onSelectFileUploadByClassId()");

        try {
            fileUploadList =  registerSubjectService.findListFileByClassId(subjectId.getId());
            log.debug("fileUploadList : {}", fileUploadList.size());
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public StreamedContent onDownloadFile(int fileId){
        log.debug("onUploadFile ");
        try {

            fileDownload =  registerSubjectService.downloadFileById(fileId);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            log.error(e.getMessage());
        }
        return fileDownload;
    }
}
