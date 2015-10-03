package by.polina.library.dao;

import by.polina.library.entity.Book;

import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves standard methods (with concrete data type) to process data from the database
 */
public interface IBookDao extends IDao<Book> {

    @Override
    ArrayList<Book> takeList() throws DaoException;

    @Override
    Book takeById(int id) throws DaoException;

    @Override
    void add(Book book) throws DaoException;

    @Override
    void deleteById(int id) throws DaoException;

    @Override
    void update(int id, Book book) throws DaoException;

    @Override
    int takeId(String name) throws DaoException;

    ArrayList<Book> takeBooksByCategory(int category)
            throws DaoException;

    ArrayList<Book> takeBooksByTitle(String title)
            throws DaoException;

    void decrementAvailabilityBook(int idBook, Book book)
            throws DaoException;

    void incrementAvailabilityBook(int idBook, Book book)
            throws DaoException;
}
