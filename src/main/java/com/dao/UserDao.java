package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import com.entity.User;

public class UserDao {

	private Connection con;

	public UserDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean userRegister(User u) {
		boolean f = false;

		try {
			String sql = "insert into userinfo (name,email,phno,password) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPhno());
			ps.setString(4, u.getPassword());
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return f;

	}

	public boolean updateAddress(User u) {
		boolean f = false;

		try {
			String sql = "update userinfo set address=?,landmark=?,state=?,city=?,pincode=? where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getAddress());
			ps.setString(2, u.getLandmark());
			ps.setString(3, u.getState());
			ps.setString(4, u.getCity());
			ps.setString(5, u.getPincode());
			ps.setInt(6, u.getId());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return f;

	}

	public User getUserByEmail(String email) {
		User u = null;

		try {

			String sql = "select * from userinfo where email=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPhno(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setAddress(rs.getString(6));
				u.setCity(rs.getString(7));
				u.setLandmark(rs.getString(8));
				u.setState(rs.getString(9));
				u.setPincode(rs.getString(10));

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return u;
	}

	/*
	 * public boolean checkPassword(int id, String password) { boolean f = false;
	 * 
	 * try {
	 * 
	 * String sql = "select * from userinfo where id=? and password=? ";
	 * PreparedStatement ps = con.prepareStatement(sql); ps.setInt(1, id);
	 * ps.setString(2, password); ResultSet rs = ps.executeQuery(); while
	 * (rs.next()) { f = true; }
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * } return f; }
	 */
	public boolean checkPassword(int id, String plainPassword) {
	    boolean f = false;
	    try {
	        String sql = "SELECT password FROM userinfo WHERE id=?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            String storedHash = rs.getString("password");
	            f = BCrypt.checkpw(plainPassword, storedHash); // <-- correct check
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return f;
	}


	public boolean checkEmail(String email) {
		boolean f = false;

		try {

			String sql = "select * from userinfo where email=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean editProfile(User u) {
		boolean f = false;

		try {
			String sql = "update userinfo set name=? ,email=?,phno=?,password=? where id=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPhno());
			ps.setString(4, u.getPassword());
			ps.setInt(5, u.getId());
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return f;

	}

	public User getUserById(int id) {
		User u = null;

		try {

			String sql = "select * from userinfo where id=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPhno(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setAddress(rs.getString(6));
				u.setLandmark(rs.getString(7));
				u.setState(rs.getString(8));
				u.setCity(rs.getString(9));
				u.setPincode(rs.getString(10));
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return u;
	}

}
