package com.bsuir.skripskaya.wt3.dao;

import com.bsuir.skripskaya.wt3.entities.Course;
import com.bsuir.skripskaya.wt3.entities.Order;
import com.bsuir.skripskaya.wt3.entities.User;
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
public class OrderDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDao userDao;

    public void delete(int id) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Order del = session.get(Order.class, id);
            session.delete(del);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    public ArrayList<Order> getAll() throws DaoException {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = String.format("from %s", Order.class.getCanonicalName());
            Query SQLQuery = session.createQuery(hql);
            ArrayList<Order> result = (ArrayList<Order>) SQLQuery.list();
            session.getTransaction().commit();
            for(Order order : result){
                order.getCourses();
                order.getCustomer();
            }
            if (session.isOpen()) {
                session.close();
            }
            return result;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public Order getById(int id) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Order result = session.get(Order.class, id);
            result.getCourses();
            result.getCustomer();
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
            return result;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    public void update(Order object) throws DaoException{
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


    public int add(Order object) throws DaoException {
        try{
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

    public List<Order> getPageElements(int firstResult, int maxResult) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            String hql = String.format("from %s", Order.class.getCanonicalName());
            Query query = session.createQuery(hql);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            List<Order> elements = query.list();
            for(Order order : elements){
                order.getCourses();
                order.getCustomer();
            }
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
            String hql = String.format("select count(*) from %s",Order.class.getCanonicalName());
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
    public void clear() throws DaoException{
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = String.format("delete from %s", Order.class.getCanonicalName());
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

    public List<Order> getOrdersByCourse(Course course) throws DaoException {
        try{
            Session session = sessionFactory.openSession();
            List<Order> orders = course.getOrders();
            for(Order order : orders){
                order.setCourses(null);
            }
            if(session.isOpen()){
                session.close();
            }
            return orders;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }
    public List<Order> getOrdersByUser(User user) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User userInstance = userDao.getById(user.getId());
            List<Order> orders = userInstance.getOrders();
            for(Order order : orders){
                order.setCourses(null);
            }
            session.getTransaction().commit();
            if(session.isOpen()){
                session.close();
            }
            return orders;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }
}
