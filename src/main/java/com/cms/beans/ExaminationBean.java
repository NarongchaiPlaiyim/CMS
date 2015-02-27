package com.cms.beans;

import com.cms.service.ExaminationService;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "examinationBean")
public class ExaminationBean extends Bean {
    private static final long serialVersionUID = 4112578684029812345L;
    @ManagedProperty("#{examinationService}") private ExaminationService examinationService;
}
