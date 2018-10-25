package com.jpa.byname.service.impl;

import com.jpa.byname.dao.DepartmentRepository;
import com.jpa.byname.entity.SenyiDeptmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<SenyiDeptmentEntity> findListByDeptName(String deptName){
       return departmentRepository.findByDepName(deptName);
    }


}
