package by.polina.library.command;

import by.polina.library.constant.PagePathConstant;
import by.polina.library.entity.Book;
import by.polina.library.entity.BookReader;
import by.polina.library.entity.OrderBook;
import by.polina.library.service.ServiceException;
import by.polina.library.service.Service;
import by.polina.library.util.TakeLocaleUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * adds an order of a book (lending library or the reading hall)
 */
public class OrderBookCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(OrderBookCommand.class);

    private final static String ID_BOOK = "id";
    private final static String TYPE_OF_ORDER = "type";


    /**
     * adds an order and sets new lists with books and orders
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        String orderType;
        Book book;
        int idBook;
        ResourceBundle rb;

        idBook = Integer.parseInt
                (request.getParameter(ID_BOOK));                                       // gets a parameter from show_books.jsp
        LOGGER.debug("OrderBookCommand-----idBook = "
                + idBook);

        Service service = Service.initService();
        try {
            book = service.takeBookById(idBook);

            int bookQuantity = book.getCount();
            if (bookQuantity >= 1) {
                BookReader bookReader;

                HttpSession session = request.getSession();
                bookReader = (BookReader) session.getAttribute("reader");

                boolean isBook = service.
                        checkIsBookOrderedReader(bookReader.getId(), idBook);

                rb = TakeLocaleUtil.takeLocale(request);                                // gets current locale

                if (isBook) {
                    page = PagePathConstant.SHOW_BOOKS_PAGE;
                    request.setAttribute("book_already_ordered",
                            rb.getString("book.ordered.by_reader"));
                    return page;

                } else {
                    service.decrementQuantityBook(idBook, book);

                    String readerId = request.getSession().
                            getAttribute("readerId").toString();                                 // gets the attribute from VerifyLoginInfoCommand

                    OrderBook orderBook = new OrderBook();
                    orderBook.setIdBook(idBook);
                    orderBook.setIdReader(Integer.parseInt(readerId));

                    orderType = request.getParameter(TYPE_OF_ORDER);

                    String lendingLibrary = "lending_library";
                    String readingHall = "reading_hall";
                    if (orderType.equals(lendingLibrary)) {
                        orderBook.setStatus(0);                                                   // 0 - lending library, 1 - reading_hall
                    } else if (orderType.equals(readingHall)) {
                        orderBook.setStatus(1);
                    }
                    service.addOrder(orderBook);
                }
                ArrayList<OrderBook> orders;
                orders = service.takeOrders();
                request.setAttribute("orders", orders);                                             // sets a new orders

                ArrayList<Book> books;
                books = service.takeBooks();
                LOGGER.debug("OrderBookCommand-----books = "
                        + books);
                request.setAttribute("books", books);                                                // sets a new books
            }
            page = PagePathConstant.ORDER_PAGE;

        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in OrderBookCommand ");
        }
        return page;
    }
}
