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
@Table(name = "class")
@Proxy(lazy=false)
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="class_code")
    private String classCode;

    @Column(name="class_name")
    private String className;

    @Column(name="week_no")
    private int weekNo;

    @Column(name="description")
    private String description;

    @OneToOne
    @JoinColumn(name="subject_id")
    private SubjectModel subjectModel;

    @Column(name="active", nullable=false, columnDefinition="int default 1")
    private int active;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("classCode", classCode)
                .append("weekNo", weekNo)
                .append("className", className)
                .append("description", description)
                .append("subjectModel", subjectModel)
                .append("active", active)
                .toString();
    }
}
