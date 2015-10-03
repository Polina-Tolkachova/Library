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
 * changes a ban status of a reader
 */
public class ChangeBanStatusCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(ChangeBanStatusCommand.class);

    private static final String ID = "id";


    /**
     * changes a ban status of a reader to opposite
     * sets an attribute with a new list of readers
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        Service service = Service.initService();
        BookReader bookReader;

        try {
            bookReader = service.takeReaderById(Integer.parseInt
                    (request.getParameter(ID)));                                                    // gets the parameter from reader_list.jsp
            LOGGER.debug("ChangeBanStatusCommand-----bookReader = "
                    + bookReader);

            service.changeBanStatusReader(bookReader);                                              // changes a status of the reader to opposite

            ArrayList<BookReader> bookReaders;
            bookReaders = service.takeReaders();
            request.setAttribute("readers", bookReaders);

            page = PagePathConstant.READERS_LIST_PAGE;

        } catch (ServiceException e) {
            page = PagePathConstant.ERROR_PAGE;
            request.setAttribute("error_message",
                    "Error in ChangeBanStatusCommand ");
        }
        return page;
    }
}