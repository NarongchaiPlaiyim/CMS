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
@Table(name = "enroll")
@Proxy(lazy=false)
public class EnrollModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="student_id")
    private UserModel userModel;

    @OneToOne
    @JoinColumn(name="subject_id")
    private SubjectModel subjectModel;

    @Column(name="active", nullable=false, columnDefinition="int default 1")
    private int active;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("userModel", userModel)
                .append("subjectModel", subjectModel)
                .append("active", active)
                .toString();
    }
}
