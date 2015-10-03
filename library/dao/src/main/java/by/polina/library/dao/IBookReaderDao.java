package by.polina.library.dao;

import by.polina.library.entity.BookReader;

import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves standard methods (with concrete data type) to process data from the database
 */
public interface IBookReaderDao extends IDao<BookReader> {

    @Override
    ArrayList<BookReader> takeList() throws DaoException;

    @Override
    BookReader takeById(int id) throws DaoException;

    @Override
    void add(BookReader bookReader) throws DaoException;

    @Override
    void deleteById(int id) throws DaoException;

    @Override
    void update(int id, BookReader bookReader) throws DaoException;

    @Override
    int takeId(String name) throws DaoException;

    boolean verifyIsExistReader(String mail)
            throws DaoException;

    BookReader takeReaderByLoginInfo(String mail, String password)
            throws DaoException;

    void changeBanStatusReader(BookReader bookReader)
            throws DaoException;
}