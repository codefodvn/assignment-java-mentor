#WEEK3_OOP
1.Lập trình hướng đối tượng là gì? So sánh với hướng lập trình thủ tục
*Lập trình hướng đối tượng (OOP): Là mô hình lập trình dựa trên khái niệm "đối tượng", kết hợp dữ liệu (thuộc tính) và hành vi (phương thức) trong một thực thể. OOP tập trung vào việc mô phỏng thế giới thực, sử dụng các nguyên tắc như encapsulation, inheritance, polymorphism, abstraction.
*Lập trình thủ tục: Là mô hình lập trình tuyến tính, tập trung vào các hàm/procedure thao tác trên dữ liệu, dữ liệu và logic thường tách biệt (ví dụ: C).
*So sánh:
OOP tổ chức mã theo đối tượng, dễ bảo trì và mở rộng hơn; thủ tục tổ chức mã theo hàm, đơn giản hơn cho chương trình nhỏ.
OOP hỗ trợ tái sử dụng (inheritance), thủ tục không có cơ chế này.
Thủ tục thường nhanh hơn do không có chi phí quản lý đối tượng, nhưng khó quản lý trong dự án lớn.
2.Giải thích 4 nguyên tắc cơ bản OOP: Encapsulation, Abstraction, Inheritance, Polymorphism
*Encapsulation (Đóng gói): Ẩn chi tiết triển khai bên trong, chỉ cung cấp giao diện ra ngoài. Thực hiện bằng cách đặt biến instance là private và sử dụng getter/setter. Giúp bảo vệ dữ liệu.
*Abstraction (Trừu tượng): Che giấu sự phức tạp, chỉ hiển thị những gì cần thiết. Dùng qua abstract class hoặc interface.
*Inheritance (Kế thừa): Cho phép một lớp (subclass) thừa hưởng thuộc tính và phương thức từ lớp khác (superclass), tăng khả năng tái sử dụng mã (dùng từ khóa extends).
*Polymorphism (Đa hình): Một hành vi có thể được thực hiện theo nhiều cách khác nhau. Gồm:
Compile-time: Qua method overloading (cùng tên, tham số khác).
Runtime: Qua method overriding (ghi đè phương thức từ lớp cha).
3.Interface và abstract Class khác nhau như thế nào?
*Interface:
Chỉ chứa phương thức trừu tượng (abstract methods, từ Java 8 có thể có default và static methods).
Không có biến instance, chỉ có hằng số (public static final).
Một lớp có thể triển khai nhiều interface (implements).
*Abstract Class:
Có thể chứa cả phương thức trừu tượng và cụ thể (có thân hàm).
Có thể có biến instance và constructor.
Một lớp chỉ kế thừa một abstract class (extends).
*Sự khác nhau giữa Interface và Abstract:
Methods: Class abstract có các phương thức abstract và non-abstract. Trong khi Interface chỉ có phương thức abstract, từ Java 8, thì Interface có thêm 2 loại phương thức là default và static.
Variables: Class abstract có thể có các biến final, non-final, static và non-static. Trong khi Interface chỉ có các biến static và final.
Implementation: Class abstract có thể implement các Interface. Trong khi Interface thì không thể implement class abstract.
Inheritance: Class abstract có thể kế thừa được một class khác. Trong khi Interface có thể kế thừa được nhiều Interface khác.
Accessibility: các thành viên trong Interface kiếu mặc định là public. Trong khi class abstract thì lại có thể là private, protected,...
4.Một class có thể kế thừa nhiều class cùng lúc không? Nếu không thì tại sao?
*Không, Java không hỗ trợ đa kế thừa với class (multiple inheritance).
*Lý do: Tránh vấn đề "Diamond Problem" - khi một lớp kế thừa từ hai lớp cha có cùng phương thức, gây mâu thuẫn về việc gọi phương thức nào. Ví dụ: Nếu Class A và Class B đều có methodX(), Class C kế thừa cả hai sẽ không biết dùng phiên bản nào.
*Java giải quyết bằng cách cho phép đa kế thừa qua interface, vì interface chỉ định nghĩa phương thức mà không triển khai, tránh xung đột.
5.Làm thế nào để đảm bảo một class tuân theo nguyên tắc Encapsulation?
*Đặt tất cả biến instance là private để ngăn truy cập trực tiếp từ bên ngoài.
*Cung cấp các phương thức công khai (public) như getter (lấy giá trị) và setter (thiết lập giá trị) để kiểm soát cách truy cập và thay đổi dữ liệu.
6.Tại sao nên sử dụng getter và setter thay vì truy cập trực tiếp biến instance?
*Kiểm soát truy cập: Getter/setter cho phép thêm logic (kiểm tra giá trị, giới hạn quyền sửa đổi).
*Ẩn chi tiết triển khai: Thay đổi cấu trúc bên trong class không ảnh hưởng mã bên ngoài.
*Tính linh hoạt: Có thể thay getter/setter bằng logic phức tạp (ví dụ: tính toán giá trị) mà không cần sửa mã dùng class.
7.Một class có thể vừa là abstract class và là interface được không? Tại sao Java không hỗ trợ đa kế thừa với class, nhưng lại hỗ trợ đa kế thừa với interface?
*Không, một class không thể vừa là abstract class vừa là interface. Trong Java, abstract class và interface là hai khái niệm riêng biệt: class là thực thể kế thừa được (extends), interface là hợp đồng triển khai được (implements).
*Không hỗ trợ đa kế thừa với class: Vì xung đột triển khai (Diamond Problem), gây khó khăn trong việc xác định hành vi thừa kế.
*Hỗ trợ đa kế thừa với interface: Interface chỉ định nghĩa phương thức (không triển khai, trừ default), nên không có xung đột cụ thể; lớp triển khai chịu trách nhiệm cung cấp body.
8.Khi nào sử dụng abstract class thay vì interface?
*Class abstract đại diện cho mối quan hệ "IS - A" (Huy là sinh viên)
Interface đại diện cho mối quan hệ "like - A" (Sinh viên có thể lập trình).
Tạo một class abstract khi đang cung cấp các hướng dẫn cho một class cụ thể.
Tạo Interface khi cung cấp các hành vi bổ sung cho class cụ thể và những hành vì này không bắt buộc đối với class đó.
9.Sự khác nhau giữa method overloading và method overriding
*Method Overloading:
Xảy ra trong cùng một lớp, cùng tên phương thức nhưng khác tham số (số lượng, kiểu).
Xác định tại compile-time (static polymorphism).
Ví dụ: add(int a, int b) và add(double a, double b).
Overloading là dạng Compiletime polymorphism còn Overriding là Runtime polymorphism
Overloading cần khác đối số còn Overriding khi muốn khác cách thực thi bên trong phương thức
Overloading thực hiện trong cùng 1 class còn Overriding thực hiện thông qua class kế thừa
*Method Overriding:
Xảy ra khi lớp con ghi đè phương thức của lớp cha, cùng tên, cùng tham số, cùng kiểu trả về.
Xác định tại runtime (dynamic polymorphism).
Ví dụ: Animal có sound(), Dog override thành bark().
*->Nếu chúng ta sai Overloading sẽ dễ nhận thấy vì chúng được quyết định vào thời điểm compile - các IDE sẽ báo lỗi nhưng nếu chúng ta sai Overriding vì quyết định vào thời điểm runtime - khi chương trình chạy nên sẽ gây ra nhiều vấn đề không dễ giải quyết
10.Có thể override một phương thức static không? Về mặt ý nghĩa thì tại sao?
*Không, không thể override phương thức static, nhưng có thể "ẩn" (hide) nó trong lớp con bằng cách định nghĩa lại cùng tên và tham số.
*Ý nghĩa:
Phương thức static gắn với lớp, không phải instance, nên không tham gia vào đa hình runtime (polymorphism). Override liên quan đến hành vi động của đối tượng, không áp dụng cho static.
Khi gọi phương thức static, Java dựa vào kiểu tham chiếu (reference type), không phải kiểu thực tế (actual object).
11.Garbage Collection hoạt động như thế nào trong Java? Làm sao để buộc hệ thống thực hiện thu gom rác?
*Cách hoạt động:
Garbage Collection (GC) là cơ chế tự động trong JVM, giải phóng bộ nhớ từ các đối tượng không còn được tham chiếu (unreachable).
JVM dùng thuật toán như Mark-and-Sweep: "Mark" đánh dấu các đối tượng còn dùng, "Sweep" xóa những đối tượng không được đánh dấu.
Các thế hệ bộ nhớ (Young, Old) và thuật toán tối ưu (G1, CMS) được áp dụng để tăng hiệu suất.
*Buộc GC hoạt động:
Không thể buộc GC chạy ngay lập tức, nhưng có thể gợi ý bằng System.gc() hoặc Runtime.getRuntime().gc().
JVM quyết định có chạy hay không tùy thuộc vào tài nguyên và trạng thái hệ thống.