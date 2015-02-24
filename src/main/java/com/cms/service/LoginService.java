package com.cms.service;

import com.cms.model.dao.UserDAO;
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
//    @Resource private MenuObjectDAO menuObjectDAO;
//
//    @Getter StaffModel staffModel;
//
    public boolean isUserExist(final String userName, final String password){
        log.debug("-- isUserExist({}, {})", userName, password);
        return true;
//        boolean result = Utils.TRUE;
//        try {
//            staffModel = staffDAO.findByUserNameAndPassword(userName, password);
//            if(Utils.isNull(staffModel)){
//                result = !result;
//            }
//            return result;
//        } catch (Exception e) {
//            log.error("Exception while calling isUserExist()", e);
//            return !result;
//        }
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
