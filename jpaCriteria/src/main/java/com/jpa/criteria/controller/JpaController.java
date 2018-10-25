package com.jpa.criteria.controller;

import com.jpa.criteria.service.impl.DepartmentService;
import com.jpa.criteria.entity.SenyiDeptmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpaController")
public class JpaController {
    @Autowired
    private DepartmentService departmentService;
    /**
     * 查询列表的操作
     */
    //http://localhost:8084/jpaController/findList
    @GetMapping("/findList")
    public List<SenyiDeptmentEntity> findList(){
        SenyiDeptmentEntity senyiDeptmentEntity = new SenyiDeptmentEntity();
        senyiDeptmentEntity.setDepName("部门1");
        senyiDeptmentEntity.setDeptCode("01");
        senyiDeptmentEntity.setNote("note");
        return departmentService.findList(senyiDeptmentEntity);
    }

    //http://localhost:8084/jpaController/findListByPage?page=0&size=5
    @GetMapping("/findListByPage")
    public List<SenyiDeptmentEntity> findListByEntity(int page, int size){
        SenyiDeptmentEntity senyiDeptmentEntity = new SenyiDeptmentEntity();
        senyiDeptmentEntity.setDepName("部门");
        senyiDeptmentEntity.setDeptCode("00");
        senyiDeptmentEntity.setNote("note");
        return departmentService.findListBypage(page,size,senyiDeptmentEntity).getContent();
    }

    //-----------------------------------------以下是JPA query的EntityManager方式-----------
    //http://localhost:8084/jpaController/findListByCriteria?offset=0&limit=5
    @GetMapping("/findListByCriteria")
    public List<SenyiDeptmentEntity> findListByCriteria(int offset, int limit) {
        return departmentService.findListByCriteria(offset, limit);
    }
}
