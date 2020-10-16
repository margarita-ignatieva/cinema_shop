package com.cinema.shop.dao.impl;

import com.cinema.shop.dao.CinemaHallDao;
import com.cinema.shop.exceptions.DataProcessingException;
import com.cinema.shop.lib.Dao;
import com.cinema.shop.model.CinemaHall;
import com.cinema.shop.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    private static final Logger log = Logger.getLogger(CinemaHallDaoImpl.class);

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        log.info("Trying to add Cinema Hall " + cinemaHall);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            log.info("Added a Cinema Hall" + cinemaHall);
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Cinema Hall" + cinemaHall, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        log.info("Trying to get all Cinema Halls");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Cinema Halls from DB", e);
        }
    }
}
