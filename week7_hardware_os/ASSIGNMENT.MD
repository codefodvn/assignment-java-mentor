# Week 7: Hardware / OS

1. What are the two main components of a CPU?

- Có 2 thành phần chính của CPU:
  +ALU (Arithmetic Logic Unit) - Đơn vị số học và logic

      Thực hiện các phép toán số học (cộng, trừ, nhân, chia).

      Thực hiện các phép toán logic (AND, OR, NOT, XOR).

      So sánh giá trị và đưa ra kết quả cho các lệnh điều kiện.

+CU (Control Unit) - Bộ điều khiển

    Điều khiển luồng dữ liệu bên trong CPU và giữa CPU với các bộ phận khác.

    Giải mã và thực thi lệnh từ bộ nhớ.

    Quản lý các tín hiệu điều khiển để phối hợp hoạt động của toàn bộ hệ thống.

2. What is the purpose of the ALU?

- Thực hiện các phép toán số học

Cộng, trừ, nhân, chia.

Xử lý các phép toán số học với số nguyên và số thực (tùy kiến trúc CPU).

Thực hiện các phép toán logic

AND, OR, NOT, XOR.

Dịch bit (bit shifting), so sánh giá trị (>, <, =).

Đưa ra kết quả cho các quyết định điều kiện

Xác định kết quả đúng/sai để hỗ trợ các lệnh điều kiện (if-else, vòng lặp).

Hỗ trợ thao tác với cờ (flags) trong CPU để xác định trạng thái của phép tính.

3. What is the purpose of the memory hierarchy?

Tăng tốc độ truy cập dữ liệu ⚡

Dữ liệu thường xuyên sử dụng được lưu ở các mức bộ nhớ nhanh hơn (như cache, RAM) để CPU có thể truy xuất ngay lập tức.

Giảm độ trễ (latency) ⏳

Nếu CPU phải lấy dữ liệu từ bộ nhớ ngoài (HDD/SSD) thì rất chậm. Hệ thống phân cấp giúp giảm thiểu việc phải truy xuất trực tiếp từ các bộ nhớ tốc độ thấp này.

Cân bằng giữa chi phí và dung lượng 💰

Bộ nhớ nhanh (cache, RAM) rất đắt và có dung lượng nhỏ, trong khi bộ nhớ chậm (HDD, SSD) rẻ và có dung lượng lớn. Hệ thống phân cấp giúp tận dụng cả hai loại hiệu quả nhất.

Cải thiện hiệu suất tổng thể của hệ thống 🚀

Bằng cách lưu trữ dữ liệu quan trọng ở bộ nhớ gần CPU hơn, hiệu suất xử lý của hệ thống tăng lên đáng kể.

    -Các cấp trong hệ thống phân cấp bộ nhớ:

1.Thanh ghi (Register) – Nhanh nhất, ngay trong CPU, nhưng dung lượng rất nhỏ.

2.Bộ nhớ cache – Lưu trữ tạm dữ liệu thường dùng để giảm truy xuất RAM.

3.RAM (Bộ nhớ chính) – Truy cập nhanh hơn bộ nhớ ngoài nhưng vẫn chậm hơn cache.

4.Bộ nhớ ảo (Virtual Memory) & SSD/HDD – Dung lượng lớn, nhưng tốc độ truy cập chậm nhất.

4. What is the difference between a hard drive and a solid state drive?

- a hard drive: có đĩa từ quay và đầu đọc/ghi, có độ bền thấp, tốc độ chậm nhưng đổi lại giá thành rẻ.
- a solid state drive: dùng chip nhớ flash, có độ bền tốt, tốc độ nhanh hơn nhiều so với HDD nhưng đắt hơn khi ở cùng dung lượng

5. What is the purpose of the operating system?

Quản lý phần cứng ⚙️

Điều khiển CPU, bộ nhớ, ổ cứng, thiết bị ngoại vi (chuột, bàn phím, máy in).

Phân phối tài nguyên hợp lý giữa các ứng dụng.

Quản lý tiến trình và đa nhiệm 🔄

Xử lý nhiều chương trình cùng lúc (đa nhiệm - multitasking).

Điều phối CPU để các chương trình chạy trơn tru mà không xung đột.

Quản lý bộ nhớ 🧠

Cấp phát và giải phóng bộ nhớ RAM khi chương trình chạy.

Sử dụng bộ nhớ ảo để mở rộng không gian làm việc khi RAM đầy.

Quản lý hệ thống tệp (File System) 📂

Tổ chức, lưu trữ và truy xuất dữ liệu trên ổ cứng hoặc SSD.

Quản lý quyền truy cập tệp tin và thư mục.

Giao diện người dùng 🖥️

Cung cấp môi trường đồ họa (GUI) hoặc dòng lệnh (CLI) để người dùng thao tác dễ dàng.

Quản lý thiết bị ngoại vi ⌨️🖱️

Điều khiển thiết bị như máy in, chuột, bàn phím, webcam.

Cung cấp trình điều khiển (driver) để giao tiếp với phần cứng.

Bảo mật và quản lý quyền truy cập 🔐

Xác thực người dùng, bảo vệ dữ liệu và kiểm soát quyền truy cập.

Cung cấp tường lửa và các tính năng bảo vệ hệ thống khỏi virus, malware.

6. What is the difference between a process and a thread?

-A Process: là một chương trình chạy, có không gian bộ nhớ riêng và không chia sẻ.
-THread: là đơn vị nhỏ hơn trong process, chia sẻ bộ nhớ và tài nguyuên với các thread khác, hoạt động trực tiếp với CPU

7. How does the operating system handle thread synchronization?

🔹 Mutex (Mutual Exclusion) - Khóa độc quyền
Chỉ cho phép một luồng truy cập vào tài nguyên chung tại một thời điểm.
Khi một luồng giữ khóa (lock), các luồng khác phải chờ đến khi khóa được giải phóng.

🔹 Semaphore - Bộ đếm kiểm soát số luồng truy cập
Khác với Mutex, Semaphore có thể cho phép nhiều luồng truy cập cùng lúc (giới hạn theo giá trị Semaphore).

🔹 Monitor - Cơ chế đồng bộ cấp cao (dùng trong Java, Python, C#)
Monitor là một lớp bao bọc Mutex + điều kiện chờ (condition variables), giúp quản lý đồng bộ hóa dễ dàng hơn.

🔹 Condition Variable - Điều kiện chờ
Dùng kết hợp với Mutex hoặc Monitor để tạo cơ chế chờ và thông báo khi một điều kiện xảy ra.

8. What is the purpose of the scheduler?

- Mục đích để quản lí các tiến trình đang chạy trong hệ thống, phân bổ chúng 1 cách hợp lí,
  có thể sắp xếp để chạy theo mức độ ưu tiên và có 1 thời gian nhất định khi chạy 1 thread.
  ✅ Tối ưu hóa hiệu suất CPU: Giúp CPU luôn bận rộn, không để lãng phí tài nguyên.
  ✅ Đảm bảo công bằng giữa các tiến trình: Giúp mọi tiến trình đều có cơ hội thực thi.
  ✅ Tối ưu hóa thời gian phản hồi và thông lượng hệ thống: Đáp ứng nhanh cho người dùng và tối đa số tiến trình hoàn thành.
  ✅ Quản lý đa nhiệm hiệu quả: Đảm bảo nhiều ứng dụng có thể chạy cùng lúc một cách mượt mà.

9. What is the purpose of the file system?
   🔹 a) Quản lý lưu trữ và tổ chức dữ liệu 📂
   Tạo ra cấu trúc thư mục (folders) để lưu trữ dữ liệu có tổ chức.

Hỗ trợ phân loại tệp theo định dạng (.txt, .jpg, .exe…).

🔹 b) Cung cấp cơ chế truy xuất tệp 🖥️
Hệ điều hành cung cấp API và lệnh để người dùng mở, đọc, ghi và xóa tệp.

Hỗ trợ thao tác tìm kiếm nhanh dựa trên tên hoặc metadata (ngày tạo, kích thước...).

🔹 c) Kiểm soát quyền truy cập 🔐
Bảo vệ tệp bằng cách xác định ai có quyền đọc, ghi, sửa đổi hoặc xóa.

Hỗ trợ các cơ chế bảo mật như phân quyền người dùng (Owner, Group, Others).

🔹 d) Quản lý không gian lưu trữ 💾
Hệ thống tệp theo dõi các khối dữ liệu trống và đã sử dụng trên ổ đĩa.

Hỗ trợ chống phân mảnh để tối ưu tốc độ truy xuất.

🔹 e) Đảm bảo tính toàn vẹn dữ liệu (Data Integrity) ✅
Sử dụng nhật ký (journaling) để khôi phục dữ liệu nếu có lỗi hệ thống.

Một số hệ thống tệp như NTFS, ext4 hỗ trợ tự động kiểm tra lỗi và phục hồi.

10. How does the file system handle file access control?

Kiểm soát truy cập dựa trên quyền
Danh sách kiểm soát truy cập
Kiểm soát truy cập bắt buộc
Kiểm soát truy cập dựa trên vai trò
