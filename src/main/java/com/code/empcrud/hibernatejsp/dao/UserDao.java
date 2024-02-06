package com.code.empcrud.hibernatejsp.dao;

import com.code.empcrud.hibernatejsp.model.User;
import com.code.empcrud.hibernatejsp.util.Dbutil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = Dbutil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = Dbutil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public void deleteUser(int id) {
        Transaction transaction = null;
        try (Session session = Dbutil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("The user with id " + id + " was deleted successfully");
            }
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public User getUser(int id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = Dbutil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
        return user;
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = Dbutil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("from User", User.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
        return userList;
    }

    private void handleTransactionException(Transaction transaction, Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}
