# 🏀 SportBooking API

## 📌 Giới thiệu

SportBooking API là backend service cho ứng dụng đặt sân thể thao, được phát triển bằng Spring Boot. API này cung cấp các endpoint để quản lý việc đặt sân, quản lý người dùng, và các tính năng liên quan đến hệ thống đặt sân thể thao.

## 👥 Thành viên nhóm

- **Huỳnh Việt Đan** - 22110306
- **Nguyễn Phan Minh Trí** - 22110443

## 🛠 Công nghệ sử dụng

- **Java**: version 21
- **Spring Boot**: version 3.4.0
- **Spring Security** + **JWT** cho authentication và authorization
- **MySQL**: Database
- **Maven**: Build tool và dependency management
- **Lombok**: Giảm boilerplate code
- **Swagger/OpenAPI**: API documentation
- **Spring Mail**: Gửi email

## 🚀 Tính năng chính

- Xác thực và phân quyền người dùng (JWT)
- Quản lý người dùng (đăng ký, đăng nhập, quản lý thông tin cá nhân)
- Quản lý sân thể thao (thêm, sửa, xóa, tìm kiếm)
- Đặt sân (tạo, hủy, xem lịch sử đặt sân)
- Quản lý thanh toán
- Gửi email xác nhận

## 📥 Cài đặt và Chạy

### Yêu cầu hệ thống

- Java Development Kit (JDK) 21
- Maven
- MySQL Server

### Các bước cài đặt

1. Clone repository:

```bash
git clone [repository-url]
```

2. Cấu hình database:

- Tạo database MySQL
- Import file `booking_app.sql`
- Cập nhật thông tin database trong `src/main/resources/application.properties`

3. Build project:

```bash
mvn clean install
```

4. Chạy ứng dụng:

```bash
mvn spring-boot:run
```

Ứng dụng sẽ chạy tại `http://localhost:8080`

## 📚 API Documentation

Sau khi chạy ứng dụng, bạn có thể truy cập API documentation tại:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## 🔑 Cấu trúc project

```
src/main/java/com/example/apiproject/
├── config/         # Cấu hình Spring Boot và Security
├── controller/     # REST API endpoints
├── dto/           # Data Transfer Objects
├── entity/        # JPA entities
├── enums/         # Enums
├── repository/    # Spring Data JPA repositories
├── service/       # Business logic
└── JWT/           # JWT authentication
```

## 🔒 Security

- Sử dụng JWT (JSON Web Tokens) cho authentication
- Spring Security cho authorization
- Password encryption với BCrypt
- Role-based access control (RBAC)

## 📧 Environment Variables

Các biến môi trường cần được cấu hình:

- `MYSQL_URL`: URL kết nối database
- `MYSQL_USERNAME`: Username database
- `MYSQL_PASSWORD`: Password database
- `JWT_SECRET`: Secret key cho JWT
- `MAIL_USERNAME`: Email để gửi thông báo
- `MAIL_PASSWORD`: Password email

## 📡 API Endpoints

### 🔐 Authentication

```http
POST /api/auth/register     # Đăng ký tài khoản mới
POST /api/auth/login        # Đăng nhập và nhận JWT token
POST /api/auth/refresh      # Làm mới JWT token
POST /api/auth/logout       # Đăng xuất
```

### 👤 User Management

```http
GET    /api/users                  # Lấy danh sách người dùng (ADMIN)
GET    /api/users/{id}            # Lấy thông tin người dùng
PUT    /api/users/{id}            # Cập nhật thông tin người dùng
DELETE /api/users/{id}            # Xóa người dùng (ADMIN)
PUT    /api/users/password        # Đổi mật khẩu
POST   /api/users/forgot-password # Quên mật khẩu
POST   /api/users/reset-password  # Đặt lại mật khẩu
```

### ⚽ Sport Venues

```http
GET    /api/venues                    # Lấy danh sách sân
GET    /api/venues/{id}              # Lấy thông tin chi tiết sân
POST   /api/venues                    # Thêm sân mới (ADMIN)
PUT    /api/venues/{id}              # Cập nhật thông tin sân (ADMIN)
DELETE /api/venues/{id}              # Xóa sân (ADMIN)
GET    /api/venues/search            # Tìm kiếm sân theo tiêu chí
GET    /api/venues/{id}/availability # Kiểm tra lịch trống của sân
```

### 📅 Bookings

```http
POST   /api/bookings                # Đặt sân
GET    /api/bookings               # Lấy danh sách đặt sân của user
GET    /api/bookings/{id}         # Xem chi tiết đặt sân
PUT    /api/bookings/{id}         # Cập nhật đặt sân
DELETE /api/bookings/{id}         # Hủy đặt sân
GET    /api/bookings/history      # Xem lịch sử đặt sân
```

### 💰 Payments

```http
POST   /api/payments                # Tạo thanh toán mới
GET    /api/payments/{id}          # Xem chi tiết thanh toán
GET    /api/payments/booking/{id}  # Xem thanh toán của booking
POST   /api/payments/webhook       # Webhook nhận kết quả thanh toán
```

### 📊 Reviews & Ratings

```http
POST   /api/reviews                # Đánh giá sân
GET    /api/reviews/venue/{id}    # Xem đánh giá của sân
PUT    /api/reviews/{id}          # Cập nhật đánh giá
DELETE /api/reviews/{id}          # Xóa đánh giá
```

### 🏷️ Categories & Sports

```http
GET    /api/categories            # Lấy danh sách loại sân
GET    /api/sports               # Lấy danh sách môn thể thao
```

## 📝 Request & Response Examples

### Đăng ký tài khoản

```http
POST /api/auth/register
```

Request body:

```json
{
  "username": "user123",
  "email": "user@example.com",
  "password": "password123",
  "fullName": "Nguyen Van A",
  "phone": "0123456789"
}
```

Response:

```json
{
  "status": "success",
  "message": "User registered successfully",
  "data": {
    "id": 1,
    "username": "user123",
    "email": "user@example.com",
    "fullName": "Nguyen Van A",
    "role": "USER"
  }
}
```

### Đặt sân

```http
POST /api/bookings
```

Request body:

```json
{
  "venueId": 1,
  "date": "2024-03-20",
  "startTime": "18:00",
  "endTime": "20:00",
  "numberOfPeople": 10,
  "note": "Đặt sân bóng đá 5 người"
}
```

Response:

```json
{
  "status": "success",
  "data": {
    "bookingId": 1,
    "venue": {
      "id": 1,
      "name": "Sân bóng đá ABC"
    },
    "totalAmount": 500000,
    "status": "PENDING",
    "paymentUrl": "https://payment.example.com/checkout/123"
  }
}
```

## 🔍 Query Parameters

### Tìm kiếm sân

```http
GET /api/venues/search
```

Parameters:

- `sport`: Loại môn thể thao (football, basketball, etc.)
- `district`: Quận/Huyện
- `date`: Ngày muốn đặt (YYYY-MM-DD)
- `startTime`: Giờ bắt đầu (HH:mm)
- `endTime`: Giờ kết thúc (HH:mm)
- `minPrice`: Giá tối thiểu
- `maxPrice`: Giá tối đa
- `rating`: Đánh giá tối thiểu (1-5)
- `page`: Số trang (default: 0)
- `size`: Số item mỗi trang (default: 10)
- `sort`: Sắp xếp theo (price, rating, distance)

## 🎯 Status Codes

```
200 - OK                     → Request thành công
201 - Created               → Tạo mới thành công
400 - Bad Request           → Request không hợp lệ
401 - Unauthorized         → Chưa xác thực
403 - Forbidden            → Không có quyền truy cập
404 - Not Found            → Không tìm thấy resource
409 - Conflict             → Xung đột dữ liệu
422 - Unprocessable Entity → Dữ liệu không hợp lệ
500 - Server Error         → Lỗi server
```

## 🔒 License

[MIT License](LICENSE)
