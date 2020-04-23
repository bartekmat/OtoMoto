package com.sda.service;

import com.sda.model.Ad;
import com.sda.model.User;
import com.sda.repository.AdRepository;
import com.sda.request.GetFilteredRequest;
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

    public List<Ad> getAllAdsFiltered(GetFilteredRequest request){
        if (request.getCompany().equals("any")){
            return adRepository.getAdsFiltered(request);
        }else {
            return adRepository.getAdsFiltered(request, request.getCompany());
        }
    }
    public List<Ad> getAdsByUser(User user){
        System.out.println(adRepository.getAdsByUser(user));
        return adRepository.getAdsByUser(user);
    }

    public List<String> getAllCompanies() {
        return adRepository.getAllCompanies();
    }
}
