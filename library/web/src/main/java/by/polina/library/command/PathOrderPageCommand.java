package by.polina.library.command;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

import by.polina.library.constant.PagePathConstant;
import by.polina.library.entity.Book;
import by.polina.library.entity.OrderBook;
import by.polina.library.service.Service;
import by.polina.library.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * redirects to another page
 */
public class PathOrderPageCommand implements ActionCommand{

    /**
     * redirects to order.jsp
     * displays information about orders of a reader
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String  page;
        ArrayList<OrderBook> orders;

        Service service = Service.initService();
        try {
            orders = service.takeOrders();
            request.setAttribute("orders", orders);

            ArrayList<Book> books;
            books = service.takeBooks();
            request.setAttribute("books", books);

            page =  PagePathConstant.ORDER_PAGE;

        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in PathOrderPageCommand ");
        }
        return page;
    }
}


