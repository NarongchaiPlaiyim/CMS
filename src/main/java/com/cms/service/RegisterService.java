package com.cms.service;

import com.cms.model.dao.UserDAO;
import com.cms.model.db.UserModel;
import com.cms.service.security.encryption.EncryptionService;
import com.cms.utils.Type;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Transactional
public class RegisterService extends Service{
    private static final long serialVersionUID = 4112578630941419914L;
    @Resource private UserDAO userDAO;

    public boolean isRecordExist(String id) throws Exception{
        return userDAO.isExist(id);
    }

    public void createNewUser(UserModel userModel, Type type) throws Exception{
        userModel.setRole(type);
        userModel.setPassword(EncryptionService.encryption(userModel.getPassword()));
        userDAO.persist(userModel);
    }
}
