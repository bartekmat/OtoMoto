package com.sda.service;

import com.sda.DTO.AdDTO;
import com.sda.model.Ad;
import com.sda.model.User;
import com.sda.repository.AdRepository;
import com.sda.request.GetFilteredRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<AdDTO> getAllAdsFiltered(GetFilteredRequest request, User user){
        List<AdDTO> foundAds;
        List<Ad> observedAdsByUser = adRepository.getObservedAdsByUser(user.getId());
        System.out.println("observed "+observedAdsByUser.size());
        if (request.getCompany().equals("any")){
             foundAds = adRepository.getAdsFiltered(request).stream()
                            .map(ad -> checkIsObserved(ad, observedAdsByUser)).collect(Collectors.toList());
        }else {
             foundAds = adRepository.getAdsFiltered(request, request.getCompany()).stream()
                     .map(ad -> checkIsObserved(ad, observedAdsByUser)).collect(Collectors.toList());
        }
        System.out.println("total "+foundAds.size());
        return foundAds;
    }

    public List<Ad> getAdsByUser(Integer userId){
        return adRepository.getAdsByUser(userId);
    }

    public List<String> getAllCompanies() {
        return adRepository.getAllCompanies();
    }

    private AdDTO checkIsObserved(Ad ad, List<Ad> observedAds){
        AdDTO adDTO = new AdDTO(ad);
        if (observedAds.contains(ad)){
            adDTO.setObservedByCurrentUser(true);
        }else {
            adDTO.setObservedByCurrentUser(false);
        }return adDTO;
    }
}
