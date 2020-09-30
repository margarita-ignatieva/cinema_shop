package com.cinema.shop.dao.impl;

import com.cinema.shop.dao.MovieDao;
import com.cinema.shop.exceptions.DataProcessingException;
import com.cinema.shop.lib.Dao;
import com.cinema.shop.model.Movie;
import com.cinema.shop.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieDaoImpl implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't process "
                    + movie.getTitle() + " movie data", e);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movie> getAllMovies = session.createQuery("FROM Movie", Movie.class);
            return getAllMovies.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get movies from DB", e);
        }
    }
}
