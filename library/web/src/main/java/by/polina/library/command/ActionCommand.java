package by.polina.library.command;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 *  the interface for Command classes
 */
public interface ActionCommand {

     String execute(HttpServletRequest request);
}
