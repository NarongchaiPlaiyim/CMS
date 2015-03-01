package com.cms.beans;

import com.cms.model.db.UserModel;
import com.cms.service.LoginService;
import com.cms.service.RegisterService;
import com.cms.service.security.SimpleAuthenticationManager;
import com.cms.service.security.UserDetail;
import com.cms.utils.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "loginBean")
public class LoginBean extends Bean{
    private static final long serialVersionUID = 4112578634029374840L;
    @ManagedProperty("#{loginService}") private LoginService loginService;
    @ManagedProperty("#{sessionRegistry}") private SessionRegistry sessionRegistry;
    @ManagedProperty("#{sas}") private CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy;
    @ManagedProperty("#{registerService}") private RegisterService registerService;

    private String userName = "";
    private String password = "";
    private String teacherId = "";

    private UserDetail userDetail;
    private Map<String,String> map;

    private String typeRadio;

    private boolean teacherFlag;
    private boolean studentFlag;

    private final String TEACHER = "0";
    private final String STUDENT = "1";

    private List<UserModel> teacherList;
    private UserModel userModel;
    private UserModel userModelSelected;


    @PostConstruct
    private void init(){
//        if(!Utils.isNull(SecurityContextHolder.getContext().getAuthentication())){
//            userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            map = (Map<String, String>) FacesUtil.getSession(false).getAttribute(AttributeName.AUTHORIZE.getName());
//        } else {
//            log.debug("[NEW] CODE MAP");
//            map = new HashMap<String, String>();
//        }
        initTeacher();
        typeRadio = TEACHER;
    }

    private void initTeacher(){
        teacherFlag = true;
        studentFlag = false;
        onClickRegister();
        initTeacherList();
    }

    private void initStudent(){
        teacherFlag = false;
        studentFlag = true;
        onClickRegister();
        initTeacherList();
    }

    private void initTeacherList(){
        teacherList = loginService.getTeacherList();
    }

    public void onClickRegister(){
        userModel = new UserModel();
    }
    public void onClickRegStudentSubmit(){
        try {
            if(!registerService.isRecordExist(userModel.getPersonId())){
                if(!loginService.isUserExist(userModel.getUserName())){
                    registerService.createNewUser(userModel, Type.STUDENT);
                    showDialogCreated();
                    init();
                } else {
                    showDialogWarning("Already existed username.");
                }
            } else {
                showDialogWarning("Already existed student id.");
            }
        } catch (Exception e) {
            showDialogError(e.getMessage());
            log.debug("Exception while processing ", e);
        }
    }

    public void onClickRegTeacherSubmit(){
        System.out.println("onClickRegTeacherSubmit");
        try {
            if(!registerService.isRecordExist(userModel.getPersonId())){
                if(!loginService.isUserExist(userModel.getUserName())){
                    registerService.createNewUser(userModel, Type.TEACHER);
                    showDialogCreated();
                    init();
                } else {
                    showDialogWarning("Already existed username.");
                }
            } else {
                showDialogWarning("Already existed teacher id.");
            }
        } catch (Exception e) {
            showDialogError(e.getMessage());
            log.debug("Exception while processing ", e);
        }
    }

    public void onClickRadio(){
        if (STUDENT.equals(typeRadio) ) {
            initStudent();
        } else {
            initTeacher();
        }
    }

    public String login(){
        log.info("-- SessionRegistry principle size: {}", sessionRegistry.getAllPrincipals().size());
        if(isTeacherFlag()){
            if(!Utils.isZero(userName.length()) && !Utils.isZero(password.length())) {
                if(loginService.isUserExist(getUserName(), getPassword(), Type.TEACHER)){
                    UserModel userModel = loginService.getUserModel();
                    userDetail = new UserDetail(userModel.getId(),
                            userModel.getUserName(),
                            userModel.getPassword(),
                            userModel.getRole());
                    HttpServletRequest httpServletRequest = FacesUtil.getRequest();
                    HttpServletResponse httpServletResponse = FacesUtil.getResponse();
                    UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken(getUserDetail(), getPassword());
                    request.setDetails(new WebAuthenticationDetails(httpServletRequest));
                    SimpleAuthenticationManager simpleAuthenticationManager = new SimpleAuthenticationManager();
                    Authentication result = simpleAuthenticationManager.authenticate(request);
                    log.debug("-- authentication result: {}", result.toString());
                    SecurityContextHolder.getContext().setAuthentication(result);
                    compositeSessionAuthenticationStrategy.onAuthentication(request, httpServletRequest, httpServletResponse);
                    HttpSession httpSession = FacesUtil.getSession(false);
                    httpSession.setAttribute(AttributeName.USER_DETAIL.getName(), getUserDetail());
//                httpSession.setAttribute(AttributeName.AUTHORIZE.getName(), loginService.getAuthorize());
                    log.debug("-- userDetail[{}]", userDetail.toString());
                    return "PASS";
                }
            }
            showDialog(MessageDialog.WARNING.getMessageHeader(), "Invalid username or password.");
            return "loggedOut";
        } else {
            if(!Utils.isZero(getUserName().length()) && !Utils.isZero(getPassword().length())) {
                if(loginService.isUserExist(getUserName(), getPassword(), Type.STUDENT)){
                    try {
                        if(!registerService.isRecordExist(getTeacherId())){
                            showDialog(MessageDialog.WARNING.getMessageHeader(), "Invalid teacher id.");
                            return "loggedOut";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    UserModel userModel = loginService.getUserModel();
                    userDetail = new UserDetail(userModel.getId(),
                            userModel.getUserName(),
                            userModel.getPassword(),
                            userModel.getRole());
                    HttpServletRequest httpServletRequest = FacesUtil.getRequest();
                    HttpServletResponse httpServletResponse = FacesUtil.getResponse();
                    UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken(getUserDetail(), getPassword());
                    request.setDetails(new WebAuthenticationDetails(httpServletRequest));
                    SimpleAuthenticationManager simpleAuthenticationManager = new SimpleAuthenticationManager();
                    Authentication result = simpleAuthenticationManager.authenticate(request);
                    log.debug("-- authentication result: {}", result.toString());
                    SecurityContextHolder.getContext().setAuthentication(result);
                    compositeSessionAuthenticationStrategy.onAuthentication(request, httpServletRequest, httpServletResponse);
                    HttpSession httpSession = FacesUtil.getSession(false);
                    httpSession.setAttribute(AttributeName.USER_DETAIL.getName(), getUserDetail());
//                httpSession.setAttribute(AttributeName.AUTHORIZE.getName(), loginService.getAuthorize());
                    log.debug("-- userDetail[{}]", userDetail.toString());
                    return "PASS";
                }
            }
            showDialog(MessageDialog.WARNING.getMessageHeader(), "Invalid username or password.");
            return "loggedOut";
        }
    }

    public boolean isRendered(String key){
        try {
            return map.containsKey(key);
        } catch (Exception e) {
            log.error("Exception : {}", e);
            return false;
        }
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        SecurityContextHolder.clearContext();
        return "loggedOut";
    }

    public void test(){
        System.out.println("test");
//        barcodePrintingService.insert();
//        try {
////            loginService.getStaffModel()
//        } catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }

}
