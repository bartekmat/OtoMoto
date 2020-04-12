package com.sda.service;

import com.sda.model.Ad;
import com.sda.model.User;
import com.sda.repository.AdRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdService {

    private static AdService adService;

    private AdRepository adRepository;


    public static AdService getInstance(){
        if(adService == null){
            adService = new AdService(AdRepository.getInstance());
        }
        return adService;
    }

    public void saveAd(Ad ad) {
        adRepository.saveAd(ad);
    }

    public List<Ad> getAllAdsFiltered(Integer minPrice, Integer maxPrice, Integer minMileage, Integer maxMileage, Integer minYear, Integer maxYear, String company, String sort){
        return adRepository.getAdsFiltered(minPrice, maxPrice, minMileage, maxMileage, minYear, maxYear, company, sort);
    }
    public List<Ad> getAdsByUser(User user){
        System.out.println(adRepository.getAdsByUser(user));
        return adRepository.getAdsByUser(user);
    }

    public List<String> getAllCompanies() {
        return adRepository.getAllCompanies();
    }
}
