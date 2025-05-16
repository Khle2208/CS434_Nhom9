package com.nhom9.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fa.mockproject.model.DeliveryInformation;

@Repository
public class DeliveryInformationDAO implements DAOInterface<DeliveryInformation> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insert(DeliveryInformation t) {
        try {
            String sql = "INSERT INTO [Delivered_information] (delivered_informationId, phone, userId) VALUES (?, ?, ?)";
            return jdbcTemplate.update(sql, t.getDeliveredInformationId(), t.getPhone(), t.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(DeliveryInformation t) {
        try {
            String sql = "UPDATE [Delivered_information] SET phone = ? WHERE delivered_informationId = ?";
            return jdbcTemplate.update(sql, t.getPhone(), t.getDeliveredInformationId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(DeliveryInformation t) {
        try {
            String sql = "DELETE FROM [Delivered_information] WHERE delivered_informationId = ?";
            return jdbcTemplate.update(sql, t.getDeliveredInformationId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<DeliveryInformation> findAll() {
        try {
            String sql = "SELECT * FROM [Delivered_information]";
            return (ArrayList<DeliveryInformation>) jdbcTemplate.query(sql, new RowMapper<DeliveryInformation>() {
                @Override
                public DeliveryInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
                	DeliveryInformation deliveredInformation = new DeliveryInformation();
                    deliveredInformation.setDeliveredInformationId(null);
                    deliveredInformation.setPhone(rs.getString("phone"));
                    deliveredInformation.setUserId(UUID.fromString(String.valueOf(rs.getInt("userID"))));
                    return deliveredInformation;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public DeliveryInformation findById(DeliveryInformation t) {
        try {
            String sql = "SELECT * FROM [Delivered_information] WHERE delivered_informationId = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{t.getDeliveredInformationId()}, new RowMapper<DeliveryInformation>() {
                @Override
                public DeliveryInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
                	DeliveryInformation deliveredInformation = new DeliveryInformation();
                    deliveredInformation.setDeliveredInformationId(null);
                    deliveredInformation.setPhone(rs.getString("phone"));
                    deliveredInformation.setUserId(UUID.fromString(String.valueOf(rs.getInt("userID"))));
                    return deliveredInformation;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
} 

