package com.jpa.entitymanage.controller;

import com.jpa.entitymanage.entity.SenyiDeptmentEntity;
import com.jpa.entitymanage.service.impl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpaController")
public class JpaController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 对实体的增删改查
     *
     * @return
     */
    //http://localhost:8082/jpaController/saveEntity
    //{"depId":16,"depName":"部门16","deptCode":"016","note":"note16"}
    @PostMapping("/saveEntity")
    public void saveEntity(@RequestBody SenyiDeptmentEntity senyiDeptmentEntity) {
        departmentService.saveEntity(senyiDeptmentEntity);
    }

    //http://localhost:8082/jpaController/deleteById?depId=1
    @DeleteMapping("/deleteById")
    public void deleteEntity(int depId) {
        departmentService.deleteEntity(depId);
    }

    //http://localhost:8082/jpaController/updateEntity
    //{"depId":16,"depName":"部门16new","deptCode":"016","note":"note16new"}
    @PatchMapping("/updateEntity")
    public void updateDeptment(@RequestBody SenyiDeptmentEntity senyiDeptmentEntity) {
        departmentService.updateDeptment(senyiDeptmentEntity);
    }

    //http://localhost:8082/jpaController/findEntity?depId=16
    @GetMapping("/findEntity")
    public SenyiDeptmentEntity findDeptmentById(int depId) {
        return departmentService.findDeptmentById(depId);
    }

    /**
     * 查询列表的操作
     */
    //http://localhost:8082/jpaController/findList
    @GetMapping("/findList")
    public List<SenyiDeptmentEntity> findList() {
        return departmentService.findList();
    }

    //http://localhost:8082/jpaController/findListByPage?offset=0&limit=5
    @GetMapping("/findListByPage")
    public List<SenyiDeptmentEntity> findListByEntity(int offset, int limit) {
        return departmentService.findListBypage(offset, limit);
    }
}
