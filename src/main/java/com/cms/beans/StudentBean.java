package com.cms.beans;

import com.cms.model.db.UserModel;
import com.cms.service.StudentService;
import com.cms.service.security.UserDetail;
import com.cms.utils.FacesUtil;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "studentBean")
public class StudentBean extends Bean {
    private static final long serialVersionUID = 4112578684029874849L;
    @ManagedProperty("#{studentService}") private StudentService studentService;
    private UserDetail studentId;
    private UserModel userModel;
    private HttpSession httpSession;

    @PostConstruct
    public void onCreation(){
        log.debug("onCreation().");
        if(preLoad() && isAuthorizeStudent()){
            httpSession = FacesUtil.getSession(false);
            init();
        }
    }

    private void init(){
        onLoad();
    }

    private void onLoad(){
        studentId = (UserDetail) httpSession.getAttribute("studentSubject");
        userModel = studentService.getStudent(studentId.getId());
    }
}
