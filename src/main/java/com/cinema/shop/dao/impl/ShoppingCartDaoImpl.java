package com.cinema.shop.dao.impl;

import com.cinema.shop.dao.ShoppingCartDao;
import com.cinema.shop.exceptions.DataProcessingException;
import com.cinema.shop.model.ShoppingCart;
import com.cinema.shop.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger log = Logger.getLogger(ShoppingCartDaoImpl.class);
    private final SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        log.info("Trying to add Shopping Cart " + shoppingCart);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            log.info("Added Shopping Cart " + shoppingCart);
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Shopping Cart" + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        log.info("Trying to getById " + user + " Shopping Cart ");
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> query = session.createQuery("FROM ShoppingCart sc"
                    + " join fetch sc.user"
                    + " left join fetch sc.tickets"
                    + " WHERE sc.user.id = :id", ShoppingCart.class);
            query.setParameter("id", user.getId());
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't getById Shopping Cart with User  "
                    + user + "from DB", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        log.info("Trying to update Shopping Cart " + shoppingCart);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
            log.info("Updated Shopping Cart " + shoppingCart);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update Shopping Cart" + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
