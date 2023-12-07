package com.bsuir.skripskaya.wt3.dao;

import com.bsuir.skripskaya.wt3.entities.Order;
import com.bsuir.skripskaya.wt3.entities.User;
import com.bsuir.skripskaya.wt3.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void delete(int id) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User del = session.get(User.class, id);
            session.delete(del);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    public ArrayList<User> getAll() throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = String.format("from %s",User.class.getCanonicalName());
            Query SQLQuery = session.createQuery(hql);
            ArrayList<User> result = (ArrayList<User>) SQLQuery.list();
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
            return result;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public User getById(int id) throws DaoException{
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User result = session.get(User.class, id);
            result.getOrders();
            for(Order order : result.getOrders()){
                order.getCourses();
            }
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
            return result;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    public void update(User object)throws DaoException {
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


    public int add(User object) throws DaoException{
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            int index = (int)session.save(object);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
            return index;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public List<User> getPageElements(int firstResult, int maxResult) throws DaoException{
        try {
            Session session = sessionFactory.openSession();
            String hql = String.format("from %s", User.class.getCanonicalName());
            Query query = session.createQuery(hql);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            List<User> elements = query.list();
            if(session.isOpen()){
                session.close();
            }
            return elements;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public long getTableRowsCount() throws DaoException{
        try {
            Session session = sessionFactory.openSession();
            String hql = String.format("select count(*) from %s", User.class.getCanonicalName());
            Query query = session.createQuery(hql);
            Long result = (Long) query.uniqueResult();
            if(session.isOpen()){
                session.close();
            }
            return result;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public User getUserByLogin(String login) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            String hql = String.format("from %s u",User.class.getCanonicalName());
            hql=hql.concat(" where u.login = :login");
            Query query = session.createQuery(hql);
            query.setParameter("login",login);
            User user = (User)query.uniqueResult();
            user.getOrders();
            if(session.isOpen()){
                session.close();
            }
            return user;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public User getUserByLoginAndPassword(String login,String password) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            String hql = String.format("from %s u",User.class.getCanonicalName());
            hql=hql.concat(" where u.login = :login and u.password = :password");
            Query query = session.createQuery(hql);
            query.setParameter("login",login);
            query.setParameter("password",password);
            User user = (User)query.uniqueResult();
            if(session.isOpen()){
                session.close();
            }
            return user;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    @Deprecated
    public void clear() throws DaoException{
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = String.format("delete from %s", User.class.getCanonicalName());
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
