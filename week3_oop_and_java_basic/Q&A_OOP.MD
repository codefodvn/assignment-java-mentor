
# Week 3: OOP

1. Lập trình hướng đối tượng là gì? So sánh với hướng lập trình thủ tục

- Answer: Lập trình hướng đối tượng sử dụng class và object để viết chương trình, trong khi hướng thủ tục là thực hiện tuần tự từng dòng code 1. Mà OOP mang theo 4 nguyên tắc cơ bản cho nên sẽ thuận tiện và tối ưu hơn.

2. Giải thích 4 nguyên tắc cơ bản OOP: Encapsulation, abstraction, Inheritance, Polymorphism

- Answer:
        Encapsulation: sử dụng từ khoá private,public,protected hoặc default để quy định phạm vi hoạt động của biến/method/class
        Abstraction: class chỉ giữ lại những cái chung nhất, loại bỏ những cái riêng và không có instace
        Inheritance: các class con có thể sử dụng lại các method đã viết ở class cha ( tạo ra sự phân cấp) 
        Polymorphism: @Overriding và Overloading ( method cùng tên nhưng khác tham số)


3. Interface và abstract Class khác nhau như thế nào?

- Answer: Interface chỉ giữ lại tên method, tức là chỉ định nghĩa các phương thức của class để đảm bảo là class sẽ có những phương thức nào, trong khi Abstract class tạo ra 1 khuôn mẫu ( bao gồm biến, method đã đc viết sẵn hoặc chưa) và các lớp con có thể viết lại phần thân của method nào đó trong lớp cha .

4. Một class có thể kế thừa nhiều class cùng lúc không? nếu không thì tại sao?

- Answer:Không đa kế thừa được, vì nó sẽ gây ra sự mơ hồ khi định nghĩa lớp con ( 2 lớp cha đều có method void a() thì không biết void a() này sẽ là của lớp cha nào)

5. Làm thế nào để đảm bảo một class tuân theo nguyên tắc Encapsulation
- Answer: sử dụng các từ khoá hợp lý ( ví dụ private với biến hoặc protected với biến của lớp cha,  public với method hoặc nếu method chỉ dùng trong class thì xài private, tương tự với inner class)

6. Tại sao nên sử dụng getter và setter thay vì truy cập trực tiếp biến instance?
- Answer:để đảm bảo tính đóng gói, tránh sử dụng biến instance sẽ k gây ra lỗi ở mã nguồn, ngoài ra dùng getter setter có thể kiểm soát đầu vào,... tốt hơn

7. Một class có thể vừa là abstract class và là interface được không? Tại sao Java không hỗ trợ đa kế thừa với class, nhưng lại hỗ trợ đa kế thừa với interface?
- Answer: abstract khác interface. 1 class chỉ hỗ trợ kế thừa 1 lớp cha ( giải thích ở trên). Interface thì ko có biến instance, nếu gọi trùng method với 1 hay 2 interface cha thì java yêu cầu override nên sẽ k gây ra xung đột.

8. Khi nào sử dụng abstract class thay vì interface?
- Answer: sử dụng abstract khi cần 1 khuôn mẫu cho nhiều class có chung đặc điểm ( ví dụ abstrac person -> employee, customer). Còn dùng interface khi tạo ra 1 khuôn các method cần có ( ví dụ interface service thì có add(), update())

9. Sự khác nhau giữa method overloading và method overriding
- Answer:Overloading : nhiều method cùng tên nhưng khác tham số
        Overriding: ghi đè lại method từ lớp cha

10. Có thể override một phương thức static không? về mặt ý nghĩa thì tại sao?
- Answer: không ghi đè đc. Về mặt ý nghĩa thì static thuộc về class, mà override thuộc về tính chất của OOP ( object) nên 2 cái này khác hoàn toàn nhau

11. Garbage Collection hoạt động như thế nào trong Java? Làm sao để buộc hệ thống thực hiện thu gom rác?
- Answer: GB dọn các biến k dc tham chiếu đến hoặc đc gán là null, các object bị thay thế bới object khác, hoặc biến local khi method kết thúc. để GB buộc thu gom rác: System.GC();
