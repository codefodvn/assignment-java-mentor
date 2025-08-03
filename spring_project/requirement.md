
# YÊU CẦU BÀI TẬP TỔNG HỢP SPRING BOOT

## 1. **Mục tiêu**

Xây dựng một hệ thống RESTful API quản lý Đơn hàng (Order), Sản phẩm (Product), Khách hàng (Customer) cho công ty có nhiều chi nhánh, áp dụng toàn diện các kiến thức:

* Spring Boot
* Spring Security (JWT)
* Hibernate/JPA
* Multi Database
* Spring Data JPA Specification
* Logging, AOP
* Exception Handling

---

## 2. **Mô tả tổng quan hệ thống**

* Hệ thống phục vụ nhiều chi nhánh (branch) trên toàn quốc.
* **Khách hàng (Customer):** Thông tin lưu ở database trung tâm (MySQL).
* **Đơn hàng (Order), Sản phẩm (Product):** Mỗi chi nhánh có một database riêng (PostgreSQL).
* Người dùng đăng nhập, thao tác qua REST API.
* Có 2 loại tài khoản: `ADMIN` (toàn quyền), `BRANCH` (quản lý đơn hàng & sản phẩm của chi nhánh mình).

---

## 3. **Yêu cầu chức năng**

### 3.1. **Xác thực & Phân quyền**

* Người dùng phải đăng nhập bằng username/password để lấy JWT token.
* Gắn JWT token vào header các request sau khi đăng nhập.
* **ADMIN**: truy cập mọi API.
* **BRANCH**: chỉ được thao tác dữ liệu đơn hàng, sản phẩm thuộc chi nhánh của mình.
* Phân quyền truy cập theo annotation hoặc cấu hình security.

---

### 3.2. **Quản lý Đa cơ sở dữ liệu**

* **Database1 (MySQL):** Lưu Customer.
* **Database2 (PostgreSQL):** Lưu Product, Order.
* Kết nối, transaction phải được cấu hình đúng.
* Khi tạo đơn hàng, cần kiểm tra Customer hợp lệ từ DB1 và lưu Order ở DB2.

---

### 3.3. **Chức năng CRUD và Tìm kiếm**

* **Customer:** CRUD (tạo, sửa, xóa, xem chi tiết, tìm kiếm theo tên, email).
* **Product:** CRUD, tìm kiếm, filter theo tên, giá, loại sản phẩm.
* **Order:** CRUD, tìm kiếm, filter theo mã đơn, ngày tạo, khách hàng.

---

### 3.4. **Tìm kiếm nâng cao (Specification)**

* Áp dụng Spring Data JPA Specification cho Product và Order:

  * API `/products/search`, `/orders/search` cho phép filter động nhiều trường (giá, loại sản phẩm, khách hàng, ngày…).

---

### 3.5. **Logging & Audit**

* Toàn bộ request/response API phải được log lại (dùng AOP logging hoặc filter).
* Các thao tác thêm/sửa/xóa dữ liệu phải có Audit Log ghi rõ người thao tác, hành động, thời gian (dùng Spring AOP).

---

### 3.6. **Xử lý ngoại lệ**

* Cài đặt global exception handler (`@ControllerAdvice`) cho toàn bộ hệ thống.
* Khi lỗi phải trả về response chuẩn gồm code, message, timestamp.
* Xử lý tốt các lỗi phổ biến: 400, 401, 403, 404, 500.

---

### 3.7. **API Docs & Test**

* Tích hợp Swagger/OpenAPI docs cho toàn bộ API.
* Có tối thiểu 5 test case unit test cho các service chính.

---

### 3.8. **Bonus (khuyến khích, không bắt buộc)**

* Gửi email khi tạo Order thành công.
* Đưa project lên Docker Compose chạy kèm MySQL, PostgreSQL.
* Caching kết quả search bằng Redis.

---

## 4. **Yêu cầu về kỹ thuật**

* **Ngôn ngữ:** Java 17+
* **Framework:** Spring Boot 3.x
* **Database:** MySQL, PostgreSQL (multi-datasource)
* **ORM:** JPA/Hibernate
* **Security:** Spring Security, JWT
* **API docs:** Swagger
* **Build tool:** Maven hoặc Gradle
* **Logging:** Logback, SLF4J, AOP logging
* **Testing:** JUnit, Mockito
* **Khuyến khích:** Docker Compose file

---

## 5. **Yêu cầu về báo cáo nộp**

1. Source code đầy đủ (repository hoặc zip).
2. File `README.md` hướng dẫn setup, migrate database, test các API mẫu.
3. **Tối thiểu:** Triển khai đầy đủ các chức năng bắt buộc từ mục 3.1 đến 3.7.
4. Có ảnh chụp màn hình minh họa gọi API, Swagger UI, log file, ví dụ lỗi.

---

## 6. **Gợi ý tiến trình thực hiện**

1. Phân tích requirement, vẽ sơ đồ ERD.
2. Thiết kế entity, cấu hình multi-datasource.
3. Xây dựng chức năng xác thực & phân quyền.
4. Xây dựng các API CRUD, search động, logging, exception handler.
5. Tích hợp Swagger, viết unit test, bổ sung báo cáo.

---
