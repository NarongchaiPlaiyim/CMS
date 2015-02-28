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
@Table(name = "examination")
@Proxy(lazy=false)
public class ExaminationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Exam_id")
    private String examId;

    @Column(name="description")
    private String description;

    @Column(name="score")
    private double score;

    @OneToOne
    @JoinColumn(name="subject_id")
    private SubjectModel subjectModel;

    @Column(name="active", nullable=false, columnDefinition="int default 1")
    private int active;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("examId", examId)
                .append("description", description)
                .append("score", score)
                .append("subjectModel", subjectModel)
                .append("active", active)
                .toString();
    }
}
