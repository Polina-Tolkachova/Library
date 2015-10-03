package by.polina.library.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * write the text information for footer.jsp
 */
@SuppressWarnings("serial")
public class TextFooterTag extends TagSupport {

    private static final String COPY_RIGHT_INFO = "© 2015, Polina Tolkachyova";


    /**
     * write the text information
     *
     * @return SKIP_BODY
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {

        try {
            String footerText = COPY_RIGHT_INFO;

            JspWriter out = pageContext.getOut();                                       // has access to a output stream
            out.write(footerText);

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;                                                               // signals a system to ignore any contents between the first and the last elements of the tag (if there isn't a body in the description of the tag)
    }
}



