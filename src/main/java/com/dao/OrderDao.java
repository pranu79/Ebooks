package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Order;
import com.entity.OrderItem;

public class OrderDao {

	private Connection con;

	public OrderDao(Connection con) {
		super();
		this.con = con;
	}

	public List<OrderItem> getOrderItems(String orderId) {
	    List<OrderItem> list = new ArrayList<>();
	    String sql = "SELECT * FROM order_items WHERE order_id=?";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, orderId);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            OrderItem i = new OrderItem();
	            i.setOrderId(rs.getString("order_id"));   // VARCHAR
	            i.setBookId(rs.getInt("book_id"));        // INT
	            i.setBookName(rs.getString("book_name")); // VARCHAR
	            i.setAuthor(rs.getString("author"));      // VARCHAR
	            i.setQuantity(rs.getInt("quantity"));     // INT
	            i.setPrice(rs.getInt("price"));           // INT
	            list.add(i);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}


	public boolean saveOrder(Order order) {
		boolean f = false;
		try {
			con.setAutoCommit(false); // transaction start

			// 1. Insert into orderinfo
			String sql = "INSERT INTO orderinfo(order_id, user_name, email, address, phno, payment,status, total_amount,razorpay_order_id,razorpay_payment_id,razorpay_signature) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, order.getOrderid());
			ps.setString(2, order.getUsername());
			ps.setString(3, order.getEmail());
			ps.setString(4, order.getFulladdress());
			ps.setString(5, order.getPhno());
			ps.setString(6, order.getPaymentType());
			ps.setString(7, order.getStatus());
			ps.setDouble(8, order.getTotalAmount());
			ps.setString(9, order.getRazorpayOrderId());
			ps.setString(10, order.getRazorpayPaymentId());
			ps.setString(11, order.getPaymentSignature());


			int i = ps.executeUpdate();

			if (i == 1) {
				// 2. Save order items
				String itemSql = "INSERT INTO order_items(order_id, book_id, book_name,author, quantity, price) VALUES(?,?,?,?,?,?)";
				PreparedStatement ps2 = con.prepareStatement(itemSql);

				for (OrderItem item : order.getItems()) {
					ps2.setString(1, order.getOrderid());
					ps2.setInt(2, item.getBookId());
					ps2.setString(3, item.getBookName());
					ps2.setString(4, item.getAuthor());
					ps2.setInt(5, item.getQuantity());
					ps2.setInt(6, item.getPrice());
					ps2.addBatch();
				}

				int[] results = ps2.executeBatch();
				if (results.length == order.getItems().size()) {
					f = true;
				}
			}

			con.commit(); // transaction success
		} catch (Exception e) {
			try {
				con.rollback(); // rollback on error
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return f;
	}

	public Order getOrder(String orderId) {
		Order order = null;
		try {
			String sql = "SELECT * FROM orderinfo WHERE order_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, orderId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				order = new Order();
				order.setOrderid(rs.getString(1));
				order.setUsername(rs.getString(2));
				order.setEmail(rs.getString(3));
				order.setFulladdress(rs.getString(4));
				order.setPhno(rs.getString(5));
				order.setStatus(rs.getString(6));
				order.setTotalAmount(rs.getDouble(7));

				// fetch order items
				order.setItems(getOrderItems(orderId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	// insert in orderinfo
	/*
	 * public boolean saveOrder(List<Order> blist) { boolean f = false;
	 * 
	 * try { String sql =
	 * "insert into orderinfo (order_id,user_name,email,address,phno,book_name,author,price,payment,status,created_time) values(?,?,?,?,?,?,?,?,?,?,?)"
	 * ;
	 * 
	 * con.setAutoCommit(false); PreparedStatement ps = con.prepareStatement(sql);
	 * 
	 * for (Order o : blist) { ps.setString(1, o.getOrderid()); ps.setString(2,
	 * o.getUsername()); ps.setString(3, o.getEmail()); ps.setString(4,
	 * o.getFulladdress()); ps.setString(5, o.getPhno()); ps.setString(6,
	 * o.getBookname()); ps.setString(7, o.getAuthor()); //ps.setDouble(8,
	 * o.getPrice()); ps.setString(9, o.getPaymentType()); ps.setString(10,
	 * o.getStatus()); ps.setTimestamp(11, o.getCreatedAt()); ps.addBatch(); }
	 * 
	 * int[] count = ps.executeBatch(); con.commit(); f = true;
	 * con.setAutoCommit(true);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return f;
	 * 
	 * }
	 */

	// Insert order items
	public boolean addOrderItems(OrderItem item) throws SQLException {
		boolean f = false;
		try {
			String sql = "INSERT INTO order_items(order_id, book_id, book_name,author, quantity, price) VALUES(?,?,?,?,?,?)";
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, item.getOrderId());
			ps.setInt(2, item.getBookId());
			ps.setString(3, item.getBookName());
			ps.setString(4, item.getAuthor());
			ps.setInt(5, item.getQuantity());
			ps.setInt(6, item.getPrice());
			int i = ps.executeUpdate();  // execute directly
	        if (i > 0) {
	            f = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return f;

	}

	public List<Order> getOrderByUser(String email) {
		List<Order> list = new ArrayList<>();
		Order o = null;
		try {
			String sql = "select * from orderinfo where email=? order by created_time DESC" ;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				o = new Order();
				o.setId(rs.getInt("id"));
				o.setOrderid(rs.getString("order_id"));
				o.setUsername(rs.getString("user_name"));
				o.setEmail(rs.getString("email"));
				o.setFulladdress(rs.getString("address"));
				o.setPhno(rs.getString("phno"));
				o.setPaymentType(rs.getString("payment"));
				o.setStatus(rs.getString("status"));
				o.setCreatedAt(rs.getTimestamp("created_time"));
				o.setRazorpayOrderId(rs.getString("razorpay_order_id"));
				o.setRazorpayPaymentId(rs.getString("razorpay_payment_id"));
				o.setPaymentSignature(rs.getString("razorpay_signature"));
				o.setTotalAmount(rs.getDouble("total_amount"));
	            o.setItems(getOrderItems(o.getOrderid()));

				list.add(o);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public void updateOrder(String orderId, String status, String razorpayOrderId, String payment_id,
			String payment_signature) {
		try {
			String sql = "UPDATE orderinfo SET status=?,razorpay_order_id=? ,razorpay_payment_id=?, razorpay_signature=? WHERE order_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, status.toUpperCase());
			ps.setString(2, razorpayOrderId);
			ps.setString(3, payment_id);
			ps.setString(4, payment_signature);
			ps.setString(5, orderId);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Order> getOrderByStatus(String status) {

		ArrayList<Order> list = new ArrayList<>();
		Order o = null;

		try {
			String sql = "SELECT razorpay_payment_id FROM orderinfo WHERE status='PENDING' OR status='SUCCESS' ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				o = new Order();
				o.setRazorpayPaymentId(rs.getString(1));
				list.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public boolean updatedOrderStatusByPaymentId(String paymentId, String status) {

		boolean f = false;
		try {
			String sql = "Update orderinfo set status=? where razorpay_payment_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, status.toUpperCase());
			ps.setString(2, paymentId);
			ps.executeUpdate();
			f = true;

			System.out.println("✅ Reconciled " + paymentId + " -> " + status);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("⚠️ Error checking " + paymentId + ": " + e.getMessage());
		}

		return f;
	}

	public List<Order> getAllOrder() {
		List<Order> list = new ArrayList<>();
		Order o = null;
		try {
			String sql = "select * from orderinfo order by created_time DESC";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				o = new Order();
				o.setId(rs.getInt("id"));
				o.setOrderid(rs.getString("order_id"));
				o.setUsername(rs.getString("user_name"));
				o.setEmail(rs.getString("email"));
				o.setFulladdress(rs.getString("address"));
				o.setPhno(rs.getString("phno"));
				o.setPaymentType(rs.getString("payment"));
				o.setStatus(rs.getString("status"));
				o.setCreatedAt(rs.getTimestamp("created_time"));
				o.setRazorpayOrderId(rs.getString("razorpay_order_id"));
				o.setRazorpayPaymentId(rs.getString("razorpay_payment_id"));
				o.setPaymentSignature(rs.getString("razorpay_signature"));
				o.setTotalAmount(rs.getDouble("total_amount"));
				o.setItems(getOrderItems(o.getOrderid()));
				list.add(o);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

}
