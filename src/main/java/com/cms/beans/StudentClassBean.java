package com.cms.beans;

import com.cms.model.db.ClassEntity;
import com.cms.model.db.SubjectModel;
import com.cms.service.StudentClassService;
import com.cms.service.security.UserDetail;
import com.cms.utils.AttributeName;
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
@ManagedBean(name = "studentClassBean")
public class StudentClassBean extends Bean{
    private static final long serialVersionUID = 4112585632998374840L;
    @ManagedProperty("#{studentClassService}") private StudentClassService studentClassService;
    private List<SubjectModel> subjectModelList;
    private List<ClassEntity> classDetailList;
    private ClassEntity classSelected;
    private SubjectModel subjectModelSelected;
    private int currentSubjectId;

    private UserDetail studentId;
    private HttpSession session;

    @PostConstruct
    public void onCreation(){
        log.debug("onCreation().");
        if(preLoad() && isAuthorizeStudent()){
            init();
        }
    }

    private void init(){
        classDetailList = new ArrayList<ClassEntity>();
        onload();
    }

    private void onload(){
        session = FacesUtil.getSession(true);

        subjectModelList = (List<SubjectModel>) session.getAttribute(AttributeName.STUDENT_SUBJECT.getName());
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
        FacesUtil.redirect("/site/studentChat.xhtml");
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
            studentClassService.save(classSelected);
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
            studentClassService.deleteClassById(classSelected.getId());
            classDetailList = findClassBySubjectId(classSelected.getId());
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    private List<ClassEntity> findClassBySubjectId(int id) throws Exception{
        return studentClassService.findClassBySubjectId(id);
    }
}
