package by.polina.library.command;

import by.polina.library.entity.Author;
import by.polina.library.entity.Book;
import by.polina.library.service.ServiceException;
import by.polina.library.service.Service;
import by.polina.library.constant.PagePathConstant;
import by.polina.library.util.TakeLocaleUtil;
import by.polina.library.validator.DataValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * updates the information about a book or adds a new book
 */
public class AddUpdateBookCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(AddUpdateBookCommand.class);

    private static final String BOOK_AVAILABILITY = "count";
    private static final String BOOK_ID = "id";
    private static final String NAME = "name";
    private static final String AUTHOR_SURNAME = "author_surname";
    private static final String AUTHOR_NAME = "author_name";
    private static final String CATEGORY = "category";


    /**
     * adds a new book if the book isn't exist
     * updates the information about a book if it's exist
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        String bookId;
        int authorId;
        Book book;
        ResourceBundle rb;

        /**
         *  validates input information necessary for adding a book
         */
        DataValidator dataValidator = new DataValidator();

        boolean validateQuantity = dataValidator.numbersMatch
                (request.getParameter(BOOK_AVAILABILITY));
        boolean validateTitle = dataValidator.bookTitleMatch
                (request.getParameter(NAME));
        boolean validateName = dataValidator.authorNameMatch
                (request.getParameter(AUTHOR_NAME));
        boolean validateSurname = dataValidator.authorNameMatch
                (request.getParameter(AUTHOR_SURNAME));

        rb = TakeLocaleUtil.takeLocale(request);                       // gets current locale
        page = PagePathConstant.UPDATE_ADD_BOOK_PAGE;

        if (!validateTitle) {
            request.setAttribute("invalid_title",
                    rb.getString("validate.title"));
            return page;

        } else if (!validateName) {
            request.setAttribute("invalid_name",
                    rb.getString("validate.name"));
            return page;

        } else if (!validateSurname) {
            request.setAttribute("invalid_surname",
                    rb.getString("validate.surname"));
            return page;

        } else if (!validateQuantity) {
            request.setAttribute("invalid_quantity",
                    rb.getString("validate.quantity"));
            return page;
        }

        Service service = Service.initService();
        try {
            authorId = service.takeAuthorId(request.getParameter(AUTHOR_SURNAME),       // gets an author's id by surname, name
                    request.getParameter(AUTHOR_NAME));

            book = new Book();

            if (authorId == 0) {
                Author author = new Author();
                author.setName(request.getParameter(AUTHOR_NAME));
                author.setSurname(request.getParameter(AUTHOR_SURNAME));
                LOGGER.debug("AddUpdateBookCommand----author = "
                        + author);
                service.addAuthor(author);

                book.setAuthor(service.takeLastAuthorId());           //  adds to a book the id of a new author
            } else {
                book.setAuthor(authorId);                             // if the author is exist in the database
            }

            book.setTitle(request.getParameter(NAME));
            book.setCategory(Integer.parseInt
                    (request.getParameter(CATEGORY)));
            book.setCount(Integer.parseInt
                    (request.getParameter(BOOK_AVAILABILITY)));

            bookId = request.getParameter(BOOK_ID);

            if (bookId != null && !bookId.isEmpty()) {
                service.updateBook(Integer.parseInt
                        (bookId), book);
            } else {
                service.addBook(book);                                 // adds a new book
            }

            ArrayList<Book> books;
            books = service.takeBooks();
            LOGGER.debug("AddUpdateBookCommand-----books = "
                    + books);
            request.setAttribute("books", books);

            page = PagePathConstant.SHOW_BOOKS_PAGE;

        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in AddUpdateBookCommand");
        }
        return page;
    }
}






