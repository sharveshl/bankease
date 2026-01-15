package com.bankease.dao;

import com.bankease.model.User;
import com.bankease.util.DBConnection;

import java.sql.*;

public class UserDAO {

    private Connection conn;

    public UserDAO() {
        conn = DBConnection.getConnection();
    }

    // --------------------------------------
    // CREATE USER
    // --------------------------------------
    public boolean createUser(User user) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // --------------------------------------
    // GET USER BY EMAIL
    // --------------------------------------
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
