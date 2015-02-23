package com.cms.model.db;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
@Proxy(lazy=false)
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="login_id")
    private String loginId;

    @Column(name="name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="email_address")
    private String email;

    @Column(name="facebook")
    private String facebook;

    @OneToOne
    @JoinColumn(name="role_id")
    private MSRoleModel roleModel;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("loginId", loginId)
                .append("userName", userName)
                .append("password", password)
                .append("email", email)
                .append("facebook", facebook)
                .append("roleModel", roleModel)
                .toString();
    }
}
