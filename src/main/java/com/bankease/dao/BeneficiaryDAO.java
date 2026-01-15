package com.bankease.dao;

import com.bankease.model.Beneficiary;
import com.bankease.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeneficiaryDAO {

    private Connection conn;

    public BeneficiaryDAO() {
        conn = DBConnection.getConnection();
    }

    // Add new beneficiary
    public boolean addBeneficiary(Beneficiary b) {
        String sql = "INSERT INTO beneficiaries (user_id, name, account_number, bank_name) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, b.getUser_id());
            ps.setString(2, b.getName());
            ps.setString(3, b.getAccount_number());
            ps.setString(4, b.getBank_name());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all beneficiaries for a user
    public List<Beneficiary> getBeneficiariesByUser(int userId) {
        String sql = "SELECT * FROM beneficiaries WHERE user_id = ?";
        List<Beneficiary> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Beneficiary b = new Beneficiary();
                b.setBeneficiary_id(rs.getInt("beneficiary_id"));
                b.setUser_id(rs.getInt("user_id"));
                b.setName(rs.getString("name"));
                b.setAccount_number(rs.getString("account_number"));
                b.setBank_name(rs.getString("bank_name"));

                list.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
