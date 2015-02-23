package com.cms.beans;

import com.cms.model.view.dilog.TeacherRegisterView;
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
@ManagedBean(name = "teacherRegisterBean")
public class TeacherRegisterBean extends Bean {
    private static final long serialVersionUID = 4116348634029374840L;
    @ManagedProperty("#{registerService}") private RegisterService registerService;
    private TeacherRegisterView view;

    @PostConstruct
    private void init(){
        view = new TeacherRegisterView();
    }

    public void onClickSubmit(){
        view.setCreateDate(Utils.currentDate());
    }
}
