package by.polina.library.dao;

import by.polina.library.entity.BookReader;
import by.polina.library.pool.ConnectionPool;
import by.polina.library.security.HashCoder;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves methods to process data from Book table of the database
 */
public class BookReaderDao implements IBookReaderDao {

    /**
     * sql queries for Reader table
     */
    private static final String TAKE_ALL_READERS = "SELECT * FROM reader";
    private static final String TAKE_READER_BY_ID = "SELECT * FROM reader WHERE id = ?";
    private static final String TAKE_READER_BY_MAIL = "SELECT * FROM reader WHERE mail = ?";
    private static final String TAKE_LAST_READER_ID = "SELECT id FROM reader ORDER BY id DESC LIMIT 1";
    private static final String ADD_READER = "INSERT INTO reader VALUES (?,?,?,?,?,?,?)";
    private static final String TAKE_READER_BY_MAIL_AND_PASSWORD = "SELECT * FROM reader WHERE mail = ? AND password = ?";
    private static final String UPDATE_BAN_STATUS = "UPDATE reader SET banned = ? WHERE id = ?";

    private static BookReaderDao bookReaderDao = new BookReaderDao();

    private BookReaderDao() {
    }

    /**
     * takes an object of BookReaderDao class
     *
     * @return bookReaderDao
     */
    public static BookReaderDao getInstanceBookReaderDao() {
        return bookReaderDao;
    }


    /**
     * takes a list with objects (readers) from Reader table
     *
     * @return bookReaders
     * throws DaoException
     */
    @Override
    public ArrayList<BookReader> takeList() throws DaoException {

        ArrayList<BookReader> bookReaders = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.
                    executeQuery(TAKE_ALL_READERS);
            while (resultSet.next()) {
                BookReader bookReader = new BookReader();
                bookReader.setId(resultSet.getInt("id"));
                bookReader.setName(resultSet.getString("name"));
                bookReader.setSurname(resultSet.getString("surname"));
                bookReader.setMail(resultSet.getString("mail"));
                bookReader.setPassword(resultSet.getString("password"));
                bookReader.setRole(resultSet.getInt("role"));
                bookReader.setBanned(resultSet.getInt("banned"));
                bookReaders.add(bookReader);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in BookReaderDao " +
                    "class takeList()", e);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return bookReaders;
    }


    /**
     * takes an object (reader) by id from Reader table
     *
     * @param id
     * @return bookReader
     * throws DaoException
     */
    @Override
    public BookReader takeById(int id) throws DaoException {

        BookReader bookReader = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection
                    .prepareStatement(TAKE_READER_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookReader = new BookReader();
                bookReader.setId(id);
                bookReader.setName(resultSet.getString("name"));
                bookReader.setSurname(resultSet.getString("surname"));
                bookReader.setMail(resultSet.getString("mail"));
                bookReader.setPassword(resultSet.getString("password"));
                bookReader.setRole(resultSet.getInt("role"));
                bookReader.setBanned(resultSet.getInt("banned"));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in BookReaderDao " +
                    "class, takeById(int id)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return bookReader;
    }


    /**
     * adds an object (reader) to Reader table
     *
     * @param bookReader
     * throws DaoException
     */
    @Override
    public void add(BookReader bookReader) throws DaoException {

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            ResultSet result = statement
                    .executeQuery(TAKE_LAST_READER_ID);
            int id = 1;
            while (result.next()) {
                id += result.getInt("id");
            }

            preparedStatement = connection
                    .prepareStatement(ADD_READER);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, bookReader.getName());
            preparedStatement.setString(3, bookReader.getSurname());
            preparedStatement.setString(4, bookReader.getMail());

            String password = bookReader.getPassword();                             //hashes a password
            HashCoder createHashCode = new HashCoder();
            String hashResult = createHashCode.
                    createHashedValue(password);
            preparedStatement.setString(5, hashResult);

            preparedStatement.setInt(6, 2);                                         //sets role (1 - admin, 2 - user)
            preparedStatement.setInt(7, 0);                                         //sets banned (0 - no banned, 1 - banned)
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SQLException in BookReaderDao " +
                    "class, add(BookReader bookReader)", e);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * check if a reader is exist in Reader Table (by mail)
     *
     * @param mail
     * @return checkReader
     * @throws DaoException
     */
    @Override
    public boolean verifyIsExistReader(String mail) throws DaoException {

        boolean checkReader = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().
                    takeConnection();

            preparedStatement = connection
                    .prepareStatement(TAKE_READER_BY_MAIL);
            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {                                                        // if a reader isn't exist
                checkReader = false;
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in BookReaderDao class, " +
                    "verifyIsExistReader(BookReader bookReader)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return checkReader;
    }


    /**
     * takes a reader by mail and password (login information)
     *
     * @param mail
     * @param password
     * @return bookReader
     */
    @Override
    public BookReader takeReaderByLoginInfo(String mail, String password)
            throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        BookReader bookReader = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection
                    .prepareStatement(TAKE_READER_BY_MAIL_AND_PASSWORD);
            preparedStatement.setString(1, mail);

            HashCoder createHashCode = new HashCoder();
            String hashResult = createHashCode.createHashedValue(password);
            preparedStatement.setString(2, hashResult);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookReader = new BookReader();
                bookReader.setId(resultSet.getInt("id"));
                bookReader.setName(resultSet.getString("name"));
                bookReader.setSurname(resultSet.getString("surname"));
                bookReader.setMail(mail);
                bookReader.setPassword(resultSet.getString("password"));
                bookReader.setRole(resultSet.getInt("role"));
                bookReader.setBanned(resultSet.getInt("banned"));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in BookReaderDao class, " +
                    "takeReaderByLoginInfo(String mail, String password)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return bookReader;
    }


    /**
     * changes banned date about a reader in Reader Table
     * (1 - banned, 0 - no banned)
     *
     * @param bookReader
     * @throws DaoException
     */
    @Override
    public void changeBanStatusReader(BookReader bookReader)
            throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(UPDATE_BAN_STATUS);
            if (bookReader.getBanned() == 0) {
                preparedStatement.setInt(1, 1);
            } else {
                preparedStatement.setInt(1, 0);
            }
            preparedStatement.setInt(2, bookReader.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SQLException in in BookReaderDao class," +
                    " changeBanStatusReader(BookReader bookReader)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    @Override
    public void deleteById(int id) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "deleteById(int id) in AuthorDao class " +
                "is in developing");
    }


    @Override
    public void update(int id, BookReader bookReader)
            throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "update(int id, BookReader bookReader) " +
                "in BookReaderDao class is in developing");
    }


    @Override
    public int takeId(String mail) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "takeId(String mail) in BookReaderDao class " +
                "is in developing");
    }
}







