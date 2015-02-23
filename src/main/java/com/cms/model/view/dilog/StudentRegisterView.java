package com.cms.model.view.dilog;

import com.cms.model.view.View;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRegisterView extends View {
    private String studentId;
    private String userName;
    private String password;
    private String email;

    public StudentRegisterView() {
        init();
    }

    private void init(){

    }
}
