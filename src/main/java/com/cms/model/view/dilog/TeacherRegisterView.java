package com.cms.model.view.dilog;

import com.cms.model.view.View;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("email", email)
                .append("password", password)
                .append("userName", userName)
                .append("teacherId", teacherId)
                .toString();
    }
}
