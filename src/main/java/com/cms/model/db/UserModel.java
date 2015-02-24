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

    @Column(name="teacher_id")
    private String teacherId;

    @Column(name="student_id")
    private String studentId;

    @Column(name="username")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="facebook")
    private String facebook;

    @Column(name="role")
    private String role;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("teacherId", teacherId)
                .append("studentId", studentId)
                .append("userName", userName)
                .append("password", password)
                .append("email", email)
                .append("facebook", facebook)
                .append("role", role)
                .toString();
    }
}
