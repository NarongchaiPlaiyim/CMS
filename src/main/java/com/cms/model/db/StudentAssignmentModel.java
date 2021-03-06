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
@Table(name = "student_assignment")
@Proxy(lazy=false)
public class StudentAssignmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="student_id")
    private UserModel userModel;

    @OneToOne
    @JoinColumn(name="assignment_id")
    private AssignmentModel assignmentModel;

    @Column(name="submit_status", nullable=false, columnDefinition="int default 0")
    private boolean submitStatus;

    @Column(name="score")
    private BigDecimal score;

    @Column(name="location")
    private String location;

    @Column(name="filename")
    private String filename;

    @Column(name="active", nullable=false, columnDefinition="int default 1")
    private int active = 1;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("userModel", userModel)
                .append("assignmentModel", assignmentModel)
                .append("submitStatus", submitStatus)
                .append("score", score)
                .append("location", location)
                .append("filename", filename)
                .append("active", active)
                .toString();
    }
}
