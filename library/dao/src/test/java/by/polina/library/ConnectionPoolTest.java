package by.polina.library;

import by.polina.library.pool.ConnectionPool;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;


/**
* Created by Polina Tolkchyova on 01.08.2015.
*/

/**
* tests ConnectionPool class
*/
public class ConnectionPoolTest {

    private static ConnectionPool connectionPool;


    /**
     * creates the object of ConnectionPool class
     */
    @BeforeClass
    public static void initConnectionPool() {

        connectionPool = ConnectionPool.getInstance();
    }


    /**
     * takes a connection from the pool (queue)
     */
    @Test
    public void getConnectionTest() {

       for (int i = 0; i < 5; i++) {
            Assert.assertNotNull(connectionPool.takeConnection());
        }
    }


    /**
     * close connections in the pool (queue)
     */
    @AfterClass
    public static void cleanUpConnectionPool() {

        connectionPool.cleanUp();
    }
}


