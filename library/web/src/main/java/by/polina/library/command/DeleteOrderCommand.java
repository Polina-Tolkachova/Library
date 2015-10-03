package by.polina.library.command;

import by.polina.library.constant.PagePathConstant;
import by.polina.library.entity.Book;
import by.polina.library.entity.OrderBook;
import by.polina.library.service.ServiceException;
import by.polina.library.service.Service;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * deletes an order
 */
public class DeleteOrderCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(DeleteOrderCommand.class);

    private final static String ID_ORDER = "id";

    /**
     * deletes an order and sets new lists with orders and books
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        int idBook;
        int idOrder;
        String page;
        Book book;

        idOrder = Integer.parseInt
                (request.getParameter(ID_ORDER));                                               // gets a parameter from order.jsp
        LOGGER.debug("DeleteOrderCommand-----idOrder = "
                + idOrder);

        Service service = Service.initService();
        try {
            idBook = service.takeIdBookByIdOrder(idOrder);                                      // takes an id of a book

            service.deleteOrderById(idOrder);

            book = service.takeBookById(idBook);
            LOGGER.debug("DeleteOrderCommand-----book = "
                    + book);

            service.incrementQuantityBook(idBook, book);                                        // increments the quantity of the book

            ArrayList<OrderBook> orders;
            orders = service.takeOrders();
            request.setAttribute("orders", orders);

            ArrayList<Book> books;
            books = service.takeBooks();
            request.setAttribute("books", books);

            page = PagePathConstant.ORDER_PAGE;

        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in DeleteOrderCommand");
        }
        return page;
    }
}

