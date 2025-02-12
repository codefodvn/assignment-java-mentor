
# Week 1: Git

1. Git thực sự lưu trữ dữ liệu như thế nào? Các đối tượng (objects) như blob, tree, commit, và tag có quan hệ gì với nhau trong hệ thống lưu trữ phân tán của Git?

- Answer:
+Git: git lưu lại snap shot không như các phiên bản cũ(lưu trữ toàn bộ thay đổi file)
+blob: lưu trữ nội dung file
+tree: liên kết đến các blob, cấu trúc các thư mục
+commit: lưu trữ snap shot của repo (trỏ đến 1 tree), ref đến 1 commit trc và
+tag: gán nhãn cho commit

2. Khi ta di chuyển giữa các commit, Git làm gì dưới lớp vỏ bọc của nó? HEAD thực chất là gì và nó tác động thế nào đến trạng thái hiện tại của repository?

- Answer:
+Khi di chuyển giữa các commit thì HEAD sẽ thay đổi theo (di chuyển đến commit hiện tại)
Git cũng sẽ cập nhật các tệp trong thư mục khớp với commit mà HEAD đang trỏ (HEAD thực chat là 1 con trỏ có thể trỏ đến các commit, quyết định trạng thái hiện tại của repo và quyết định commit mới sẽ đi về đâu)

3. Git có thể tạo hàng nghìn nhánh (branches) gần như tức thì mà không tốn tài nguyên. Điều này hoạt động ra sao về mặt kỹ thuật, và tại sao việc hợp nhất (merge) đôi khi lại dẫn đến xung đột?

- Answer:
+Khi tạo nhánh đơn giản là git chỉ cần trỏ đến 1 commit mới(không cần phải sao chép toàn bộ dữ lieu hiện tại của repo) nên chỉ tốn rất ít dung lượng (chỉ cần ID của commit) -> gần như không tốn tài nguyên
+Khi merge code thì git sẽ hợp 2 commit lại với nhau nhưng 2 commit là khác nhau -> git không biết nên chọn bên nào dẫn đến xung đột

4. Khi ta thêm file vào staging area, thực chất Git làm gì? Điều gì thực sự thay đổi khi ta chạy lệnh git commit?

- Answer:Khi chúng ta thêm vào staging area -> git sẽ lưu trữ file thành 1 blob trong object
 

5. Git phân biệt working directory, staging area và repository như thế nào? Tại sao có những thay đổi được theo dõi nhưng chưa được commit?

- Answer: 
Working directory là nơi mà có các file đang làm việc, thay đổi
Staging area: là nơi mà git đánh dấu các file thay đổi -> chuẩn bị cho commit
Repository: là nơi lưu trữ các commit, khi chạy lệnh commit git sẽ lấy file trong staging (snap shot) để lưu nó vào repo
-File được theo dõi trong staging nhưng chưa được commit vì có thể chúng ta còn thay đổi tiếp 

6. Git lưu trữ thay đổi của ta ở đâu? Làm thế nào Git có thể khôi phục những thay đổi đã bị "stash" một cách chính xác?

- Answer: git lưu trữ thay đổi của ta ở Working directory
- khi chạy lệnh git stash thì git sẽ lưu trữ những file thay đổi trong Stash Stack -> khôi phục thì đơn giản là bạn chỉ cần lấy trong stack đó ra với câu lệnh git stash apply(commit stash mới nhất) hoặc git stash pop

7. Khi xảy ra xung đột hợp nhất (merge conflict), tại sao Git không thể tự động giải quyết? Git sử dụng cơ chế nào để xác định sự khác biệt và yêu cầu sự can thiệp của lập trình viên?

- Answer: 
+Khi xảy ra xung đọt hợp nhất, git không giải quyết được vì 2 commit cùng tác động vào 1 file và có nội dung khác nhau trên 1 dòng ->> Git không thể biết đựohc commit nào đúng nên sẽ để cho dev giải quyết thủ công
+Git sử dung cơ chế so sánh "Three-Way Merge": 
	Base Commit: trạng thái trc khi tách nhánh
	Current Commit: Trạng thái file mà muốn merge vào
	Incoming Commit: commit muốn merge vào commit hiện tại
Khi phát hiện xuung đột -> git đánh dấu và cần sự can thiệp của dev
<<<<<<<<<<HEAD
COntent
=======
Connnnntent
>>>>>>>>>> feature

8.  Git duy trì lịch sử commit như thế nào? Cấu trúc cây (tree structure) của Git giúp tối ưu hóa việc theo dõi và truy xuất lịch sử ra sao?

- Answer:
+Git duy trì lịch sử commit dưới dạng 1 mã hash gồm các thông tin như: 
	Commit trước (cha), Thời gian, nội dung snap shot(trỏ đến tree), message của commit đó
+Cấu trúc cây không lưu trữ sự khác biệt mà lưu toàn bộ snap shot (gồm các blob) -> có thể truy xuất dễ dàng vì khi file không thay đổi -> tìm blob cũ không cần tạo mới

9. Những lệnh này khác nhau như thế nào về bản chất? Tại sao git reset --hard có thể gây mất dữ liệu nhưng git revert thì không?

- Answer:
+git reset --hard: git sẽ quay về 1 commit cũ và xóa các commit sau commit cũ đó, nếu chưa push thì git có thể xóa vĩnh viễn dữ lieu của các commit đó ->>>> có thể gây mất dữ lieu
+git revert: thì đơn giản là git tạo 1 commit mới đảo ngược commit cũ mà không mất bất kì dữ lieu gì(lịch sử commit vẫn được dữ nguyên) 
EX: commit A tạo mới file txt
-khi dung revert sẽ tạo commit B xóa đi file txt
-khi dung reset thì sẽ xóa luôn commit A và trở về commit trước đó

10. Khi dùng git commit --amend, Git thực sự làm gì? Nó có chỉnh sửa commit cũ không, hay tạo ra một commit hoàn toàn mới?

- Answer: Dùng câu lệnh khi muón thay đổi commit mà chưa push lên. Nếu push lên rồi thì nên tạo commit mới chứ không dung amend.
Git sẽ tạo 1 commit mới với nội dung cập nhật mới nhất -> sau đó git sẽ thay đổi commit hiện tại thành commit mới tạo dó