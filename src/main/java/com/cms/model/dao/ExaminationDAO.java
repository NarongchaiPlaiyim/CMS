package com.cms.model.dao;

import com.cms.model.db.ExaminationModel;
import com.cms.utils.Utils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExaminationDAO extends GenericDAO<ExaminationModel, Integer>{
    public List<ExaminationModel> findBySubject(int subjectId) throws Exception {
        return Utils.safetyList(getCriteria().add(Restrictions.eq("subjectModel.id", subjectId))
                .add(Restrictions.eq("active", 1)).list());
    }
}
