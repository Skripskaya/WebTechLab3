package com.bsuir.skripskaya.wt3.service;

import com.bsuir.skripskaya.wt3.dao.AdminDao;
import com.bsuir.skripskaya.wt3.entities.Admin;
import com.bsuir.skripskaya.wt3.entities.User;
import com.bsuir.skripskaya.wt3.exceptions.DaoException;
import com.bsuir.skripskaya.wt3.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserService userService;

    public void registerAdmin(User user) throws ServiceException {
        try {
            String hashedPassword = PasswordHashing.generatePasswordHash(user.getPassword());
            user.setPassword(hashedPassword);
            userService.createUser(user);
            User createdUser = userService.findUserByLogin(user.getLogin());
            Admin admin = new Admin(createdUser, true);
            adminDao.add(admin);
        } catch (NoSuchAlgorithmException | DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public Admin loginAdmin(User user) throws ServiceException{
        try {
            User logInUser = userService.findUserByLoginAndPassword(user.getLogin(),user.getPassword());
            if(logInUser == null){
                return null;
            } else {
                Admin logInAdmin = adminDao.getById(logInUser.getId());
                return logInAdmin;
            }
        } catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
