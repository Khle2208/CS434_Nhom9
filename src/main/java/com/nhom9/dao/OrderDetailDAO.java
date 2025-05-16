package com.nhom9.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fa.mockproject.model.CartItems;
import fa.mockproject.model.Order;
import fa.mockproject.model.OrderDetail;


@Repository
public class OrderDetailDAO implements DAOInterface<OrderDetail> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insert(OrderDetail t) {
        try {
            String sql = "INSERT INTO [Order_detail] (order_detailId, orderId, bookId, book_name, price, quantity_order, discount, total_price, image_data) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, t.getOrderDetailId(), t.getOrderId(), t.getBookId(), t.getBookName(), t.getPrice(), t.getQuantityOrder(), t.getDiscount(), t.getTotalPrice(), t.getAvatar());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(OrderDetail t) {
        try {
            String sql = "UPDATE [Order_detail] SET book_name = ?, price = ?, quantity_order = ?, discount = ?, total_price = ?, image_data = ? WHERE order_detailId = ?";
            return jdbcTemplate.update(sql, t.getBookName(), t.getPrice(), t.getQuantityOrder(), t.getDiscount(), t.getTotalPrice(), t.getAvatar(), t.getOrderDetailId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(OrderDetail t) {
        try {
            String sql = "DELETE FROM [Order_detail] WHERE order_detailId = ?";
            return jdbcTemplate.update(sql, t.getOrderDetailId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<OrderDetail> findAll() {
        try {
            String sql = "SELECT * FROM [Order_detail]";
            return (ArrayList<OrderDetail>) jdbcTemplate.query(sql, new RowMapper<OrderDetail>() {
                @Override
                public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderDetailId(UUID.fromString(rs.getString("order_detailId")));
                    orderDetail.setOrderId(UUID.fromString(rs.getString("orderId")));
                    orderDetail.setBookId(UUID.fromString(rs.getString("bookId")));
                    orderDetail.setBookName(rs.getString("book_name"));
                    orderDetail.setPrice(rs.getBigDecimal("price"));
                    orderDetail.setQuantityOrder(rs.getInt("quantity_order"));
                    orderDetail.setDiscount(rs.getBigDecimal("discount"));
                    orderDetail.setTotalPrice(rs.getBigDecimal("total_price"));
                    orderDetail.setAvatar(rs.getBytes("image_data"));
                    return orderDetail;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public OrderDetail findById(OrderDetail t) {
        try {
            String sql = "SELECT * FROM [Order_detail] WHERE order_detailId = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{t.getOrderDetailId()}, new RowMapper<OrderDetail>() {
                @Override
                public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderDetailId(UUID.fromString(rs.getString("order_detailId")));
                    orderDetail.setOrderId(UUID.fromString(rs.getString("orderId")));
                    orderDetail.setBookId(UUID.fromString(rs.getString("bookId")));
                    orderDetail.setBookName(rs.getString("book_name"));
                    orderDetail.setPrice(rs.getBigDecimal("price"));
                    orderDetail.setQuantityOrder(rs.getInt("quantity_order"));
                    orderDetail.setDiscount(rs.getBigDecimal("discount"));
                    orderDetail.setTotalPrice(rs.getBigDecimal("total_price"));
                    orderDetail.setAvatar(rs.getBytes("image_data"));
                    return orderDetail;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<OrderDetail> getItemsByOrderID(UUID orderId) {
        try {
            String sql = "SELECT order_detailId, orderId, bookId, book_name, price, quantity_order, discount, total_price, image_data " +
                    "FROM Order_detail " +
                    "WHERE orderId = ?";

            return (ArrayList<OrderDetail>) jdbcTemplate.query(sql, new Object[]{orderId}, new RowMapper<OrderDetail>() {
                @Override
                public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderDetailId(UUID.fromString(rs.getString("order_detailId")));
                    orderDetail.setOrderId(UUID.fromString(rs.getString("orderId")));
                    orderDetail.setBookId(UUID.fromString(rs.getString("bookId")));
                    orderDetail.setBookName(rs.getString("book_name"));
                    orderDetail.setPrice(rs.getBigDecimal("price"));
                    orderDetail.setQuantityOrder(rs.getInt("quantity_order"));
                    orderDetail.setDiscount(rs.getBigDecimal("discount"));
                    orderDetail.setTotalPrice(rs.getBigDecimal("total_price"));
                    orderDetail.setAvatar(rs.getBytes("image_data"));
                    return orderDetail;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
public ArrayList<OrderDetail> findByOrderId(Order o){
    	
    	try {
            String sql = "SELECT * FROM [Order_detail] WHERE orderId = ?";
            return (ArrayList<OrderDetail>) jdbcTemplate.query(sql, new Object[] {o.getOrderId()}, new RowMapper<OrderDetail>() {
                @Override
                public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderDetailId(UUID.fromString(rs.getString("order_detailId")));
                    orderDetail.setOrderId(UUID.fromString(rs.getString("orderId")));
                    orderDetail.setBookId(UUID.fromString(rs.getString("bookId")));
                    orderDetail.setBookName(rs.getString("book_name"));
                    orderDetail.setPrice(rs.getBigDecimal("price"));
                    orderDetail.setQuantityOrder(rs.getInt("quantity_order"));
                    orderDetail.setDiscount(rs.getBigDecimal("discount"));
                    orderDetail.setTotalPrice(rs.getBigDecimal("total_price"));
                    orderDetail.setAvatar(rs.getBytes("image_data"));
                    return orderDetail;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

