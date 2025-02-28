,
# Week 3: OOP

1. Lập trình hướng đối tượng là gì? So sánh với hướng lập trình thủ tục

- Answer: hướng dối tượng là mô hình triển khai trên khái niệm về đối tượng trong đó đối tượng là sự kết hợp của dữ liệu (attributes) và hành vi (methods).
HƯớng thủ tục thì dựa trên hàm, khó scale lên lớn, dữ lieu tách rời ít đóng gói thường dung cho các chương trình nhỏ thao tác đơn giản

2. Giải thích 4 nguyên tắc cơ bản OOP: Encapsulation, abstraction, Inheritance, Polymorphism

Encapsulation (Đóng gói): Giấu thông tin bên trong đối tượng, chỉ cho phép truy cập thông qua các phương thức được định nghĩa.
Inheritance (Kế thừa): Lớp con có thể kế thừa thuộc tính và phương thức từ lớp cha, giúp tái sử dụng mã nguồn.
Polymorphism (Đa hình): Cùng một phương thức có thể hoạt động theo nhiều cách khác nhau, tùy thuộc vào lớp đối tượng.
Abstraction (Trừu tượng hóa): Che giấu các chi tiết không cần thiết, chỉ hiển thị những cái interface đơn giản dễ hiểu cho người dùng

3. Interface và abstract Class khác nhau như thế nào?

- Answer: Abstract Class thì định nghĩa những đặc điểm tính chất rất rất chung cho các đối tượng -> mỗi class chỉ extend đc 1 abstract
Trong khi đó thì Interface sẽ định nghĩa những hành vi chung mà các class cần thực hiện -> 1 class có thể imple dược nhiều interface

4. Một class có thể kế thừa nhiều class cùng lúc không? nếu không thì tại sao?

-Không vì khi kế thừa tức là nó đã thừa hưởng những đặc điểm rất đặc thù theo class đó nên không thể nào thừa kế them của class khác nữa

5. Làm thế nào để đảm bảo một class tuân theo nguyên tắc Encapsulation
- Đặt các thuộc tính (fields) ở mức truy cập private
-Cung cấp phương thức get set để truy cập biến
- sử dung final để dữ lieu không thay đổi

6. Tại sao nên sử dụng getter và setter thay vì truy cập trực tiếp biến instance?
- Answer: để đảm bảo nguyên tắc Encap, sau này khi scale lên rộng thì dễ sửa đổi, giúp program linh hoạt hơn

7. Một class có thể vừa là abstract class và là interface được không? Tại sao Java không hỗ trợ đa kế thừa với class, nhưng lại hỗ trợ đa kế thừa với interface?
-Không thể vì abstract có thể có phương thức có than còn interface thì k
-Không vì khi kế thừa tức là nó đã thừa hưởng những đặc điểm rất đặc thù theo class đó nên không thể nào thừa kế them của class khác nữa
còn interface chỉ là nhưungx hành vi nên hoàn toàn có thể 1 class thực hiện nheièu hành vi

8. Khi nào sử dụng abstract class thay vì interface?
- Answer:Cần cung cấp một bộ khung chung cho các lớp con

9. Sự khác nhau giữa method overloading và method overriding
- Answer:overloading là cùng tên nhưng khác param truyền vào
overriding tức là thừa kế lại hoàn toàn method đó nhưng ở 1 class khác

10. Có thể override một phương thức static không? về mặt ý nghĩa thì tại sao?
-Không vì + phương thức static không thuộc về object và thuộc class dẫn đến nó không đảm bảo tính đa hình

11. Garbage Collection hoạt động như thế nào trong Java? Làm sao để buộc hệ thống thực hiện thu gom rác?
-  Garbage Collection (GC) là quá trình tự động giải phóng bộ nhớ bằng cách thu hồi các đối tượng không còn được tham chiếu để tránh rò rỉ bộ nhớ 
Dùng System.gc(), Runtime.getRuntime().gc()