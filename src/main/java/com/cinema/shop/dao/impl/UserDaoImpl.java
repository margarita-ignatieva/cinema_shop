package com.cinema.shop.dao.impl;

import com.cinema.shop.dao.UserDao;
import com.cinema.shop.exceptions.DataProcessingException;
import com.cinema.shop.lib.Dao;
import com.cinema.shop.model.User;
import com.cinema.shop.util.HibernateUtil;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert User" + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email");
            query.setParameter("email", email);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get User with email " + email + "from DB", e);
        }
    }
}
