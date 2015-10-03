package by.polina.library.dao;

import by.polina.library.entity.Author;
import by.polina.library.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves methods to process data from Author table of the database
 */
public class AuthorDao implements IAuthorDao {

    /**
     * sql queries for Author table
     */
    private static final String TAKE_AUTHOR_BY_ID = "SELECT * FROM author WHERE id = ?";
    private static final String TAKE_LAST_AUTHOR_ID = "SELECT id FROM author ORDER BY id DESC LIMIT 1";
    private static final String ADD_AUTHOR = "INSERT INTO author VALUES (?,?,?)";
    private static final String TAKE_AUTHOR_ID_BY_SURNAME_NAME = "SELECT id FROM author WHERE surname = ? and name = ?";

    private static AuthorDao authorDao = new AuthorDao();

    private AuthorDao() {
    }

    /**
     * takes an object of AuthorDao class
     *
     * @return authorDao
     */
    public static AuthorDao takeInstanceAuthorDao() {
        return authorDao;
    }


    /**
     * takes an object (author) by id from Author table
     *
     * @param id
     * @return author
     * throws DaoException
     */
    @Override
    public Author takeById(int id) throws DaoException {

        Author author = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection
                    .prepareStatement(TAKE_AUTHOR_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                author = new Author();
                author.setId(id);
                author.setName(resultSet.getString("name"));
                author.setSurname(resultSet.getString("surname"));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in AuthorDao, " +
                    "takeById(int id)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return author;
    }


    /**
     * adds an object (author) to Author table
     *
     * @param author
     * throws DaoException
     */
    @Override
    public void add(Author author) throws DaoException {

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection
                    .prepareStatement(ADD_AUTHOR);

            int lastAuthorId = takeLastAuthorId();
            lastAuthorId++;
            preparedStatement.setInt(1, lastAuthorId);

            preparedStatement.setString(2, author.getName());
            preparedStatement.setString(3, author.getSurname());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SQLException in AuthorDao, " +
                    "add(Author author) method", e);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * takes the last id in Author table
     *
     * @return lastAuthorId
     * @throws DaoException
     */
    @Override
    public int takeLastAuthorId() throws DaoException {

        int lastAuthorId = 0;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery(TAKE_LAST_AUTHOR_ID);
            while (resultSet.next()) {
                lastAuthorId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in AuthorDao, " +
                    "takeLastAuthorId()", e);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return lastAuthorId;
    }


    /**
     * takes id of the author by surname and name (from Author table)
     *
     * @param surname, name
     * return idAuthor
     * throws DaoException
     */
    @Override
    public int takeAuthorId (String surname, String name) throws DaoException {

        int idAuthor = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection
                    .prepareStatement(TAKE_AUTHOR_ID_BY_SURNAME_NAME);
            preparedStatement.setString(1, surname);
            preparedStatement.setString(2, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idAuthor = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in AuthorDao, " +
                    "takeId(String surname)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return idAuthor;
    }


    @Override
    public ArrayList<Author> takeList() throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "takeList in AuthorDao class " +
                "is in developing");
    }


    @Override
    public void deleteById(int id) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "deleteById(int id) in AuthorDao class " +
                "is in developing");
    }


    @Override
    public void update(int id, Author author) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "update(int id, Author author) " +
                "in AuthorDao class is in developing");
    }


    @Override
    public int takeId(String name) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "takeId(String name) in AuthorDao class " +
                "is in developing");
    }
}
