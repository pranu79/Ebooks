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
<img width="1354" height="595" alt="image" src="https://github.com/user-attachments/assets/2b26d1b5-cb90-4aec-8c3e-d771662f208f" />

🏠 Home Page
<img width="1339" height="644" alt="image" src="https://github.com/user-attachments/assets/2f8ff5dc-7e2d-46c6-ad77-6f05b8bd63a3" />

🛒 Cart Page
<img width="1315" height="654" alt="image" src="https://github.com/user-attachments/assets/c68e9636-70f0-43f0-bfd9-ff9e8c22c955" />

💳 Payment (Razorpay Integration)
<img width="1330" height="643" alt="image" src="https://github.com/user-attachments/assets/684d9c8e-029f-4f6a-ba3d-5f9bf22f1a6e" />

📦 Admin Dashboard
<img width="1350" height="586" alt="image" src="https://github.com/user-attachments/assets/a820de99-d59c-40a7-8a7d-a9dfa05c74d0" />

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


