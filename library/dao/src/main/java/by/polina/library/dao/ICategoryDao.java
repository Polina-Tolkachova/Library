package by.polina.library.dao;

import by.polina.library.entity.Category;

import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves standard methods (with concrete data type) to process data from the database
 */
public interface ICategoryDao extends IDao<Category> {

    @Override
    ArrayList<Category> takeList() throws DaoException;

    @Override
    Category takeById(int id) throws DaoException;

    @Override
    void add(Category category) throws DaoException;

    @Override
    void deleteById(int id) throws DaoException;

    @Override
    void update(int id, Category category) throws DaoException;

    @Override
    int takeId(String name) throws DaoException;
}