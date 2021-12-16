package rw.ac.rca.termOneExam.utils;

import rw.ac.rca.termOneExam.domain.City;

import java.util.List;
import java.util.Objects;

public class InputValidity {
    public Integer lessThan40Validity(List<City> cities){
        int flag = 0;

        for(City city: cities){
            if(city.getWeather() > 40){
                flag++;
            }
        }

        return flag;
    }

    public Integer greaterThan10Validity(List<City> cities){
        int flag = 0;

        for(City city: cities){
            if(city.getWeather() < 10){
                flag++;
            }
        }

        return flag;
    }

    public boolean containsMusanzeAndKigali(List<City> cities) {
        boolean exists = false;
        for (City city : cities) {
            if(Objects.equals(city.getName(), "Musanze") || Objects.equals(city.getName(), "Kigali")){
                exists = true;
                break;
            }
        }
        return exists;
    }
}