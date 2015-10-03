package by.polina.library.dao;

import by.polina.library.entity.OrderBook;
import by.polina.library.pool.ConnectionPool;

import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves methods to process data from Order table of the database
 */
public class OrderBookDao implements IOrderBookDao {


    /**
     * sql queries for Order table
     */
    private static final String TAKE_ALL_ORDERS = "SELECT * FROM order_book";
    private static final String TAKE_BOOK_ID_BY_ORDER_ID = "SELECT id_book FROM order_book WHERE id = ?";
    private static final String TAKE_ORDER_ID_BY_BOOK_ID = "SELECT id FROM order_book WHERE id_book = ?";
    private static final String TAKE_LAST_ORDER_ID = "SELECT id FROM order_book ORDER BY id DESC LIMIT 1";
    private static final String TAKE_BOOK_ID_BY_READER_ID = "SELECT id_book FROM order_book INNER JOIN reader ON ? = id_reader";
    private static final String ADD_ORDER = "INSERT INTO order_book VALUES (?,?,?,?)";
    private static final String DELETE_ORDER = "DELETE FROM order_book WHERE id = ?";

    private static OrderBookDao orderBookDao = new OrderBookDao();

    private OrderBookDao() {
    }

    /**
     * takes an object of OrderBookDao class
     *
     * @return orderBookDao
     */
    public static OrderBookDao getInstanceOrderDao() {
        return orderBookDao;
    }


    /**
     * takes a list with objects (orders) from Order table
     *
     * @return orders
     * throws DaoException
     */
    @Override
    public ArrayList<OrderBook> takeList() throws DaoException {

        ArrayList<OrderBook> orders = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(TAKE_ALL_ORDERS);

            while (resultSet.next()) {
                OrderBook orderBook = new OrderBook();
                orderBook.setId(resultSet.getInt("id"));
                orderBook.setIdBook(resultSet.getInt("id_book"));
                orderBook.setIdReader(resultSet.getInt("id_reader"));
                orderBook.setStatus(resultSet.getInt("status"));            //0 - lending library  1 - reading hall
                orders.add(orderBook);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in OrderBookDao," +
                    "takeList()", e);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return orders;
    }


    /**
     * adds an object (order) to Order table
     *
     * @param order
     * throws DaoException
     */
    @Override
    public void add(OrderBook order) throws DaoException {

        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery(TAKE_LAST_ORDER_ID);
            int id = 1;
            if (resultSet.next()) {
                id += resultSet.getInt("id");
            }

            PreparedStatement preparedStatement = connection
                    .prepareStatement(ADD_ORDER);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, order.getIdBook());
            preparedStatement.setInt(3, order.getIdReader());
            preparedStatement.setInt(4, order.getStatus());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SQLException in OrderBookDao," +
                    "add(OrderBook order)", e);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * deletes an order from Order table
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
                    .prepareStatement(DELETE_ORDER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SQLException in OrderBookDao," +
                    "deleteById(int id)", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * takes id of a book by id of the order
     *
     * @param idOrder
     * @return idBook
     * @throws DaoException
     */
    @Override
    public int takeBookIdByOrderId(int idOrder) throws DaoException {

        int idBook = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection
                    .prepareStatement(TAKE_BOOK_ID_BY_ORDER_ID);
            preparedStatement.setInt(1, idOrder);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idBook = resultSet.getInt("id_book");
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in OrderBookDao," +
                    "takeBookIdByOrderId(int idOrder) ", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return idBook;
    }


    /**
     * takes id of the order by book id
     *
     * @param bookId
     * @return
     * @throws DaoException
     */
    @Override
    public int takeOrderId(int bookId)throws DaoException {

        int orderID = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection
                    .prepareStatement(TAKE_ORDER_ID_BY_BOOK_ID);
            preparedStatement.setInt(1, bookId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderID = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in OrderBookDao, " +
                    "takeOrderId(int BookId) ", e);

        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return orderID;
    }


    /**
     * check if a book has already ordered by a reader
     *
     * @param readerId
     * @param bookId
     * @return isBook
     * @throws DaoException
     */
    @Override
    public boolean checkIsBookOrderedReader(int readerId, int bookId)
            throws DaoException {

        boolean isBook = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection
                    .prepareStatement(TAKE_BOOK_ID_BY_READER_ID);
            preparedStatement.setInt(1, readerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (bookId == resultSet.getInt(1)) {
                    isBook = true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in " +
                    "checkIsBookOrderedReader(int readerId, " +
                    "int bookId), takeList()", e);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return isBook;
    }


    @Override
    public OrderBook takeById(int id) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "takeById(int id) OrderBookDao class is in developing");
    }


    @Override
    public void update(int id, OrderBook order) throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "update(int id, OrderBook order) in " +
                "OrderBookDao class is in developing");
    }


    @Override
    public int takeId(String name)throws DaoException {

        throw new UnsupportedOperationException("The method " +
                "takeId(String name) in OrderBookDao class " +
                "is in developing");
    }
}













