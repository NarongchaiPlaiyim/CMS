package com.cms.service;

import com.cms.model.dao.UserDAO;
import com.cms.model.db.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Transactional
public class StudentService extends Service {
    @Resource private UserDAO userDAO;

    public UserModel getStudent(int studentId){
        try {
            return userDAO.findByID(studentId);
        } catch (Exception e) {
            log.debug("Exception error getStudent : ", e);
            return null;
        }
    }
}
