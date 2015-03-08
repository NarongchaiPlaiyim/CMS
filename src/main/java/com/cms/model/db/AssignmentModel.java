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
@Table(name = "assignment")
@Proxy(lazy=false)
public class AssignmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="assignment_no")
    private String assignmentNo;

    @Column(name="year")
    private String year;

    @Column(name="semester")
    private String semester;

    @Column(name="description", length = 2500)
    private String description;

    @OneToOne
    @JoinColumn(name="subject_id")
    private SubjectModel subjectModel;

    @Column(name="active", nullable=false, columnDefinition="int default 1")
    private int active = 1;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("assignmentNo", assignmentNo)
                .append("year", year)
                .append("semester", semester)
                .append("description", description)
                .append("subjectModel", subjectModel)
                .append("active", active)
                .toString();
    }
}
