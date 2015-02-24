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
@Table(name = "assignment")
@Proxy(lazy=false)
public class AssignmentModel {
    @Id
    @Column(name="assignment_id")
    private String assignmentId;

    @Column(name="assignment_no")
    private BigDecimal assignmentNo;

    @Column(name="year")
    private String year;

    @Column(name="semester")
    private String semester;

    @Column(name="description")
    private String description;

    @OneToOne
    @JoinColumn(name="class_id")
    private ClassModel classModel;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("assignmentId", assignmentId)
                .append("assignmentNo", assignmentNo)
                .append("year", year)
                .append("semester", semester)
                .append("description", description)
                .append("classModel", classModel)
                .toString();
    }
}
