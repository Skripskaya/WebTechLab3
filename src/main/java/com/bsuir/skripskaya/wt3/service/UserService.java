package com.bsuir.skripskaya.wt3.service;

import com.bsuir.skripskaya.wt3.dao.UserDao;
import com.bsuir.skripskaya.wt3.entities.User;
import com.bsuir.skripskaya.wt3.exceptions.DaoException;
import com.bsuir.skripskaya.wt3.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void createUser(User user) throws ServiceException {
        try {
            user.setRegistrationDate(new Date(System.currentTimeMillis()));
            userDao.add(user);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public User findUserByLogin(String login) throws ServiceException {
        try {
            return userDao.getUserByLogin(login);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public User findUserByLoginAndPassword(String login,String password) throws ServiceException {
        try {
            return userDao.getUserByLoginAndPassword(login,PasswordHashing.generatePasswordHash(password));
        }catch (NoSuchAlgorithmException | DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public User findUserById(int id) throws ServiceException {
        try {
            return userDao.getById(id);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public void updateUser(User user) throws ServiceException{
        try {
            userDao.update(user);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
