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
    private int currentSubjectId;



    @PostConstruct
    private void init(){
        classDetailList = new ArrayList<ClassEntity>();
        onload();
    }

    private void onload(){
        HttpSession session = FacesUtil.getSession(true);

        subjectModelList = (List<SubjectModel>) session.getAttribute("subjectModelList");
    }

    public void selectData(SelectEvent event){
        System.out.println(";llll   "+subjectModelSelected.toString());
        log.debug("selectData() : {}",subjectModelSelected.toString());

        try {
            currentSubjectId = subjectModelSelected.getId();
            classDetailList = findClassBySubjectId(subjectModelSelected.getId());
            System.out.println(classDetailList.size());
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

    public void goToChat(int classId){
        HttpSession session = FacesUtil.getSession(true);
        session.setAttribute("chatClassId", classId);
        FacesUtil.redirect("/site/chat.xhtml");
    }

    public void onClickAdd(){
        log.debug("onClickAdd()");
        classSelected = new ClassEntity();
        SubjectModel subjectModel = new SubjectModel();
        subjectModel.setId(currentSubjectId);
        classSelected.setSubjectModel(subjectModel);
    }

    public void onSaveNewTutorial(){
        log.debug("onSaveNewTutorial : [{}]", classSelected.toString());
        try {
            classTutorialService.save(classSelected);
            classDetailList = findClassBySubjectId(currentSubjectId);
            showDialogSaved();
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public void onDeleteNewTutorial(){
        log.debug("onDeleteNewTutorial : [{}]", classSelected.toString());
        try {
            classTutorialService.deleteClassById(classSelected.getId());
            classDetailList = findClassBySubjectId(classSelected.getId());
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    private List<ClassEntity> findClassBySubjectId(int id) throws Exception{
        return classTutorialService.findClassBySubjectId(id);
    }

}
