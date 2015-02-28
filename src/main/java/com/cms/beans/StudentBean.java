package com.cms.beans;

import com.cms.model.db.SubjectModel;
import com.cms.service.StudentService;
import com.cms.utils.FacesUtil;
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
@ManagedBean(name = "studentBean")
public class StudentBean extends Bean {
    private static final long serialVersionUID = 4112578684029874849L;
    @ManagedProperty("#{studentService}") private StudentService studentService;
    private List<SubjectModel> subjectModelList;
    private SubjectModel subjectModelSelected;

    @PostConstruct
    private void init(){
        subjectModelSelected = new SubjectModel();
        onload();
    }

    private void onload(){
        HttpSession session = FacesUtil.getSession(true);

        subjectModelList = (List<SubjectModel>) session.getAttribute("subjectModelList");
    }
}
