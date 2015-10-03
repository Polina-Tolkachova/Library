package by.polina.library.command;

import by.polina.library.constant.PagePathConstant;
import by.polina.library.entity.Author;
import by.polina.library.entity.Book;
import by.polina.library.entity.Category;
import by.polina.library.service.ServiceException;
import by.polina.library.service.Service;
import by.polina.library.util.TakeLocaleUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

/**
* Created by Polina Tolkachyova on 01.08.2015.
*/

/**
 * take the information about a book to show a reader
 */
public class TakeInfoAboutBookCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(TakeInfoAboutBookCommand.class);

    private static final String BOOK_ID = "id";


    /**
     * takes a book, a category and an author of the book
     * sets this information as attributes
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        Book book;
        ResourceBundle rb;

        Service service = Service.initService();
        rb = TakeLocaleUtil.takeLocale(request);
        try {
            book = service.takeBookById(Integer.parseInt
                    (request.getParameter(BOOK_ID)));                                   // takes a book
            LOGGER.debug("TakeInfoAboutBookCommand---BOOK_ID = "
                    + book);

            Category category;
            category = service.takeCategoryById(                            // gets a category of the book
                    book.getCategory());

            String categoryName = category.getName();                       // sets attributes for types of category
                switch (categoryName) {
                    case ("education"):
                        request.setAttribute("category",
                                rb.getString("category.education"));
                        break;
                    case ("historical"):
                        request.setAttribute("category",
                                rb.getString("category.historical"));
                        break;
                    case ("science fiction"):
                        request.setAttribute("category",
                                rb.getString("category.science_fiction"));
                        break;
                    case ("classical"):
                        request.setAttribute("category",
                                rb.getString("category.classical"));
                        break;
                    case ("philosophy"):
                        request.setAttribute("category",
                                rb.getString("category.philosophy"));
                        break;
                }

            Author author;
            author = service.takeAuthorById
                    (book.getAuthor());                                                 // takes an author of this book

            request.setAttribute("book", book);
            request.setAttribute("author", author);

            page = PagePathConstant.BOOK_INFO;

        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in TakeInfoAboutBookCommand");
        }
        return page;
    }
}
