package com.cms.service;

import com.cms.model.dao.EnrollDAO;
import com.cms.model.db.EnrollModel;
import com.cms.model.db.SubjectModel;
import com.cms.utils.Utils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class StudentService extends Service {
    @Resource private EnrollDAO enrollDAO;

    public List<EnrollModel> getList(SubjectModel model){
        List<EnrollModel> examinationModelList = Utils.getEmptyList();
        try {
            examinationModelList = enrollDAO.findBySubject(model);
        } catch (Exception e) {
            log.debug("Exception error load : ", e);
        }
        return examinationModelList;
    }

    public List<EnrollModel> getList(SubjectModel model, String semester, String year){
        List<EnrollModel> examinationModelList = Utils.getEmptyList();
        try {
            examinationModelList = enrollDAO.findBySubject(model, semester, year);
        } catch (Exception e) {
            log.debug("Exception error load : ", e);
        }
        return examinationModelList;
    }
}
