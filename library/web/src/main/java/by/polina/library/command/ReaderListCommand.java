package by.polina.library.command;

import by.polina.library.entity.BookReader;
import by.polina.library.service.ServiceException;
import by.polina.library.service.Service;
import by.polina.library.constant.PagePathConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * takes a list with readers
 */
public class ReaderListCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(ReaderListCommand.class);


    /**
     * takes and sets as an attribute a list with readers
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        ArrayList<BookReader> bookReaders;

        Service service = Service.initService();
        try {
            bookReaders = service.takeReaders();
            LOGGER.debug("ReaderListCommand-----bookReaders = "
                    + bookReaders);

            request.setAttribute("readers", bookReaders);

            page = PagePathConstant.READERS_LIST_PAGE;

        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in ReaderListCommand");
        }
        return page;
    }
}