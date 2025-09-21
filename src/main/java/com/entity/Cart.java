package com.entity;

public class Cart {

	private int cid;
	private int bid;
	private int uid;
	private String book_name;
	private String author;
	private int price;
	private int total_price;
	private int quantity;

	
	/**
	 * @return the cid
	 */
	public int getCid() {
		return cid;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}

	/**
	 * @return the bid
	 */
	public int getBid() {
		return bid;
	}

	/**
	 * @param bid the bid to set
	 */
	public void setBid(int bid) {
		this.bid = bid;
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * @return the book_name
	 */
	public String getBook_name() {
		return book_name;
	}

	/**
	 * @param book_name the book_name to set
	 */
	public void setBook_name(String book_name) {
		this.book_name = book_name;
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

	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the total_price
	 */
	public int getTotal_price() {
		return total_price;
	}

	/**
	 * @param total_price the total_price to set
	 */
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	@Override
	public String toString() {
		return "Cart [cid=" + cid + ", bid=" + bid + ", uid=" + uid + ", book_name=" + book_name + ", author=" + author
				+ ", price=" + price + ", total_price=" + total_price + ", quantity=" + quantity + "]";
	}

}
