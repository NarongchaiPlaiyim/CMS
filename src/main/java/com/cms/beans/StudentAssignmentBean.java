package com.cms.beans;

import com.cms.model.dao.FileUploadDAO;
import com.cms.model.db.FileUploadModel;
import com.cms.model.db.StudentAssignmentModel;
import com.cms.model.db.SubjectModel;
import com.cms.service.StudentAssignmentService;
import com.cms.service.UploadService;
import com.cms.service.security.UserDetail;
import com.cms.utils.AttributeName;
import com.cms.utils.FacesUtil;
import com.cms.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import java.util.List;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "studentAssginmentBane")
public class StudentAssignmentBean extends Bean{
    private static final long serialVersionUID = 4112585632998374840L;
    @ManagedProperty("#{studentAssignmentService}") private StudentAssignmentService studentAssignmentService;

    private List<SubjectModel> subjectModelList;
    private SubjectModel subjectModel;
    private List<FileUploadModel> fileUploadList;
    private UploadedFile uploadedFile;
    @Resource
    private FileUploadDAO fileUploadDAO;
    @Resource private UploadService uploadService;
    private List<StudentAssignmentModel> studentAssignmentModelList;
    private int studentAssignmentId;
    private String fileName;
    private boolean flagUpload;
    HttpSession httpSession;
    private UserDetail studentId;

    @PostConstruct
    private void init(){
        httpSession = FacesUtil.getSession(false);
        subjectOnload();
    }

    private void subjectOnload(){
        subjectModelList = (List<SubjectModel>) httpSession.getAttribute(AttributeName.STUDENT_SUBJECT.getName());
    }

    public void onClickTable(){
        studentId = (UserDetail) httpSession.getAttribute(AttributeName.USER_DETAIL.getName());
        studentAssignmentModelList = studentAssignmentService.getStudentAssignment(subjectModel.getId(), studentId.getId());
    }

    public void onSave(){
        studentAssignmentService.save(studentAssignmentModelList);
        showDialogSaved();
    }

    public void onUpload(){
        log.debug("onClickAdd()");
        fileName = "";
    }

    public void onUploadFile(){
        log.debug("onUploadFile : [{}]", fileName);
        try {
            studentAssignmentService.uploadFile(fileName, uploadedFile, studentAssignmentId);
//            onSelectFileUploadBySubjectId();
//            showDialogSaved();
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public void onSelectFile(){
        if (Utils.isNull(studentAssignmentService.getById(studentAssignmentId).getFilename()) || "".equals(studentAssignmentService.getById(studentAssignmentId).getFilename())){
            fileName = "";
            flagUpload = false;
        } else {
            flagUpload = true;
            fileName = studentAssignmentService.getById(studentAssignmentId).getFilename();
        }
    }
}
