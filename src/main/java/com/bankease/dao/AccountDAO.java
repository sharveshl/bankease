package com.bankease.dao;

import com.bankease.model.Account;
import com.bankease.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private Connection conn;

    public AccountDAO() {
        conn = DBConnection.getConnection();
    }

    // Create new account
    public boolean createAccount(Account account) {
        String sql = "INSERT INTO accounts (user_id, account_number, account_type, balance) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, account.getUser_id());
            ps.setString(2, account.getAccount_number());
            ps.setString(3, account.getAccount_type());
            ps.setDouble(4, account.getBalance());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get account by ID
    public Account getAccountById(int id) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        Account account = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setAccount_id(rs.getInt("account_id"));
                account.setUser_id(rs.getInt("user_id"));
                account.setAccount_number(rs.getString("account_number"));
                account.setAccount_type(rs.getString("account_type"));
                account.setBalance(rs.getDouble("balance"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    // Get all accounts for a user
    public List<Account> getAccountsByUser(int userId) {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        List<Account> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setAccount_id(rs.getInt("account_id"));
                a.setUser_id(rs.getInt("user_id"));
                a.setAccount_number(rs.getString("account_number"));
                a.setAccount_type(rs.getString("account_type"));
                a.setBalance(rs.getDouble("balance"));

                list.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Update balance
    public boolean updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, accountId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
