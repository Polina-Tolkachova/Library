package by.polina.library.dao;

import by.polina.library.entity.OrderBook;

import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves standard methods (with concrete data type) to process data from the database
 */
public interface IOrderBookDao extends IDao<OrderBook> {

    @Override
    ArrayList<OrderBook> takeList() throws DaoException;

    @Override
    OrderBook takeById(int id) throws DaoException;

    @Override
    void add(OrderBook orderBook) throws DaoException;

    @Override
    void deleteById(int id) throws DaoException;

    @Override
    void update(int id, OrderBook orderBook) throws DaoException;

    @Override
    int takeId(String name) throws DaoException;

    int takeBookIdByOrderId(int idOrder) throws DaoException;

    int takeOrderId(int BookId)throws DaoException;

    boolean checkIsBookOrderedReader(int readerId, int bookId)
            throws DaoException;
}