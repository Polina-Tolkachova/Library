package by.polina.library.pool;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * manages a connection to the data base
 */
public class ConnectionPool {

    private static final Logger LOGGER = Logger
            .getLogger(ConnectionPool.class);


    private static ConnectionPool instance = null;
    private BlockingQueue<Connection> pool;
    private static Lock lock = new ReentrantLock();                                         // execute  synchronization
    private static AtomicBoolean takeConnectionAtomic =
            new AtomicBoolean(true);                                                        // executes non-locking  synchronization
    private static AtomicBoolean createPoolAtomic =
            new AtomicBoolean(false);
    private static final int POOL_SIZE = 5;
                                                     // execute blocking queue


    /**
     * creates connections to the database
     */
    private ConnectionPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Properties property = new Properties();
            InputStream input = new FileInputStream(this.getClass().
                    getClassLoader().getResource("config.properties").getFile());
            property.load(input);                                                           // loads key and its corresponding value

            String url = property.getProperty("url");
            String login = property.getProperty("login");
            String password = property.getProperty("password");

            pool = new ArrayBlockingQueue<>(POOL_SIZE);                                     // sets a size of the queue

            for (int i = 0; i <= POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection
                        (url, login, password);
                pool.offer(connection);                                                     // adds a connection to the queue
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            LOGGER.fatal("Fatal exception in ConnectionPool class ", e);
            throw new RuntimeException("Fatal exception " +
                    "in ConnectionPool class ", e);
        }
    }


    /**
     * creates the object of ConnectionPool class
     *
     * @return instance
     */
    public static ConnectionPool getInstance() {

        if (!createPoolAtomic.get()) {                                                          // manages the access and updating only one object
            lock.lock();                                                                        // lock the object, another tread must waite
            try{
            if (instance == null) {
                instance = new ConnectionPool();
                createPoolAtomic.set(true);
            }
            } finally {
                lock.unlock();                                                                  // releases a lock
            }
        }
        return instance;
    }


    /**
     * takes a connection from the pool (queue)
     *
     * @return connection
     */
    public Connection takeConnection()  {

        Connection connection = null;
        if (takeConnectionAtomic.get()) {
            try {
                connection = pool.take();                                                       // takes and deletes a connection out of the pool (queue)
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException in " +
                        "ConnectionPool class, takeConnection()", e);
            }
        }
        return connection;
    }


    /**
     * return a connection to the pool (queue)
     *
     * @param connection
     */
    public void returnConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                pool.add(connection);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in ConnectionPool class, " +
                    "returnConnection(Connection connection)", e);
        }
    }


    /**
     * close connections in the pool (queue)
     */
    public void cleanUp() {

        final int WAITING_RETURN_THREADS_TO_POOL = 50;

        takeConnectionAtomic = new AtomicBoolean(false);                                            // to stop all threads
        try {
            TimeUnit.MILLISECONDS.sleep(WAITING_RETURN_THREADS_TO_POOL);
                                                                                                    // waits to collects all threads in the pool
            Iterator<Connection> iterator = pool.iterator();
            while (iterator.hasNext()) {
                Connection connection = iterator.next();
                if (connection != null) {
                    connection.close();                                                             // closes connection if it isn't empty
                }
                iterator.remove();
            }
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException in " +
                    "ConnectionPool class, cleanUp()", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in ConnectionPool class ," +
                    "cleanUp method", e);
        }
    }
}
