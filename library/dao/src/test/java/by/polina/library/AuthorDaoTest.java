package by.polina.library;

import by.polina.library.dao.AuthorDao;

import by.polina.library.entity.Author;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;



/**
* Created by Polina Tolkchyova on 01.08.2015.
*/

/**
* tests methods of AuthorDao class
*/
public class AuthorDaoTest {

    private static final Logger LOGGER = Logger
            .getLogger(AuthorDaoTest.class);

    public Author getTestAuthor() {

        Author author = new Author();
        author.setName("Name");
        author.setSurname("Surname");
        return author;
    }

    /**
     * tests takeId(), takeById(int id), add(Author author), deleteById(int id)
     * @throws Exception
     */
    @Test
    public void testAuthorDaoMethods()throws Exception{   //todo какую ошибку указывать?
        try {
            Author testAuthor = getTestAuthor();

            AuthorDao authorDao = AuthorDao.takeInstanceAuthorDao();
            authorDao.add(testAuthor);

            int id = authorDao.takeId("Surname");
            testAuthor.setId(id);

            Author author = authorDao.takeById(id);

            Assert.assertEquals(testAuthor, author);

            authorDao.deleteById(id);

        } catch (Exception e) {
            LOGGER.debug("Exception in AuthorDaoTest, " +
                    " testAuthorDaoMethods()");
        }
    }
}
