package com.bsuir.skripskaya.wt3.dao;

import com.bsuir.skripskaya.wt3.entities.Course;
import com.bsuir.skripskaya.wt3.entities.Order;
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
public class CourseDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private OrderDao orderDao;

    public void delete(int id) throws DaoException {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Course del = session.get(Course.class, id);
            session.delete(del);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    public ArrayList<Course> getAll() throws DaoException {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = String.format("from %s",Course.class.getCanonicalName());
            Query SQLQuery = session.createQuery(hql);
            ArrayList<Course> result = (ArrayList<Course>) SQLQuery.list();
            for(Course course : result){
                course.getOrders();
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

    public Course getById(int id) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Course result = session.get(Course.class, id);
            result.getOrders();
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
            return result;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }


    public void update(Course object) throws DaoException {
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


    public int add(Course object) throws DaoException{
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

    public List<Course> getPageElements(int firstResult, int maxResult) throws DaoException{
        try{
            Session session = sessionFactory.openSession();
            String hql = String.format("from %s", Course.class.getCanonicalName());
            Query query = session.createQuery(hql);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            List<Course> elements = query.list();
            for(Course course : elements){
                course.getOrders();
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
        try{
            Session session = sessionFactory.openSession();
            String hql = String.format("select count(*) from %s",Course.class.getCanonicalName());
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
            String hql = String.format("delete from %s", Course.class.getCanonicalName());
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

    public List<Course> getCoursesByOrder(Order order) throws DaoException{
        try {
            Session session = sessionFactory.openSession();
            Order orderInstance = orderDao.getById(order.getId());
            List<Course> courses = orderInstance.getCourses();
            for(Course course : courses){
                course.setOrders(null);
            }
            if (session.isOpen()) {
                session.close();
            }
            return courses;
        }catch (HibernateException e){
            throw new DaoException(e.getMessage(),e);
        }
    }
}
