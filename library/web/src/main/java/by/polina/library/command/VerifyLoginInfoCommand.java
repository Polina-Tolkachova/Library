package by.polina.library.command;

import by.polina.library.constant.PagePathConstant;
import by.polina.library.entity.Book;
import by.polina.library.entity.BookReader;
import by.polina.library.service.ServiceException;
import by.polina.library.service.Service;
import by.polina.library.util.TakeLocaleUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * checks if a reader is exist and manages the displaying
 * information depend on a ban status
 */
public class VerifyLoginInfoCommand implements ActionCommand {

    private static final String MAIL = "mail";
    private static final String PASSWORD = "password";


    /**
     * checks if a reader is exist in the data base
     * manages the displaying information depend on a ban status of the reader
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        Service service = Service.initService();
        BookReader bookReader;
        ResourceBundle rb;

        try {
            String mail = request.getParameter(MAIL);
            bookReader = service.takeReaderByLoginInfo
                    (mail, request.getParameter(PASSWORD));                                  // gets parameters from login.jsp

            if (bookReader != null && bookReader.getBanned() == 0) {                        // not banned
                HttpSession session = request.getSession();
                session.setAttribute("reader", bookReader);

                int readerId = bookReader.getId();
                request.getSession().setAttribute("readerId", readerId);                    // sets the attribute for LendingLibraryCommand

                ArrayList<Book> books;
                books = service.takeBooks();
                request.setAttribute("books", books);

                page = PagePathConstant.SHOW_BOOKS_PAGE;

            } else if (bookReader != null && bookReader.getBanned() == 1) {                 // banned
                page = PagePathConstant.BAN_PAGE;;
                return page;

            } else {
                boolean isExistReader = service.verifyIsExistReader(mail);
                rb = TakeLocaleUtil.takeLocale(request);                                // gets current locale

                if (isExistReader) {
                    request.setAttribute("incorrect_password_message",
                            rb.getString("validate.password.mail"));

                    page = PagePathConstant.LOGIN_PAGE;
                    return page;
                }
                request.setAttribute("not_exist_account_message",
                        rb.getString("validate.password.mail"));
                page = PagePathConstant.LOGIN_PAGE;;
            }
        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in VerifyLoginInfoCommand ");
        }
        return page;
    }
}

