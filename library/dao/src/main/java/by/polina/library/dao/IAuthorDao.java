package by.polina.library.dao;

import by.polina.library.entity.Author;

import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves standard methods (with concrete data type) to process data from the database
 */
public interface IAuthorDao extends IDao<Author> {

    @Override
    ArrayList<Author> takeList() throws DaoException;

    @Override
    Author takeById(int id) throws DaoException;

    @Override
    void add(Author author) throws DaoException;

    @Override
    void deleteById(int id) throws DaoException;

    @Override
    void update(int id, Author author) throws DaoException;

    @Override
    int takeId(String name) throws DaoException;

    int takeAuthorId (String surname, String name) throws DaoException;

    int takeLastAuthorId() throws DaoException;
}
