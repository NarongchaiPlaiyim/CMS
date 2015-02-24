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
    @Column(name="subject_id")
    private int subjectId;

    @Column(name="subject_code")
    private String subjectCode;

    @Column(name="subject_name")
    private String subjectName;

    @Column(name="description")
    private String description;

    @Column(name="semester")
    private String semester;

    @OneToOne
    @JoinColumn(name="teacher_id")
    private UserModel teacherId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("subjectId", subjectId)
                .append("subjectCode", subjectCode)
                .append("subjectName", subjectName)
                .append("description", description)
                .append("semester", semester)
                .append("teacherId", teacherId)
                .toString();
    }
}
