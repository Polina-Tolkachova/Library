package by.polina.library.filter;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
* Created by Polina Tolkchyova on 01.08.2015.
*/


/**
* sets encoding for transmitted data to the servlet
*/
public class EncodingFilter implements Filter {

    private static final String UTF_8 = "UTF-8";

    /**
     * calls by the web container to indicate to a filter
     * that it is being placed into service
     *
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
    }


    /**
     * checks and sets UTF-8 encoding
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String encoding = request.getCharacterEncoding();                               // defines a character encoding of a request

        if (!UTF_8.equalsIgnoreCase(encoding)) {
            request.setCharacterEncoding(UTF_8);
        }
        try {
            filterChain.doFilter(request, response);
        } catch (IOException e) {
            throw new UnsupportedEncodingException(e.getMessage());
        }
    }


    /**
     * calls by the web container to indicate to a filter
     * that it is being taken out of service
     */
    public void destroy() {
    }
}
