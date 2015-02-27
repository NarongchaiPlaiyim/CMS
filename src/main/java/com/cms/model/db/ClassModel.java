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
public class ClassModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="class_id")
    private String classId;

    @Column(name="week_no")
    private int weekNo;

    @Column(name="topic")
    private String topic;

    @Column(name="description")
    private String description;

    @OneToOne
    @JoinColumn(name="subject_id")
    private SubjectModel subjectModel;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("classId", classId)
                .append("weekNo", weekNo)
                .append("topic", topic)
                .append("description", description)
                .append("subjectModel", subjectModel)
                .toString();
    }
}
