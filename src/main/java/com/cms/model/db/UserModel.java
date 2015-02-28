package com.cms.model.db;

import com.cms.utils.Type;
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

    @Column(name="person_id")
    private String personId;

    @Column(name="username")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="facebook")
    private String facebook;

    @Column(name="role")
    @Enumerated(EnumType.ORDINAL)
    private Type role;

    @Column(name="active", nullable=false, columnDefinition="int default 1")
    private int active = 1;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("personId", personId)
                .append("userName", userName)
                .append("password", password)
                .append("email", email)
                .append("facebook", facebook)
                .append("role", role)
                .append("active", active)
                .toString();
    }
}
