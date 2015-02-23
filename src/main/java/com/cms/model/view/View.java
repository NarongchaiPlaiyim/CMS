package com.cms.model.view;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public abstract class View {
    protected int createBy;
    protected Date createDate;
    protected int updateBy;
    protected Date updateDate;
}
