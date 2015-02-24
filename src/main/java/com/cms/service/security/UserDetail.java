package com.cms.service.security;

import com.cms.utils.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

//@EqualsAndHashCode
@Getter
@Setter
public class UserDetail implements Serializable {
    private int id;
    private String userName;
    private String password;
    private Type role;

    public UserDetail(int id, String userName, String password, Type role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("userName", userName)
                .append("password", password)
                .append("role", role)
                .toString();
    }
}
