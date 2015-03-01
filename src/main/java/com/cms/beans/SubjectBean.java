package com.cms.beans;

import com.cms.model.db.EnrollModel;
import com.cms.model.db.SubjectModel;
import com.cms.service.SubjectService;
import com.cms.service.security.UserDetail;
import com.cms.utils.AttributeName;
import com.cms.utils.FacesUtil;
import com.cms.utils.MessageDialog;
import com.cms.utils.Utils;
import lombok.Getter;
import lombok.Setter;

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
@ManagedBean(name = "subjectBean")
public class SubjectBean extends Bean {
    private static final long serialVersionUID = 4112578634998374840L;
    @ManagedProperty("#{subjectService}") private SubjectService subjectService;
    private List<SubjectModel> subjectModelList;
    private SubjectModel subjectModelSelected;
    private final List<String> SEMESTER = Utils.getSemester();
    private final List<String> Year = Utils.getAcademicYear();
    private String semesterSelected;
    private UserDetail userDetail;
    private int subjectId;
    private List<EnrollModel> enrollModelList;

    @PostConstruct
    private void init(){
        subjectModelList = new ArrayList<SubjectModel>();
        subjectModelSelected = new SubjectModel();
        HttpSession httpSession = FacesUtil.getSession(true);
        userDetail = (UserDetail) httpSession.getAttribute(AttributeName.USER_DETAIL.getName());
        onload();
    }

    private void onLoad(){
        subjectModelList = subjectService.getSubject(userDetail.getId());
        HttpSession session = FacesUtil.getSession(true);
        session.setAttribute("subjectModelList", subjectModelList);
    }

    public void onClickAdd(){
        subjectModelSelected = new SubjectModel();
    }

    public void onClickDelete(){
        subjectService.remove(subjectId);
        onLoad();
    }

    public void onSubmit(){
        log.debug("subjectModelSelected : [{}]", subjectModelSelected.toString());
        subjectService.save(subjectModelSelected, userDetail.getId());
        showDialogSaved();
        onLoad();
    }

    public void studentInSubject(){
        enrollModelList = subjectService.getStudent(subjectId);
    }

    public void onEditSubject(){
        subjectModelSelected = subjectService.edit(subjectId);
    }
}
