package com.cms.model.db;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "examination")
@Proxy(lazy=false)
public class Examination {
    @Id
    @Column(name="Exam_id")
    private String examId;

    @Column(name="description")
    private String description;

    @Column(name="score")
    private BigDecimal score;

    @OneToOne
    @JoinColumn(name="subject_id")
    private SubjectModel subjectModel;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("examId", examId)
                .append("description", description)
                .append("score", score)
                .append("subjectModel", subjectModel)
                .toString();
    }
}
