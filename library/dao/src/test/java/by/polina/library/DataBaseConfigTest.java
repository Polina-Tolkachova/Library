package by.polina.library;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
* Created by Polina Tolkchyova on 01.08.2015.
*/

/**
* tests the database configuration file
*/
public class DataBaseConfigTest {

    private static final Logger LOGGER = Logger
            .getLogger(DataBaseConfigTest.class);


    /**
     * tests getting information from the database configuration file
     */
    @Test
    public void configPropertiesTest() {

        Properties property = new Properties();
        InputStream input;
        try {
            input = new FileInputStream(this.getClass().
                    getClassLoader().getResource("config.properties").getFile());
            property.load(input);

            String url = property.getProperty("url");
            String login = property.getProperty("login");
            String password = property.getProperty("password");

            Assert.assertNotNull(url);
            Assert.assertNotNull(login);
            Assert.assertNotNull(password);

        } catch (IOException e) {
            LOGGER.debug("IOException in DataBaseConfigTest");
        }
    }
}


