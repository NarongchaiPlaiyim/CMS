package com.cms.model.view.dilog;

import com.cms.model.view.View;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherRegisterView extends View {
    private String teacherId;
    private String userName;
    private String password;
    private String email;

    public TeacherRegisterView() {
        init();
    }

    private void init(){

    }
}
