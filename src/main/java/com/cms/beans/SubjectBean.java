package com.cms.beans;

import com.cms.model.db.SubjectModel;
import com.cms.service.SubjectService;
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
@ManagedBean(name = "subjectBean")
public class SubjectBean extends Bean {
    private static final long serialVersionUID = 4112578634998374840L;
    @ManagedProperty("#{subjectService}") private SubjectService subjectService;
    private List<SubjectModel> subjectModelList;
    private SubjectModel subjectModelSelected;
    private final List<String> SEMESTER = Utils.getSemester();
    private String semesterSelected;

    @PostConstruct
    private void init(){
        subjectModelList = new ArrayList<SubjectModel>();
        subjectModelSelected = new SubjectModel();
    }



    public void onClickAdd(){
        subjectModelSelected = new SubjectModel();
    }

    public void onClickDelete(){

    }

    public void onSubmit(){

    }
}
