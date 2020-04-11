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

    private List<User> users;

    public static UserRepository getInstance(){
        if(userRepository == null){
            userRepository = new UserRepository(getAllUsers());
            //here init -> to be removed when db is set up
            User defaultUser = new User("Tim", "Buchalka", "tim@buchalka.au", "tim");
            //userRepository.save(defaultUser);
        }
        return userRepository;
    }

    public boolean save(User user) {
        Optional<User> existingUser = users.stream()
                .filter(u -> u.getEmail().equals(user.getEmail()))
                .findAny();
        if(existingUser.isPresent()) {
            return false;
        }
        users.add(user);

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
        }finally {
            session.close();
        }
        return true;
    }

    public Optional<User> canLogin(String email, String password){
        Optional<User> existingUser = users.stream()
                .filter(u -> u.getEmail().equals(email))
                .filter(u -> u.getPassword().equals(password))
                .findAny();
        return existingUser;
    }

    public User getDefaultUser(){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        try {
            user = session.get(User.class, 1);
            System.out.println("found user "+user);
            //users.add(user);
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
    public static List<User> getAllUsers(){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> foundUsers = new ArrayList<>();
        try {
            foundUsers = (List<User>) session.createQuery("from Users").getResultList();
            transaction.commit();
        }catch (Exception e){
            System.out.println("failed to load users");
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return foundUsers;
    }
}
