package com.nhom9.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fa.mockproject.model.Payment;

@Repository
public class PaymentDAO implements DAOInterface<Payment> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Payment t) {
        try {
            String sql = "INSERT INTO [Payment] (paymentId, userId, orderId, payment_date, payment_amount, payment_method, payment_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, t.getPaymentId(), t.getUserId(), t.getOrderId(), t.getPaymentDate(), t.getPaymentAmount(), t.getPaymentMethod(), t.getPaymentStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Payment t) {
        try {
            String sql = "UPDATE [Payment] SET payment_date = ?, payment_amount = ?, payment_method = ?, payment_status = ? WHERE paymentId = ?";
            return jdbcTemplate.update(sql, t.getPaymentDate(), t.getPaymentAmount(), t.getPaymentMethod(), t.getPaymentStatus(), t.getPaymentId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Payment t) {
        try {
            String sql = "DELETE FROM [Payment] WHERE paymentId = ?";
            return jdbcTemplate.update(sql, t.getPaymentId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<Payment> findAll() {
        try {
            String sql = "SELECT * FROM [Payment]";
            return (ArrayList<Payment>) jdbcTemplate.query(sql, new RowMapper<Payment>() {
                @Override
                public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Payment payment = new Payment();
                    payment.setPaymentId(UUID.fromString(rs.getString("paymentId")));
                    payment.setUserId(UUID.fromString(rs.getString("userId")));
                    payment.setOrderId(UUID.fromString(rs.getString("orderId")));
                    payment.setPaymentDate(rs.getDate("payment_date"));
                    payment.setPaymentAmount(rs.getBigDecimal("payment_amount"));
                    payment.setPaymentMethod(rs.getString("payment_method"));
                    payment.setPaymentStatus(rs.getString("payment_status"));
                    return payment;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Payment findById(Payment t) {
        try {
            String sql = "SELECT * FROM [Payment] WHERE paymentId = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{t.getPaymentId()}, new RowMapper<Payment>() {
                @Override
                public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Payment payment = new Payment();
                    payment.setPaymentId(UUID.fromString(rs.getString("paymentId")));
                    payment.setUserId(UUID.fromString(rs.getString("userId")));
                    payment.setOrderId(UUID.fromString(rs.getString("orderId")));
                    payment.setPaymentDate(rs.getDate("payment_date"));
                    payment.setPaymentAmount(rs.getBigDecimal("payment_amount"));
                    payment.setPaymentMethod(rs.getString("payment_method"));
                    payment.setPaymentStatus(rs.getString("payment_status"));
                    return payment;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int completePayment(UUID id_user, UUID orderID, BigDecimal totalPrice, UUID addressId, String payment_method) {
        try {
            String sql = "INSERT INTO Payment (userId, orderId, payment_amount, payment_method, payment_status, addressId) VALUES (?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, id_user, orderID, totalPrice, payment_method, "Completed", addressId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public Payment getPaymentByOrderID(UUID orderId) {
        try {
            String sql = "SELECT * FROM [Payment] WHERE orderId = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{orderId}, new RowMapper<Payment>() {
                @Override
                public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Payment payment = new Payment();
                    payment.setPaymentId(UUID.fromString(rs.getString("paymentId")));
                    payment.setUserId(UUID.fromString(rs.getString("userId")));
                    payment.setOrderId(UUID.fromString(rs.getString("orderId")));
                    payment.setPaymentDate(rs.getDate("payment_date"));
                    payment.setPaymentAmount(rs.getBigDecimal("payment_amount"));
                    payment.setPaymentMethod(rs.getString("payment_method"));
                    payment.setPaymentStatus(rs.getString("payment_status"));
                    payment.setAddressId(UUID.fromString(rs.getString("addressId")));
                    return payment;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

