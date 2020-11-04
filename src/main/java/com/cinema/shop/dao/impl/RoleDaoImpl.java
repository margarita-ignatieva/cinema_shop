package com.cinema.shop.dao.impl;

import com.cinema.shop.dao.RoleDao;
import com.cinema.shop.exceptions.DataProcessingException;
import com.cinema.shop.model.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private static final Logger log = Logger.getLogger(RoleDaoImpl.class);
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role role) {
        Transaction transaction = null;
        Session session = null;
        log.info("Trying to add Role " + role);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(role);
            transaction.commit();
            log.info("Added Role " + role);
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Role " + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> query = session.createQuery("FROM Role WHERE roleName = :roleName");
            query.setParameter("roleName", roleName);
            return query.getSingleResult();
        }
    }
}
