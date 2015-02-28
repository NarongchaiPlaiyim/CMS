package com.cms.model.dao;

import com.cms.model.db.ExaminationModel;
import com.cms.model.db.SubjectModel;
import com.cms.utils.Utils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExaminationDAO extends GenericDAO<ExaminationModel, Integer>{
    public List<ExaminationModel> findBySubject(SubjectModel model) throws Exception {
        return Utils.safetyList(getCriteria().add(Restrictions.eq("subjectModel", model)).list());
    }
}
