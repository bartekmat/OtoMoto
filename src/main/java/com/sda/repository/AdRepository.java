package com.sda.repository;

import com.sda.model.Ad;
import com.sda.model.Car;
import com.sda.model.User;
import com.sda.utils.HibernateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdRepository {

    private static AdRepository adRepository;

    private UserRepository userRepository;
    private List<Ad> ads;


    public static AdRepository getInstance(){
        if(adRepository == null){
            adRepository = new AdRepository(UserRepository.getInstance(), new ArrayList<>());
//            Ad ad1 = new Ad(UserRepository.getInstance().getUserById(1), new Car("Toyota", "Corolla", 90000, 2004), 5500, Timestamp.valueOf(LocalDateTime.now()));
//            Ad ad2 = new Ad(UserRepository.getInstance().getUserById(1), new Car("Toyota", "Auris", 45000, 2014), 7500, Timestamp.valueOf(LocalDateTime.now()));
//            Ad ad3 = new Ad(UserRepository.getInstance().getUserById(1), new Car("Honda", "Civic", 120000, 2009), 16000, Timestamp.valueOf(LocalDateTime.now()));
//            Ad ad4 = new Ad(UserRepository.getInstance().getUserById(1), new Car("Opel", "Astra", 72000, 2010), 1100, Timestamp.valueOf(LocalDateTime.now()));
//            Ad ad5 = new Ad(UserRepository.getInstance().getUserById(1), new Car("Hyundai", "i30", 50000, 2017), 45000, Timestamp.valueOf(LocalDateTime.now()));
//            Ad ad6 = new Ad(UserRepository.getInstance().getUserById(1), new Car("Honda", "Civic", 205000, 2006), 9000, Timestamp.valueOf(LocalDateTime.now()));
//            adRepository.saveAd(ad1);
//            adRepository.saveAd(ad2);
//            adRepository.saveAd(ad3);
//            adRepository.saveAd(ad4);
//            adRepository.saveAd(ad5);
//            adRepository.saveAd(ad6);
        }
        return adRepository;
    }

    public boolean saveAd(Ad ad){

        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            System.out.println("Saved ad "+ad);
            session.persist(ad.getCar());
            session.flush();
            session.persist(ad);
            transaction.commit();
        }catch (Exception e){
            System.out.println("failed to save ad of "+ad.getCar().getCompany()+" "+ad.getCar().getModel());
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
        return true;
    }

    public List<Ad> getAdsByUser(User user){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Ad> foundAds = new ArrayList<>();
        try {
            Integer userId = userRepository.getUserByEmail(user.getEmail()).get().getId();
            foundAds = (List<Ad>) session.createQuery("from Ads where user_id = :id")
                    .setParameter("id", userId)
                    .getResultList();
            transaction.commit();
        }catch (Exception e){
            System.out.println("failed to save user "+user.getSurname());
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return foundAds;
    }
    public List<Ad> getAdsFiltered(Integer minPrice,
                                   Integer maxPrice,
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

    private Stream<Ad> getAdsBetweenPrice(Stream<Ad> stream,Integer min, Integer max) {
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
