package com.jpa.manadatasource.controller;

import com.jpa.manadatasource.secondary.secondentity.WeatherEntity;
import com.jpa.manadatasource.service.impl.EntityManagerService;
import com.jpa.manadatasource.service.impl.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("jpaControllerSecondary")
public class JpaControllerSecondary {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private EntityManagerService entityManagerService;

    //http://localhost:8086/jpaControllerSecondary/findWeatherById?id=50
    @GetMapping("/findWeatherById")
    public WeatherEntity findWeatherById(Integer id){
        return weatherService.findById(id);
    }

    //http://localhost:8086/jpaControllerSecondary/findWeatherAll
    @GetMapping("/findWeatherAll")
    public List<WeatherEntity> findWeatherAll(){
        return weatherService.findByList();
    }

    //http://localhost:8086/jpaControllerSecondary/updateCityById?city=上海city&id=50
    @PatchMapping("/updateCityById")
    public void updateCityById(String city,Integer id){
        weatherService.updateWeather(city,id);
    }

    //http://localhost:8086/jpaControllerSecondary/findALl
    @GetMapping("/findALl")
    public List<WeatherEntity> findALl(){
        return entityManagerService.getWeather();
    }

    //http://localhost:8086/jpaControllerSecondary/deleteALl
    @GetMapping("/deleteALl")
    public void deleteAll(){
        entityManagerService.update();
    }

}
