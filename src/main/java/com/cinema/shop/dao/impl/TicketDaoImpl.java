package com.cinema.shop.dao.impl;

import com.cinema.shop.dao.TicketDao;
import com.cinema.shop.exceptions.DataProcessingException;
import com.cinema.shop.model.Ticket;
import com.cinema.shop.lib.Dao;
import com.cinema.shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Ticket" + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
