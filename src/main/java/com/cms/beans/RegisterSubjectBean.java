package com.cms.beans;

import com.cms.model.db.SubjectModel;
import com.cms.service.RegisterSubjectService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "registerSubjectBean")
public class RegisterSubjectBean extends Bean{
    private static final long serialVersionUID = 4112579994998374840L;
    @ManagedProperty("#{registerSubjectService}") private RegisterSubjectService registerSubjectService;
    private List<SubjectModel> subjectModelList;

    private int subjectId;

    @PostConstruct
    private void init(){
        subjectInTeacher();
    }


    private void subjectInTeacher(){
        subjectModelList =  registerSubjectService.findByTeacherId(4);
    }
}