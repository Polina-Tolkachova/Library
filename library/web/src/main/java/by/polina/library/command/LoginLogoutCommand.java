package by.polina.library.command;

import by.polina.library.constant.PagePathConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * manages the logging in/out
 */
public class LoginLogoutCommand implements ActionCommand {


    private static final String ACTION = "action";


    /**
     * manages the logging in and the logging out a reader
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;

        String action = request.getParameter(ACTION);                                           // gets parameter "action" from header.jsp

        if ("logout".equals(action)) {

            HttpSession session = request.getSession();
            session.removeAttribute("name");                                                   //deletes a variable of the session
            session.invalidate();                                                               // destroyed a session

            page = PagePathConstant.INDEX_PAGE;
        } else {
            page = PagePathConstant.LOGIN_PAGE;
        }
        return page;
    }
}

