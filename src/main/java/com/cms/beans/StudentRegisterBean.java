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
        view.setCreateDate(Utils.currentDate());
        System.out.println(view.toString());
    }
}