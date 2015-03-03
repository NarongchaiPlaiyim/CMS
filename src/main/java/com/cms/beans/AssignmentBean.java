package com.cms.beans;

import com.cms.model.db.*;
import com.cms.service.AssignmentService;
import com.cms.utils.FacesUtil;
import com.cms.utils.Utils;
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
@ManagedBean(name = "assignmentBean")
public class AssignmentBean extends Bean{
    private static final long serialVersionUID = 4112579994998374840L;
    @ManagedProperty("#{assignmentService}") private AssignmentService assignmentService;
    private List<SubjectModel> subjectModelList;
    private SubjectModel subjectModelSelected;
    private List<AssignmentModel> assignmentModels;
    private AssignmentModel assignmentModel;
    private final List<String> SEMESTER = Utils.getSemester();
    private final List<String> Year = Utils.getAcademicYear();
    private List<StudentAssignmentModel> studentAssignmentModelList;
    private List<EnrollModel> enrollModelList;
    private FileUploadModel fileUploadSelected;
    private List<FileUploadModel> fileUploadList;
    private UploadedFile uploadedFile;

    private String selectSemester;
    private String selectYear;
    private boolean flagAdd;
    private int assignmentId;
    private StreamedContent fileDownload;

    @PostConstruct
    public void onCreation(){
        log.debug("onCreation().");
        if(preLoad() && isAuthorizeTeacher()){
            init();
        }
    }

    private void init(){
        subjectModelSelected = new SubjectModel();
        onload();
        flagAdd = true;
    }

    private void onload(){
        HttpSession session = FacesUtil.getSession(true);

        subjectModelList = (List<SubjectModel>) session.getAttribute("subjectModelList");
    }

    public void onClickAdd(){
        assignmentModel  = new AssignmentModel();
        showDialog("assignmetDlg");
    }

    public void getAssignmentBySubject(){
        flagAdd = false;
        assignmentModels = assignmentService.getAssignment(subjectModelSelected.getId());
    }

    public void addAssignment(){
        assignmentService.save(assignmentModel, subjectModelSelected.getId());
        showDialogSaved();
        assignmentModels = assignmentService.getAssignment(subjectModelSelected.getId());
    }

    public void onClickDelete(){
        assignmentService.remove(assignmentId);
        getAssignmentBySubject();
    }

    public void loadStudentAssignment(){
        studentAssignmentModelList = assignmentService.getStudentAssignment(assignmentId);
    }

    public void onAddScore(){
        for (StudentAssignmentModel model : studentAssignmentModelList){
            log.debug("---------- {}", model.getScore());
        }
        assignmentService.saveScore(studentAssignmentModelList);
        showDialogSaved();
    }

    public void onUpload(){
        log.debug("onClickAdd()");
        fileUploadSelected = new FileUploadModel();
    }

    public void onSelectFileUploadByAssignmentId(){
        log.debug("onSelectFileUploadByClassId()");

        try {
            fileUploadList =  assignmentService.findListFileByClassId(assignmentId);
            log.debug("fileUploadList : {}", fileUploadList.size());
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

    public void onUploadFile(){
        log.debug("onUploadFile : [{}]", fileUploadSelected.toString());
        try {
            assignmentService.uploadFile(fileUploadSelected, uploadedFile, assignmentId);
            onSelectFileUploadByAssignmentId();
//            showDialogSaved();
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public void onDeleteFile(){
        log.debug("onDeleteFile : [{}]", fileUploadSelected.toString());
        try {
            assignmentService.deleteFileById(fileUploadSelected.getId());
            onSelectFileUploadByAssignmentId();
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public StreamedContent onDownloadFile(int fileId){
        log.debug("onUploadFile ");
        try {

            fileDownload =  assignmentService.downloadFileById(fileId);
            System.out.println("**************  "+fileDownload.getName() +"  "+fileDownload.getContentType());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            log.error(e.getMessage());
        }
        return fileDownload;
    }
}
