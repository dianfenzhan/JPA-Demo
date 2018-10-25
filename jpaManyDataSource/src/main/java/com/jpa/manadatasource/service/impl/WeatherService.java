package com.jpa.manadatasource.service.impl;

import com.jpa.manadatasource.secondary.seconddao.WeatherRepository;
import com.jpa.manadatasource.secondary.secondentity.WeatherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    @Transactional(value="transactionManagerSecondary",readOnly = true)
    public WeatherEntity findById(Integer id){
      return weatherRepository.findById(id).orElse(null);
    }

    @Transactional(value="transactionManagerSecondary",readOnly = true)
    public List<WeatherEntity> findByList(){
     return weatherRepository.findAll();
    }

    @Transactional(value="transactionManagerSecondary")
    public void updateWeather(String city, int id){
      weatherRepository.updatecityById(city,id);
    }
}
