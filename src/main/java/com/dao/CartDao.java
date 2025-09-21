package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Cart;

public class CartDao {

	private Connection con;

	public CartDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean addToCart(Cart c) {
		boolean f = false;

		try {

			String sql = "insert into cart (bid,uid,book_name,author,price,total_price,quantity) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, c.getBid());
			ps.setInt(2, c.getUid());
			ps.setString(3, c.getBook_name());
			ps.setString(4, c.getAuthor());
			ps.setInt(5, c.getPrice());
			ps.setInt(6, c.getTotal_price());
			ps.setInt(7, c.getQuantity());
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}
	// Fetch a specific cart item by user & book
	public Cart getCartItemByUserAndBook(int userId, int bookId) {
	    Cart c = null;
	    try {
	        String sql = "SELECT * FROM cart WHERE uid=? AND bid=?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, userId);
	        ps.setInt(2, bookId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            c = new Cart();
	            c.setCid(rs.getInt("cid"));
	            c.setUid(rs.getInt("uid"));
	            c.setBid(rs.getInt("bid"));
	            c.setBook_name(rs.getString("book_name"));
	            c.setAuthor(rs.getString("author"));
	            c.setQuantity(rs.getInt("quantity"));
	            c.setPrice(rs.getInt("price"));
	            c.setTotal_price(rs.getInt("total_price"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return c;
	}
	
	// Update cart item (for quantity & total price update)
	public boolean updateCart(Cart cart) {
	    boolean f = false;
	    try {
	        String sql = "UPDATE cart SET quantity=?, total_price=? WHERE cid=?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, cart.getQuantity());
	        ps.setInt(2, cart.getTotal_price());
	        ps.setInt(3, cart.getCid());

	        int i = ps.executeUpdate();
	        if (i == 1) f = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return f;
	}


	public boolean addToCart(List<Cart> clist) {
		boolean f = false;

		try {

			String sql = "insert into cart (bid,uid,book_name,author,price,total_price,quantity) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			con.setAutoCommit(false);
			for (Cart c : clist) {
				ps.setInt(1, c.getBid());
				ps.setInt(2, c.getUid());
				ps.setString(3, c.getBook_name());
				ps.setString(4, c.getAuthor());
				ps.setInt(5, c.getPrice());
				ps.setInt(6, c.getTotal_price());
				ps.setInt(7, c.getQuantity());
				ps.addBatch();
			}
			int[] count = ps.executeBatch();
			con.commit();
			f = true;
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}

	public List<Cart> getBookByUserId(int id) {
		List<Cart> list = new ArrayList<>();
		Cart c = null;
		int totalPrice = 0;

		try {

			String sql = "select * from cart where uid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				c = new Cart();
				c.setCid(rs.getInt(1));
				c.setUid(rs.getInt(2));
				c.setBid(rs.getInt(3));
				c.setBook_name(rs.getString(4));
				c.setAuthor(rs.getString(5));
				c.setPrice(rs.getInt(6));
				totalPrice = totalPrice + rs.getInt(7);
				c.setTotal_price(totalPrice);
				c.setQuantity(rs.getInt(8));
				list.add(c);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public boolean deleteCart(int userId) {
		boolean f = false;
		try {
			String sql = "delete from cart where uid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}

	public boolean removeBook(int bid, int uid, int cid) {
		boolean f = false;

		try {

			String sql = "delete from cart where bid=? and uid=? and cid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bid);
			ps.setInt(2, uid);
			ps.setInt(3, cid);

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}

}
