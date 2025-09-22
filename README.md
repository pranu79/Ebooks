# 📚 E-Book Web Application

**A full-stack web-based E-Book Store built with Java (Spring MVC, Servlets/JSP), featuring Razorpay payment integration, BCrypt password encryption, and AuthFilter-based session & access control.**

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring MVC, Servlet/JSP  
- **Payment:** Razorpay (online payments)  
- **Security:** BCrypt for password encryption, AuthFilter for authorization/session control  
- **Database:** MySQL  
- **Frontend:** JSP, HTML, CSS, Bootstrap  
- **Build Tool:** Maven  
- **Server:** Apache Tomcat  

---

## 🚀 Features

**User Features**  
- Secure registration & login (BCrypt password encryption)  
- Browse books, add to cart, checkout  
- Payment options: Cash on Delivery & Razorpay online payment  
- Sell books on the platform  
- Manage profile & addresses  
- View order history & status  

**Admin Features**  
- Admin login with session protection (using AuthFilter)  
- Add, update, delete books  
- View all orders, manage status, perform reconciliation  
- Manage users  

---

## ⚙️ Installation & Setup

1. **Clone the repository**  
   ```bash
   git clone https://github.com/pranu79/ebook.git
   cd ebook

2. **Set up the Database**  
   ```sql
   CREATE DATABASE ebook;
   
Update the database credentials in your DB connection class (in com.db) or wherever configured.

3. **Configure for Razorpay**  
Use Razorpay key & secret in configuration (preferably via environment variables or a config file)
Use Razorpay sandbox / test credentials for development

4. **Build & Deploy**
   ```bash
   mvn clean install

Deploy the compiled WAR to Apache Tomcat (or run via IDE)

5. ***Run / Access Application***
Open browser →

    ```bash
    http://localhost:8080/ebook
    
---

## 📸 Screenshots

🔐 Login Page
<img width="1324" height="629" alt="image" src="https://github.com/user-attachments/assets/8d6cf9bb-98a3-4175-ba0b-1170b66df9c3" />


🏠 Home Page
<img width="1323" height="636" alt="image" src="https://github.com/user-attachments/assets/5a831868-6ed0-4426-9af0-e3aa3ab7c5cb" />


🛒 Cart Page
<img width="1203" height="630" alt="image" src="https://github.com/user-attachments/assets/14f2d952-5671-4209-8c42-ed1998a1feaf" />


💳 Payment (Razorpay Integration)
<img width="1220" height="635" alt="image" src="https://github.com/user-attachments/assets/d7b1bf8a-55f4-40f1-9fde-04e1d46932c2" />


📦 Admin Dashboard
<img width="1311" height="633" alt="image" src="https://github.com/user-attachments/assets/fdb09086-ec98-4311-8f5f-db1243c1d8d0" />


---

## 🔮 Future Enhancements

- Book recommendation system
- Wishlist & Review features
- Email notifications on order updates
- REST API endpoints for mobile / frontend JS apps
- Analytics dashboard for admin
- Add CI/CD pipeline, Docker setup for easy deployment

---

## 👩‍💻 Author

Pranali Dalvi

GitHub: pranu79

📫 Contact me if you'd like to collaborate or see a live demo!

---


