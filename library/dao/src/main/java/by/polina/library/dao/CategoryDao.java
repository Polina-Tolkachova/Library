package by.polina.library.dao;

import by.polina.library.entity.Category;
import by.polina.library.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves methods to process data from Category table of the database
 */
public class CategoryDao implements ICategoryDao {

    /**
     * sql queries for Category table
     */
    private static final String TAKE_CATEGORY_BY_ID = "SELECT * FROM category WHERE id = ?";

    private static CategoryDao categoryDao = new CategoryDao();

    private CategoryDao() {
    }

    /**
     * takes an object of CategoryDao class
     *
     * @return categoryDao
     */
    public static CategoryDao getInstanceCategoryDao() {
        return categoryDao;
    }


    /**
     * takes an object (category) by id from Category table
     *
     * @param id
     * @return category
     * throws DaoException
     */
    @Override
    public Category takeById(int id) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Category category = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection
                    .prepareStatement(TAKE_CATEGORY_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = new Category();
                category.setId(id);
                category.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in CategoryDao, " +
                    "takeById(int id)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return category;
    }


    @Override
    public ArrayList<Category> takeList() throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "takeList() in CategoryDao class " +
                "is in developing");
    }


    @Override
    public void add(Category category) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "add(Category category) in CategoryDao class " +
                "is in developing");
    }


    @Override
    public void deleteById(int id) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "deleteById(int id) in CategoryDao class " +
                "is in developing");
    }


    @Override
    public void update(int id, Category category) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "update(int id, Category category) in " +
                "CategoryDao class is in developing");
    }


    @Override
    public int takeId(String name) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "takeId(String name) in CategoryDao class " +
                "is in developing ");
    }
}
