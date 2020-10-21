package com.cinema.shop.dao.impl;

import com.cinema.shop.dao.OrderDao;
import com.cinema.shop.exceptions.DataProcessingException;
import com.cinema.shop.model.Order;
import com.cinema.shop.model.User;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger log = Logger.getLogger(OrderDaoImpl.class);

    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        log.info("Trying to add Order " + order);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
            log.info("Added Order " + order);
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Order" + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        log.info("Trying to get order history by " + user);
        try (Session session = sessionFactory.openSession()) {
            Query<Order> getOrdersQuery = session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "LEFT JOIN FETCH o.tickets "
                    + "WHERE o.user = : user", Order.class);
            getOrdersQuery.setParameter("user", user);
            return getOrdersQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all orders by user: "
                    + user, e);
        }
    }
}
