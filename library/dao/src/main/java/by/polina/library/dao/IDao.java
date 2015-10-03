package by.polina.library.dao;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves standard methods to process data from the database
 */
public interface IDao<T> {

    static final Logger LOGGER = Logger.getLogger(IDao.class);


    ArrayList<T> takeList() throws DaoException;

    T takeById(int id) throws DaoException;

    void add(T t) throws DaoException;

    void deleteById(int id) throws DaoException;

    void update(int id, T t) throws DaoException;

    int takeId(String name) throws DaoException;


    /**
     * closes an object of Statement to release resources of the database
     * @param statement
     */
    default void closeStatement(Statement statement) {                                      // it isn't necessary to implement it
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("THe object of Statement wasn't " +
                        "been closed ", e);
            }
        }
    }
}