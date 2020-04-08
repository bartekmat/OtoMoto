package com.sda.repository;

import com.sda.model.Ad;
import com.sda.model.Car;
import com.sda.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdRepository {

    private static AdRepository adRepository;

    private List<Ad> ads;

    public static AdRepository getInstance(){
        if(adRepository == null){
            adRepository = new AdRepository(new ArrayList<>());
            Ad ad1 = new Ad(UserRepository.getInstance().getDefaultUser(), new Car("Toyota", "Corolla", 90000, 2004), 5500.0, LocalDateTime.now());
            Ad ad2 = new Ad(UserRepository.getInstance().getDefaultUser(), new Car("Toyota", "Auris", 45000, 2014), 7500.0, LocalDateTime.now());
            Ad ad3 = new Ad(UserRepository.getInstance().getDefaultUser(), new Car("Honda", "Civic", 120000, 2009), 16000.0, LocalDateTime.now());
            Ad ad4 = new Ad(UserRepository.getInstance().getDefaultUser(), new Car("Opel", "Astra", 72000, 2010), 1100.0, LocalDateTime.now());
            Ad ad5 = new Ad(UserRepository.getInstance().getDefaultUser(), new Car("Hyundai", "i30", 50000, 2017), 45000.0, LocalDateTime.now());
            Ad ad6 = new Ad(UserRepository.getInstance().getDefaultUser(), new Car("Honda", "Civic", 205000, 2006), 9000.0, LocalDateTime.now());
            adRepository.save(ad1);
            adRepository.save(ad2);
            adRepository.save(ad3);
            adRepository.save(ad4);
            adRepository.save(ad5);
            adRepository.save(ad6);
        }

        return adRepository;
    }
    public boolean save(Ad ad){
        ads.add(ad);
        return true;
    }

    public List<Ad> getAll(){
        return ads;
    }
    public List<Ad> getAdsByUser(User user){
        return ads.stream()
                .filter(ad -> ad.getOwner().getEmail().equals(user.getEmail()))
                .collect(Collectors.toList());
    }
    public List<Ad> getAdsFiltered(Double minPrice,
                                   Double maxPrice,
                                   Integer minMileage,
                                   Integer maxMileage,
                                   Integer minYear,
                                   Integer maxYear,
                                   String company,
                                   String sort){

        return getAdsOfComapny(
                    getAdsBetweenYear(
                            getAdsBetweenPrice(
                                    getAdsBetweenMileage(
                                            ads.stream(), minMileage, maxMileage)
                                    , minPrice,maxPrice)
                            ,minYear, maxYear),
                    company)
                            .collect(Collectors.toList());

    }

    private Stream<Ad> getAdsOfComapny(Stream<Ad> stream, String company){
        if (company.equals("any")){
            return stream;
        }
        return stream.filter(ad -> ad.getCar().getCompany().equals(company));
    }

    private Stream<Ad> sort(Stream<Ad> stream, String sort){
        if (sort.equals("newest")){
            return stream.sorted(Comparator
                    .comparing(ad -> ad.getCar().getYear(), Comparator.reverseOrder()));
        }
        if (sort.equals("cheapest")){
            return stream.sorted(Comparator
                    .comparing(Ad::getPrice, Comparator.reverseOrder()));
        }
        if (sort.contains("mileage")){
            return stream.sorted(Comparator
                    .comparing(ad -> ad.getCar().getMileage(), Comparator.reverseOrder()));
        }
        if (sort.contains("recent")){
            return stream.sorted(Comparator
                    .comparing(Ad::getCreatedAt, Comparator.reverseOrder()));
        }
        return stream;

    }

    private Stream<Ad> getAdsBetweenPrice(Stream<Ad> stream,Double min, Double max) {
        return stream
                .filter(ad -> ad.getPrice()>=min)
                .filter(ad -> ad.getPrice()<=max);
    }

    private Stream<Ad> getAdsBetweenMileage(Stream<Ad> stream, Integer min, Integer max) {
        return stream
                .filter(ad -> ad.getCar().getMileage()>=min)
                .filter(ad -> ad.getCar().getMileage()<=max);
    }

    private Stream<Ad> getAdsBetweenYear(Stream<Ad> stream, Integer min, Integer max) {
        return stream
                .filter(ad -> ad.getCar().getYear()>=min)
                .filter(ad -> ad.getCar().getYear()<=max);
    }

    public List<String> getAllCompanies() {
        return ads.stream().map(ad -> ad.getCar().getCompany()).distinct().collect(Collectors.toList());
    }
}
