package com.cms.service;

import com.cms.model.dao.ClassDAO;
import com.cms.model.dao.SubjectDAO;
import com.cms.model.db.ClassEntity;
import com.cms.model.db.SubjectModel;
import com.cms.service.security.UserDetail;
import com.cms.utils.Utils;
import org.primefaces.model.UploadedFile;
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
        entity.setSubjectModel(subjectModel);
        entity.setActive(1);
        classDao.persist(entity);
    }

    public void deleteClassById(int id)throws Exception {
        log.debug(" ClassTutorialService deleteClassById() id : {}", id);
        ClassEntity entity = classDao.findByID(id);
        entity.setActive(0);
        classDao.update(entity);
    }

}
