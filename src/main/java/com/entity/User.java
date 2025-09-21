package com.entity;

public class User {

	private int id;
	private String name;
	private String email;
	private String phno;
	private String password;
	private String address;
	private String landmark;
	private String city;
	private String state;
	private String pincode;

	public User(int id, String name, String email, String phno, String password, String address, String landmark,
			String city, String state, String pincode) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phno = phno;
		this.password = password;
		this.address = address;
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the landmark
	 */
	public String getLandmark() {
		return landmark;
	}

	/**
	 * @param landmark the landmark to set
	 */
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phno=" + phno + ", password=" + password
				+ ", address=" + address + ", landmark=" + landmark + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + "]";
	}

}
