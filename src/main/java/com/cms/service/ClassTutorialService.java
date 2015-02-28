package com.cms.service;

import com.cms.model.dao.ClassDAO;
import com.cms.model.dao.SubjectDAO;
import com.cms.model.db.ClassEntity;
import com.cms.model.db.SubjectModel;
import com.cms.utils.Utils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class ClassTutorialService extends Service {

    @Resource
    private ClassDAO classDao;
    @Resource
    private SubjectDAO subjectDAO;

    public List<ClassEntity> findClassBySubjectId(int id)throws Exception{
        log.debug("ClassTutorialService findClassBySubjectCode() id : {}",id);

        return Utils.safetyList(classDao.findClassBySubjectId(id));
    }

    public void save(ClassEntity entity)throws Exception{
        log.debug(" ClassTutorialService save() entity : {}",entity.toString());

        if(null == entity){
            throw new Exception("entity can't be null!");
        }
        SubjectModel subjectModel = subjectDAO.findByID(entity.getSubjectModel().getId());

        if(null == subjectModel){
            throw new Exception("subject is null!");
        }
        entity.setSubjectModel(subjectDAO.findByID(entity.getSubjectModel().getId()));
        entity.setActive(1);
        classDao.persist(entity);
    }



}
