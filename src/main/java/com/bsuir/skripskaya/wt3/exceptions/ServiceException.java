package com.bsuir.skripskaya.wt3.exceptions;


public class ServiceException extends DaoException {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message,Throwable packedException) {
        super(message,packedException);
    }
}
