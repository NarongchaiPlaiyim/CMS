package com.cms.beans;

import com.cms.model.db.ExaminationModel;
import com.cms.model.db.SubjectModel;
import com.cms.service.ExaminationService;
import com.cms.utils.FacesUtil;
import com.cms.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
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

    private final List<String> SEMESTER = Utils.getSemester();
    private final List<String> ACADEMICYEAR = Utils.getAcademicYear();
    private final List<String> EXAMTYPE = Utils.getExamType();

    @PostConstruct
    private void init(){
        examinationModel = new ExaminationModel();
        subjectModelSelected = new SubjectModel();
        onLoad();
        onInitList();
    }

    private void onInitList(){
        examinationModelList = examinationService.getList();
    }

    private void onLoad(){
        subjectModelList = (List<SubjectModel>) FacesUtil.getSession(true).getAttribute("subjectModelList");
    }

    public void onClickSubmit(){
        examinationService.create(examinationModel);
        showDialogSaved();
        init();
    }

    public void onClickAdd(){
        subjectModelSelected = new SubjectModel();
    }
}
