package com.cms.beans;

import com.cms.model.db.EnrollModel;
import com.cms.model.db.SubjectModel;
import com.cms.service.StudentService;
import com.cms.utils.FacesUtil;
import com.cms.utils.Utils;
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
    private final List<String> ACADEMICYEAR = Utils.getAcademicYear();
    private final List<String> YEAR = Utils.getAcademicYear();

    private String semesterSelected;
    private String yearSelected;

    private List<EnrollModel> enrollModelList;
    private EnrollModel enrollModel;

    private String year;
    private String term;

    @PostConstruct
    public void onCreation(){
        log.debug("onCreation().");
        if(preLoad() && isAuthorizeTeacher()){
            init();
        }
    }

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
    }

    public void onClickSearch(){
        System.out.println("onClickSearch");
        enrollModelList = studentService.getList(subjectModelSelected, semesterSelected, yearSelected);
        System.out.println(enrollModelList.size());
    }
}
