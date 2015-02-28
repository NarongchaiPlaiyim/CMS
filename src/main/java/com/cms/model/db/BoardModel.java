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
@Table(name = "board")
@Proxy(lazy=false)
public class BoardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="detail")
    private String detail;

    @OneToOne
    @JoinColumn(name="subject_id")
    private SubjectModel subjectModel;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserModel userModel;

    @Column(name="active", nullable=false, columnDefinition="int default 1")
    private int active = 1;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("detail", detail)
                .append("subjectModel", subjectModel)
                .append("userModel", userModel)
                .append("active", active)
                .toString();
    }
}
