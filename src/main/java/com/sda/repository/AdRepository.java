package com.sda.repository;

import com.sda.model.Ad;
import com.sda.request.GetFilteredRequest;
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

    public List<Ad> getAdsFiltered(GetFilteredRequest request, String company){

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
                    .setParameter("priceMin", request.getMinPrice())
                    .setParameter("priceMax", request.getMaxPrice())
                    .setParameter("mileageMin", request.getMinMileage())
                    .setParameter("mileageMax", request.getMaxMileage())
                    .setParameter("yearMin", request.getMinYear())
                    .setParameter("yearMax", request.getMaxYear())
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

    public List<Ad> getAdsFiltered(GetFilteredRequest request){

        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Ad> foundAds = new ArrayList<>();
        try {
            foundAds = (List<Ad>) session.createQuery("select a from ads a join fetch a.car where " +
                                                                "(a.price between :priceMin and :priceMax) and " +
                                                                "(a.car.mileage between :mileageMin and :mileageMax) and " +
                                                                "(a.car.year between :yearMin and :yearMax)")

                    .setParameter("priceMin", request.getMinPrice())
                    .setParameter("priceMax", request.getMaxPrice())
                    .setParameter("mileageMin", request.getMinMileage())
                    .setParameter("mileageMax", request.getMaxMileage())
                    .setParameter("yearMin", request.getMinYear())
                    .setParameter("yearMax", request.getMaxYear())
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

    public List<Ad> getAdsByUser(Integer userId){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Ad> foundAds = new ArrayList<>();
        try {
            foundAds = (List<Ad>) session.createQuery("from ads where user_id = :id")
                    .setParameter("id", userId)
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

    public List<Ad> getObservedAdsByUser(Integer userId) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Ad> observedAds = new ArrayList<>();
        try {
            observedAds = (List<Ad>) session.createQuery("select u.ads from users u where u.id=:id")
                    .setParameter("id",userId)
                    .getResultList();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return observedAds;
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
