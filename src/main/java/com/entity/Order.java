package com.entity;

import java.sql.Timestamp;
import java.util.List;

public class Order {

	private int id;
	private String orderid;
	private String username;
	private String email;
	private String fulladdress;
	private String phno;
	//private String bookname;
	//private String author;
	//private double price;
	

	private String paymentType;
	private String status;
	private Timestamp createdAt;
	private String razorpayOrderId;
	private String razorpayPaymentId;
	private String paymentSignature;
	private Double totalAmount;
    private List<OrderItem> items; // child records


	


	/**
	 * @return the total_amount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}


	/**
	 * @param total_amount the total_amount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}


	/**
	 * @return the items
	 */
	public List<OrderItem> getItems() {
		return items;
	}


	/**
	 * @param items the items to set
	 */
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}


	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @return the razorpayOrderId
	 */
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}


	/**
	 * @param razorpayOrderId the razorpayOrderId to set
	 */
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}


	/**
	 * @return the razorpayPaymentId
	 */
	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}


	/**
	 * @param razorpayPaymentId the razorpayPaymentId to set
	 */
	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}


	/**
	 * @return the paymentSignature
	 */
	public String getPaymentSignature() {
		return paymentSignature;
	}


	/**
	 * @param paymentSignature the paymentSignature to set
	 */
	public void setPaymentSignature(String paymentSignature) {
		this.paymentSignature = paymentSignature;
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
	 * @return the createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}


	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the orderid
	 */
	public String getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid the orderid to set
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the fulladdress
	 */
	public String getFulladdress() {
		return fulladdress;
	}

	/**
	 * @param fulladdress the fulladdress to set
	 */
	public void setFulladdress(String fulladdress) {
		this.fulladdress = fulladdress;
	}

	/**
	 * @return the phno
	 */
	public String getPhno() {
		return phno;
	}

	/**
	 * @param phno the phno to set
	 */
	public void setPhno(String phno) {
		this.phno = phno;
	}

	/**
	



	


	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderid=" + orderid + ", username=" + username + ", email=" + email
				+ ", fulladdress=" + fulladdress + ", phno=" + phno + ", paymentType=" + paymentType + ", status="
				+ status + ", createdAt=" + createdAt + ", razorpayOrderId=" + razorpayOrderId + ", razorpayPaymentId="
				+ razorpayPaymentId + ", paymentSignature=" + paymentSignature + ", totalAmount=" + totalAmount
				+ ", items=" + items + "]";
	}


	


	
	

}
