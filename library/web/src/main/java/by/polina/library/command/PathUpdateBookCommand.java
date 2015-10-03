package by.polina.library.command;

import by.polina.library.constant.PagePathConstant;

import javax.servlet.http.HttpServletRequest;

/**
* Created by Polina Tolkchyova on 01.08.2015.
*/

/**
* redirects to another page
*/
public class PathUpdateBookCommand implements ActionCommand {


    /**
     * redirects to update_add_book.jsp
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page = PagePathConstant.UPDATE_ADD_BOOK_PAGE;
        return page;
    }
}
