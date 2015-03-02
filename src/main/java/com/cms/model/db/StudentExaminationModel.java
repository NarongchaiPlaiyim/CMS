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
@Table(name = "studentExamination")
@Proxy(lazy=false)
public class StudentExaminationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="student_id")
    private UserModel userModel;

    @OneToOne
    @JoinColumn(name="examination_id")
    private ExaminationModel examinationModel;

    @Column(name="score")
    private BigDecimal score;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("userModel", userModel)
                .append("examinationModel", examinationModel)
                .append("score", score)
                .toString();
    }
}
