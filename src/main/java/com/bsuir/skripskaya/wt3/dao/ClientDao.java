package com.bsuir.skripskaya.wt3.dao;

import com.bsuir.skripskaya.wt3.entities.Client;
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
public class ClientDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void delete(int id)throws DaoException {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Client del = session.get(Client.class, id);
            session.delete(del);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    public ArrayList<Client> getAll() throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = String.format("from %s",Client.class.getCanonicalName());
            Query SQLQuery = session.createQuery(hql);
            ArrayList<Client> result = (ArrayList<Client>) SQLQuery.list();
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
            return result;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public Client getById(int id) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Client result = session.get(Client.class, id);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
            return result;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    public void update(Client object)throws DaoException {
        try {
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


    public Client add(Client object)throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Client index = (Client)session.save(object);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
            return index;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public List<Client> getPageElements(int firstResult, int maxResult) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            String hql = String.format("from %s",Client.class.getCanonicalName());
            Query query = session.createQuery(hql);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            List<Client> elements = query.list();
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
            String hql = String.format("select count(*) from %s",Client.class.getCanonicalName());
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
            String hql = String.format("delete from %s", Client.class.getCanonicalName());
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
