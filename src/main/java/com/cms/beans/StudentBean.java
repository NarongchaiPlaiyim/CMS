package com.cms.beans;

import com.cms.model.db.EnrollModel;
import com.cms.model.db.SubjectModel;
import com.cms.service.StudentService;
import com.cms.utils.FacesUtil;
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
@ManagedBean(name = "studentBean")
public class StudentBean extends Bean {
    private static final long serialVersionUID = 4112578684029874849L;
    @ManagedProperty("#{studentService}") private StudentService studentService;
    private List<SubjectModel> subjectModelList;
    private SubjectModel subjectModelSelected;

    private List<EnrollModel> enrollModelList;
    private EnrollModel enrollModel;

    @PostConstruct
    private void init(){
        subjectModelSelected = new SubjectModel();
        enrollModel = new EnrollModel();
        onLoad();
    }

    private void onLoad(){
        subjectModelList = (List<SubjectModel>) FacesUtil.getSession(true).getAttribute("subjectModelList");
    }

    public void onClickTable(){
        enrollModelList = studentService.getList(subjectModelSelected);
        log.debug("-------- : {}", enrollModelList.size());
    }
}
