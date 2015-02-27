package com.cms.service;

import com.cms.model.dao.UserDAO;
import com.cms.model.db.UserModel;
import com.cms.utils.Type;
import com.cms.utils.Utils;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class LoginService extends Service{
    private static final long serialVersionUID = 4112578634088874840L;
    @Resource private UserDAO userDAO;
    @Getter private UserModel userModel;

    public List<UserModel> getTeacherList(){
        List<UserModel> userModelList = Utils.getEmptyList();
        try {
            userModelList = userDAO.findAllTeachers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userModelList;
    }

    public boolean isUserExist(final String userName, final String password, Type role){
        log.debug("-- isUserExist({}, {})", userName, password);
        boolean result = false;
        try {
            userModel = userDAO.findByUserNameAndPassword(userName, password, role);
            if(!Utils.isNull(userModel)){
                result = true;
            }
        } catch (Exception e) {
            log.error("Exception while calling isUserExist()", e);
        }
        return result;
    }

    public boolean isUserExist(final String userName){
        log.debug("-- isUserExist({}, {})", userName);
        boolean result = false;
        try {
            userModel = userDAO.findByUserNameAndPassword(userName);
            if(!Utils.isNull(userModel)){
                result = true;
            }
        } catch (Exception e) {
            log.error("Exception while calling isUserExist()", e);
        }
        return result;
    }

//
//    public Map<String, String> getAuthorize(){
//        List<String> stringList;
//        Map<String, String> map = new HashMap();
//        try {
//            stringList = menuObjectDAO.findByStaffId(staffModel.getId());
//            for (String s : stringList) map.put(s, s);
//        } catch (Exception e) {
//            System.err.println(e);
//            log.error("Exception while calling getAuthorize()", e);
//        }
//        return map;
//    }
//
//    public List<MenuObjectModel> getAllMenuObject(){
//        try {
//            return menuObjectDAO.findAllOrderByCode();
//        } catch (Exception e) {
//            System.err.println(e);
//            return Collections.EMPTY_LIST;
//        }
//    }
}
