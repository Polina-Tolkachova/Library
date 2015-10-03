package by.polina.library.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */


/**
 *  involves methods and patterns to validate input data
 */
public class DataValidator {


    private final static String NUMBERS_PATTERN = "[1-9]{0,2}|20?|30?|40?|50?|60?|70?|80?|90?|100?";

    private final static String BOOK_TITLE_PATTERN = "(^[А-ЯЁІЎ]+[А-ЯЁІЎа-яёіў\\s\\,\\-\\?\\.\\:\\(\\)\\d]{0,29}$)" +
            "|(^[A-Z]+[\\w\\s\\,\\-\\?\\.\\:\\(\\)\\d]{0,29}$)|(^[ㄱ-ㅎㅏ-ㅣ가-힣]+[ㄱ-ㅎㅏ-ㅣ가-힣\\s\\,\\-\\?\\.\\:\\(\\)\\d]{0,19}$)";

    private final static String AUTHOR_NAME_PATTERN = "(^[А-ЯЁІЎ]{1}[а-яёіў]{0,14}$)|(^[А-ЯЁІЎ]{1}[а-яёіў]{0,14}\\s{1}" +
            "[А-ЯЁІЎ]{1}[а-яёіў]{0,14}$)|(^[A-Z]{1}[a-z]{0,14}$)|(^[A-Z]{1}[a-z]{0,14}" +
            "\\s{1}[A-Z]{1}[a-z]{0,14}$)|(^[ㄱ-ㅎㅏ-ㅣ가-힣]{0,14}$)|(^[ㄱ-ㅎㅏ-ㅣ가-힣]{0,14}" +
            "\\s{1}[ㄱ-ㅎㅏ-ㅣ가-힣]{0,15}$)";

    private final static String READER_NAME_PATTERN = "(^[A-Z\\s][a-z\\s]{1,40}$)";

    private final static String MAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

    private final static String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";


    /**
     * validates inputted numbers
     *
     * @param number
     * @return boolean
     */
    public boolean numbersMatch(String number) {

        Pattern pattern = Pattern.compile(NUMBERS_PATTERN);
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }


    /**
     * validates inputted book title
     *
     * @param title
     * @return boolean
     */
    public boolean bookTitleMatch(String title) {

        Pattern pattern = Pattern.compile(BOOK_TITLE_PATTERN);
        Matcher matcher = pattern.matcher(title);

        return matcher.matches();
    }


    /**
     * validates inputted author's name or surname
     *
     * @param name
     * @return boolean
     */
    public boolean authorNameMatch(String name) {

        Pattern pattern = Pattern.compile(AUTHOR_NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }


    /**
     * validates inputted reader's name or surname
     *
     * @param name
     * @return boolean
     */
    public boolean readerNameMatch(String name) {

        Pattern pattern = Pattern.compile(READER_NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }


    /**
     * validates inputted mail
     *
     * @param mail
     * @return boolean
     */
    public boolean mailMatch(String mail) {

        Pattern pattern = Pattern.compile(MAIL_PATTERN);
        Matcher matcher = pattern.matcher(mail);

        return matcher.matches();
    }


    /**
     * validates inputted password
     *
     * @param password
     * @return boolean
     */
    public boolean passwordMatch(String password) {

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
