
# Week 1: Git

1. Git thực sự lưu trữ dữ liệu như thế nào? Các đối tượng (objects) như blob, tree, commit, và tag có quan hệ gì với nhau trong hệ thống lưu trữ phân tán của Git?

- Answer:  git lưu dữ liệu theo bảng băm với các giá trị key và  value là các object cần lưu . Tức là value giống nhau thì có key giống nhau. Blob chỉ chứa dữ liệu , không chứa metadata ( quyền truy cập , tên file,...). tree chứa blob và subtree( thư mục con). Mỗi entry trong tree chứa tên file, mã SHA-1 của blob và quyền truy cập file. Commit thì lưu trạng thái toàn bộ project, chứa tree, nhiều tree, metadata, mã sha-1 của commit cha. Tag kiểu như đánh dấu 1 commit

2. Khi ta di chuyển giữa các commit, Git làm gì dưới lớp vỏ bọc của nó? HEAD thực chất là gì và nó tác động thế nào đến trạng thái hiện tại của repository?

- Answer: khi ta chuyển giữa các commit, có 3 thứ git nó sẽ làm:
    1. Cập nhật head: head thực chất là 1 con trỏ đặc biệt chỉ tới commit hiện tại trong repo 
    2.  Cập nhật trạng thái thư mục làm việc : tuỳ vào commit mà head trỏ vào thì các file của commit đc trỏ vào sẽ cập nhật/xoá đi 
    3. Staging area sẽ thay đổi để khớp với commit được trỏ vào
- 

3. Git có thể tạo hàng nghìn nhánh (branches) gần như tức thì mà không tốn tài nguyên. Điều này hoạt động ra sao về mặt kỹ thuật, và tại sao việc hợp nhất (merge) đôi khi lại dẫn đến xung đột?

- Answer: về cơ bản tạo 1 nhánh mới tức là git tạo ra 1 tham chiếu (con trỏ ) tới commit cuối cùng trong repo. Tức là nó chỉ là 1 cái tên với tham chiếu là commit cuối cùng. 
- Merge có thể dẫn đến xung đột trong trường hợp: ví dụ cùng 1 file, 1 nhánh chỉnh 10 dòng cuối , nhánh kia  cũng chỉnh 10 dòng cuối thì git không biết nên chọn cái nào -> xung đột

4. Khi ta thêm file vào staging area, thực chất Git làm gì? Điều gì thực sự thay đổi khi ta chạy lệnh git commit?

- Answer: thêm file staging area tức là git snapshot cái file đó, ghi lại metadata của file rồi đưa vào staging area. 
Khi ta chạy lệnh git commit, file từ staging area sẽ đc đóng gói lại thành 1 commit ( entry cuối cùng của tree) 


5. Git phân biệt working directory, staging area và repository như thế nào? Tại sao có những thay đổi được theo dõi nhưng chưa được commit?
- Answer:

6. Git lưu trữ thay đổi của ta ở đâu? Làm thế nào Git có thể khôi phục những thay đổi đã bị "stash" một cách chính xác?

- Answer: git lưu thanh đổi ở working directory nếu chưa add, add rồi thì lưu ở staging area , và lưu ở local repo nếu đã commit. để gi

7. Khi xảy ra xung đột hợp nhất (merge conflict), tại sao Git không thể tự động giải quyết? Git sử dụng cơ chế nào để xác định sự khác biệt và yêu cầu sự can thiệp của lập trình viên?

- Answer:Vì 2 ng chỉnh cùng 1 dòng trong cùng 1 file thì git ko phân biệt đc nên chọn cái nào.

8.  Git duy trì lịch sử commit như thế nào? Cấu trúc cây (tree structure) của Git giúp tối ưu hóa việc theo dõi và truy xuất lịch sử ra sao?

- Answer:Mỗi khi commit, thì tree sẽ mọc ra thêm 1 subtree mới ( là cái vừa mới commit bao gồm mã SHA-1 của commit cha,metadata ) . Tức là nếu muốn quay lại commit trước đó hay bất kì commit khác thì git chỉ cần truy ngược lên theo từng commit 1. 

9. Những lệnh này khác nhau như thế nào về bản chất? Tại sao git reset --hard có thể gây mất dữ liệu nhưng git revert thì không?

- Answer: về bản chất, git reset –hard sẽ di chuyển HEAD về 1 commit trc đó, cập nhật cả index lẫn working directory về trc đó luôn, tức là những cái sau HEAD sẽ biến mất. còn git revert chỉ là tạo ra 1 cái commit mới tham chiếu đến 1 commit trc đó, nó sẽ xoá đi những thay đổi mà commit đc revert đã thực hiện, tuy nhiên lịch sử commit vẫn còn, muốn quay lại vẫn đc. 

10. Khi dùng git commit --amend, Git thực sự làm gì? Nó có chỉnh sửa commit cũ không, hay tạo ra một commit hoàn toàn mới?

- Answer: git –amend sao chép toàn bộ commit của head, lưu nó vào 1 commit mới, xoá commit cũ , head trỏ vào commit mới đó

