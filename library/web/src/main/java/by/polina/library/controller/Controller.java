package by.polina.library.controller;

import by.polina.library.command.ActionCommand;
import by.polina.library.command.ActionFactory;
import by.polina.library.pool.ConnectionPool;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */


/**
 * manages commands and do necessary actions depend on commands (Invoker)
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;


    /**
     * gets and configures log4j
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        String log4j = config.getInitParameter("log4j_pass");                               // gets the parameter (llog4j_pass) by its name

        String path = getServletContext().getRealPath(log4j);                               // get a real path of log4j, getServletContext() - gets a link  for a object of ServletContext class
        PropertyConfigurator.configure(path);                                               // configures log4j
    }


    /**
     * process a request
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }


    /**
     * process a request
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }


    /**
     * close connections in the pool
     */
    @Override
    public void destroy() {

        super.destroy();
        ConnectionPool.getInstance().cleanUp();
    }


    /**
     * defines a command, do necessary actions and calls a page in response
     * to the request
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        String page;
        ActionFactory actionFactory = new ActionFactory();
        ActionCommand command = actionFactory.defineCommand(request);                           // defines a command from the request and gets an object of concrete Command class

        page = command.execute(request);                                                        // does necessary actions in defined Command class

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher(page);
        dispatcher.forward(request, response);                                                  // calls the concrete page in response to the request
    }
}

