1.Git thực sự lưu trữ dữ liệu như thế nào? Các đối tượng (objects) như blob, tree, commit, và tag có quan hệ gì với nhau trong hệ thống lưu trữ phân tán của Git?

- Git là 1 SCM (Source Controll Management) - điều này có thể nhận thấy trong quá trình tải git thông qua file setup .exe.
- Là 1 hệ thống quản lý mã nguồn phân tán, sử dụng kiến trúc lưu trữ dữ liệu dưới dạng có hướng không tuần hoàn (DAG). Để lưu trữ dữ liệu, git phụ thuộc vào 4 entities:BLob, Tree, Commit, Tag. Chúng được lưu trữ trong folder .git khi mình tiến hành clone repo từ trên server về. Link bài viết của bọn tàu này có nói khá rõ về cấu trúc tổ chức các thư mục lưu trữ của git: <https://blog.csdn.net/ynkonghao/article/details/53415968>

a, Blob (Binary Large Object)

- Dùng để lưu trữ nội dung của 1 file. Mỗi 1 version sẽ được save dưới 1 blob riêng biệt và có đánh mã SHA-1  (cái này lại liên quan đến cơ chế con trỏ - reference).
- Vậy cơ chế lưu nội dung thế nào?
+) Khi add 1 file vào git, git sẽ tạo HASH-1 cho nội dung file (mã này là duy nhất) và đại diện cho nó.
+) Git lưu blob vào thư mục tổng (.git/objects). Tên file được chia làm 2 phần: 2 kí tự đầu là tên thư mục con, 38 kí tự còn lại là tên của tệp trong thư mục con (hash được quy chuẩn có độ dài 40 kí tự)
Ex: Nếu hash của blob là 3b18e5f8cbbac0d153c29724bcca2d22b5737684, thì nó sẽ được lưu trong .git/objects/3b/18e5f8cbbac0d153c29724bcca2d22b5737684.
+) Nén nội dung tệp băngf zlib trước khi đẩy lên để tối ưu hiệu suất
- 1 số câu lệnh làm việc với Blob:
+) Xem danh sách Blob:git ls-tree -r HEAD
+)Xem nội dung của 1 Blob:git cat-file -p <hash>
- Contact của Blob với 3 đứa kia:
+)Tree: Ánh xạ tên tệp với hash của blob
+)Commit: Lưu snapshot bằng cách tham chiếu đến 1 tree object
+)Tag: Đánh dấu 1 commit cụ thể

b, Tree

- Blob là lưu trữ nội dung file thì Tree lại ánh xạ file vào hệ thống thư mục, tức lưu trữ cấu trúc của thư mục. Chứa danh sách file (blob) và thư mục con (tree).
- Tóm lại là chứa các blob và trỏ đến chúng. Mỗi 1 Tree lưu trữ các thông tin:
+) Tên file/thư mục
+) Loại object (Blob/Tree)
+) Mode
+) SHA-1 code
- Contact của Tree với 3 đứa kia:
+) Blob: lưu danh sách các Blob, tham chiếu đến chúng
+) Commit: mỗi commit tham chiếu đến 1 Tree, mô tả trạng thái của project tại thời điểm commit
+)Tag:trỏ đến 1 commit và gián tiếp trỏ đến Tree
c, Commit:
- Là trạng thái snapshot của repo tại 1 thời điểm cụ thể.
- Lưu trữ:
+)Tree object trỏ đến thư mục gốc của projecttại thời điểm commit
+)Parent commit -> liên kết các version với nhau
+)Author và committer
+)timestamp ghi tại thời điểm commit được tạo
+)Commit message để ghi lại thay đổi
d, Tag:
- 2 loại tag chính:
+) Lightweight tag:tham chiếu đến commit, không lưu metadata
+) Annotated tag:lưu trữ metadata (người tạo tag, ngày tạo, message)
- Contact:
+)Commit:Trỏ đến commit, nếu commit mất hoặc thay đổi, tag vẫn liên kết đến comit gốc
+)Tree: tham chiếu gián tiếp thông qua commit
+)Blob: tiếp tục được tham chiếu thông qua Tree -> Commit -> Tag
2.Khi ta di chuyển giữa các commit, Git làm gì dưới lớp vỏ bọc của nó? HEAD thực chất là gì và nó tác động thế nào đến trạng thái hiện tại của repository?
- Khi bạn chuyển giữa các commit trong Git, Git thực hiện các thay đổi trên working directory, staging area (index) và HEAD để phản ánh commit mới mà vừa checkout
a, Cập nhật HEAD:
- Thay đổi HEAD để trỏ đến commit mới
b, Cập nhật Working Directory và Staging Area:
- Sử dụng Tree object của commit mới để cập nhật nội dung thư mục làm việc.
- Nếu commit mới khác với commit hiện tại, Git sẽ thay đổi các file để khớp với snapshot của commit mới.
- Nếu có file bị thay đổi nhưng chưa commit, Git có thể báo lỗi hoặc yêu cầu stash trước khi chuyển đổi commit.
c,Trạng thái Detached HEAD (nếu checkout commit cụ thể):
- Khi checkout một commit bằng hash (git checkout <commit>), HEAD sẽ không còn trỏ đến một nhánh mà trỏ trực tiếp vào commit đó.
- HEAD là một con trỏ, chỉ đến commit hoặc nhánh hiện tại mà đang làm việc. XXác định trạng thái hiện tại của repository.
- Tác động của HEAD đến repo:
a, Xác định commit hiện tại:
- HEAD giúp Git biết đang làm việc trên commit nào. Khi commit thì commit mới sẽ gắn vào nhánh hiện tại mà HEAD đang trỏ đến.
b, Chuyển nhánh (git checkout branch):
- Khi checkout một nhánh, HEAD thay đổi để trỏ đến nhánh đó.
3.Git có thể tạo hàng nghìn nhánh (branches) gần như tức thì mà không tốn tài nguyên. Điều này hoạt động ra sao về mặt kỹ thuật, và tại sao việc hợp nhất (merge) đôi khi lại dẫn đến xung đột?
- Vì Git sử dụng tham chiếu thay vì copy hết data.
+) 1 branch chỉ là 1 con trỏ trỏ đến 1 commit. Chỉ cần thay đổi con trỏ, không cần copy dữ liệu.
+) Chỉ khi thay đổi tên file, Git mới ghi lại dữ liệu khác biệt: Khi commit trên một nhánh mới, Git chỉ lưu dữ liệu thay đổi so với commit trước, thay vì sao chép toàn bộ nội dung.
- Xung động khi merge là do sự khác biệt giữa các branch:
Ex: Hai nhánh chỉnh sửa cùng một dòng trong cùng một file, lịch sử commit không đồng nhất,...

4.Khi ta thêm file vào staging area, thực chất Git làm gì? Điều gì thực sự thay đổi khi ta chạy lệnh git commit?

- Điều này lại quay về cách hoạt động của các objects.
+)Tạo đối tượng blob (Binary Large Object):
Git tạo một blob object (đối tượng nhị phân) chứa nội dung của file trong thư mục .git/objects/.
Nội dung file được nén và mã hóa bằng SHA-1 hash.
Ví dụ, nếu file.txt có nội dung "Hello Git", Git sẽ tạo một blob với hash abcdef12345....
+)Ghi metadata của file vào index (staging area):
Git lưu thông tin về file vào index (staging area) trong file .git/index.
Index chứa:
Hash của blob object vừa tạo.
Đường dẫn file trong thư mục làm việc.
Các thông tin khác như quyền truy cập.
a, Khi chạy git commit,Git thực hiện 3 bước:
- Tạo đối tượng tree (Tree Object):
+)Git nhóm tất cả các blob (file đã được thêm vào staging) vào một tree object.
+)Tree là một dạng "snapshot" của toàn bộ thư mục, lưu cấu trúc cây thư mục cùng với hash của các blob.
b,Tạo commit object (Commit Object):
- Git tạo một commit object lưu metadata của commit:
+)Author: Tác giả commit.
+)Date: Thời gian commit.
+)Message: Nội dung commit.
+)Tree: Hash của tree object vừa tạo.
+)Parent commit: Hash của commit trước đó (trừ commit đầu tiên).
c, Cập nhật HEAD và branch
- Git cập nhật branch hiện tại để trỏ đến commit mới.
- HEAD (con trỏ trạng thái hiện tại) được cập nhật để trỏ đến commit mới.
5.Git phân biệt working directory, staging area và repository như thế nào? Tại sao có những thay đổi được theo dõi nhưng chưa được commit?
Git tổ chức dữ liệu trong ba vùng chính:
a, Working Directory (Thư mục làm việc):
- Đây là nơi chứa tất cả các file và thư mục mà bạn đang làm việc.
- Khi chỉnh sửa hoặc thêm file mới, các thay đổi chỉ xảy ra trong thư mục làm việc.
- Git không tự động theo dõi thay đổi ở đây cho đến khi sử dụng lệnh git add.
b, Staging Area (Chỉ mục / Khu vực tạm):
- Đây là khu vực trung gian nơi Git lưu trữ các thay đổi đã được đánh dấu để commit.
- Khi chạy git add <file>, Git thêm file vào index chứ chưa ghi vào lịch sử repository.
c, Repository (Kho lưu trữ):
- Khi chạy git commit, Git lưu trạng thái của Staging Area vào repository bằng cách tạo một commit.
- Các commit được lưu trong .git/objects/ với một tree object đại diện cho snapshot của toàn bộ project.
Một số thay đổi được Git theo dõi nhưng chưa được commit do chúng nằm trong Staging Area, chưa được lưu vào repository.
6.Git lưu trữ thay đổi của ta ở đâu? Làm thế nào Git có thể khôi phục những thay đổi đã bị "stash" một cách chính xác?
- Git theo dõi thay đổi trong folder .git/. Khi thực hiện thay đổi thì lưu trữ dưới dạng snapshot.
+)objects/ -> Chứa dữ liệu thực tế (commit, blob, tree)
+)refs/ -> Chứa thông tin về các nhánh và HEAD
+)index -> Lưu trạng thái Staging Area
+)logs/ -> Ghi lại lịch sử tham chiếu HEAD
+)stash/ -> Lưu các thay đổi tạm thời khi dùng git stash
- Để khôi phục một stash:
+)Lấy stash entry từ stack
+)Áp dụng patch từ commit stash vào working directory
+)Khôi phục trạng thái Staging Area
+)Đưa HEAD về trạng thái trước khi stash nếu cần
7.Khi xảy ra xung đột hợp nhất (merge conflict), tại sao Git không thể tự động giải quyết? Git sử dụng cơ chế nào để xác định sự khác biệt và yêu cầu sự can thiệp của lập trình viên?
- Bắt buộc phải có sự can thiệp của mình trong nhiều case cụ thể
+)2 nhánh cùng chỉnh sửa 1 dòng/1 file nhưng khác nhau -> Conflict
+)1 nhánh xóa file này, đồng thời nhánh kia cũng sửa file đó
+)2 nhánh tạo 1 file có blob khác nhau
- Về cơ chế này thì lại liên quan đến thuật toán của Git, em có research về chúng (diff và DAG). Khá khó hiểu và trừu tượng, em sẽ tìm hiểu thêm =(((

8.Git duy trì lịch sử commit như thế nào? Cấu trúc cây (tree structure) của Git giúp tối ưu hóa việc theo dõi và truy xuất lịch sử ra sao?
a,Cách Git duy trì lịch sử commit:

- Mỗi commit là một đối tượng (commit object)
+) Mỗi commit được nhận diện bằng một hash SHA-1 duy nhất.
+)Commit chứa:
Hash của commit cha (hoặc nhiều cha trong trường hợp merge).
Tham chiếu đến tree object (snapshot trạng thái thư mục).
Metadata: Tác giả, thời gian commit, thông điệp commit.
- Commit không lưu trữ thay đổi dạng diff, mà là snapshot toàn bộ trạng thái:
+)Mỗi commit trỏ đến một tree object, tree này chứa danh sách blobs (file) và subtrees (thư mục con).
- HEAD là con trỏ trỏ đến commit hiện tại:
+)Khi chuyển nhánh hoặc checkout commit, Git thay đổi HEAD để trỏ đến commit tương ứng.
b,Cấu trúc cây của Git:
- Blob Objects (Nút lá):
+)Mỗi file trong Git được lưu dưới dạng một blob object.
+)Nếu file không thay đổi giữa các commit, Git tái sử dụng blob cũ thay vì tạo một bản sao mới.
- Tree Objects (Nút trung gian):
+)Một tree object đại diện cho thư mục và chứa nhiều blob hoặc tree con.
+)Nếu một thư mục không thay đổi giữa các commit, Git tái sử dụng tree object cũ, giúp tiết kiệm dung lượng.
- Commit Objects (Nút gốc của cây):
+)Mỗi commit trỏ đến một tree object.
+)Commit lưu trữ tham chiếu đến commit trước đó, tạo nên cấu trúc DAG giúp Git dễ dàng theo dõi lịch sử.
9.Những lệnh này khác nhau như thế nào về bản chất? Tại sao git reset --hard có thể gây mất dữ liệu nhưng git revert thì không?
- reset --hard: Di chuyển HEAD về commit trước đó và xóa sạch thay đổi trong working directory & staging area. Lịch sử commit sẽ bị thay đổi và có thể mất dữ liệu vĩnh viễn nêú không backup.
- revert: Tạo một commit mới đảo ngược nội dung commit trước đó. Lịch sử commit nguyên vẹn và không mất dữ liệu vì reverse commit.
a, reset --hard có thể gây mất dữ liệu vì:
- Cách hoạt động:
+)Di chuyển HEAD và branch hiện tại về commit cũ
+)Xóa tất cả commit sau commit đích (C2, C3 biến mất).
+)Xóa thay đổi trong working directory và staging area
- Nguyên nhân mất dữ liệu:
+)Các commit bị reset không còn tham chiếu nào trỏ tới.
+)Không thể khôi phục nếu chưa git reflog hoặc chưa push lên remote.
- Cách khắc phục nếu lỡ tay:
+)Dùng git reflog để tìm lại commit cũ:
git reflog
+)Reset về commit đó:
git reset --hard <commit_hash>
b, git revert không gây mất dữ liệu vì:
+)Không xóa commit cũ, mà tạo một commit mới đảo ngược lại thay đổi.
+)Lịch sử commit vẫn giữ nguyên.
10.Khi dùng git commit --amend, Git thực sự làm gì? Nó có chỉnh sửa commit cũ không, hay tạo ra một commit hoàn toàn mới?
- Lệnh này dùng để chỉnh sửa commit gần nhất
- Cơ chế hoạt động:
+)Git tạo một commit mới dựa trên commit cũ
+)Commit mới có nội dung giống commit trước nhưng có thể thay đổi:
Thông điệp commit (commit message).
File đã được staged (thêm, xóa hoặc sửa đổi file).
Thời gian commit.
Tác giả commit (nếu thay đổi bằng --author option).
+)Git thay đổi HEAD để trỏ đến commit mới
Commit cũ không bị sửa đổi mà chỉ bị bỏ vì không còn nhánh nào trỏ đến nó.
- commit --amend không thay đổi commit cũ mà chỉ tạo ra 1 commit có mã SHA-1 khác thôi. Nếu muốn tìm lại commit cũ thì dùng git reflog.
