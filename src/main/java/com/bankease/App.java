package com.bankease;
import com.bankease.dao.UserDAO;
import com.bankease.model.User;

public class App 
{
    public static void main( String[] args )
    {
        User user = new User("Sharvesh", "sharvesh@gmail.com", "password123");
        UserDAO userDAO = new UserDAO();
        userDAO.addUser(user);
    }
}
