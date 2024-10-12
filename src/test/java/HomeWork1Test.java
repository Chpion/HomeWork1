import Homework.DataBase;
import Homework.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class HomeWork1Test {
    DataBase dataBase;
    Scanner in;
    User user;

    @BeforeEach
    void beforeRunTest(){
        dataBase = new DataBase();
        in = new Scanner(System.in);
        String line;
        user = new User("name", "email", "password");
        dataBase.addUser(user);
    }

    @Test
    void authorizationTest(){
        User user1 = new User();
        user1 = dataBase.authorization();
        Assertions.assertEquals(new User("name", "email", "password"), user1);
    }

    @Test
    void createUserTest(){
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        Assertions.assertEquals(dataBase.getUsers(), users);
    }
}
