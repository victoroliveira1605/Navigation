package com.victoroliveira.navigation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class City implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ibgeId;
    private String uf;
    private String city;
    private boolean capital;
    private double lon;
    private double lat;
    private String cityNoAccents;
    private String alternativeNames;
    private String microregion;
    private String mesoregion;

    public City() {
    }

    public City(Integer ibgeId, String uf, String city, boolean capital, double lon, double lat, String cityNoAccents, String alternativeNames, String microregion, String mesoregion) {
        this.ibgeId = ibgeId;
        this.uf = uf;
        this.city = city;
        this.capital = capital;
        this.lon = lon;
        this.lat = lat;
        this.cityNoAccents = cityNoAccents;
        this.alternativeNames = alternativeNames;
        this.microregion = microregion;
        this.mesoregion = mesoregion;
    }

    public Integer getIbgeId() {
        return ibgeId;
    }

    public void setIbgeId(Integer ibgeId) {
        this.ibgeId = ibgeId;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCityNoAccents() {
        return cityNoAccents;
    }

    public void setCityNoAccents(String cityNoAccents) {
        this.cityNoAccents = cityNoAccents;
    }

    public String getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(String alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public String getMicroregion() {
        return microregion;
    }

    public void setMicroregion(String microregion) {
        this.microregion = microregion;
    }

    public String getMesoregion() {
        return mesoregion;
    }

    public void setMesoregion(String mesoregion) {
        this.mesoregion = mesoregion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(getIbgeId(), city.getIbgeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIbgeId());
    }
}
