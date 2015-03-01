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

    private SubjectModel subjectId;

    @PostConstruct
    public void onCreation(){
        log.debug("onCreation().");
        if(preLoad() && isAuthorizeStudent()){
            init();
        }
    }
    private void init(){
        subjectInTeacher();
    }

    private void subjectInTeacher(){
        //TeacherId
        subjectModelList =  registerSubjectService.findByTeacherId(4);
    }

    public void onRegister(){
        //StudentId
        registerSubjectService.register(subjectId, 8);
        showDialogSaved();
    }
}
