package com.sda.repository;

import com.sda.model.Ad;
import com.sda.model.User;
import com.sda.utils.HibernateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdRepository {

    private static AdRepository adRepository;

    private UserRepository userRepository;

    public static AdRepository getInstance(){
        if(adRepository == null){
            adRepository = new AdRepository(UserRepository.getInstance());
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
            foundAds = (List<Ad>) session.createQuery("from ads where user_id = :id")
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

        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Ad> foundAds = new ArrayList<>();
        try {
            foundAds = (List<Ad>) session.createQuery("select a from ads a join fetch a.car where " +
                                                                "(a.car.company = :company) and "+
                                                                "(a.price between :priceMin and :priceMax) and " +
                                                                "(a.car.mileage between :mileageMin and :mileageMax) and " +
                                                                "(a.car.year between :yearMin and :yearMax)")
                    .setParameter("company", company)
                    .setParameter("priceMin", minPrice)
                    .setParameter("priceMax", maxPrice)
                    .setParameter("mileageMin", minMileage)
                    .setParameter("mileageMax", maxMileage)
                    .setParameter("yearMin", minYear)
                    .setParameter("yearMax", maxYear)
                    .getResultList();
            transaction.commit();
        }catch (Exception e){
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
                                   String sort){

        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Ad> foundAds = new ArrayList<>();
        try {
            foundAds = (List<Ad>) session.createQuery("select a from ads a join fetch a.car where " +
                                                                "(a.price between :priceMin and :priceMax) and " +
                                                                "(a.car.mileage between :mileageMin and :mileageMax) and " +
                                                                "(a.car.year between :yearMin and :yearMax)")

                    .setParameter("priceMin", minPrice)
                    .setParameter("priceMax", maxPrice)
                    .setParameter("mileageMin", minMileage)
                    .setParameter("mileageMax", maxMileage)
                    .setParameter("yearMin", minYear)
                    .setParameter("yearMax", maxYear)
                    .getResultList();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return foundAds;
    }

    public List<String> getAllCompanies() {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<String> foundCompanies = new ArrayList<>();
        try {
            foundCompanies = (List<String>) session.createQuery("select distinct c.company from cars c").getResultList();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return foundCompanies;
    }
}
