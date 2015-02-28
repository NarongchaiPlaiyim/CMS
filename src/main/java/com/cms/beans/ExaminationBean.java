package com.cms.beans;

import com.cms.model.db.ExaminationModel;
import com.cms.model.db.StudentExaminationModel;
import com.cms.model.db.SubjectModel;
import com.cms.service.ExaminationService;
import com.cms.utils.FacesUtil;
import com.cms.utils.MessageDialog;
import com.cms.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "examinationBean")
public class ExaminationBean extends Bean {
    private static final long serialVersionUID = 4112578684029812345L;
    @ManagedProperty("#{examinationService}") private ExaminationService examinationService;

    private List<ExaminationModel> examinationModelList;
    private ExaminationModel examinationModel;
    private List<SubjectModel> subjectModelList;
    private SubjectModel subjectModelSelected;
    private List<StudentExaminationModel> studentExaminationModelList;

    private final List<String> SEMESTER = Utils.getSemester();
    private final List<String> ACADEMICYEAR = Utils.getAcademicYear();
    private final List<String> EXAMTYPE = Utils.getExamType();
    private int examId;

    private boolean flagBtnAdd = true;
    @PostConstruct
    private void init(){
        examinationModel = new ExaminationModel();
        subjectModelSelected = new SubjectModel();
        onLoad();
        onInitList();
    }

    private void onInitList(){
        examinationModelList = new ArrayList<ExaminationModel>();
    }

    private void onLoad(){
        subjectModelList = (List<SubjectModel>) FacesUtil.getSession(true).getAttribute("subjectModelList");
    }

    public void onClickSubmit(){
        examinationModel.setSubjectModel(subjectModelSelected);
        examinationService.create(examinationModel);
        showDialogSaved();
        examinationModelList = examinationService.getList(subjectModelSelected);
    }

    public void onClickAdd(){
        examinationModel = new ExaminationModel();
    }

    public void onClickTable(){
        flagBtnAdd = false;
        examinationModelList = examinationService.getList(subjectModelSelected);
    }

    public void getStudentExam(){
        studentExaminationModelList = examinationService.getStudentExamByExam(examId);
        log.debug("---------- : {}", studentExaminationModelList.size());
    }

    public void preDelete(){
        showDialog(MessageDialog.WARNING.getMessageHeader(), "Are you want to delete?", "confirmDlg");
    }

    public void onClickDelete(){
        examinationService.remove(examId);
        onClickTable();
        showDialog("Remove", "Sucess", "msgBoxSystemMessageDlg");
    }

    public void onEditScore(){
        examinationService.saveScore(studentExaminationModelList);
        showDialogSaved();
    }

    public void getExamBySearch(){
        studentExaminationModelList = examinationService.search(subjectModelSelected.getYear(), subjectModelSelected.getSemester(), examId);
    }
}
