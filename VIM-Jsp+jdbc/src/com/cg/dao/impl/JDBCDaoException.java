package com.cg.dao.impl;

//Follow TODOs (if available)
/**
 * 
 * This is a JDBCDaoException class
 * @see java.lang.Object
 * @author Abhishek
 * 
 *
 */
 //TODO 1 Implement it as a Unchecked Exception
public class JDBCDaoException extends Exception{

    public JDBCDaoException() {
        super();
    }

    public JDBCDaoException(String message) {
        super(message);
    }

    public JDBCDaoException(Throwable cause) {
        super(cause);
    }

    public JDBCDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
