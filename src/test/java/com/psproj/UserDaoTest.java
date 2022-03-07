package com.psproj;

import com.psproj.repository.dao.UserDao;
import com.psproj.repository.dao.UserRoleDAO;
import com.psproj.repository.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;
    private UserRoleDAO userRoleDAO;

    @Test
    @Order(1)
    @Rollback(value = true)
    public void saveUserTest(){
        User user = new User();

        user.setEmail("test2@gmail.com");
        user.setPassword("password2");
        user.setFirstName("Test2 First Name");
        user.setLastName("Test2 Last Name");
        user.setPhone("111-111-1111");
        user.setUsername("testuser2");

        userDao.save(user);
        Assertions.assertTrue(user.getId()>0);

    }

    @Test
    @Order(2)
    @Rollback(value = true)
    public void findUserTest(){
        User user = new User();

        user.setEmail("test2@gmail.com");
        user.setPassword("password2");
        user.setFirstName("Test2 First Name");
        user.setLastName("Test2 Last Name");
        user.setPhone("111-111-1111");
        user.setUsername("testuser2");

        userDao.save(user);

        User user1 = userDao.findByEmail("test2@gmail.com");

        Assertions.assertTrue(user1.getUsername().equals("testuser2"));

    }

    @Test
    @Order(3)
    @Rollback(value = true)
    public void findAllUserTest(){

        List<User> user = userDao.findAll();
        Assertions.assertTrue(user.size()>1);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void deleteUserTest(){
        User user = new User();

        user.setEmail("test51@gmail.com");
        user.setPassword("password5");
        user.setFirstName("Test5 First Name");
        user.setLastName("Test5 Last Name");
        user.setPhone("111-111-1111");
        user.setUsername("testuser51");

        userDao.save(user);
        Assertions.assertTrue(user.getId()>0);

        Assertions.assertTrue( userDao.findByEmail("test51@gmail.com").getUsername().equals("testuser51"));
//
        User userFound = userDao.findByEmail("test51@gmail.com");
        userDao.delete(userFound);

        Assertions.assertTrue( userDao.findByEmail("test51@gmail.com") == null);


    }

}
