package by.polina.library.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * gets current locale
 */
public class TakeLocaleUtil {

    /**
     * gets current locale and sets for ResourceBundle object
     *
     * @param request
     * @return rb
     */
    public static ResourceBundle takeLocale (HttpServletRequest request) {

        HttpSession session = request.getSession();
        String localeInfo = (String) session.getAttribute("locale");
        Locale locale;
        ResourceBundle rb;

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
            locale = new Locale("be", "BY");
        }
        rb = ResourceBundle.getBundle("messages", locale);

        return rb;
    }
}
