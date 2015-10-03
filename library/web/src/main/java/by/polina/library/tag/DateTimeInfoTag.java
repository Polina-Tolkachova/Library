package by.polina.library.tag;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */


/**
 * manages and writes the date and time information
 */
@SuppressWarnings("serial")                                                                                     // ignores the absence of serialVersionUID for serializable classes
public class DateTimeInfoTag extends TagSupport {

    private static final Logger LOGGER = Logger
            .getLogger(DateTimeInfoTag.class);


    /**
     * manages information about a date and time depend on the current locale
     *
     * @return SKIP_BODY
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {

        HttpSession session = pageContext.getSession();                                     // has access to all the name scope that associate with a context of the page
        String localeInfo = (String) session.getAttribute("locale");

        Locale locale;
        if (localeInfo != null) {
            if ("kr".equals(localeInfo)) {
                locale = new Locale("ko", "KR");
            } else if (("by".equals(localeInfo))) {
                locale = new Locale("be", "BY");
            } else {
                locale = new Locale(localeInfo.toLowerCase(),
                        localeInfo.toUpperCase());
            }
        } else {
            locale = new Locale("be", "BY");;
        }

        DateFormat dateFormat = DateFormat.getDateTimeInstance                              // date and time at a point in time of operation
                (DateFormat.DEFAULT, DateFormat.DEFAULT, locale);                           // indicates the style of a date and  time to display
        Date date = new Date();
        String dateTimeInfo = dateFormat.format(date);                                      // gets the date and time information as a string

        JspWriter out = pageContext.getOut();                                               // has access to a output stream
        try {
            out.write(dateTimeInfo);                                                        // write on the page
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;                                                                   // signals a system to ignore any contents between the first and the last elements of the tag (if there isn't a body in the description of the tag)
    }


    /**
     * calls one time after processing all other methods
     *
     * @return EVAL_PAGE
     * @throws JspException
     */
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;                                                       // allows the future processing of the page
    }
}




