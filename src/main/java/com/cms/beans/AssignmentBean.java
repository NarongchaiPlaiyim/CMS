package com.cms.beans;

import com.cms.model.db.AssignmentModel;
import com.cms.model.db.StudentAssignmentModel;
import com.cms.model.db.SubjectModel;
import com.cms.service.AssignmentService;
import com.cms.utils.FacesUtil;
import com.cms.utils.MessageDialog;
import com.cms.utils.Utils;
import lombok.Getter;
import lombok.Setter;

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

    private String selectSemester;
    private String selectYear;
    private boolean flagAdd;
    private int assignmentId;

    @PostConstruct
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

    public void preDelete(){
        showDialog(MessageDialog.WARNING.getMessageHeader(), "Are you want to delete?", "confirmDlg");
    }

    public void onClickDelete(){
        assignmentService.remove(assignmentId);
        getAssignmentBySubject();
        showDialog("Remove", "Sucess", "msgBoxSystemMessageDlg");
    }

    public void loadStudentAssignment(){
        studentAssignmentModelList = assignmentService.getStudentAssignment(assignmentId);
    }

    public void onAddScore(){
        assignmentService.saveScore(studentAssignmentModelList);
        showDialogSaved();
    }
}
