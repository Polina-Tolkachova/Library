package by.polina.library.command;

import by.polina.library.constant.PagePathConstant;
import by.polina.library.entity.Book;
import by.polina.library.service.ServiceException;
import by.polina.library.service.Service;
import by.polina.library.util.TakeLocaleUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */


/**
 * searches for a book by the title
 */
public class SearchBookByTitleCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(SearchBookByTitleCommand.class);

    private static final String SEARCH_BOOK = "search_book";


    /**
     * manages the searching for a book
     * in the database (by the title)
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        ArrayList<Book> books;
        ResourceBundle rb;

        String searchBook = request.
                getParameter(SEARCH_BOOK);                                                          // takes the parameter from show_books.jsp
        LOGGER.debug("SearchBookByTitleCommand---search_book = "
                + searchBook);

        Service service = Service.initService();
        try {
            books = service.
                    takeBooksByTitle(searchBook);

            rb = TakeLocaleUtil.takeLocale(request);                                // gets current locale
            if (books.isEmpty()) {
                request.setAttribute("no_book",
                        rb.getString("search.library"));
            }
            request.setAttribute("books", books);

            page = PagePathConstant.SHOW_BOOKS_PAGE;

        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in SearchBookByTitleCommand");
        }
        return page;
    }
}
