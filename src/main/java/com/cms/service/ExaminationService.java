package com.cms.service;

import com.cms.model.dao.ExaminationDAO;
import com.cms.model.db.ExaminationModel;
import com.cms.utils.Utils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class ExaminationService extends Service {
    @Resource private ExaminationDAO examinationDAO;

    public List<ExaminationModel> getList(){
        List<ExaminationModel> examinationModelList = Utils.getEmptyList();
        try {
            examinationModelList = examinationDAO.findAll();
        } catch (Exception e) {
            log.debug("Exception error load : ", e);
        }
        return examinationModelList;
    }

    public void create(ExaminationModel examinationModel){
        try {
            examinationDAO.persist(examinationModel);
        } catch (Exception e) {
            log.debug("Exception error save : ", e);
        }
    }
}
