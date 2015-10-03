package by.polina.library.security;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * hashes a password
 */
public class HashCoder {

    private static final Logger LOGGER = Logger
            .getLogger(HashCoder.class);


    /**
     * hashes a password
     * transforms an entering array with date of arbitrary size
     * to an exiting bit string of fixed size
     *
     * @param password
     * @return hashResult
     */
    public String createHashedValue(String password) {

        MessageDigest messageDigest;
        byte[] array = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");                           // "MD5" - 128-bit algorithm of hashing
            messageDigest.update(password.getBytes("utf-8"));                           // processes the date
            array = messageDigest.digest();                                             // calls to finishes the calculation of hash

        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("NoSuchAlgorithmException " +
                    "in HashCoder class, createHashedValue()");

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException " +
                    "in HashCoder class, createHashedValue()");
        }
        BigInteger bigInteger = new BigInteger(1, array);
        String hashResult = bigInteger.toString(16);

        return hashResult;
    }
}
