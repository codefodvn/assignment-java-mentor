
# Week 3: Java basic

1. Sự khác biệt giữa dữ liệu primitive và non-primitive trong Java là gì?

-primitive: là kiểu dữ lieu nguyên thủy và ược lưu trực tiếp vào bộ nhớ stack
-non-primitive: là kiểu dữ lieu không nguyên thủy,dđược xem như là 1 object và lưu ở heap
, có them nhiều phương thức đi kèm
2. tại sao String là non-primitive type trong khi có vẻ nó chỉ chứa kí tự?

- Answer: vì String là immutable và được lưu ở heap

3. Sự khác biệt giữa int và Integer là gì? khi nào nào dùng cái nào?

- Answer:sự khác biệt là lưu trữ, phương thức đi kèm, hiệu suất
Nên dung int khi program cần hiệu suất cao và đơn giản
Dùng Integer khi cần sự linh hoạt, có thể nhận giá trị null và có thể liên quan đến collection thì nên dung Integer

4. Auto-boxing và unboxing là gì? Khi nào chúng xảy ra?

-Auto-boxing là quá trình tự động chuyển đổi kiểu dữ liệu nguyên thủy (primitive) thành đối tượng của lớp bao bọc (Wrapper class)
- Unboxing thì ngược lại từ Wrapper class ->> primitive
Nên sử dung auto khi cần sử dung trong collection, khi gán 1 biến vào Wrapper class
nên sử dung unbox khi thực hiện các phép toán đơn giản, khi phương thức cần kiểu nguyên thủy

5. Sự khác biệt giữa mảng tĩnh (Array) và danh sách động (ArrayList) trong Java?- Answer:
-Array thì khởi tạo phải tĩnh, hiệu suất cao hơn, hỗ trợ kiểu nguyên thủy, ít tốn bộ nhớ hơn
-ArrayLIst thì chậm hơn nhưng có thể thay đổi kích thước, là 1 lớp trong util, them xóa phần tử liinh hoạt, xử dung nhiều bộ nhớ hơn do quản lí rộng

6. Sự khác biệt giữa String, StringBuffer, và StringBuilder?
- String thì là đối tượng không thể thay đổi
Do đó cần đến StringBuilder để có thể thay đổi dữ lieu chuỗi trong 1 đối tượng
-> StringBuilder không có safe thread nên được dung trong 1 thread và lưu ở stack
còn StringBuffer thì có thể dung trong nhiều thread, có thể chậm hơn chút so với String builder

7. Giải thích về String Pool trong Java? Tại sao Java sử dụng String Pool?
- String pool được lưu trữ ở trong Heap memory, String pull được Java sử dung để tránh phải tạo nhiều đối tượng going nhau
VD:String a = "minh"; String b = "minh"  lần tạo b thì chỉ cần tham chiếu đến "minh"ở trong string pool

8. String là immutable, điều này có nghĩa gì? Tại sao String được thiết kế theo cách này?
- Nó có nghĩa là String là 1 đối tượng không thể thay đổi value
- được thiết kế như vậy vì có thẻ làm khóa ngoai, đảm bảo an toàn trong xử lí đa luồng

9. intern() method trong String làm gì?
- Để kiểm tra xem chuỗi đã có trong String pool chưa, nếu chưa thì nó sẽ tạo mới trong đó còn rồi thì nó chỉ cần tham chiếu đến

10. Chuyện gì xảy ra nếu thay đổi một giá trị của String được tạo bằng String literal?
- Answer:nó sẽ tạo một đối tượng mới...

11. Toán tử == và .equals() khác nhau thế nào khi so sánh Object?
- Answer: equals là phép toán tương tự như ==, nhưng nó có thể override lại bởi nhiều class để giúp so sánh chat chẽ hơn
== so sánh value trong memory, mà đối tượng thì lưu value ở dạng địa chỉ => lưu dạng ref như với primitive thì nó sẽ so sánh value luôn 


12. Tại sao 
double a = 0.1
double b = 0.2
boolean check = (a + b) == 0.3
tại sao check lại bằng false?
kiểu dữ lieu double sẽ chỉ lấy giá trị gần = như là 0.3000000000004 biểu diễn theo dạng nhị phân => nó không có value == 0.3
Nếu double có nhược điểm như vậy tại sao nó vẫn tồn tại
- Answer:  Do nó chỉ tốn dung lượng ít nhưng hiệu suất tính toán lại cao, độ sai thì rất nhỏ
