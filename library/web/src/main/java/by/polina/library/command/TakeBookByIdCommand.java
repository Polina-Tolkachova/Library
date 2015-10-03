package by.polina.library.command;

import by.polina.library.entity.Author;
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
 *  takes a book by id to display information about the book for the admin
 */
public class TakeBookByIdCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(TakeBookByIdCommand.class);

    private final static String ID_BOOK = "id";


    /**
     * takes a book by id
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        String authorName;
        String authorSurname;
        Book book;
        Author author;
        ResourceBundle rb;

        Service service = Service.initService();
        rb = TakeLocaleUtil.takeLocale(request);
        try {
            String id = request.getParameter(ID_BOOK);
            book = service.takeBookById(Integer.parseInt(id));                                           // gets a parameter from show_books.jsp
            LOGGER.debug("TakeBookByIdCommand-----book = " + book);
            request.setAttribute("book", book);

            author = service.takeAuthorById
                    (book.getAuthor());
            authorName = author.getName();
            authorSurname = author.getSurname();
            request.setAttribute("author_name", authorName);
            request.setAttribute("author_surname", authorSurname);

            page = PagePathConstant.UPDATE_ADD_BOOK_PAGE;

            int bookId = service.takeOrderId(Integer.parseInt(id));;

            if (bookId != 0) {
                request.setAttribute("book_edit",
                        rb.getString("book.edit"));

                ArrayList<Book> books;
                books = service.takeBooks();
                request.setAttribute("books", books);

                page = PagePathConstant.SHOW_BOOKS_PAGE;
            }
        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in TakeBookByIdCommand");
        }
        return page;
    }
}