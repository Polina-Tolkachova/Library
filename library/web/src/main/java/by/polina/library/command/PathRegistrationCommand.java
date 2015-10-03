package by.polina.library.command;

import by.polina.library.constant.PagePathConstant;

import javax.servlet.http.HttpServletRequest;

/**
* Created by Polina Tolkchyova on 01.08.2015.
*/

/**
* redirects to another page
*/
public class PathRegistrationCommand implements ActionCommand {


    /**
     * redirects to registration.jsp
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page =  PagePathConstant.REGISTRATION_PAGE;
        return page;
    }
}

