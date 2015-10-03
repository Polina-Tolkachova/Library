package by.polina.library.command;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 *  gets an object of necessary Command class depend on received parameter (Client)
 */
public class ActionFactory {

    private static final String PARAM = "param";

    /**
     * defines a command and gets an object of necessary ~Command class
     *
     * @param request
     * @return actionCommand
     */
    public ActionCommand defineCommand(HttpServletRequest request) {

        String parameter = request.getParameter(PARAM);                                         // gets a value of "param" from jsp
        ActionCommand actionCommand;

        if (parameter == null || parameter.isEmpty()) {
            actionCommand = new TakeAllBooksCommand();
        } else {
            CommandEnum paramEnum = CommandEnum.valueOf
                    (parameter.toUpperCase());
            actionCommand = paramEnum.getCommand();                                            // defines and gets  an object of necessary Command class
        }
        return actionCommand;
    }
}