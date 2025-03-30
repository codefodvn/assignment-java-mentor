#WEEK3: JAVA BASIC
1.Sự khác biệt giữa dữ liệu primitive và non-primitive trong Java là gì?
*Dữ liệu primitive (như int, float, char, boolean,...) là các kiểu dữ liệu cơ bản, được định nghĩa sẵn trong Java. Chúng lưu trữ giá trị trực tiếp trong bộ nhớ stack, không có phương thức hay thuộc tính đi kèm, và kích thước cố định.
*Dữ liệu non-primitive (như String, Array,...) là các đối tượng, được lưu trữ dưới dạng tham chiếu trong heap. Chúng có thể chứa dữ liệu phức tạp, hỗ trợ các phương thức, và kích thước không cố định. Sự khác biệt chính nằm ở cách lưu trữ và khả năng mở rộng chức năng.
2.Tại sao String là non-primitive type trong khi có vẻ nó chỉ chứa ký tự?
*Mặc dù String biểu diễn chuỗi ký tự, nó không phải là kiểu primitive mà là một lớp (java.lang.String). Điều này có nghĩa nó là một đối tượng, chứa không chỉ dữ liệu ký tự mà còn các phương thức như length(), substring(), toLowerCase(),.... String được lưu trữ dưới dạng tham chiếu đến một mảng ký tự (char[]) trong heap, không phải giá trị trực tiếp như char.
3.Sự khác biệt giữa int và Integer là gì? Khi nào dùng cái nào?
*int là kiểu primitive, lưu giá trị số nguyên trực tiếp (32-bit, phạm vi -2³¹ đến 2³¹-1), không phải đối tượng, nên không có phương thức và chiếm ít bộ nhớ hơn.
*Integer là một wrapper class (non-primitive), bao bọc int trong một đối tượng, cung cấp các phương thức như parseInt(), toString(), và hỗ trợ giá trị null.
*Khi nào dùng: Dùng int khi ưu tiên hiệu suất và không cần tính năng của đối tượng (ví dụ: tính toán đơn giản). Dùng Integer khi cần đối tượng, chẳng hạn trong Collections (ArrayList<Integer> không chấp nhận int), hoặc khi cần null để biểu thị giá trị chưa xác định.
4.Auto-boxing và unboxing là gì? Khi nào chúng xảy ra?
*Auto-boxing: Là quá trình Java tự động chuyển đổi từ kiểu primitive sang wrapper class tương ứng (ví dụ: int → Integer). Ví dụ: Integer x = 5; (Java tự động gọi Integer.valueOf(5)).
*Unboxing: Là quá trình ngược lại, chuyển từ wrapper sang primitive (ví dụ: Integer x = 5; int y = x; → Java tự động gọi x.intValue()).
*Khi nào xảy ra: Xảy ra tự động trong các phép gán, truyền tham số hàm, hoặc khi sử dụng toán tử với sự kết hợp giữa primitive và wrapper. Điều này giúp mã nguồn linh hoạt hơn nhưng có thể ảnh hưởng hiệu suất nếu lạm dụng.
5.Sự khác biệt giữa mảng tĩnh (Array) và danh sách động (ArrayList) trong Java?
*Array: Là cấu trúc dữ liệu tĩnh, kích thước được xác định khi khởi tạo (int[] arr = new int[5]) và không thể thay đổi. Nó nhanh hơn do truy cập trực tiếp qua index, nhưng thiếu linh hoạt.
*ArrayList: Là lớp trong java.util, có kích thước động (tự động mở rộng khi cần), hỗ trợ thêm/xóa phần tử dễ dàng (add(), remove()). Tuy nhiên, nó chậm hơn Array vì phải quản lý kích thước và sao chép dữ liệu khi mở rộng.
* -> Array phù hợp khi kích thước cố định và cần hiệu suất cao; ArrayList dùng khi cần tính linh hoạt.
6.Sự khác biệt giữa String, StringBuffer, và StringBuilder?
*String: Là immutable (không thay đổi được), mỗi thao tác như nối chuỗi (+) tạo ra một đối tượng mới, hiệu quả thấp khi thay đổi nhiều.
*StringBuffer: Là mutable (có thể thay đổi), thread-safe nhờ đồng bộ hóa (synchronized methods), phù hợp khi nhiều luồng truy cập cùng lúc, nhưng chậm hơn do chi phí đồng bộ.
*StringBuilder: Cũng mutable, nhưng không thread-safe, nhanh hơn StringBuffer vì không có đồng bộ hóa, thích hợp cho ứng dụng một luồng.
*->String cho chuỗi cố định, StringBuilder cho thay đổi trong một luồng, StringBuffer cho đa luồng.
7.Giải thích về String Pool trong Java? Tại sao Java sử dụng String Pool?
*String Pool: Là một khu vực đặc biệt trong heap (thuộc Java Memory Pool), lưu trữ các String literal (chuỗi khai báo trực tiếp như "hello"). Khi một literal được tạo, Java kiểm tra Pool: nếu đã tồn tại, nó trả về tham chiếu cũ thay vì tạo mới.
*Lý do sử dụng: Tiết kiệm bộ nhớ bằng cách tái sử dụng chuỗi giống nhau, tăng hiệu suất khi so sánh chuỗi bằng == (so sánh tham chiếu), và đảm bảo tính nhất quán trong ứng dụng.
8.String là immutable, điều này có nghĩa gì? Tại sao String được thiết kế theo cách này?
*Một khi đối tượng String được tạo, nội dung của nó không thể thay đổi (ví dụ: "abc" không thể thành "abcd" trực tiếp). Thay đổi tạo ra một String mới.
*Lý do thiết kế:
An toàn: Không thể sửa đổi ngoài ý muốn trong môi trường đa luồng.
Tối ưu String Pool: Chuỗi không đổi đảm bảo Pool hoạt động hiệu quả.
Bảo mật: Ngăn chặn thay đổi chuỗi trong các cấu hình nhạy cảm (như tên file, URL).
9.intern() method trong String làm gì?
*Kiểm tra xem chuỗi hiện tại có trong String Pool chưa. Nếu có, trả về tham chiếu từ Pool, nếu không, thêm chuỗi vào Pool và trả về tham chiếu mới. Ví dụ: String s = new String("hello").intern(); sẽ tham chiếu đến "hello" trong Pool thay vì đối tượng mới trong heap. Dùng để tối ưu bộ nhớ hoặc khi cần so sánh chuỗi bằng ==.
10.Chuyện gì xảy ra nếu thay đổi một giá trị của String được tạo bằng String literal?
*Vì String là immutable, bạn không thể thay đổi trực tiếp giá trị của literal (như "hello"). Khi thực hiện thao tác như "hello" + "world", Java tạo một String mới ("helloworld") trong heap hoặc Pool (nếu dùng intern()), còn "hello" gốc vẫn giữ nguyên trong Pool. Điều này đảm bảo tính toàn vẹn của String Pool.
11.Toán tử == và .equals() khác nhau thế nào khi so sánh Object?
*==: So sánh tham chiếu (kiểm tra hai biến có trỏ cùng địa chỉ bộ nhớ không). Với Object, chỉ đúng nếu là cùng một instance.
*.equals(): So sánh nội dung, nhưng phụ thuộc vào cách lớp ghi đè phương thức này. Mặc định (Object), .equals() giống ==, nhưng với String, nó so sánh giá trị ký tự.
*Ví dụ: String a = "huy"; String b = "huy"; → a == b (true, cùng Pool); String c = new String("huy"); → a == c (false), nhưng a.equals(c) (true).
12.Tại sao double a = 0.1, double b = 0.2, boolean check = (a + b) == 0.3 lại false? Nếu double có nhược điểm như vậy tại sao nó vẫn tồn tại?
*Lý do false: double dùng biểu diễn nhị phân IEEE 754, không thể lưu chính xác tất cả số thập phân (ví dụ: 0.1 và 0.2 là xấp xỉ). Khi cộng 0.1 + 0.2, kết quả thực tế là khoảng 0.30000000000000004, không phải 0.3, nên == trả về false.
*Tại sao tồn tại:
double cung cấp phạm vi lớn (64-bit) và hiệu suất cao cho tính toán.
Sai số nhỏ chấp nhận được trong nhiều ứng dụng thực tế.
Có giải pháp thay thế như BigDecimal cho độ chính xác tuyệt đối, nhưng chậm hơn và phức tạp hơn.
*->Dùng Math.abs((a + b) - 0.3) < epsilon (với epsilon nhỏ) để so sánh số thực.