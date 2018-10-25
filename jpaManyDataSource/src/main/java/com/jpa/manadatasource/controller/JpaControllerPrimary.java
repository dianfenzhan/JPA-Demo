package com.jpa.manadatasource.controller;

import com.jpa.manadatasource.primary.entity.SenyiDeptmentEntity;
import com.jpa.manadatasource.service.impl.DepartmentService;
import com.jpa.manadatasource.service.impl.EntityManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jpaControllerPrimary")
public class JpaControllerPrimary {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EntityManagerService entityManagerService;
    //http://localhost:8086/jpaControllerPrimary/findDeptById?depId=9
    @GetMapping("/findDeptById")
    public SenyiDeptmentEntity findDeptById(int depId){
        return departmentService.findDeptById(depId);
    }

    //http://localhost:8086/jpaControllerPrimary/findByPage?depName=1&page=0&size=10
    @GetMapping("/findByPage")
    public List<SenyiDeptmentEntity> findByPage(String depName,Integer page, Integer size){
      return departmentService.findByPage(depName,page,size);
    }

    //http://localhost:8086/jpaControllerPrimary/updateNum?depName=部门10100101我们&depId=10
    @PatchMapping("/updateNum")
    public Map<String,Object> updateNum(String depName,int depId){
        return departmentService.updateNum(depName,depId);
    }

    //http://localhost:8086/jpaControllerPrimary/findALl
    @GetMapping("/findALl")
    public List<SenyiDeptmentEntity> findALl(){
        return entityManagerService.getDep();
    }
}
