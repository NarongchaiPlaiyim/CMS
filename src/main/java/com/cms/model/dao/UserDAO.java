package com.cms.model.dao;

import com.cms.model.db.UserModel;
import com.cms.service.security.encryption.EncryptionService;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends GenericDAO<UserModel, Integer>{
    public UserModel findByUserNameAndPassword(String userName, String password) throws Exception {
        return (UserModel) getCriteria().add(Restrictions.and(
                Restrictions.eq("userName", userName),
                Restrictions.eq("password", EncryptionService.encryption(password)))).uniqueResult();
    }
}
