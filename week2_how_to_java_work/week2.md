#WEEK 2:
##Câu 1: JDK, JVM, và JRE khác nhau như thế nào? Vai trò của từng thành phần trong hệ sinh thái Java?
*JDK (Java Development Kit): 
- Bộ công cụ phát triển phần mềm Java. Chúng bao gồm các công cụ để biên dịch, run và debug cho chương trình. Có rất nhiều version JDK được phát triển qua từng năm, đặc biệt là những bản big update có nhiều thay đổi về công nghệ (Java SE 17,...). JDK có các thành phần chính cơ bản như: JRE, JVM, compiler, Debugging tools,...
*JRE (Java Runtime Environment):
- Môi trường thực thi chương trình Java. Dùng để chạy các chương trình Java đã được compile, bao gồm những thành phần như: JVM, Runtime libraries (java.lang, java.util, java.io). JRE là lựa chọn cho end user và chỉ chạy khi đã được compile, khi cần run 1 app Java thì cần JRE để chạy chúng.
*JVM (Java Virtual Machine):
- Máy ảo Java. Cho phép chạy chương trình Java trên bất kì hệ sinh thái nào. Ví dụ thực tế là dùng khác hệ điều hành (Windows, macOS, Linux) nhưng chỉ cần JVM thì sẽ chạy được chương trình Java trên tất cả các hệ điều hành. Đây chính là minh chứng cho câu nói WORA - Write Once Run Anywhere. JVM có nhiệm vụ: 
+)Thực thi mã bytecode (tức mã đuôi .class) được tạo ra thông qua quá trình biên dịch từ trình biên dịch javac.
+)Quản lý bộ nhớ: Sử dụng Garbage Collection để giải phóng bộ nhớ khi không còn sử dụng
+)Tối ưu hiệu suất: Sử dụng JIT Compiler để biên dịch mã bytecode cho phù hợp với hệ điều hành và hardware
+)Đảm bảo tính độc lập nền tảng: JVM có từng version cho từng hệ điều hành khác nhau nhưng bản chất về mã bytecode thì giống nhau.
*Sự khác biệt chính của 3 thành phần này chính là chức năng. JDK dùng để phát triển và chạy app, JRE dùng để chạy app Java đã biên dịch và JVM dùng để chạy bytecode Java. Ngoài ra, còn khác nhau về đối tượng sử dụng: JDK dùng cho Dev, JRE dùng cho end user và JVM là hệ thống cốt lõi
*Mối quan hệ: JVM nằm trong JRE, JRE nằm trong JDK. JDK = JRE + tools, JRE = JVM + libraries, JVM = Công cụ thực thi bytecode.
##Câu 2: JDK có những công cụ nào quan trọng cho lập trình viên Java?
*Ngoài JVM, JRE thì JDK có 1 loạt các công cụ mạnh mẽ nằm trong thư mục bin của JDK khi tải về.
*a, javac - trình biên dịch Java:
- Biến mã nguồn Java (file .java) thành bytecode (file .class) để JVM chạy được
*b, java - trình chạy ứng dụng Java:
- Gọi JVM thực thi file bytecode đã biên dịch
*c, jar - công cụ đóng gói:
- Đóng gói các file bytecode và tài nguyên (image,...) thành file .jar. Được sử dụng để run các app lớn hoặc chia sẻ app với other dev, customer.
*d, javadoc - Tạo tài liệu API:
- Automation Generate tài liệu HTML từ comment trong code, dùng khi làm việc nhóm hoặc chia sẻ tài liệu. Tóm lại là giải thích code mà không cần phải đọc code.
*e, jdb - Trình gỡ lỗi (Debugger):
- Giúp tìm và sửa lỗi trong chương trình
*f, javap - Analysis file .class:
- Phân tích toàn bộ bên trong file .class để xem cấu trúc bytecode, danh sách hàm, các biến,... Được dùng để hiểu sâu hơn cách code Java được biên dịch, rất hữu ích khi tối ưu hiệu suất.
*j, jconsole - Giám sát app Java:
- Theo dõi hiệu suất chương trình (CPU, memory, thread) trong lúc chạy - tức là kiểm tra JVM. Dùng để check và fix trong trường hợp xảy ra các vấn đề về tối ưu hiệu suất
##Câu 3: JVM hoạt động như thế nào khi bạn chạy một chương trình Java?
*Khi tiến hành chạy chương trình Java, JVM tiến hành 1 loạt các bước:
a, Loading Process:
- Tải bytecode (Class loading):
+)Sử dụng ClassLoader Subsystem (gồm 3 loại) để tìm và tải các file bytecode vào bộ nhớ. Nếu không được tải đúng cách thì sẽ dẫn đến 1 lỗi "ác mộng" của dev -> ClassNotFoundException.
b, Linking Process:
- Verification: kiểm tra bytecode. Sau khi tải, JVM sẽ kiểm tra file bytecode về cú pháp, một số lỗi có thể mắc phải về out of range,...
- Prepareration: Memory Allocation. JVM cấp phát bộ nhớ trong Runtime Data Areas để lưu trữ dữ liệu chương trình.
c, Initialization Process:
- Resolution: JVM "kết nối" các tham chiếu mang tính biểu tượng (symbolic references) trong bytecode thành tham chiếu thực tế (actual references).
d, Execution: Chạy code bằng Interpreter/JIT Compiler.
e, Garbage Collection: Dọn dẹp bộ nhớ khi cần
##Câu 4: Có những thành phần quan trọng nào bên trong JVM?
*JVM gồm 3 thành phần quan trọng: ClassLoader, JVM Memory (Runtime Date Areas), Execution Engine. Còn cụ thể chúng kết hợp thế nào thì câu trả lời số 3 đã phân tích.
##Câu 5: JVM quản lý bộ nhớ như thế nào? Các vùng nhớ quan trọng trong JVM là gì?
*Đây là câu hỏi phân tích sâu về thành phần thứ 2 của JVM, tức Runtime Date Areas:
a, Method Area:
- Chức năng: Lưu trữ thông tin về các class, interface, phương thức, trường (fields), và hằng số (constants) trong suốt thời gian chạy. Đây cũng là nơi chứa Runtime Constant Pool (hồ chứa hằng runtime), lưu các tham chiếu biểu tượng và giá trị hằng số
- Quản lý bộ nhớ: kích thước linh hoạt, có thể thay đổi theo nhu cầu:
+)Có thể kiểm soát kích thước ban đầu, tối đa, tối thiểu.
+)Không yêu cầu bộ nhớ liên tục.
+)Method Area không động như Heap, nó được cấp phát khi class được tải (bởi ClassLoader) và chỉ giải phóng khi class không còn được sử dụng.
+)Nó không chứa đối tượng động, mà chỉ lưu metadata của class (tên class, phương thức, static fields,...).
+)Dung lượng Method Area có thể được cấu hình qua tham số -XX:MaxMetaspaceSize (trong Java 8+, thay thế PermGen từ Java 7).
- Là "bộ nhớ tĩnh" để JVM hiểu cấu trúc chương trình, nhưng nếu quá tải (do tải quá nhiều class) -> ném ra lỗi OutOfMemoryError: Metaspace.
b, Heap Area:
- Chức năng: : Là khu vực lớn nhất và động nhất, chứa tất cả các đối tượng và mảng được tạo bằng từ khóa new. Đây là nơi lưu trữ của các instance object và Arrays trong Java.
- Quản lý bộ nhớ: Kích thước linh hoạt, có thể thay đổi theo nhu cầu:
+)Có thể kiểm soát kích thước ban đầu, tối đa, tối thiểu.
+)Không yêu cầu bộ nhớ liên tục.
+)GC tự động thu hồi bộ nhớ: Garbage Collection (GC) hoạt động chủ yếu trên Heap, sử dụng các thuật toán như G1GC, CMS, hoặc Parallel GC để dọn dẹp đối tượng không còn tham chiếu.
+)Heap được chia thành các thế hệ (generations) để tối ưu GC:
  -)Young Generation: Chứa các đối tượng mới (thường ngắn hạn). Bao gồm Eden Space (nơi new object xuất hiện) và hai Survivor Spaces (S0, S1) để chuyển đối tượng còn sống qua lại.
  -)Old Generation (Tenured Generation): Chứa các đối tượng sống lâu, di chuyển từ Young sau nhiều lần GC.
  -)PermGen (trước Java 8) hoặc Metaspace (Java 8+): Đã chuyển sang Method Area, nhưng đôi khi vẫn được nhắc đến trong ngữ cảnh cũ.
- Heap là nơi chứa dữ liệu động, và hiệu suất GC trên Heap quyết định tốc độ ứng dụng. Nếu Heap đầy -> ném ra lỗi OutOfMemoryError: Java heap space.
c, Stack Area:
- Chức năng: Mỗi thread trong JVM có một stack riêng để lưu các frame (khung) đại diện cho từng method gọi (được tạo ra cùng lúc với thread và bị hủy khi thread terminate). Mỗi frame chứa:
+)Biến cục bộ (local variables).
+)Tham số method (parameters).
+)Giá trị trả về (return values).
+)Con trỏ tham chiếu đến operand stack (nơi thực hiện các phép toán).
- Quản lý bộ nhớ: Kích thước linh hoạt, có thể thay đổi theo nhu cầu:
+)Có thể kiểm soát kích thước ban đầu, tối đa, tối thiểu.
+)Không yêu cầu bộ nhớ liên tục.
+)Nếu Stack có kích thước cố định: kích thước của từng stack có thể được config khi tạo thread.
+)Mỗi khi gọi method, một frame mới được đẩy vào stack; khi method kết thúc, frame bị pop ra.
+)Dung lượng stack cho mỗi thread có thể cấu hình qua -Xss (stack size).
- Stack không bị GC, vì nó tự quản lý theo vòng đời thread. Nhưng nếu stack quá sâu (do recursion không kiểm soát) -> ném ra lỗi StackOverflowError. Hoặc lỗi OutOfMemory.
d, PC Registers (Program Counter Registers):
- Chức năng: Mỗi thread có một PC Register để lưu trữ địa chỉ của lệnh bytecode tiếp theo mà thread hiện tại sẽ thực thi. Tóm lại là chỉ đường cho thread chạy.
- Quản lý bộ nhớ:
+)PC Register rất nhỏ, chỉ lưu một địa chỉ (pointer) cho mỗi thread.
+)Không liên quan đến GC, vì nó chỉ là bộ nhớ tạm để theo dõi thực thi.
- Tại 1 thời điểm, 1 thread chỉ thực thi 1 method duy nhất:
+)Non-native method: PC Register chứa địa chỉ.
+) Native method: PC Register undefined
- Tồn tại trong vòng đời của thread.
- Có kích thước đủ lớn để lưu trữ địa chỉ trả về hoặc 1 con trỏ đến native method.
- Là thanh ghi ảo mà JVM tạo ra cho mỗi thread, không phải là 1 thanh ghi vật lý trong CPU.
-> Tóm lại là giúp JVM giữ đúng thứ tự thực thi trong môi trường đa luồng, tránh nhầm lẫn giữa các luồng.
e, Native Method StacK:
- Chức năng: Lưu thông tin về các method native (viết bằng C/C++ hoặc ngôn ngữ khác) được gọi qua JNI (Java Native Interface). Nó hoạt động tương tự Stack Area, nhưng cho code native.
- Quản lý bộ nhớ:
+)Native Method Stack được cấp phát cho mỗi thread khi gọi method native.
+)Không bị GC, vì nó thuộc về code ngoài JVM, nhưng cần quản lý cẩn thận để tránh leak bộ nhớ bên native.
- Cho phép Java tích hợp với mã native, nhưng cần cẩn thận vì native code không có GC hỗ trợ.
##Câu 6: Class Metadata (Method Area) trong JVM có vai trò gì?
*Giúp JVM hiểu và thực thi mã Java:
a, Lưu trữ thông tin cấu trúc class/interface:
- Chức năng: Lưu trữ tất cả thông tin cần thiết về một class hoặc interface, bao gồm:
+)Tên class/interface.
+)Tên và loại của các field (biến thành viên).
+)Tên, kiểu trả về, và tham số của các phương thức.
+)Thông tin về các superclasses, interfaces được implements.
+)Các hằng số (constants) trong Runtime Constant Pool.
b, Hỗ trợ quá trình Linking và Initialization:
- Chức năng: Class Metadata được sử dụng trong các giai đoạn Loading, Linking (Verification, Preparation, Resolution), và Initialization của Class Loader Subsystem:
+)Verification: Kiểm tra xem Class Metadata có hợp lệ không (cú pháp, bảo mật).
+)Preparation: Cấp phát bộ nhớ cho các field static (nếu có) dựa vào Class Metadata.
+)Resolution: Phân giải các tham chiếu biểu tượng trong Runtime Constant Pool thành tham chiếu thực tế, dựa trên thông tin trong Class Metadata.
+)Initialization: Khởi tạo giá trị thực cho các biến static, dựa trên Class Metadata.
c, Hỗ trợ thực thi bytecode:
- Chức năng: Execution Engine (Interpreter/JIT Compiler) truy cập Class Metadata để hiểu cách thực thi bytecode. Nó biết phương thức nào gọi gì, field nào nằm ở đâu, và cách xử lý logic của class.
d, Quản lý đa thread và phân giải class:
- Chức năng: Class Metadata giúp JVM quản lý các class trong môi trường đa thread, đảm bảo mỗi thread truy cập đúng thông tin class mà không xung đột. 
- Nó cũng hỗ trợ phân giải các class phụ thuộc (dependent classes).
e, Hỗ trợ Garbage Collection và Unloading Class:
- Chức năng: Class Metadata giúp JVM quyết định khi nào một class có thể được unload (gỡ bỏ) khỏi Method Area. Nếu không còn instance nào của class, không có ClassLoader giữ tham chiếu, và không có tham chiếu nào từ Runtime Constant Pool, class có thể được GC unload.
##Câu 7: PermGen và Metaspace khác nhau như thế nào? Tại sao Java chuyển từ PermGen sang Metaspace từ Java 8?
*Khác nhau:
a, Vị trí bộ nhớ:
- PermGen: Trong Heap Area.
- Metaspace: Ngoài Heap, dùng bộ nhớ native.
b, Giới hạn dung lượng:
- PermGen: giới hạn bởi -Xmx (Heap max).
- Metaspace: Linh hoạt, phụ thuộc vào bộ nhớ hệ thống.
c, Quản lý bộ nhớ:
- PermGen: Bị GC trên Heap.
- Metaspace: Không bị GC, quản lý bởi OS.
d, Hiệu suất GC:
- PermGen: Dễ gây OutOfMemoryError: PermGen space do GC trên Heap.
- Metaspace: Ít gặp lỗi bộ nhớ, đặc biệt với ứng dụng tải nhiều class.
e, Cấu hình:
- PermGen: Dùng -XX:MaxPermSize để giới hạn.
- Metaspace: Dùng -XX:MaxMetaspaceSize (tùy chọn, không bắt buộc).
f, Khả năng mở rộng: 
- PermGen: Giới hạn, khó mở rộng trong Heap.
- Metaspace: Linh hoạt, mở rộng theo bộ nhớ native.
*Tại sao Java chuyển từ PermGen sang Metaspace từ Java 8?
- Vấn đề của PermGen: giới hạn bộ nhớ cố định, Hiệu suất GC kém, khó quản lý. Ngược lại là những lợi ích của Metaspace: Bộ nhớ native linh hoạt, hiệu suất tốt hơn, quản lý dễ dàng và hỗ trợ tốt hơn cho class loading.
##Câu 8: Heap và Stack trong JVM khác nhau như thế nào?
*Mục đích:
- Heap: Lưu trữ đối tượng và mảng.
- Stack: Lưu trữ frame method, local variables, parameters.
*Phạm vi:
- Heap: Chung cho tất cả thread.
- Stack: Riêng cho mỗi thread.
*Kiểu dữ liệu:
- Heap: Dữ liệu động (objects, arrays).
- Stack: Dữ liệu tạm thời (local variables, parameters).
*Quản lý bộ nhớ:
- Heap: GC tự động.
- Stack: Tự động cấp phát/giải phóng theo vòng đời method.
*Kích thước:
- Heap: Lớn, có thể cấu hình qua -Xmx, -Xms.
- Stack: Nhỏ, cấu hình qua -Xss cho mỗi thread
*Hiệu suất truy cập:
- Heap: Chậm hơn (do phải tìm kiếm).
- Stack: Nhanh hơn (truy cập theo LIFO – Last In, First Out).
*Liên quan đến GC:
- Heap: Bị GC quản lý (Young, Old Generation).
- Stack: Không bị GC, tự động dọn dẹp khi method kết thúc.
##Câu 9: Garbage Collection trong JVM là gì? Tại sao nó quan trọng?
*GC là gì?
- Garbage Collection (GC) là một cơ chế tự động trong JVM, chịu trách nhiệm quản lý bộ nhớ bằng cách tìm và giải phóng (collect) các đối tượng không còn được sử dụng (unreferenced objects) trong Heap Area.
- GC giúp lập trình viên Java không phải tự tay cấp phát hoặc giải phóng bộ nhớ như trong ngôn ngữ C/C++, giảm nguy cơ lỗi như memory leak hoặc tràn bộ nhớ (memory overflow).
*Vì sao GC quan trọng:
- Ngăn ngừa memory leak:
+)Dev không cần tự giải phóng bộ nhớ như C/C++ (sử dụng free hoặc delete). GC tự động dọn dẹp, giảm nguy cơ quên giải phóng bộ nhớ (memory leak), dẫn đến OutOfMemoryError.
- Tăng hiệu suất:
+)GC tự động quản lý bộ nhớ, giúp dev tập trung vào logic ứng dụng thay vì quản lý bộ nhớ thủ công. Điều này đặc biệt quan trọng trong các ứng dụng lớn, phức tạp như server, game, hoặc framework.
##Câu 10: Những thuật toán chính của Garbage Collection mà JVM sử dụng là gì?
*Mark-and-Sweep:
- Là một thuật toán GC cơ bản, bao gồm hai giai đoạn:
Marking (Đánh dấu): Quét qua Heap, đánh dấu các đối tượng "sống" (reachable) bằng cách theo dõi từ các root (như biến cục bộ, static field, thread stack).
Sweeping (Thu gom): Loại bỏ các đối tượng "chết" (unreachable), giải phóng bộ nhớ mà không di chuyển đối tượng sống.
- Vai trò trong JVM: Thường được dùng trong Old Generation hoặc các bộ GC như CMS (Concurrent Mark-Sweep). Nó cũng là nền tảng cho các thuật toán phức tạp hơn (như Mark-Compact).
*Copying (Copy-Collect hoặc Semi-Space):
- Thuật toán này chia Heap thành hai không gian (semi-spaces): một không gian đang dùng (from-space) và một không gian trống (to-space).
Quét from-space, đánh dấu các đối tượng "sống," rồi sao chép chúng sang to-space, bỏ lại các đối tượng "chết" trong from-space. Sau đó, from-space và to-space đổi vai trò (from trở thành to, và ngược lại).
- Vai trò trong JVM: Dùng chủ yếu trong Young Generation (Eden và Survivor Spaces) cho Minor GC. Thuật toán này nhanh vì chỉ xử lý đối tượng "sống," nhưng yêu cầu Heap gấp đôi dung lượng lý thuyết (do chia đôi không gian).
*Mark-Compact:
- Là một biến thể của Mark-and-Sweep, thêm giai đoạn Compacting (Nén) sau khi đánh dấu và thu gom. Sau khi đánh dấu đối tượng "sống" và thu gom "chết," GC nén các đối tượng "sống" lại gần nhau trong Heap, loại bỏ phân mảnh.
- Vai trò trong JVM: Thường được dùng trong Old Generation hoặc các bộ GC như Parallel GC, G1GC (trên từng vùng). Nó không phổ biến trong Young Generation do yêu cầu di chuyển đối tượng, gây "stop-the-world" lâu hơn.
*Generational Garbage Collection (Generational GC):
- Không phải một thuật toán thu gom riêng lẻ, mà là một chiến lược tối ưu hóa, kết hợp các thuật toán khác (như Copying cho Young, Mark-and-Sweep/Mark-Compact cho Old) dựa trên vòng đời đối tượng. Chia Heap thành Young Generation (Eden, Survivor Spaces) và Old Generation (Tenured). Young dùng Minor GC (thường Copying), Old dùng Major GC hoặc Full GC (thường Mark-and-Sweep hoặc Mark-Compact).
- Vai trò trong JVM: Là cơ chế mặc định trong hầu hết các bộ GC (Serial, Parallel, CMS, G1GC), dựa trên giả thuyết rằng đa số đối tượng ngắn hạn (short-lived) nằm ở Young, ít đối tượng dài hạn (long-lived) ở Old.
*G1GC (Garbage First Garbage Collector):
- Là bộ GC mặc định từ Java 9, chia Heap thành các vùng (regions) thay vì thế hệ cố định, ưu tiên thu gom các vùng chứa nhiều rác (garbage-first). Kết hợp Mark-and-Sweep, Copying, và Mark-Compact trên từng vùng. Dùng Young GC (trên vùng Young), Mixed GC (trên cả Young và Old), và Full GC (nếu cần).
- Vai trò trong JVM: Dành cho ứng dụng lớn, cần low-pause (thời gian "stop-the-world" ngắn), như server, game.
*ZGC (Z Garbage Collector) và Shenandoah GC
- ZGC: Từ Java 15, tối ưu cho low-latency, gần như không gây "stop-the-world" ngay cả với Heap lớn (hàng chục GB). Sử dụng vùng (regions) như G1GC, nhưng áp dụng màu (coloring) để theo dõi đối tượng, kết hợp Mark, Relocate, và Remap.
- Shenandoah: Tương tự ZGC, tập trung vào hiệu suất thực thời, có trong một số bản build OpenJDK, dùng Mark, Concurrency, và Compaction.
- Vai trò trong JVM: Dành cho ứng dụng cực kỳ nhạy với độ trễ (low-latency), như game, giao dịch tài chính.
##Câu 11: JVM có những loại Garbage Collector nào? Khác nhau ở điểm nào?
*Serial Garbage Collector:
- Là GC đơn thread, thực hiện tất cả các tác vụ (marking, sweeping, compacting) tuần tự trên một thread duy nhất, gây "stop-the-world" (tạm dừng toàn bộ ứng dụng) trong suốt quá trình GC.
*Parallel Garbage Collector (Parallel GC):
- Là GC đa thread, sử dụng nhiều thread để thực hiện các tác vụ GC (marking, sweeping, compacting) song song, nhưng vẫn gây "stop-the-world" trong Full GC hoặc Major GC trên Old Generation.
*Concurrent Mark-Sweep (CMS) Garbage Collector:
- Là GC đa thread, thực hiện phần lớn công việc (marking, sweeping) đồng thời (concurrently) với ứng dụng, giảm thời gian "stop-the-world" so với Serial và Parallel. Tuy nhiên, nó vẫn cần "stop-the-world" ngắn trong một số giai đoạn (như initial mark, remark).
*Garbage First (G1) Garbage Collector:
- Là GC mặc định từ Java 9, chia Heap thành các vùng (regions) thay vì thế hệ cố định (Young, Old). Nó ưu tiên thu gom các vùng chứa nhiều rác (garbage-first), kết hợp Mark-and-Sweep, Copying, và Mark-Compact trên từng vùng, giảm "stop-the-world" và phân mảnh.
*Z Garbage Collector (ZGC):
- Là GC được giới thiệu từ Java 15, tối ưu cho low-latency, gần như không gây "stop-the-world" (dưới 1ms) ngay cả với Heap lớn (hàng chục GB đến terabytes). ZGC chia Heap thành vùng (regions), sử dụng cơ chế màu (coloring) để theo dõi đối tượng, kết hợp Mark, Relocate, và Remap.
*Shenandoah Garbage Collector:
- Là GC được phát triển bởi Red Hat, có trong một số bản build OpenJDK (như OpenJDK 12+), tối ưu cho low-latency, tương tự ZGC. Shenandoah cũng chia Heap thành vùng, sử dụng Mark, Concurrency, và Compaction đồng thời với ứng dụng.
##Câu 12: Sự khác biệt giữa Minor GC, Major GC và Full GC là gì?
*Vùng Heap:
- Minor GC: Young Generation.
- Major GC: Old Genertion.
- Full GC: Toàn bộ Heap (Young + Old).
*Thuật toán:
- Minor GC: Copying.
- Major GC: Mark-and-Sweep/Mark-Compact.
- Full GC: Mark-and-Sweep/Mark-Compact hoặc kết hợp.
##Câu 13: Bộ nhớ Stack có bị thu hồi bởi Garbage Collector không? Nếu không, nó được quản lý như thế nào?
*Bộ nhớ Stack có bị thu hồi bởi GC không?
- Không. Vì Stack Area trong JVM không chứa các đối tượng hoặc dữ liệu động (như trong Heap), mà chỉ lưu trữ các frame (khung) đại diện cho các method gọi.
*Stack được quản lý thế nào?
- Cấp phát bộ nhớ:
+)Mỗi thread trong JVM có một Stack Area riêng, được cấp phát khi thread khởi tạo.
+)Kích thước Stack cho mỗi thread được cấu hình qua tham số JVM -Xss (stack size), mặc định thường nhỏ (ví dụ: 1MB mỗi thread trên nhiều hệ thống).
+)Khi gọi một method, JVM đẩy một frame mới vào Stack. Frame này chứa tất cả biến cục bộ, tham số, và thông tin thực thi của method đó.
- Giải phóng bộ nhớ:
+)Khi method kết thúc (bằng return, ngoại lệ, hoặc kết thúc bình thường), frame tương ứng bị "pop" (xóa) khỏi Stack, tự động giải phóng bộ nhớ ngay lập tức.
+)Quá trình này diễn ra theo nguyên tắc LIFO: frame cuối cùng đẩy vào Stack sẽ được pop ra đầu tiên, đảm bảo Stack luôn gọn gàng và không bị tràn nếu quản lý tốt.
->Vì vậy, Stack không cần GC, mà tự quản lý bằng cơ chế vòng đời của thread và method.
- Độc lập với thread:
+)Mỗi thread có Stack riêng, nên không có xung đột giữa các thread. Điều này giúp JVM quản lý luồng thực thi độc lập trong môi trường đa thread.
+)Nếu thread kết thúc, toàn bộ Stack của thread đó được giải phóng, không cần GC can thiệp. 
##Câu 14:Làm thế nào để tránh Memory Leak trong Java?
*Xóa tham chiếu không cần thiết:
- Đặt các biến hoặc tham chiếu không còn dùng về null khi không cần nữa, giúp GC nhận ra đối tượng không còn reachable.
*Quản lý Collections cẩn thận:
- Đảm bảo xóa các đối tượng không cần trong List, Map, Set bằng clear(), remove(), hoặc sử dụng WeakHashMap để tự động thu gom entry không tham chiếu.
*Tránh sử dụng biến static không cần thiết:
- Hạn chế dùng static cho các field chứa nhiều đối tượng, vì static tồn tại lâu dài trong Method Area, giữ tham chiếu đến Heap, ngăn GC thu gom.
##Câu 15:JIT Compiler trong JVM là gì? Nó cải thiện hiệu năng như thế nào?
*Định nghĩa:
- Just-In-Time Compiler (JIT Compiler) là một thành phần quan trọng của Execution Engine trong JVM, chịu trách nhiệm biên dịch bytecode (mã trung gian của Java) thành mã máy (machine code) tại thời điểm chạy (runtime), thay vì chỉ thực thi bytecode trực tiếp như Interpreter. Bytecode được tạo ra từ mã nguồn Java bởi trình biên dịch javac, sau đó JVM dùng JIT để tối ưu hóa và chuyển thành mã máy phù hợp với CPU của máy tính, giúp tăng tốc độ thực thi.
*Cách JIT cải thiện hiệu năng:
- Tăng tốc độ thực thi:
+)Interpreter chạy bytecode trực tiếp, chậm vì phải dịch từng dòng, đặc biệt với code lặp lại. JIT biên dịch thành mã máy một lần, sau đó tái sử dụng, giảm thời gian dịch và tăng tốc độ chạy.
- Tối ưu hóa code:
+)JIT sử dụng các kỹ thuật tối ưu hóa động (runtime optimization), như:
-)Inlining: Thay các lời gọi method bằng mã thực tế, giảm chi phí gọi hàm.
-)Loop Unrolling: Mở rộng vòng lặp thành nhiều lệnh, tăng hiệu suất trên CPU đa lõi.
-)Dead Code Elimination: Loại bỏ code không bao giờ thực thi, giảm kích thước mã máy.
-)Constant Folding: Tính toán trước các hằng số trong runtime, giảm workload.
- Giảm chi phí runtime:
+)Interpreter có thể chậm nếu bytecode phức tạp hoặc lặp nhiều. JIT giảm chi phí này bằng cách tạo mã máy một lần, lưu trong Code Cache, và tái sử dụng, giảm tải cho JVM và CPU.
##Câu 16:Khi nào JIT Compiler dịch mã bytecode sang mã máy thực thi?
*Khi phát hiện "Hot Code":
- Cách hoạt động: JIT dùng một cơ chế gọi là hot spot detection để nhận diện các đoạn code được thực thi nhiều lần, như vòng lặp, method gọi lặp lại, hoặc khối code trong luồng chính của ứng dụng.
- Ngưỡng biên dịch (Compilation Threshold): JVM theo dõi số lần thực thi một đoạn bytecode (thường là method hoặc vòng lặp). Khi số lần vượt ngưỡng mặc định (mặc định khoảng 10.000 lần thực thi với -XX:CompileThreshold), JIT kích hoạt và biên dịch đoạn code đó thành mã máy.
*Khi code được gọi thường xuyên trong runtime:
- Cách hoạt động: JIT giám sát luồng thực thi thông qua counters (bộ đếm) trong JVM, đếm số lần gọi method hoặc thực thi bytecode. Nếu một method hoặc khối code được gọi đủ thường xuyên (dựa trên ngưỡng), JIT sẽ biên dịch nó.
*Khi cần tối ưu hóa hiệu năng:
- Cách hoạt động: JIT không chỉ biên dịch mà còn tối ưu hóa mã máy bằng các kỹ thuật như inlining (gộp code method), loop unrolling (mở rộng vòng lặp), dead code elimination (loại bỏ code không dùng), và constant folding (tính toán hằng số trước). Khi JVM phát hiện một đoạn code cần tối ưu hóa để tăng tốc, JIT sẽ biên dịch nó.
##Câu 17:Khi ứng dụng Java chạy chậm, bạn sẽ kiểm tra gì đầu tiên?
*Kiểm tra mức sử dụng CPU và bộ nhớ (Memory/CPU Usage):
- Hiệu năng chậm thường do CPU hoặc bộ nhớ (Heap, Stack, Metaspace) bị quá tải.
*Kiểm tra Garbage Collection (GC) và thời gian "stop-the-world":
- GC, đặc biệt là Full GC hoặc Major GC, có thể gây "stop-the-world" (tạm dừng ứng dụng), làm chậm hiệu năng, đặc biệt với Heap lớn.
*Kiểm tra số lượng thread và deadlock:
- Quá nhiều thread hoặc deadlock (bế tắc) có thể làm CPU cao, gây chậm ứng dụng, đặc biệt trong môi trường đa thread.
*Kiểm tra JIT Compiler và Code Cache:
- JIT Compiler có thể làm chậm khởi động hoặc tốn tài nguyên nếu biên dịch quá nhiều hot code, hoặc Code Cache đầy gây CodeCacheFullError.
*Kiểm tra Memory Leak và Heap Dump:
- Memory Leak (tham chiếu không cần thiết) gây Heap đầy, dẫn đến Full GC thường xuyên, làm chậm ứng dụng.
##Câu 18:Làm thế nào để debug lỗi liên quan đến bộ nhớ Heap và Stack?
*Các bước debug lỗi Heap:
- Kiểm tra log và cấu hình JVM: Xem log ứng dụng hoặc kích hoạt log GC để phát hiện lỗi.
- Sử dụng công cụ giám sát JVM: 
+)jvisualvm hoặc jconsole: Mở ứng dụng, vào tab "Monitor" để theo dõi Heap Usage, phát hiện Heap đầy hoặc GC chậm.
- Phát hiện Memory Leak:
+)Kiểm tra các nguyên nhân phổ biến: static fields, listeners, callbacks, threads, hoặc collections không dọn dẹp.
+)Sử dụng WeakReference, SoftReference, hoặc WeakHashMap để giữ tham chiếu yếu, giúp GC thu gom dễ dàng hơn.
*Debug lỗi liên quan đến bộ nhớ Stack:
- Kiểm tra log và cấu hình JVM: Xem log ứng dụng để phát hiện StackOverflowError.
- Sử dụng công cụ giám sát thread: jstack, jvisualvm.
- Phân tích code để tìm recursion hoặc method lồng nhau.