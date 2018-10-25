package com.jpa.manadatasource.secondary.seconddao;

import com.jpa.manadatasource.secondary.secondentity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WeatherRepository extends JpaRepository<WeatherEntity,Integer> {

    @Modifying
    @Query("update WeatherEntity w set w.city = ?1 where w.id=?2")
    public void updatecityById(String city,Integer id);
}
