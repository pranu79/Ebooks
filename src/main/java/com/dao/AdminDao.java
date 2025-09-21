package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.Admin;

public class AdminDao {

	private Connection con;

	public AdminDao(Connection con) {
		super();
		this.con = con;
	}

	public Admin getAdminByUsername(String username) {
		Admin admin = null;

		try {
			String sql = "select * from admininfo where username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getInt("id"));
				admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return admin;

	}

}
