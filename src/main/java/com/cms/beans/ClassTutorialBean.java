package com.cms.beans;

import com.cms.model.db.ClassEntity;
import com.cms.model.db.SubjectModel;
import com.cms.service.ClassTutorialService;
import com.cms.utils.FacesUtil;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "classTutorialBean")
public class ClassTutorialBean extends Bean {
    private static final long serialVersionUID = 4112585632998374840L;
    @ManagedProperty("#{classTutorialService}") private ClassTutorialService classTutorialService;
    private List<SubjectModel> subjectModelList;
    private List<ClassEntity> classDetailList;
    private ClassEntity classSelected;
    private SubjectModel subjectModelSelected;
    private int subjectId;


    @PostConstruct
    private void init(){
        subjectModelSelected = new SubjectModel();
        classDetailList = new ArrayList<ClassEntity>();
        onload();
    }

    private void onload(){
        HttpSession session = FacesUtil.getSession(true);

        subjectModelList = (List<SubjectModel>) session.getAttribute("subjectModelList");
    }

    public void onClickAdd(){
        log.debug("onClickAdd()");
        classSelected = new ClassEntity();
    }

    public void onSaveNewTutorial(){
        log.debug("onSaveNewTutorial : [{}]", classSelected.toString());
        try {
            classTutorialService.save(classSelected);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }


    public void selectData(SelectEvent event){
        System.out.println(";llll   "+subjectModelSelected.toString());
        log.debug("selectData() : {}",subjectModelSelected.toString());

        try {
            classDetailList = classTutorialService.findClassBySubjectId(subjectModelSelected.getId());
            System.out.println(classDetailList.size());
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

}
