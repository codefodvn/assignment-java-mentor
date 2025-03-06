
# Week 4: Java Collections

1. Sự khác biệt giữa HashSet, LinkedHashSet và TreeSet? Trong trường hợp nào thì nên dùng cái nào?

- Answer:
HashSet thì không đảm bảo thứ tự lưu vào,
LinkedHashSet có đảm bảo thứ tự lưu 
Hiệu suất 2 cái như nhau
Tree set thì có thể sắp xếp được thứ tự dựa vào tự nhiên hoặc Comparator
-nên dung hashset khi không cần quan tâm thứ tự mà chỉ cần tồn tại duy nhất, LinkedHashSet tương tự nhưng khi cần them duy trì được thứ tự lưu vào
- Treeset dung khi sắp xếp phần tử theo tự nhiên hoặc theo điều kiện của mình

2. Làm thế nào để HashSet đảm bảo các phần tử không trùng lặp?

- Answer:Khi hashSet lưu phần tử thì sẽ qua 1 hàm băm để ra được 1 value, bên trong hashset là 1 hashmap sử dung value vào như key và value là giá trị ảo nên đảm bảo dược không có giá trị trùng lặp

3. Sự khác biệt giữa HashMap, LinkedHashMap và TreeMap? Trong trường hợp nào thì nên dùng cái nào?

- HashMap không đảm bảo thứ tự lưu vào, cấu trúc là 1 bang băm
- LinkedHashMap thì nó sẽ đảm bảo thứ tự lưu vào vì cấu trúc gồm 1 bang băm đi kèm 1 linked list
-TreeMap thì có cấu trúc cây đỏ đen với thứ tự tự nhiên có hỗ trợ comparator
Khi cần hiệu suất cao không quan tâm thứ tự thì nên dung hashmap,khi cần quan tâm thứ tự thì linkedHashMap và khi cần sắp xếp theo thứ tự tang dần thì cần TreeMap(thay đổi săps xếp các key)

4. Từ sau Java 8, HashMap xử lý collisions như thế nào?

- Answer: HashMap sẽ bị collision khi qua hàm băm ra 2 giá trị going nhau -> gắn them linked list cho giá trị băm, khi có collision thì chỉ cần them vào linkedlist(truy xuất nhanh nhưng tốn bộ nhớ)

5. Tại sao String và Integer thường được dùng làm key trong HashMap?
- Bởi vì đó là những class không nguyên thủy có thể nhận giá trị null(yêu cầu 1 object cho key), đảm bảo immutable không thay đổi giá trị cho key, có hiệu suất tốt

6. Cách hoạt động của Red-black Tree khi thêm, xoá phần tử?
- Answer:đảm bảo rang không có 2 nút cùng màu liên tiếp VD: không thể đỏ-đỏ-đen mà phải đỏ-đen-đỏ
root mặc định là đen, đảm bảo mọi đường đi từ root đến lá phải có cùng số lượng nút đen, các hàm như insert,search,delete đều có O(log n)
- nên sử dung khi cần 1 cấu trúc tìm kiếm nhanh, tự can bang

7. AVL Tree là gì? Có ưu điểm và nhược điểm gì?
- Answer:
đây là cây tìm kiếm nhị phân can bang, sau khi chèn xóa thì sẽ tự động rotation để đảm bảo sự cần bang. tối ưu cho tìm kiếm vì luôn can bang. phù hợp hệ thống tìm kiếm nhanh vì dữ lieu ít cập nhật
nhược điểm: chèn xóa sẽ chậm do phải quay liên tụcđể can bang, tốn bộ nhớ vì cần lưu them height, cấu trúc phức tạp




8. B-Tree là gì? Được sử dụng như thế nào?
- Answer:
B-Tree là một cấu trúc cây tìm kiếm tự cân bằng, được thiết kế để hoạt động hiệu quả trên ổ đĩa cứng và bộ nhớ ngoài.
Mỗi nút có tối đa m con và chứa từ (m/2) - 1 đến m-1 key.
Root có ít nhất 2 con (trừ khi nó là nút lá).
Tất cả các lá phải cùng mức (cây luôn cân bằng).
Các key trong một nút được sắp xếp theo thứ tự tăng dần.
Tìm kiếm, chèn, xóa đều thực hiện trong O(log n).

 B-Tree lý tưởng cho:
✅ Cơ sở dữ liệu (Database Indexing) - Vì truy vấn nhanh & tiết kiệm bộ nhớ.
✅ Hệ thống tệp tin (File Systems) - Dùng để quản lý thư mục trên ổ cứng.
✅ Bộ nhớ ngoài (External Storage) - Khi dữ liệu quá lớn để lưu trong RAM.