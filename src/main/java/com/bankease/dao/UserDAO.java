package com.bankease.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.bankease.model.User;
import com.bankease.util.DBConnection;

public class UserDAO {
    public boolean isUserExists(String email){
        String sql = "SELECT * FROM users WHERE email = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, email);
            var rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void addUser(User user) {
        if(isUserExists(user.getEmail())){
            System.out.println("User with email " + user.getEmail() + " already exists.");
            return;
        }
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
            System.out.println("User added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}