package by.polina.library.command;

import by.polina.library.entity.Book;
import by.polina.library.service.ServiceException;
import by.polina.library.service.Service;
import by.polina.library.constant.PagePathConstant;
import by.polina.library.util.TakeLocaleUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * deletes a book
 */
public class DeleteBookCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(DeleteBookCommand.class);

    private static final String ID_BOOK = "id";

    /**
     * deletes a book and sets a new list with books
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        int bookId;
        ResourceBundle rb;

        String id = request.getParameter(ID_BOOK);                                   // gets a parameter from show_books.jsp
        LOGGER.debug("DeleteBookCommand-----id = " + id);

        Service service = Service.initService();
        rb = TakeLocaleUtil.takeLocale(request);                                   // gets current locale
        try {
            bookId = service.takeOrderId(Integer.parseInt(id));
            if (bookId == 0) {
                service.deleteBookById(Integer.parseInt(id));
            } else {
                request.setAttribute("book_delete",
                        rb.getString("book.delete"));
            }
            ArrayList<Book> books;
            books = service.takeBooks();
            LOGGER.debug("DeleteBookCommand-----list books = "
                    + books);
            request.setAttribute("books", books);

            page = PagePathConstant.SHOW_BOOKS_PAGE;

        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in DeleteBookCommand");
        }
        return page;
    }
}