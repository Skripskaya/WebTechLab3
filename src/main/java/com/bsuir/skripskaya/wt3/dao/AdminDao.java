package com.bsuir.skripskaya.wt3.dao;

import com.bsuir.skripskaya.wt3.entities.Admin;
import com.bsuir.skripskaya.wt3.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class AdminDao {
    @Autowired
    private SessionFactory sessionFactory;


    public void delete(int id) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Admin del = session.get(Admin.class, id);
            session.delete(del);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    public ArrayList<Admin> getAll() throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = String.format("from %s",Admin.class.getCanonicalName());
            Query SQLQuery = session.createQuery(hql);
            ArrayList<Admin> result = (ArrayList<Admin>) SQLQuery.list();
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
            return result;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public Admin getById(int id) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Admin result = session.get(Admin.class, id);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
            return result;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    public void update(Admin object) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    public Admin add(Admin object) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Admin index = (Admin)session.save(object);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
            return index;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public List<Admin> getPageElements(int firstResult, int maxResult) throws DaoException {
        try{
            Session session = sessionFactory.openSession();
            String hql = String.format("from %s",Admin.class.getCanonicalName());
            Query query = session.createQuery(hql);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            List<Admin> elements = query.list();
            if(session.isOpen()){
                session.close();
            }
            return elements;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public long getTableRowsCount() throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            String hql = String.format("select count(*) from %s",Admin.class.getCanonicalName());
            Query query = session.createQuery(hql);
            Long result = (Long)query.uniqueResult();
            if(session.isOpen()){
                session.close();
            }
            return result;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Deprecated
    public void clear() throws DaoException {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = String.format("delete from %s", Admin.class.getCanonicalName());
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }
}
