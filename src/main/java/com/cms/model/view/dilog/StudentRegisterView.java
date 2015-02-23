package com.cms.model.view.dilog;

import com.cms.model.view.View;
import com.cms.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

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
        createDate = Utils.currentDate();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("email", email)
                .append("password", password)
                .append("userName", userName)
                .append("studentId", studentId)
                .toString();
    }

}
