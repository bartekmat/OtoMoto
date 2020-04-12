package com.sda.repository;

import com.sda.model.User;
import com.sda.utils.HibernateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRepository {

    private static UserRepository userRepository;

    public static UserRepository getInstance(){
        if(userRepository == null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public boolean saveUser(User user) {
        Optional<User> userByEmail = getUserByEmail(user.getEmail());
        if (userByEmail.isPresent()){
            return false;
        }

        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            System.out.println("Saved user "+user);
            session.persist(user);
            transaction.commit();
        }catch (Exception e){
            System.out.println("failed to save user "+user.getSurname());
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
        return true;
    }

    public Optional<User> canLogin(String email, String password){
        return getUserByEmailAndPass(email,password);
    }

    public User getUserById(Integer id){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        try {
            user = session.get(User.class, id);
            transaction.commit();
        }catch (Exception e){
            System.out.println("failed to save user "+user.getSurname());
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return user;
    }
    //THIS METHOD IS NOT USED ANYWHERE BUT I KEEP IT IN CASE IT'S NEEDED

//    public static List<User> getAllUsers(){
//        SessionFactory sessionFactory = HibernateUtil.getInstance();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        List<User> foundUsers = new ArrayList<>();
//        try {
//            foundUsers = (List<User>) session.createQuery("from Users").getResultList();
//            transaction.commit();
//        }catch (Exception e){
//            System.out.println("failed to load users");
//            e.printStackTrace();
//            transaction.rollback();
//        }finally {
//            session.close();
//        }
//        return foundUsers;
//    }

    private Optional<User> getUserByEmailAndPass(String email, String password){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<User> foundUser = Optional.empty();
        try {
            foundUser = (Optional<User>) session.createQuery("from Users where email = :email and password = :pass")
                    .setParameter("email", email)
                    .setParameter("pass", password)
                    .getResultList().stream().findFirst();
            transaction.commit();
        }catch (Exception e){
            System.out.println("failed to search user by login and password");
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return foundUser;
    }
    public Optional<User> getUserByEmail(String email){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<User> foundUser = Optional.empty();
        try {
            foundUser = (Optional<User>) session.createQuery("from Users where email = :email")
                    .setParameter("email", email)
                    .getResultList().stream().findFirst();
            transaction.commit();
        }catch (Exception e){
            System.out.println("failed to search user by login");
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return foundUser;
    }
}
