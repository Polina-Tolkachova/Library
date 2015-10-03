package by.polina.library.service;

import by.polina.library.entity.*;
import by.polina.library.dao.*;
import by.polina.library.dao.DaoException;

import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */


/**
 * realises methods of Dao classes
 */
public class Service {

    private static Service instance = new Service();

    private Service() {
    }

    /**
     * creates the object of Service class
     *
     * @return instance
     */
    public static Service initService() {
        return instance;
    }


    /**
     * takes a list with objects (books) from Book table
     *
     * @return books
     * @throws ServiceException
     */
    public ArrayList<Book> takeBooks() throws ServiceException {

        BookDao bookDao = BookDao.getInstanceBookDao();
        ArrayList<Book> books;
        try {
            books = bookDao.takeList();
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeBooks(), Service class ", e);
        }
        return books;
    }


    /**
     * takes an object (book) by id from Book table
     *
     * @param id
     * @return book
     * @throws ServiceException
     */
    public Book takeBookById(int id) throws ServiceException {

        BookDao bookDao = BookDao.getInstanceBookDao();
        Book book;
        try {
            book = bookDao.takeById(id);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeBookById(), Service class ", e);
        }
        return book;
    }


    /**
     * adds an object (book) to Book table
     *
     * @param book
     * @throws ServiceException
     */
    public void addBook(Book book) throws ServiceException {

        BookDao bookDao = BookDao.getInstanceBookDao();
        try {
            bookDao.add(book);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "addBook(Book book), Service class ", e);
        }
    }


    /**
     * deletes a book from Book table by id
     *
     * @param id
     * @throws ServiceException
     */
    public void deleteBookById(int id) throws ServiceException {

        BookDao bookDao = BookDao.getInstanceBookDao();
        try {
            bookDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "deleteBookById(int id), Service class ", e);
        }
    }


    /**
     * updates information about a book in Book table
     *
     * @param id
     * @param book
     * @throws ServiceException
     */
    public void updateBook(int id, Book book) throws ServiceException {

        BookDao bookDao = BookDao.getInstanceBookDao();
        try {
            bookDao.update(id, book);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "updateBook(int id, Book book), Service class ", e);
        }
    }


    /**
     * takes a list with objects (books) by the category from Book table
     *
     * @param category
     * @return books
     * @throws ServiceException
     */
    public ArrayList<Book> takeBooksByCategory(int category)
            throws ServiceException {

        BookDao bookDao = BookDao.getInstanceBookDao();
        ArrayList<Book> books;
        try {
            books = bookDao.takeBooksByCategory(category);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeBooksByCategory(int category), " +
                    "Service class ", e);
        }
        return books;
    }


    /**
     * takes a list with objects (books) by the title from Book table
     *
     * @param title
     * @return books
     * @throws ServiceException
     */
    public ArrayList<Book> takeBooksByTitle(String title)
            throws ServiceException {

        BookDao bookDao = BookDao.getInstanceBookDao();
        ArrayList<Book> books;
        try {
            books = bookDao.takeBooksByTitle(title);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeBooksByTitle(String title) " +
                    "Service class ", e);
        }
        return books;
    }


    /**
     * decrements the availability of a book in Book table
     *
     * @param id
     * @param book
     * @throws ServiceException
     */
    public void decrementQuantityBook(int id, Book book)
            throws ServiceException {

        BookDao bookDao = BookDao.getInstanceBookDao();
        try {
            bookDao.decrementAvailabilityBook(id, book);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "decrementQuantityBook(int id, Book book, " +
                    "Service class ", e);
        }
    }


    /**
     * increments the availability of a book in Book table
     *
     * @param id
     * @param book
     * @throws ServiceException
     */
    public void incrementQuantityBook(int id, Book book)
            throws ServiceException {

        BookDao bookDao = BookDao.getInstanceBookDao();
        try {
            bookDao.incrementAvailabilityBook(id, book);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "incrementQuantityBook(int id, Book book), " +
                    "Service class ", e);
        }
    }


    /**
     * takes a list with objects (readers) from Reader table
     *
     * @return bookReaders
     * @throws ServiceException
     */
    public ArrayList<BookReader> takeReaders() throws ServiceException {

        BookReaderDao bookReaderDao = BookReaderDao.
                getInstanceBookReaderDao();
        ArrayList<BookReader> bookReaders;
        try {
            bookReaders = bookReaderDao.takeList();
        } catch (DaoException e) {
            throw new ServiceException("ServiceException " +
                    "in takeReaders(), Service class ", e);
        }
        return bookReaders;
    }


    /**
     * takes an object (reader) by id from Reader table
     *
     * @param id
     * @return bookReader
     * @throws ServiceException
     */
    public BookReader takeReaderById(int id) throws ServiceException {

        BookReaderDao bookReaderDao = BookReaderDao.
                getInstanceBookReaderDao();
        BookReader bookReader;
        try {
            bookReader = bookReaderDao.takeById(id);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeReaderById(int id), Service class ", e);
        }

        return bookReader;
    }


    /**
     * adds an object (reader) to Reader table
     *
     * @param bookReader
     * @throws ServiceException
     */
    public void addReader(BookReader bookReader) throws ServiceException {

        BookReaderDao bookReaderDao = BookReaderDao.
                getInstanceBookReaderDao();
        try {
            bookReaderDao.add(bookReader);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "addReader(BookReader bookReader), " +
                    "Service class ", e);
        }
    }


    /**
     * takes a reader by mail and password (login information)
     *
     * @param mail
     * @param password
     * @return bookReader
     * @throws ServiceException
     */
    public BookReader takeReaderByLoginInfo(String mail, String password)
            throws ServiceException {

        BookReaderDao bookReaderDao = BookReaderDao.
                getInstanceBookReaderDao();
        BookReader bookReader;
        try {
            bookReader = bookReaderDao.
                    takeReaderByLoginInfo(mail, password);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeReaderByLoginInfo, Service class ", e);
        }
        return bookReader;
    }


    /**
     * check if a reader is exist in Reader Table (by mail)
     *
     * @param mail
     * @return isExist
     * @throws ServiceException
     */
    public boolean verifyIsExistReader(String mail)
            throws ServiceException {

        BookReaderDao bookReaderDao = BookReaderDao.
                getInstanceBookReaderDao();
        boolean isExist;
        try {
            isExist = bookReaderDao.verifyIsExistReader(mail);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "verifyIsExistReader(String mail), " +
                    "Service class ", e);
        }
        return isExist;
    }


    /**
     * changes banned date about a reader in Reader Table
     *
     * @param bookReader
     * @throws ServiceException
     */
    public void changeBanStatusReader(BookReader bookReader)
            throws ServiceException {

        BookReaderDao bookReaderDao = BookReaderDao.
                getInstanceBookReaderDao();
        try {
            bookReaderDao.changeBanStatusReader(bookReader);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "changeBanStatusReader(BookReader bookReader), " +
                    "Service class ", e);
        }
    }


    /**
     * takes an object (author) by id from Author table
     *
     * @param id
     * @return author
     * @throws ServiceException
     */
    public Author takeAuthorById(int id) throws ServiceException {

        AuthorDao authorDao = AuthorDao.takeInstanceAuthorDao();
        Author author;
        try {
            author = authorDao.takeById(id);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeAuthorById(int id), Service class ", e);
        }
        return author;
    }


    /**
     * adds an object (author) to Author table
     *
     * @param author
     * @throws ServiceException
     */
    public void addAuthor(Author author) throws ServiceException {

        AuthorDao authorDao = AuthorDao.takeInstanceAuthorDao();
        try {
            authorDao.add(author);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "addAuthor(Author author), Service class ", e);
        }
    }


    /**
     * takes id of the author by surname (from Author table)
     *
     * @param surname, name
     * @return id
     * @throws ServiceException
     */
    public int takeAuthorId(String surname, String name)
            throws ServiceException {

        AuthorDao authorDao = AuthorDao.takeInstanceAuthorDao();
        int id;
        try {
            id = authorDao.takeAuthorId(surname, name);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeAuthorId(String surname, String name), " +
                    "Service class ", e);
        }
        return id;
    }


    /**
     * takes the last id in Author table
     *
     * @return id
     * @throws ServiceException
     */
    public int takeLastAuthorId() throws ServiceException {

        AuthorDao authorDao = AuthorDao.takeInstanceAuthorDao();
        int id;
        try {
            id = authorDao.takeLastAuthorId();
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeLastAuthorId(), Service class ", e);
        }
        return id;
    }


    /**
     * takes an object (category) by id from Category table
     *
     * @param id
     * @return category
     * @throws ServiceException
     */
    public Category takeCategoryById(int id) throws ServiceException {

        CategoryDao categoryDao = CategoryDao.
                getInstanceCategoryDao();
        Category category;
        try {
            category = categoryDao.takeById(id);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeCategoryById(int id), Service class ", e);
        }
        return category;
    }


    /**
     * takes a list with objects (orders) from Order table
     *
     * @return orders
     * @throws ServiceException
     */
    public ArrayList<OrderBook> takeOrders() throws ServiceException {

        OrderBookDao orderDao = OrderBookDao.getInstanceOrderDao();
        ArrayList<OrderBook> orders;
        try {
            orders = orderDao.takeList();
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeOrders(), Service class ", e);
        }
        return orders;
    }


    /**
     * adds an object (order) to Order table
     *
     * @param orderBook
     * @throws ServiceException
     */
    public void addOrder(OrderBook orderBook) throws ServiceException {

        OrderBookDao orderDao = OrderBookDao.getInstanceOrderDao();
        try {
            orderDao.add(orderBook);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "addOrder(OrderBook orderBook), Service class ", e);
        }
    }


    /**
     * deletes an order from Order table
     *
     * @param id
     * @throws ServiceException
     */
    public void deleteOrderById(int id) throws ServiceException {

        OrderBookDao orderDao = OrderBookDao.getInstanceOrderDao();
        try {
            orderDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "deleteOrderById(int id), Service class ", e);
        }
    }


    /**
     * takes id of a book by id of the order
     *
     * @param idOrder
     * @return idBook
     * @throws ServiceException
     */
    public int takeIdBookByIdOrder(int idOrder) throws ServiceException {

        OrderBookDao orderDao = OrderBookDao.getInstanceOrderDao();
        int idBook;
        try {
            idBook = orderDao.takeBookIdByOrderId(idOrder);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeIdBookByIdOrder(int idOrder), " +
                    "Service class ", e);
        }
        return idBook;
    }


    /**
     * takes id of the order by book id
     *
     * @param BookId
     * @return orderId
     * @throws ServiceException
     */
    public int takeOrderId(int BookId) throws ServiceException {

        OrderBookDao orderDao = OrderBookDao.getInstanceOrderDao();
        int orderId;
        try {
            orderId = orderDao.takeOrderId(BookId);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "takeOrderId(int BookId), Service class ", e);
        }
        return orderId;
    }


    /**
     * check if a book has already ordered by a reader
     *
     * @param readerId
     * @param bookId
     * @return isBook
     * @throws ServiceException
     */
    public boolean checkIsBookOrderedReader(int readerId, int bookId)
            throws ServiceException {

        OrderBookDao orderDao = OrderBookDao.
                getInstanceOrderDao();
        boolean isBook;
        try {
            isBook = orderDao.
                    checkIsBookOrderedReader(readerId, bookId);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in " +
                    "checkIsBookOrderedReader(int readerId, int bookId), " +
                    "Service class ", e);
        }
        return isBook;
    }
}