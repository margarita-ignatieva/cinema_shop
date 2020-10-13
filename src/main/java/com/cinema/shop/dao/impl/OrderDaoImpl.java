package com.cinema.shop.dao.impl;

import com.cinema.shop.dao.OrderDao;
import com.cinema.shop.exceptions.DataProcessingException;
import com.cinema.shop.lib.Dao;
import com.cinema.shop.model.Order;
import com.cinema.shop.model.User;
import com.cinema.shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
