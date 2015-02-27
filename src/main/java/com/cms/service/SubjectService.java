package com.cms.service;

import com.cms.model.dao.SubjectDAO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Transactional
public class SubjectService extends Service {
    private static final long serialVersionUID = 4112578630941123456L;
    @Resource private SubjectDAO subjectDAO;
}
