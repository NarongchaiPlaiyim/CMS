package com.cms.model.dao;

import com.cms.model.db.UserModel;
import com.cms.service.security.encryption.EncryptionService;
import com.cms.utils.Type;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends GenericDAO<UserModel, Integer>{
    public UserModel findByUserNameAndPassword(String userName, String password) throws Exception {
        return (UserModel) getCriteria().add(Restrictions.and(
                Restrictions.eq("userName", userName),
                Restrictions.eq("password", EncryptionService.encryption(password)))).uniqueResult();
    }

    public boolean isExist(String id, Type type) throws Exception {
        boolean result;
        if(type == Type.TEACHER){
            result = isRecordExist(Restrictions.eq("teacherId", id));
        } else {
            result = isRecordExist(Restrictions.eq("studentId", id));
        }
        return result;
    }
}
