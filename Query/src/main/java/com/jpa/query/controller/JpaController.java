package com.jpa.query.controller;

import com.jpa.query.entity.SenyiDeptmentEntity;
import com.jpa.query.service.impl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jpaController")
public class JpaController {
    @Autowired
    private DepartmentService departmentService;
    //http://localhost:8083/jpaController/findDeptById?depId=9
    @GetMapping("/findDeptById")
    public SenyiDeptmentEntity findDeptById(int depId){
        return departmentService.findDeptById(depId);
    }

    //http://localhost:8083/jpaController/findByPage?depName=1&page=0&size=10
    @GetMapping("/findByPage")
    public List<SenyiDeptmentEntity> findByPage(String depName,Integer page, Integer size){
      return departmentService.findByPage(depName,page,size);
    }

    //http://localhost:8083/jpaController/updateNum?depName=部门hahaa&depId=10
    @PatchMapping("/updateNum")
    public Map<String,Object> updateNum(String depName,int depId){
        return departmentService.updateNum(depName,depId);
    }
}
