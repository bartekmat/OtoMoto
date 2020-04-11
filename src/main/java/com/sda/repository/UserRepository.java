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
            userRepository = new UserRepository(new ArrayList<>());
            //here init -> to be removed when db is set up
            User defaultUser = new User("Tim", "Buchalka", "tim@buchalka.au", "tim");
            userRepository.save(defaultUser);
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
            sessionFactory.close();
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

        User user = users.get(0);
        try {
            user = session.find(User.class, 1);
            System.out.println("Found user "+ user);
            transaction.commit();
        }catch (Exception e){
            System.out.println("failed to save user "+user.getSurname());
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
        return user;
    }
}
