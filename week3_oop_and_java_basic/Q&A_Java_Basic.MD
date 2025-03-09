
# Week 3: Java basic

1. Sự khác biệt giữa dữ liệu primitive và non-primitive trong Java là gì?

- Answer:primitive lưu ở bất kì đâu ( static thì lưu ở method area, local variable thì lưu ở stack, variable của object thì lưu ở heap chung với object đó) trong khi non-primitive thì lưu ở heap và stack lưu reference
primitive lưu giá trị trong khi non-primitice lưu địa chỉ tham chiếu.

2. tại sao String là non-primitive type trong khi có vẻ nó chỉ chứa kí tự?

- Answer:String là 1 class của java.lang. Ngoài ra String có nhiều method để xử lý dữ liệu ( concat(),...) và nó đc lưu ở heap. 

3. Sự khác biệt giữa int và Integer là gì? khi nào nào dùng cái nào?

- Answer:int lưu giá trị, Integer lưu tham chiếu. khi cần tạo biến chỉ lưu dữ liệu thì xài int, còn Integer thì có nhiều method để xử lý giá trị mà int ko có, và Integer xài đc với generic

4. Auto-boxing và unboxing là gì? Khi nào chúng xảy ra?

- Answer:Auto boxing là từ primitive -> Wrapper và unboxing thì ngược lại 
  Ví dụ:
      ArrayList<Double> list = new ArrayList();
      list.add(5); // Auto-boxing double -> Double
      System.out.println(list.get(5); // Unboxing Double -> double

5. Sự khác biệt giữa mảng tĩnh (Array) và danh sách động (ArrayList) trong Java?- Answer:
- Answer: Array là cố định size xài , ArrayList thì size tự động và phải xài non-primitive

6. Sự khác biệt giữa String, StringBuffer, và StringBuilder?
- Answer:
  String : immutable, thay đổi là tạo object mới
  String buffer: thay đổi là đổi giá trị , có threadsafe, thích hợp chạy đa luồng
  String builder: cũng như String buffer nhma k có threadsafe nên thích hợp chạy đơn luồng, đổi lại thì chạy nhanh hơn 2 cái kia

7. Giải thích về String Pool trong Java? Tại sao Java sử dụng String Pool?
- Answer:String pool chứa toàn bộ object String có sẵn. khi khởi tạo thì nó k cần tạo đối tượng mới mà lấy trong String pool xài lại. Stringpool giúp giảm các object trùng lặp -> tăng hiệu suất, tối ưu bộ nhớ

8. String là immutable, điều này có nghĩa gì? Tại sao String được thiết kế theo cách này?
- Answer: là khi giá trị String thay đổi, nó sẽ tạo ra 1 đối tượng mới thay vì sửa cái đối tượng trc đó. String đảm bảo thread-safe, để k gây xung đột khi nhiều thread chỉnh sửa 1 String, và String có thể kết hợp với String pool như câu trên.

9. intern() method trong String làm gì?
- Answer:Chuyển Object đc khởi tạo = new String("") -> vào trong String pool 

10. Chuyện gì xảy ra nếu thay đổi một giá trị của String được tạo bằng String literal?
- Answer: thì nó sẽ tạo 1 object mới trong String pool

11. Toán tử == và .equals() khác nhau thế nào khi so sánh Object?
- Answer: .equals() là method đc viết sẵn và có thể override tuỳ thuộc vào cách sử dụng, == là so sánh địa chỉ. Nếu k override .equals() thì k khác gì ==

12. Tại sao 
double a = 0.1
double b = 0.2
boolean check = (a + b) == 0.3
tại sao check lại bằng false?
Nếu double có nhược điểm như vậy tại sao nó vẫn tồn tại
- Answer: vì double lưu giá trị dưới dạng nhị phân, thì nó sẽ kiểu 0.000010000......( vòng lặp vô hạn). Giống như 1 cái là 0.111... + 0.222... != 0.3333 vì có vô hạn số ở giấu ...
mặc dù nhược điểm là vậy nhưng nó vẫn đc sử dụng vì nhanh, tiết kiệm bộ nhớ và đủ xài 
