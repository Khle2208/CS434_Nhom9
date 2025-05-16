package com.nhom9.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fa.mockproject.model.Order;

@Repository
public class OrderDAO implements DAOInterface<Order> {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int insert(Order t) {
		try {
			String sql = "INSERT INTO [Order] "
					+ "(userId, order_date, price, total_discount, total_price, status) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			return jdbcTemplate.update(sql, t.getUserId(), t.getOrderDate(), t.getPrice(), t.getTotalDiscount(),
					t.getTotalPrice(), t.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
//update-status
	@Override
	public int update(Order t) {
		try {
			String sql = "UPDATE [Order] " + "SET status = ?" + "WHERE orderID = ?";
			return jdbcTemplate.update(sql,t.getStatus(), t.getOrderId());
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(Order t) {
		try {
			String sql = "DELETE FROM [Order] WHERE orderId = ?";
			return jdbcTemplate.update(sql, t.getOrderId());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<Order> findAll() {
		try {
			String sql = "SELECT * FROM [Order]";
			return (ArrayList<Order>) jdbcTemplate.query(sql, new RowMapper<Order>() {
				@Override
				public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
					Order order = new Order();
					 order.setOrderId(UUID.fromString(rs.getString("orderId")));
	                    order.setUserId(UUID.fromString(rs.getString("userId")));
					order.setOrderDate(rs.getTimestamp("order_date"));
					order.setPrice(rs.getBigDecimal("price"));
					order.setTotalDiscount(rs.getBigDecimal("total_discount"));
					order.setTotalPrice(rs.getBigDecimal("total_price"));
					order.setStatus("status");
					return order;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public Order findById(Order t) {
		try {
		String sql = "SELECT * FROM [Order] WHERE orderId = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { t.getOrderId() }, new RowMapper<Order>() {
			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order order = new Order();
				 order.setOrderId(UUID.fromString(rs.getString("orderId")));
                 order.setUserId(UUID.fromString(rs.getString("userId")));
				order.setOrderDate(rs.getTimestamp("order_date"));
				order.setPrice(rs.getBigDecimal("price"));
				order.setTotalDiscount(rs.getBigDecimal("total_discount"));
				order.setTotalPrice(rs.getBigDecimal("total_price"));
				order.setStatus(rs.getString("status"));
				return order;
			}

		});
	} catch (Exception e) {
		e.printStackTrace();
		return null;
		}
	}

	//cuong
	public UUID addOrderbyId(String id_user, BigDecimal price, List<UUID> bookIds, UUID cartID) {
	    try {
	        UUID orderId = UUID.randomUUID();
	        String sql = "INSERT INTO [Order] "
	                + "(orderId, userId, order_date, price, total_discount, total_price, status) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

	        LocalDateTime now = LocalDateTime.now();
	        Timestamp timestamp = Timestamp.valueOf(now);

	        jdbcTemplate.update(sql, orderId, id_user, timestamp, price, null, price, "Chờ thanh toán");

	        if (bookIds != null && !bookIds.isEmpty()) {
	            addOrderDetails(orderId, bookIds, cartID);
	        }

	        return orderId;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

    private void addOrderDetails(UUID orderId, List<UUID> bookIds, UUID cartID) {
//	        String detailSql = "INSERT INTO Order_detail (orderId, bookId, book_name, price, quantity_order, discount, total_price, image_data) " +
//	                "SELECT ?, bookId, book_name, price, quantity_order, discount, image_data FROM Cart_items WHERE cart_itemId = ? AND cartId = ?";
    	
    		String detailSql = "INSERT INTO Order_detail (orderId, bookId, book_name, price, quantity_order, discount, image_data)\r\n"
    				+ "SELECT ?, bookId, book_name, price, quantity_order, discount, image_data\r\n"
    				+ "FROM Cart_items\r\n"
    				+ "WHERE cart_itemId = ? AND cartId = ?\r\n";
        for (UUID bookId : bookIds) {
        	jdbcTemplate.update(detailSql, orderId, bookId, cartID);
        }
    }
    
    public Order getOrderByID(UUID orderID) {
        try {
            String sql = "SELECT * FROM [Order] WHERE orderId = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{orderID}, new RowMapper<Order>() {
                @Override
                public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Order order = new Order();
                    order.setOrderId(UUID.fromString(rs.getString("orderId")));
                    order.setUserId(UUID.fromString(rs.getString("userId")));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                    order.setPrice(rs.getBigDecimal("price"));
                    order.setTotalDiscount(rs.getBigDecimal("total_discount"));
                    order.setTotalPrice(rs.getBigDecimal("total_price"));
                    order.setStatus(rs.getString("status"));
                    return order;
                }
            });
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            // Handle case where no order is found
            return null; // or throw a custom exception
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int updateStatus(UUID orderID, String status) {
    	try {
			String sql = "UPDATE [Order] " + "SET status = ?" + " WHERE orderID = ?";
			return jdbcTemplate.update(sql,status, orderID);
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }
    
    public ArrayList<Order> getAllByUserId(UUID userId) {
        try {
            String sql = "SELECT * FROM [Order] WHERE userId = ? ORDER BY order_date DESC";
            return (ArrayList<Order>) jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Order>() {
                @Override
                public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Order order = new Order();
                    order.setOrderId(UUID.fromString(rs.getString("orderId")));
                    order.setUserId(UUID.fromString(rs.getString("userId")));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                    order.setPrice(rs.getBigDecimal("price"));
                    order.setTotalDiscount(rs.getBigDecimal("total_discount"));
                    order.setTotalPrice(rs.getBigDecimal("total_price"));
                    order.setStatus(rs.getString("status"));
                    return order;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public ArrayList<Order> getAllByUserIdAndStatus(UUID userId, String status) {
        try {
            String sql = "SELECT * FROM [Order] WHERE userId = ? AND status = ? ORDER BY order_date DESC";
            return (ArrayList<Order>) jdbcTemplate.query(sql, new Object[]{userId, status}, new RowMapper<Order>() {
                @Override
                public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Order order = new Order();
                    order.setOrderId(UUID.fromString(rs.getString("orderId")));
                    order.setUserId(UUID.fromString(rs.getString("userId")));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                    order.setPrice(rs.getBigDecimal("price"));
                    order.setTotalDiscount(rs.getBigDecimal("total_discount"));
                    order.setTotalPrice(rs.getBigDecimal("total_price"));
                    order.setStatus(rs.getString("status"));
                    return order;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    //khoa, binh
    public ArrayList<Order> filterByDate(String startDate, String endDate){
		try {
			String sql = "SELECT * FROM [Order] WHERE order_date BETWEEN ? AND ?";
			return (ArrayList<Order>) jdbcTemplate.query(sql, new Object[] {startDate, endDate}, new RowMapper<Order>() {
				@Override
				public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
					Order order = new Order();
					order.setOrderId(UUID.fromString(rs.getString("orderId")));
					order.setUserId(UUID.fromString(rs.getString("userId")));
					order.setOrderDate(rs.getTimestamp("order_date"));
					order.setPrice(rs.getBigDecimal("price"));
					order.setTotalDiscount(rs.getBigDecimal("total_discount"));
					order.setTotalPrice(rs.getBigDecimal("total_price"));
					order.setStatus(rs.getString("status"));
					return order;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public ArrayList<Order> findByEmail(String email){
		try {
			String sql = "SELECT [Order].* FROM [Order], [User] WHERE [Order].userId = [User].userId AND [User].email = ?";
			return (ArrayList<Order>) jdbcTemplate.query(sql, new Object[] {email}, new RowMapper<Order>() {
				@Override
				public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
					Order order = new Order();
					order.setOrderId(UUID.fromString(rs.getString("orderId")));
					order.setUserId(UUID.fromString(rs.getString("userId")));
					order.setOrderDate(rs.getTimestamp("order_date"));
					order.setPrice(rs.getBigDecimal("price"));
					order.setTotalDiscount(rs.getBigDecimal("total_discount"));
					order.setTotalPrice(rs.getBigDecimal("total_price"));
					order.setStatus(rs.getString("status"));
					return order;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public ArrayList<Order> filterByStatus(String status){
		try {
			String sql = "SELECT * FROM [Order] WHERE  status = ?";
			return (ArrayList<Order>) jdbcTemplate.query(sql, new Object[] {status}, new RowMapper<Order>() {
				@Override
				public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
					Order order = new Order();
					order.setOrderId(UUID.fromString(rs.getString("orderId")));
					order.setUserId(UUID.fromString(rs.getString("userId")));
					order.setOrderDate(rs.getTimestamp("order_date"));
					order.setPrice(rs.getBigDecimal("price"));
					order.setTotalDiscount(rs.getBigDecimal("total_discount"));
					order.setTotalPrice(rs.getBigDecimal("total_price"));
					order.setStatus(rs.getString("status"));
					return order;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public ArrayList<Order> filterAll(String startDate, String endDate, String status){
		try {
			String sql = "SELECT * FROM [Order] WHERE order_date BETWEEN ? AND ? AND status = ?";
			return (ArrayList<Order>) jdbcTemplate.query(sql, new Object[] {startDate, endDate, status}, new RowMapper<Order>() {
				@Override
				public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
					Order order = new Order();
					order.setOrderId(UUID.fromString(rs.getString("orderId")));
					order.setUserId(UUID.fromString(rs.getString("userId")));
					order.setOrderDate(rs.getTimestamp("order_date"));
					order.setPrice(rs.getBigDecimal("price"));
					order.setTotalDiscount(rs.getBigDecimal("total_discount"));
					order.setTotalPrice(rs.getBigDecimal("total_price"));
					order.setStatus(rs.getString("status"));
					return order;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	
	public int countOrderByUserId(UUID userId) {
		try {
			String sql = "SELECT COUNT(*) FROM [Order] WHERE userId = ?";
			int count = jdbcTemplate.queryForObject(sql, new Object[] { userId }, Integer.class);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<Object[]> chartRevenueController(int year) {

		String sql = "SELECT \r\n" + "  MONTH(order_date) AS month,\r\n" + "  SUM(total_price) as revenue\r\n"
				+ "  FROM [Order]\r\n" + "  WHERE YEAR(order_date)=?\r\n" + "  GROUP BY MONTH(order_date)\r\n"
				+ "  ORDER BY month";
		return jdbcTemplate.query(sql, new Object[] { year }, (rs, rowNum) -> {
			return new Object[] { rs.getInt("month"), rs.getInt("revenue") };
		});
	}

	public List<Object[]> chartOrderDAO(int year) {
		String sql = " SELECT \r\n" + "    MONTH(order_date) AS month,\r\n"
				+ "    SUM(CASE WHEN status = 'Pending' THEN 1 ELSE 0 END) AS pending_orders,\r\n"
				+ "    SUM(CASE WHEN status = 'Completed' THEN 1 ELSE 0 END) AS completed_orders,\r\n"
				+ "	SUM(CASE WHEN status = 'Failed' THEN 1 ELSE 0 END) AS failed_orders\r\n" + "FROM [Order]\r\n"
				+ "WHERE YEAR(order_date) =?\r\n" + "GROUP BY MONTH(order_date)\r\n" + "ORDER BY month;";
		return jdbcTemplate.query(sql, new Object[] { year }, (rs, rowNum) -> {
			return new Object[] { rs.getInt("month"), rs.getInt("pending_orders"), rs.getInt("completed_orders"),
					rs.getInt("failed_orders") };
		});
	}

	public Integer statisticOrderDAO(Date startDate, Date endDate) {
         String sql = "  SELECT COUNT(*) FROM [Order]  WHERE order_date BETWEEN ? AND ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, startDate,endDate);
	}
}
