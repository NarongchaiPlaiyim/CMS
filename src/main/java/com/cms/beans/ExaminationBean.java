package com.cms.beans;

import com.cms.model.db.ExaminationModel;
import com.cms.service.ExaminationService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "examinationBean")
public class ExaminationBean extends Bean {
    private static final long serialVersionUID = 4112578684029812345L;
    @ManagedProperty("#{examinationService}") private ExaminationService examinationService;

    private List<ExaminationModel> examinationModelList;
    private ExaminationModel examinationModel;

    @PostConstruct
    private void init(){
        examinationModelList = new ArrayList<ExaminationModel>();
        examinationModel = new ExaminationModel();
        examinationModel.setScore(99.99);
        examinationModelList.add(examinationModel);
    }

}
