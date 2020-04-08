package com.sda.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FilterValidator {
    private static FilterValidator filterValidator;

    public static FilterValidator getInstance(){
        if(filterValidator == null){
            filterValidator = new FilterValidator();
        }
        return filterValidator;
    }

    public boolean validatePriceFilter(Double min, Double max) {
        if (min<0 || max<0){
            return false;
        }
        return min <= max;
    }

    public boolean validateMileageFilter(Integer min, Integer max){
        if (min<0 || max<0){
            return false;
        }
        return min <= max;
    }

    public boolean validateYearFilter(Integer min, Integer max) {
        if (min<0 || max<0 || min>LocalDate.now().getYear() || max> LocalDate.now().getYear()){
            return false;
        }
        return min <= max;
    }
}
