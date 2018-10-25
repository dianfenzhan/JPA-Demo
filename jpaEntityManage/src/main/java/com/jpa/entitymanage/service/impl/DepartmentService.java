package com.jpa.entitymanage.service.impl;

import com.jpa.entitymanage.dao.DepartmentRepository;
import com.jpa.entitymanage.entity.SenyiDeptmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * 对实体的增删改查操作
     *
     * @param senyiDeptmentEntity
     * @return
     */
    @Transactional
    public void saveEntity(SenyiDeptmentEntity senyiDeptmentEntity) {
        departmentRepository.saveEntity(senyiDeptmentEntity);
    }

    @Transactional
    public void deleteEntity(int depid) {
        departmentRepository.deleteEntity(depid);
    }

    @Transactional
    public SenyiDeptmentEntity updateDeptment(SenyiDeptmentEntity senyiDeptmentEntity) {
        return departmentRepository.updateDeptment(senyiDeptmentEntity);
    }

    @Transactional(readOnly = true)
    public SenyiDeptmentEntity findDeptmentById(int depid) {
        return departmentRepository.findDeptmentById(depid);
    }

    /**
     * 查询列表的操作
     */
    @Transactional(readOnly = true)
    public List<SenyiDeptmentEntity> findList() {
        return departmentRepository.findList();
    }

    /**
     * 分页查询列表的操作
     */

    @Transactional(readOnly = true)
    public List<SenyiDeptmentEntity> findListBypage(int offset, int limit) {
        return departmentRepository.findListBypage(offset, limit);
    }



}
