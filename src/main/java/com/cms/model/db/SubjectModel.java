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
@Table(name = "subject")
@Proxy(lazy=false)
public class SubjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="subject_code")
    private String subjectCode;

    @Column(name="subject_name")
    private String subjectName;

    @Column(name="description", length = 2500)
    private String description;

    @Column(name="semester")
    private String semester;

    @Column(name="year")
    private String year;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserModel userModel;

    @Column(name="active", nullable=false, columnDefinition="int default 1")
    private int active = 1;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("subjectCode", subjectCode)
                .append("subjectName", subjectName)
                .append("description", description)
                .append("semester", semester)
                .append("year", year)
                .append("userModel", userModel)
                .append("active", active)
                .toString();
    }
}
