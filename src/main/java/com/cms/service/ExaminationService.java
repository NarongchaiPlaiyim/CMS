package com.cms.service;

import com.cms.model.dao.ExaminationDAO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Transactional
public class ExaminationService extends Service {
    @Resource private ExaminationDAO examinationDAO;
}
