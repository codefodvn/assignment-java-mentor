# Week 2: How to Java Work

1. JDK, JVM, và JRE khác nhau như thế nào? Vai trò của từng thành phần trong hệ sinh thái Java?

    Answer: 
-JVM: là máy ảo để chạy Java, chủ yếu dung để chuyển đổi bytecode sang mã máy
-JRE: Bao gồm cả JVM và tổng hợp thư viện phù hợp với máy tính,cung cấp môi trường thực thi Java
-JDK: Là công cụ phát triển Java, bao gồm JRE, compiler(javac),.. bao gồm rất nhiều thứ,các tool cần cho phát triển Java 

2. JDK có những công cụ nào quan trọng cho lập trình viên Java?

    Answer:
-Compiler: biên dich mã nguôn thanh byte code để JVM chay
-Jar: nen cac class sang dinh dang jar
-javadoc: Tạo tài liệu API từ các comment trong mã nguồn Java
-jdb: Java Debugger
- jmap: Cung cấp thông tin về bộ nhớ heap của JVM.
...
3. JVM hoạt động như thế nào khi bạn chạy một chương trình Java?

    Answer: 
-Đầu tiên thì tgrình biên dịch (javac) sẽ biên dịch file code thành bytecode
-Sau đó kiểm tra tính hợp lệ của bytecode
-Liên kết (Linking) các class cần thiết.
-JVM sử dụng Interpreter vvà JIT để dịch từng lệnh bytecode sang mã máy và thực thi.
-JVM cấp phát bộ nhớ Heap, Stack, Method Area..

4. Có những thành phần quan trọng nào bên trong JVM?

	Answer:
-Class Loader: Thành phần code lõi JVM cho phép JAVA thực hiện dynamic loading: cách ly mã nguồn của mình, đảm bảo security
-Runtime Data Areas: 
 +Method Area: lưu tĩnh, ít bị thay đổi(static,..)
 +Heap: quản lý các object
 +Stack Area: quản lý local variable...
 +PC(Programming Counter) Registers: đánh dấu dòng code tiếp theo mình thực thi
 +Native Method Stack: lưu các function viết = mã máy (có thể code  = ngôn ngữ khác)
-Execution Engine:
 + Interpreter: Thông dịch (bytecode từng dòng 1 sang mã máy nên chậm hơn C/C++)
 + Compiler: biên dịch ngôn ngữ (toàn bộ bytecode sang mã máy)

5. JVM quản lý bộ nhớ như thế nào? Các vùng nhớ quan trọng trong JVM là gì?

JVM có cơ chế quản lý bộ nhớ tự động, bao gồm cấp phát và thu hồi bộ nhớ thông qua Garbage Collector.
Các vùng nhớ quan trọng trong JVM:
 +Method Area: lưu tĩnh, ít bị thay đổi(static,..)
 +Heap: quản lý các object
 +Stack Area: quản lý local variable...
 +PC(Programming Counter) Registers: đánh dấu dòng code tiếp theo mình thực thi
 +Native Method Stack: lưu các function viết = mã máy (có thể code  = ngôn ngữ khác)


6. Class Metadata (Method Area) trong JVM có vai trò gì?

	-Lưu trữ thông tin class: runtime constant pool,
	-Chia sẻ các thread
	-Lưu metadata của class, biến static

7. PermGen và Metaspace khác nhau như thế nào? Tại sao Java chuyển từ PermGen sang Metaspace từ Java 8?

        -PermGen thì nằm trong Heap còn Metaspace thì nằm ngoài
	-PermGen không có khả năng mở rộng, không tối ưu
	-Metaspace giúp cho GC hoạt động tối ưu  hơn
	-String pool đc tách ra khỏi PermGen đi vào heap giúp quản lí tốt hơn

8. Heap và Stack trong JVM khác nhau như thế nào?

    Heap: Là vùng nhớ lớn nhất trong JVM
	  CHứa tất cả các object và dữ lieu toàn cục
	  ĐƯợc quản lí bởi GC
	  CHia sẻ các thread
    Stack: mỗi thread 1 stack
	   Quản lý theo cơ chế LIFO (Last In, First Out)
	   Không bị ảnh hưởng bởi Garbage Collector.
	   Dùng để lưu trữ frame, mỗi frame chứa local.
	   Hỗ trợ PC

10. Garbage Collection trong JVM là gì? Tại sao nó quan trọng?

    Garbage Collection (GC) trong JVM là quá trình tự động thu hồi bộ nhớ của các đối tượng không còn được tham chiếu, 
	giúp quản lý bộ nhớ hiệu quả mà không cần lập trình viên giải phóng thủ công
    Nó quan trọng vì:
	- GC tự động giải phóng bộ nhớ của object không còn dùng, tránh lãng phí tài nguyên.
	-JVM tối ưu việc thu hồi bộ nhớ để tránh ứng dụng bị quá tải hoặc hết bộ nhớ.
	- Không cần quản lý bộ nhớ thủ công như C/C++.
	-Nếu không có GC, Heap có thể đầy do quá nhiều object rác.

11. Những thuật toán chính của Garbage Collection mà JVM sử dụng là gì?
	-Mark and Sweep: Duyệt qua toàn bộ đối tượng trong heap, đánh dấu những đối tượng đang được tham chiếu. Xóa bỏ những đối tượng không được đánh dấu, giải phóng bộ nhớ
	-Copying: Chia heap thành hai vùng: From-space và To-space. Khi thu gom rác, chỉ giữ lại các đối tượng còn sống và sao chép chúng từ From-space sang To-space.  
	-Mark-Compact: Kết hợp Mark and Sweep với việc Compact (gộp vùng nhớ), Sau khi xóa đối tượng chết, di chuyển các đối tượng còn sống để giảm phân mảnh bộ nhớ.
	-G1 (Garbage-First) Garbage Collector: Chia heap thành nhiều Region nhỏ thay vì Young/Old. Ưu tiên thu gom rác ở các vùng có nhiều đối tượng chết nhất để tối ưu hiệu suất.
12. JVM có những loại Garbage Collector nào? Khác nhau ở điểm nào?
	-CÓ 5 loại Garbage Collector: -Serial GC Thu gom bằng thuật toán Mark-Sweep-Compact cho Old Generation.
- Parallel GC: Thu gom rác trong Young Generation bằng thuật toán Copying GC.
-  Garbage First (G1) GC:Old Generation được thu gom bằng Concurrent Mark-Sweep, Ưu tiên thu gom vùng chứa nhiều rác nhất trước (Garbage-First).
 - Z Garbage Collector (ZGC): Hầu hết công việc GC được thực hiện song song với luồng ứng dụng 
 - Shenandoah GC: Tương tự ZGC, thực hiện thu gom đồng thời với luồng ứng dụng.

13. Sự khác biệt giữa Minor GC, Major GC và Full GC là gì?
	-Minor GC xảy ra trong Young Generation (Eden + Survivor Spaces): dọn dẹp bộ nhớ bằng cách loại bỏ các đối tượng  trong Young Generation ->>> Các đối tượng còn sống sẽ được di chuyển sang vùng Survivor hoặc Old Generation
        -Major GC thu gom rác trong Old Generation, Sử dụng các thuật toán như Mark-Sweep-Compact hoặc Concurrent Mark-Sweep 
	-Full GC là quá trình thu gom toàn bộ heap, bao gồm cả Young Generation + Old Generation + MetaSpace. Xảy ra khi bộ nhớ gần đầy hoặc khi JVM không thể phân bổ thêm bộ nhớ cho các đối tượng mới.

14. Bộ nhớ Stack có bị thu hồi bởi Garbage Collector không? Nếu không, nó được quản lý như thế nào?

 	-bộ nhớ Stack không bị thu hồi bởi Garbage Collector
	-Mỗi luồng (Thread) trong JVM có một Stack riêng biệt.
	-Khi một phương thức được gọi, JVM tạo Stack Frame chứa dữ liệu của phương thức đó.
	-Khi phương thức kết thúc, Stack Frame bị xóa ngay lập tức, giải phóng bộ nhớ theo cơ chế LIFO
15. Làm thế nào để tránh Memory Leak trong Java?

	-Giải phóng các đối tượng không cần thiết
	-Tránh sử dụng biến static không cần thiết
	-Đóng các tài nguyên sau khi sử dụng
	-Tránh lưu trữ không cần thiết trong Collection
16. JIT Compiler trong JVM là gì? Nó cải thiện hiệu năng như thế nào?

-JIT (Just-In-Time Compiler) là một bộ biên dịch động trong JVM giúp chuyển mã bytecode (Java bytecode) thành mã máy thực thi trực tiếp tại runtime.
-Nếu một phương thức nào đó được gọi hàng nghìn lần, JIT sẽ phát hiện điều này và biên dịch toàn bộ phương thức thành mã máy, giúp JVM chạy nhanh hơn so với việc diễn giải từng dòng.

17. Khi nào JIT Compiler dịch mã bytecode sang mã máy thực thi?

   Khi thấy rang một phương thức được gọi lại nhiều lần thì JIT sẽ dịch mã

18. Khi ứng dụng Java chạy chậm, bạn sẽ kiểm tra gì đầu tiên?

    Kiểm tra GC hoạt động nhưu thế nào, có bị lạm dung chạy quá nhiều không

20. Làm thế nào để debug lỗi liên quan đến bộ nhớ Heap và Stack?

    JVM ném lỗi java.lang.OutOfMemoryError: Java heap space.
    Ứng dụng bị crash ngay lập tức với lỗi java.lang.StackOverflowError.
    còn debug thì e chỉ mới biết lệnh nào mà load lâu thì lỗi ở dòng đó thôi ạ.