package by.polina.library.command;

import by.polina.library.constant.PagePathConstant;
import by.polina.library.entity.BookReader;
import by.polina.library.service.ServiceException;
import by.polina.library.service.Service;
import by.polina.library.util.TakeLocaleUtil;
import by.polina.library.validator.DataValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;


/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * manages registration data
 */
public class RegistrationCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(RegistrationCommand.class);

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String MAIL = "mail";
    private static final String PASSWORD = "password";


    /**
     * takes and verifies registration data,
     * adds a new reader
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        boolean isExistReader;
        ResourceBundle rb;


        /**
         *  validates input information necessary for the registration
         */
        DataValidator dataValidator = new DataValidator();
        boolean validateName = dataValidator.readerNameMatch
                (request.getParameter(NAME));
        boolean validateSurname = dataValidator.readerNameMatch
                (request.getParameter(SURNAME));
        boolean validateMail = dataValidator.mailMatch
                (request.getParameter(MAIL));
        boolean validatePassword = dataValidator.passwordMatch
                (request.getParameter(PASSWORD));

        rb = TakeLocaleUtil.takeLocale(request);                                // gets current locale
        page = PagePathConstant.REGISTRATION_PAGE;

        if (!validateName) {                                                    // validates input information necessary for the registration
            request.setAttribute("invalid_name",
                    rb.getString("validate.name"));
            return page;

        } else if (!validateSurname) {
            request.setAttribute("invalid_surname",
                    rb.getString("validate.surname"));
            return page;

        } else if (!validateMail) {
            request.setAttribute("invalid_mail",
                    rb.getString("validate.mail"));
            return page;

        } else if (!validatePassword) {
            request.setAttribute("invalid_password",
                    rb.getString("validate.password"));
            return page;
        }

        BookReader bookReader = new BookReader();
        bookReader.setName(request.getParameter(NAME));                          //gets parameters from registration.jsp
        bookReader.setSurname(request.getParameter(SURNAME));
        bookReader.setMail(request.getParameter(MAIL));
        bookReader.setPassword(request.getParameter(PASSWORD));

        Service service = Service.initService();
        try {
            isExistReader = service.verifyIsExistReader
                    (bookReader.getMail());
            LOGGER.debug("RegistrationCommand-----isExistReader = "
                    + isExistReader);

            if (!isExistReader) {                                                 // the reader isn't exist
                service.addReader(bookReader);
                page = PagePathConstant.CONFIRM_REGISTRATION_PAGE;
                return page;

            } else {
                request.setAttribute("registration_message",
                        rb.getString("registration.data"));

                page = PagePathConstant.REGISTRATION_PAGE;
            }
        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in RegistrationCommand");
        }
        return page;
    }
}
