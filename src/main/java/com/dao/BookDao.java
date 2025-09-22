package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Books;

public class BookDao {

	private Connection con;

	public BookDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean addBook(Books b) {
		boolean f = false;

		try {
			String sql = "insert into book (bookname,author,price,bookCategory,status,photo,email,imageData) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, b.getBookname());
			ps.setString(2, b.getAuthor());
			ps.setString(3, b.getPrice());
			ps.setString(4, b.getBookCategory());
			ps.setString(5, b.getStatus());
			ps.setString(6, b.getPhotoName());
			ps.setString(7, b.getEmail());
			ps.setBytes(8, b.getImageData());
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
			
		}

		return f;

	}

	public List<Books> getAllBooks() {
		List<Books> list = new ArrayList<>();
		Books b = null;

		try {
			String sql = "select * from book";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				b = new Books();
				b.setBookId(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				b.setImageData(rs.getBytes(9));
				list.add(b);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public Books getBookById(int bookId) {

		Books b = null;

		try {
			String sql = "select * from book where bookId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				b = new Books();
				b.setBookId(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				b.setImageData(rs.getBytes(9));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return b;

	}

	public boolean updateBook(Books b) {
		boolean f = false;

		try {
			String sql = "update book set bookname=?, author=?, price=?, status=? where bookId=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, b.getBookname());
			ps.setString(2, b.getAuthor());
			ps.setString(3, b.getPrice());
			ps.setString(4, b.getStatus());
			ps.setInt(5, b.getBookId());
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return f;

	}

	public boolean deleteBook(int bookId) {
		boolean f = false;

		try {
			String sql = "delete from book where bookId=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bookId);
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return f;

	}

	public List<Books> getNewBooks() {
		List<Books> list = new ArrayList<>();
		Books b = null;

		try {
			String sql = "select * from book where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "new");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();
			int i = 1;

			while (rs.next() && i <= 4) {
				b = new Books();
				b.setBookId(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				b.setImageData(rs.getBytes(9));

				list.add(b);
				i++;

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public List<Books> getRecentBooks() {
		List<Books> list = new ArrayList<>();
		Books b = null;

		try {
			String sql = "select * from book where status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "Active");
			ResultSet rs = ps.executeQuery();
			int i = 1;

			while (rs.next() && i <= 4) {
				b = new Books();
				b.setBookId(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				b.setImageData(rs.getBytes(9));
				list.add(b);
				i++;

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public List<Books> getOldBooks() {
		List<Books> list = new ArrayList<>();
		Books b = null;

		try {
			String sql = "select * from book where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "Old");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();
			int i = 1;

			while (rs.next() && i <= 4) {
				b = new Books();
				b.setBookId(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);
				i++;

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public List<Books> getAllNewBooks() {
		List<Books> list = new ArrayList<>();
		Books b = null;

		try {
			String sql = "select * from book where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "New");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				b = new Books();
				b.setBookId(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public List<Books> getAllRecentBooks() {
		List<Books> list = new ArrayList<>();
		Books b = null;

		try {
			String sql = "select * from book where status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "Active");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				b = new Books();
				b.setBookId(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public List<Books> getAllOldBooks() {
		List<Books> list = new ArrayList<>();
		Books b = null;

		try {
			String sql = "select * from book where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "Old");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				b = new Books();
				b.setBookId(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public List<Books> getUserOldBooks(String email, String Category) {
		List<Books> list = new ArrayList<>();
		Books b = null;

		try {
			String sql = "select * from book where email=? and bookCategory=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, Category);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				b = new Books();
				b.setBookId(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public boolean deleteUserBook(int bookId, String email) {
		boolean f = false;

		try {
			String sql = "delete from book where bookId=? and email=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setString(2, email);
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return f;

	}

	public List<Books> getBookBySearch(String ch) {
		List<Books> list = new ArrayList<>();
		Books b = null;

		try {
			String sql = "select * from book where bookname like ? or author like ? or price like ? or bookCategory like ? and status=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + ch + "%");
			ps.setString(2, "%" + ch + "%");
			ps.setString(3, "%" + ch + "%");
			ps.setString(4, "%" + ch + "%");
			ps.setString(5, "Active");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				b = new Books();
				b.setBookId(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				b.setImageData(rs.getBytes(9));
				list.add(b);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

}
