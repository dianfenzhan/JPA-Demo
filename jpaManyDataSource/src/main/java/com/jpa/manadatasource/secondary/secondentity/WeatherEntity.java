package com.jpa.manadatasource.secondary.secondentity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "weather", schema = "test", catalog = "")
public class WeatherEntity {
    private String city;
    private Integer tempLo;
    private Integer tempHi;
    private Double prcp;
    private Date date;
    private int id;

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "temp_lo")
    public Integer getTempLo() {
        return tempLo;
    }

    public void setTempLo(Integer tempLo) {
        this.tempLo = tempLo;
    }

    @Basic
    @Column(name = "temp_hi")
    public Integer getTempHi() {
        return tempHi;
    }

    public void setTempHi(Integer tempHi) {
        this.tempHi = tempHi;
    }

    @Basic
    @Column(name = "prcp")
    public Double getPrcp() {
        return prcp;
    }

    public void setPrcp(Double prcp) {
        this.prcp = prcp;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherEntity that = (WeatherEntity) o;

        if (id != that.id) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (tempLo != null ? !tempLo.equals(that.tempLo) : that.tempLo != null) return false;
        if (tempHi != null ? !tempHi.equals(that.tempHi) : that.tempHi != null) return false;
        if (prcp != null ? !prcp.equals(that.prcp) : that.prcp != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (tempLo != null ? tempLo.hashCode() : 0);
        result = 31 * result + (tempHi != null ? tempHi.hashCode() : 0);
        result = 31 * result + (prcp != null ? prcp.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
