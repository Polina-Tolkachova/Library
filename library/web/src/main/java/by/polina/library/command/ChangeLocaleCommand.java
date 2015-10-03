package by.polina.library.command;

import by.polina.library.constant.PagePathConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * changes a locale language
 */
public class ChangeLocaleCommand implements ActionCommand {

    private static final Logger LOGGER = Logger
            .getLogger(ChangeLocaleCommand.class);

    private static final String LANGUAGE = "lang";


    /**
     * defines a parameter and changes a locale language
     *
     * @param request
     * @return page
     */
    public String execute(HttpServletRequest request) {

        String page = PagePathConstant.INDEX_PAGE;
        String language = request.getParameter(LANGUAGE);                           // gets this parameter from login.jsp
        LOGGER.debug("ChangeLocaleCommand-----language = " + language);

        switch (language) {
            case "en":
                request.getSession().setAttribute("locale", "en");                  // set the attribute for include_language.jsp
                break;
            case "by":
                request.getSession().setAttribute("locale", "by");
                break;
            case "kr":
                request.getSession().setAttribute("locale", "kr");
                break;
        }
        return page;
    }
}