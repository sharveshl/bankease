package com.bankease.dao;

import com.bankease.model.Transaction;
import com.bankease.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    private Connection conn;

    public TransactionDAO() {
        conn = DBConnection.getConnection();
    }

    // Insert new transaction
    public boolean addTransaction(Transaction tx) {
        String sql = "INSERT INTO transactions (account_id, type, amount, balance_after, description) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tx.getAccount_id());
            ps.setString(2, tx.getType());
            ps.setDouble(3, tx.getAmount());
            ps.setDouble(4, tx.getBalance_after());
            ps.setString(5, tx.getDescription());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all transactions for an account
    public List<Transaction> getTransactionsByAccount(int accountId) {
        String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY created_at DESC";
        List<Transaction> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction tx = new Transaction();
                tx.setTransaction_id(rs.getInt("transaction_id"));
                tx.setAccount_id(rs.getInt("account_id"));
                tx.setType(rs.getString("type"));
                tx.setAmount(rs.getDouble("amount"));
                tx.setBalance_after(rs.getDouble("balance_after"));
                tx.setDescription(rs.getString("description"));

                list.add(tx);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
