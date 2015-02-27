package com.cms.model.dao;

import com.cms.model.db.UserModel;
import com.cms.service.security.encryption.EncryptionService;
import com.cms.utils.Type;
import com.cms.utils.Utils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO extends GenericDAO<UserModel, Integer>{
    public UserModel findByUserNameAndPassword(String userName, String password, Type role) throws Exception {
        return (UserModel) getCriteria().add(Restrictions.and(
                Restrictions.eq("userName", userName),
                Restrictions.eq("role", role),
                Restrictions.eq("password", EncryptionService.encryption(password)))).uniqueResult();
    }

    public UserModel findByUserNameAndPassword(String userName) throws Exception {
        return (UserModel) getCriteria().add(Restrictions.eq("userName", userName)).uniqueResult();
    }

    public boolean isExist(String id) throws Exception {
        return isRecordExist(Restrictions.eq("personId", id));
    }

    public List<UserModel> findAllTeachers() throws Exception {
        return Utils.safetyList(getCriteria().add(Restrictions.eq("role", Type.TEACHER)).list());
    }

}
