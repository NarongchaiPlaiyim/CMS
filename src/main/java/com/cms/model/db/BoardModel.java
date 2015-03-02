package com.cms.model.db;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "board")
@Proxy(lazy=false)
public class BoardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="detail",columnDefinition="TEXT")
    private String detail;

    @OneToOne
    @JoinColumn(name="class_id")
    private ClassEntity classModel;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserModel userModel;

    @Version
    @JoinColumn(name = "actionDate")
    private Date actionDate;

    @Column(name="active", nullable=false, columnDefinition="int default 1")
    private int active = 1;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("detail", detail)
                .append("classModel", classModel)
                .append("userModel", userModel)
                .append("actionDate", actionDate)
                .append("active", active)
                .toString();
    }
}
