package by.polina.library.command;

import by.polina.library.entity.Book;
import by.polina.library.service.ServiceException;
import by.polina.library.service.Service;
import by.polina.library.constant.PagePathConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * takes all books to show a reader
 */
public class TakeAllBooksCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(TakeAllBooksCommand.class);


    /**
     * takes a list with books and sets as an attribute
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        ArrayList<Book> books;

        Service service = Service.initService();
        try {
            books = service.takeBooks();
            LOGGER.debug("TakeBooksCommand-----books = "
                    + books);

            request.setAttribute("books", books);

            page = PagePathConstant.SHOW_BOOKS_PAGE;

        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in TakeAllBooksCommand ");
        }
        return page;
    }
}