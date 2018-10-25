package com.jpa.manadatasource.service.impl;

import com.jpa.manadatasource.primary.entity.SenyiDeptmentEntity;
import com.jpa.manadatasource.secondary.secondentity.WeatherEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class EntityManagerService {
   /* @Resource(name="entityManagerPrimary")
    private EntityManager e1;
    @Resource(name="entityManagerSecondary")
    private EntityManager e2;*/

    @PersistenceContext(unitName  = "primaryPersistenceUnit")
    private EntityManager e1;
    @PersistenceContext(unitName  = "secondaryPersistenceUnit")
    private EntityManager e2;
    public List<SenyiDeptmentEntity> getDep(){
        String jpql = "select d from SenyiDeptmentEntity d";
        return e1.createQuery(jpql).getResultList();
    }

    public List<WeatherEntity> getWeather(){
        String jpql = "select w from WeatherEntity w";
        return e2.createQuery(jpql).getResultList();
    }

     @Transactional(value="transactionManagerSecondary")
     public void update(){
        String jpql ="delete from test.weather";
       int a =  e2.createNativeQuery(jpql).executeUpdate();
       System.out.println(a);
     }
}
