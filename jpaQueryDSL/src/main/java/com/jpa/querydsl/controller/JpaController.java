package com.jpa.querydsl.controller;

import com.jpa.querydsl.entity.SenyiUserEntity;
import com.jpa.querydsl.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jpaController")
public class JpaController {
    @Autowired
    private UserService userService;

    //http://localhost:8085/jpaController/findListBySpringData
    @GetMapping("/findListBySpringData")
    public List<SenyiUserEntity> findListBySpringData() {
        SenyiUserEntity senyiUserEntity = new SenyiUserEntity();
        senyiUserEntity.setName("1");
        senyiUserEntity.setAddress("1");
        return userService.findListBySpringData(senyiUserEntity);
    }

    //http://localhost:8085/jpaController/findListBypageBySpringData?page=0&size=10
    @GetMapping("/findListBypageBySpringData")
    public List<SenyiUserEntity> findListBypageBySpringData(int page, int size) {
        SenyiUserEntity senyiUserEntity = new SenyiUserEntity();
        senyiUserEntity.setName("1");
        senyiUserEntity.setAddress("1");
        return userService.findListBypageBySpringData(page, size, senyiUserEntity).getContent();
    }

    //http://localhost:8085/jpaController/findUserByZichaxunBySpringData
    @GetMapping("/findUserByZichaxunBySpringData")
    public List<SenyiUserEntity> findUserByZichaxunBySpringData() {
        return userService.findUserByZichaxunBySpringData();
    }

    //--------------------------------------------上面是spring data jpa方式，下面是jpa方式---------------------------------------------------
    //http://localhost:8085/jpaController/findList
    @GetMapping("/findList")
    public List<SenyiUserEntity> findList() {
        SenyiUserEntity senyiUserEntity = new SenyiUserEntity();
        senyiUserEntity.setName("11");
        senyiUserEntity.setAddress("1");
        return userService.findList(senyiUserEntity);
    }

    //http://localhost:8085/jpaController/findListByPage?offset=0&limit=10
    @GetMapping("/findListByPage")
    public List<SenyiUserEntity> findListByEntity(int offset, int limit) {
        SenyiUserEntity senyiUserEntity = new SenyiUserEntity();
        senyiUserEntity.setName("1");
        senyiUserEntity.setAddress("1");
        return userService.findListBypage(offset, limit, senyiUserEntity);
    }

    //http://localhost:8085/jpaController/findListManyTables
    @GetMapping("/findListManyTables")
    public List<SenyiUserEntity> findListManyTables() {
        SenyiUserEntity senyiUserEntity = new SenyiUserEntity();
        senyiUserEntity.setName("1");
        senyiUserEntity.setAddress("1");
        return userService.findListManyTables(senyiUserEntity);
    }

    //http://localhost:8085/jpaController/findListBypageManyTables?offset=0&limit=3
    @GetMapping("/findListBypageManyTables")
    public List<SenyiUserEntity> findListBypageManyTables(Integer offset, Integer limit) {
        SenyiUserEntity senyiUserEntity = new SenyiUserEntity();
        senyiUserEntity.setName("1");
        senyiUserEntity.setAddress("1");
        return userService.findListBypageManyTables(offset, limit, senyiUserEntity);
    }

    //http://localhost:8085/jpaController/findUserByZichaxun
    @GetMapping("/findUserByZichaxun")
    public List<SenyiUserEntity> findUserByZichaxun() {
        return userService.findUserByZichaxun();
    }

}