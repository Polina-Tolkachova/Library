package by.polina.library.dao;

import by.polina.library.entity.Book;
import by.polina.library.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves methods to process data from Book table of the database
 */
public class BookDao implements IBookDao {

    /**
     * sql queries for Book table
     */
    private static final String TAKE_ALL_BOOKS = "SELECT * FROM book";
    private static final String TAKE_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?";
    private static final String TAKE_BOOK_BY_TITLE = "SELECT * FROM book WHERE title = ?";
    private static final String TAKE_BOOKS_BY_CATEGORY = "SELECT * FROM book WHERE category = ?";
    private static final String TAKE_LAST_BOOK_ID = "SELECT id FROM book ORDER BY id DESC LIMIT 1";
    private static final String ADD_BOOK = "INSERT INTO book VALUES (?,?,?,?,?)";
    private static final String DELETE_BOOK_BY_ID = "DELETE FROM book WHERE id = ?";
    private static final String UPDATE_BOOK = "UPDATE book SET title = ?, author = ?, category = ?, available = ? WHERE id = ?";
    private static final String UPDATE_AVAILABILITY_BOOK = "UPDATE book SET available = ? WHERE id = ?";

    private static BookDao bookDao = new BookDao();

    private BookDao() {
    }

    /**
     * takes an object of BookDao class
     *
     * @return bookDao
     */
    public static BookDao getInstanceBookDao() {
        return bookDao;
    }


    /**
     * takes a list with objects (books) from Book table
     *
     * @return books
     * throws DaoException
     */
    @Override
    public ArrayList<Book> takeList() throws DaoException {

        Book book;
        ArrayList<Book> books = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(TAKE_ALL_BOOKS);
            while (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getInt("author"));
                book.setCategory(resultSet.getInt("category"));
                book.setCount(resultSet.getInt("available"));
                books.add(book);
            }
        } catch (Exception e) {
            throw new DaoException("SQLException in BookDao, " +
                    "takeList()", e);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return books;
    }


    /**
     * takes an object (book) by id from Book table
     *
     * @param id
     * @return book
     * throws DaoException
     */
    @Override
    public Book takeById(int id) throws DaoException {

        Book book = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection
                    .prepareStatement(TAKE_BOOK_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = new Book();
                book.setId(id);
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getInt("author"));
                book.setCategory(resultSet.getInt("category"));
                book.setCount(resultSet.getInt("available"));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in BookDao, " +
                    "takeById(int id)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return book;
    }


    /**
     * adds an object (book) to Book table
     *
     * @param book
     * throws DaoException
     */
    @Override
    public void add(Book book) throws DaoException {

        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery(TAKE_LAST_BOOK_ID);
            int id = 1;
            while (resultSet.next()) {
                id += resultSet.getInt("id");
            }

            PreparedStatement preparedStatement = connection
                    .prepareStatement(ADD_BOOK);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getAuthor());
            preparedStatement.setInt(4, book.getCategory());
            preparedStatement.setInt(5, book.getCount());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SQLException in BookDao, " +
                    "add(Book book)", e);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * deletes a book from Book table by id
     *
     * @param id
     * throws DaoException
     */
    @Override
    public void deleteById(int id) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection
                    .prepareStatement(DELETE_BOOK_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SQLException in BookDao, " +
                    "deleteById(int id)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * updates information about a book in Book table
     *
     * @param id
     * @param book
     * throws DaoException
     */
    @Override
    public void update(int id, Book book) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection
                    .prepareStatement(UPDATE_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getAuthor());
            preparedStatement.setInt(3, book.getCategory());
            preparedStatement.setInt(4, book.getCount());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SQLException in BookDao, " +
                    "update(int id, Book book)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * takes a list with objects (books) by the category from Book table
     *
     * @param category
     * @return books
     * @throws DaoException
     */
    @Override
    public ArrayList<Book> takeBooksByCategory(int category) throws DaoException {

        ArrayList<Book> books = new ArrayList<>();
        Book book;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection
                    .prepareStatement(TAKE_BOOKS_BY_CATEGORY);
            preparedStatement.setInt(1, category);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getInt("author"));
                book.setCategory(category);
                book.setCount(resultSet.getInt("available"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in BookDao, " +
                    "takeBooksByCategory(int category)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return books;
    }


    /**
     * takes a list with objects (books) by the title from Book table
     *
     * @param title
     * @return books
     * @throws DaoException
     */
    @Override
    public ArrayList<Book> takeBooksByTitle(String title) throws DaoException {

        ArrayList<Book> books = new ArrayList<>();
        Book book;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection
                    .prepareStatement(TAKE_BOOK_BY_TITLE);
            preparedStatement.setString(1, title);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getInt("author"));
                book.setCategory(resultSet.getInt("category"));
                book.setCount(resultSet.getInt("available"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in BookDao, " +
                    "takeBooksByTitle(String title)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return books;
    }


    /**
     * decrements the availability of a book in Book table
     *
     * @param idBook
     * @param book
     * @throws DaoException
     */
    @Override
    public void decrementAvailabilityBook(int idBook, Book book)
            throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection
                    .prepareStatement(UPDATE_AVAILABILITY_BOOK);
            int count = book.getCount();
            int availability = count - 1;
            preparedStatement.setInt(1, availability);
            preparedStatement.setInt(2, idBook);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SQLException in BookDao, " +
                    "decrementAvailabilityBook(int idBook, Book book)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * increments the availability of a book in Book table
     *
     * @param idBook
     * @param book
     * @throws DaoException
     */
    @Override
    public void incrementAvailabilityBook(int idBook, Book book)
            throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection
                    .prepareStatement(UPDATE_AVAILABILITY_BOOK);
            int count = book.getCount();

            int availability = 1 + count;
            preparedStatement.setInt(1, availability);
            preparedStatement.setInt(2, idBook);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SQLException in BookDao, " +
                    "incrementAvailabilityBook(int idBook, Book book)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    @Override
    public int takeId(String title) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "takeId(String title) in BookDao class is in developing");
    }
}

