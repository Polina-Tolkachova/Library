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
 * Created by Polina Tolkachyova on 01.08.2015.
 */

/**
 * manages the searching for books by a category
 */
public class SearchBookByCategoryCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(SearchBookByCategoryCommand.class);

    private static final String CATEGORY = "category";


    /**
     * manages the searching for books by a category in the database
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        ArrayList<Book> books;
        ResourceBundle rb;

        int bookCategory = Integer.parseInt
                (request.getParameter(CATEGORY));                                           // takes the parameter from show_books.jsp
        LOGGER.debug("SearchBookByCategoryCommand-----category = "
                + bookCategory);

        Service service = Service.initService();
        try {
            books = service.takeBooksByCategory(bookCategory);

            rb = TakeLocaleUtil.takeLocale(request);                                // gets current locale
            //TODO или null?
            if (books.isEmpty()) {
                request.setAttribute("no_books_in_category",
                        rb.getString("search.category"));
            }
            request.setAttribute("books", books);

            page = PagePathConstant.SHOW_BOOKS_PAGE;

        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in SearchBookByCategoryCommand");
        }
        return page;
    }
}