package com.entity;

public class Books {

	private int bookId;
	private String bookname;
	private String author;
	private String price;
	private String bookCategory;
	private String status;
	private String photoName;
	private String email;
	private byte[] imageData;
	
	

	public Books(String bookname, String author, String price, String bookCategory, String status, String email) {
		super();
		this.bookname = bookname;
		this.author = author;
		this.price = price;
		this.bookCategory = bookCategory;
		this.status = status;
		this.email = email;
	}

	public Books(int bookId, String bookname, String author, String price, String bookCategory, String status,
			String photoName, String email) {
		super();
		this.bookId = bookId;
		this.bookname = bookname;
		this.author = author;
		this.price = price;
		this.bookCategory = bookCategory;
		this.status = status;
		this.photoName = photoName;
		this.email = email;
	}

	public Books(String bookname, String author, String price, String bookCategory, String status, String photoName,
			String email) {
		super();
		this.bookname = bookname;
		this.author = author;
		this.price = price;
		this.bookCategory = bookCategory;
		this.status = status;
		this.photoName = photoName;
		this.email = email;
	}

	public Books(String bookname, String author, String price, String bookCategory, String status, String photoName,
			String email, byte[] imageData) {
		super();
		this.bookname = bookname;
		this.author = author;
		this.price = price;
		this.bookCategory = bookCategory;
		this.status = status;
		this.photoName = photoName;
		this.email = email;
		this.imageData = imageData;
	}

	public Books(int bookId, String bookname, String author, String price, String status) {
		super();
		this.bookId = bookId;
		this.bookname = bookname;
		this.author = author;
		this.price = price;
		this.status = status;
	}

	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the bookname
	 */
	public String getBookname() {
		return bookname;
	}

	/**
	 * @param bookname the bookname to set
	 */
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the bookCategory
	 */
	public String getBookCategory() {
		return bookCategory;
	}

	/**
	 * @param bookCategory the bookCategory to set
	 */
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the photoName
	 */
	public String getPhotoName() {
		return photoName;
	}

	/**
	 * @param photoName the photoName to set
	 */
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	

	/**
	 * @return the imageData
	 */
	public byte[] getImageData() {
		return imageData;
	}

	/**
	 * @param blob the imageData to set
	 */
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Books [bookId=" + bookId + ", bookname=" + bookname + ", author=" + author + ", price=" + price
				+ ", bookCategory=" + bookCategory + ", status=" + status + ", photoName=" + photoName + ", email="
				+ email + ", imageData=" + imageData + "]";
	}
	

}
