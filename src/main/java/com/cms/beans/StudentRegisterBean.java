package com.cms.beans;

import com.cms.model.view.dilog.StudentRegisterView;
import com.cms.service.RegisterService;
import com.cms.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "studentRegisterBean")
public class StudentRegisterBean extends Bean {
    private static final long serialVersionUID = 4112578966929374840L;
    @ManagedProperty("#{registerService}") private RegisterService registerService;
    private StudentRegisterView view;

    @PostConstruct
    private void init(){
        view = new StudentRegisterView();
    }

    public void onClickSubmit(){
        try {
            if(Utils.isZero(view.getUserName()) ||  Utils.isZero(view.getPassword()) ||
                    Utils.isZero(view.getStudentId()) ||  Utils.isZero(view.getEmail())){
                showDialogWarning("กรอกให้ครบ ซิสัส");
            }
            registerService.createNewUser(view);
            showDialogCreated();
            init();
        } catch (Exception e) {
            showDialogError(e.getMessage());
            log.debug("Exception while processing ", e);
        }
    }
}
