package com.jpa.byname.controller;

import com.jpa.byname.entity.SenyiDeptmentEntity;
import com.jpa.byname.service.impl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jpaController")
public class JpaController {
    @Autowired
    private DepartmentService departmentService;
    //http://localhost:8081/jpaController/findListByDeptName?depName=部门6
    @GetMapping("/findListByDeptName")
    public List<SenyiDeptmentEntity> findListByEntity(String depName){
        return departmentService.findListByDeptName(depName);
    }
}
