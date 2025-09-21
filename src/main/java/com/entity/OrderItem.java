package com.entity;

public class OrderItem {
	
	    private int orderItemId;
	    private String orderId;
	    private int bookId;
	    private String bookName;
	    private String author;
	    private int quantity;
	    private int price;
	    
	    // getters & setters

		/**
		 * @return the orderItemId
		 */
		public int getOrderItemId() {
			return orderItemId;
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
		 * @param orderItemId the orderItemId to set
		 */
		public void setOrderItemId(int orderItemId) {
			this.orderItemId = orderItemId;
		}
		/**
		 * @return the orderId
		 */
		public String getOrderId() {
			return orderId;
		}
		/**
		 * @param orderId the orderId to set
		 */
		public void setOrderId(String orderId) {
			this.orderId = orderId;
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
		 * @return the bookName
		 */
		public String getBookName() {
			return bookName;
		}
		/**
		 * @param bookName the bookName to set
		 */
		public void setBookName(String bookName) {
			this.bookName = bookName;
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
		@Override
		public String toString() {
			return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId + ", bookId=" + bookId
					+ ", bookName=" + bookName + ", author=" + author + ", quantity=" + quantity + ", price=" + price
					+ "]";
		}

	    
	

}
