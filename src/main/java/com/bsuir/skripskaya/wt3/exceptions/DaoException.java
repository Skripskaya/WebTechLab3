package com.bsuir.skripskaya.wt3.exceptions;

import java.sql.SQLException;


public class DaoException extends SQLException {

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message,Throwable packedException) {
        super(message,packedException);
    }

}
