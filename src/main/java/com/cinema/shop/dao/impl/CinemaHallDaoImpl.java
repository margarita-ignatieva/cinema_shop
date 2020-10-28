package com.cinema.shop.dao.impl;

import com.cinema.shop.dao.CinemaHallDao;
import com.cinema.shop.exceptions.DataProcessingException;
import com.cinema.shop.model.CinemaHall;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    private static final Logger log = Logger.getLogger(CinemaHallDaoImpl.class);
    private final SessionFactory sessionFactory;

    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        log.info("Trying to add Cinema Hall " + cinemaHall);
        try {
            session = sessionFactory.openSession();
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
        log.info("Trying to getById all Cinema Halls");
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't getById Cinema Halls from DB", e);
        }
    }

    @Override
    public CinemaHall getdById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CinemaHall.class, id);
        }
    }
}
