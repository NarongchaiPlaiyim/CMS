package com.cms.beans;

import com.cms.model.db.StudentExaminationModel;
import com.cms.model.db.SubjectModel;
import com.cms.service.StudentExaminationService;
import com.cms.service.security.UserDetail;
import com.cms.utils.AttributeName;
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
@ManagedBean(name = "studentExamianationBean")
public class StudentExaminationBean extends Bean {
    private static final long serialVersionUID = 4112585632998374840L;
    @ManagedProperty("#{studentExaminationService}") private StudentExaminationService studentExaminationService;
    private List<SubjectModel> subjectModelList;
    private SubjectModel subjectModel;
    private HttpSession httpSession;
    private UserDetail studentId;
    private List<StudentExaminationModel> studentExaminationModelList;

    @PostConstruct
    public void onCreation(){
        log.debug("onCreation().");
        if(preLoad() && isAuthorizeStudent()){
            init();
        }
    }

    private void init(){
        httpSession = FacesUtil.getSession(true);
        subjectOnload();
    }

    private void subjectOnload(){
        subjectModelList = (List<SubjectModel>) httpSession.getAttribute(AttributeName.STUDENT_SUBJECT.getName());
    }

    public void onClickTable(){
        studentId = (UserDetail) httpSession.getAttribute(AttributeName.USER_DETAIL.getName());
        studentExaminationModelList = studentExaminationService.getStudentAssignment(subjectModel.getId(), studentId.getId());
    }
}
