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
@Table(name = "fileupload")
@Proxy(lazy=false)
public class FileUploadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="topic")
    private String topic;

    @Column(name="location")
    private String location;

    @OneToOne
    @JoinColumn(name="subject_id")
    private SubjectModel subjectModel;

    @OneToOne
    @JoinColumn(name="class_id")
    private ClassEntity classModel;

    @OneToOne
    @JoinColumn(name="assignment_id")
    private AssignmentModel assignmentModel;

    @Column(name="active", nullable=false, columnDefinition="int default 1")
    private int active;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("topic", topic)
                .append("location", location)
                .append("subjectModel", subjectModel)
                .append("classModel", classModel)
                .append("assignmentModel", assignmentModel)
                .append("active", active)
                .toString();
    }
}
