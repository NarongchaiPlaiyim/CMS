package com.cms.service;

import com.cms.model.dao.UserDAO;
import com.cms.model.db.UserModel;
import com.cms.model.view.dilog.StudentRegisterView;
import com.cms.model.view.dilog.TeacherRegisterView;
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

    public boolean isRecordExist(String id, Type type) throws Exception{
        return userDAO.isExist(id, type);
    }

    public void createNewUser(TeacherRegisterView view) throws Exception{
        userDAO.persist(transform(view));
    }

    public void createNewUser(StudentRegisterView view) throws Exception{
        userDAO.persist(transform(view));
    }

    private UserModel transform(Object o){
        UserModel userModel = new UserModel();
        if(o instanceof TeacherRegisterView){
            TeacherRegisterView view = (TeacherRegisterView) o;
            userModel.setUserName(view.getUserName());
            userModel.setPassword(EncryptionService.encryption(view.getPassword()));
            userModel.setEmail(view.getEmail());
            userModel.setTeacherId(view.getTeacherId());
            userModel.setRole(Type.TEACHER);
        } else {
            StudentRegisterView view = (StudentRegisterView) o;
            userModel.setUserName(view.getUserName());
            userModel.setPassword(EncryptionService.encryption(view.getPassword()));
            userModel.setEmail(view.getEmail());
            userModel.setStudentId(view.getStudentId());
            userModel.setRole(Type.STUDENT);
        }
        return userModel;
    }
}
