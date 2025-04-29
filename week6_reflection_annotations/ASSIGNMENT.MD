# Week 6: Java Reflection and Annotations

1. Reflection có thể thay đổi các thành phần final trong một lớp được không? Code ví dụ minh hoạ cách thay đổi một biến static final.

- Có thể thay doi duoc các thành phần final trong 1 lớp.
  VD:
  class Example {
  public static final String CONSTANT = "Hello, World!";
  }
  public class ReflectionExample {
  public static void main(String[] args) throws Exception {
  // Lấy đối tượng Field của biến CONSTANT
  Field field = Example.class.getDeclaredField("CONSTANT");

          // Bỏ giới hạn truy cập
          field.setAccessible(true);

          // Loại bỏ modifier FINAL
          Field modifiersField = Field.class.getDeclaredField("modifiers");
          modifiersField.setAccessible(true);
          modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

          // Thay đổi giá trị của CONSTANT
          field.set(null, "Modified Value!");

          // Kiểm tra lại giá trị
          System.out.println("Updated CONSTANT: " + Example.CONSTANT);
      }

  }

2. Reflection có ảnh hưởng như thế nào đến hiệu suất của một ứng dụng Java?

Nó truy cập chậm hơn so với việc truy cập trực tiếp, không tận dung được Just-in-time(JIT)của JVM de tái sử dụng code

3. Rủi ro về bảo mật khi sử dụng reflection? Làm thế nào để giảm thiểu rủi ro này?

- Phá vỡ tính đóng gói, bỏ qua quyền truy cập,... rủi ro cao về bảo mật
- Hạn chế sử dụng Java Reflection khi không cần thiết

4. Làm thế nào để truy cập thông tin về nested classes và anonymous classes bằng Reflection?

- getDeclaredClasses() → Lấy tất cả nested classes (kể cả private).

- getClasses() → Lấy nested classes public.

5. Cơ chế hoạt động của setAccessible(true)?

- Trong Java, phương thức setAccessible(true) thuộc lớp AccessibleObject
  (mà Field, Method, và Constructor kế thừa) cho phép truy cập vào các thành phần private,
  protected, hoặc default của một lớp thông qua Java Reflection.

6. Giải thích cơ chế hoạt động của Annotation Processor? Làm thế nào chúng được tích hợp vào quá trình biên dịch?

- Annotation Processor cho phép kiểm tra và generate ra code dựa trên Annotation trong quá trình compile
- CHúng ta có thể tích hợp vào quá trình biên dịch khi dùng trực tiếp javac
  javac -processor com.example.MyProcessor MyClass.java
  hoặc cũng có thể dùng với maven,gradle bằng cách config

7. Annotation processors có ảnh hưởng như thế nào đến quá trình biên dịch?

Annotation Processor làm tăng thời gian biên dịch vì trình biên dịch phải:

Quét tất cả các file nguồn để tìm annotation.

Thực hiện xử lý trên các annotation đó.

Có thể phải tạo thêm file mã nguồn mới.

Nếu có file mới, trình biên dịch phải biên dịch lại vòng lặp (recompile) các file mới sinh ra.

8.  Làm thế nào để tạo một annotation có thể được kế thừa?

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyAnnotation {
String value() default "Default Value";
}

@MyAnnotation("Applied to Parent")
class Parent {
}

class Child extends Parent {
}

public class TestInheritance {
public static void main(String[] args) {
Class<?> childClass = Child.class;
MyAnnotation annotation = childClass.getAnnotation(MyAnnotation.class);

        if (annotation != null) {
            System.out.println("Annotation value: " + annotation.value());
        } else {
            System.out.println("Annotation not inherited");
        }
    }

}

9. Phân biệt các loại RetentionPolicy của @Retention?

- RetentionPolicy.SOURCE:
  🔹 Annotation chỉ tồn tại trong mã nguồn và bị loại bỏ khi biên dịch.
  🔹 Không có mặt trong file .class, không thể truy xuất bằng Reflection.
  VD: @Override
- RetentionPolicy.CLASS:
  🔹 Annotation được giữ lại trong file .class nhưng không thể truy xuất lúc runtime.
  🔹 Trình biên dịch có thể dùng annotation này, nhưng không thể truy xuất bằng Reflection.
  💡 Ứng dụng thực tế:

Annotation dành cho compiler, nhưng không cần lúc runtime.

Android framework sử dụng nhiều annotation CLASS (ví dụ: @SuppressLint).

Annotation hỗ trợ ProGuard (tối ưu mã bytecode).

-RetentionPolicy.RUNTIME:
🔹 Annotation tồn tại trong file .class và có thể truy xuất lúc runtime bằng Reflection.
🔹 Hữu ích cho framework, thư viện cần đọc annotation trong lúc chạy.

💡 Ứng dụng thực tế:

Spring (@Component, @Service, @Autowired): Spring sử dụng các annotation này để quét bean lúc runtime.

JUnnnoit (@Test): JUnit tìm kiếm atation @Test để chạy các unit test.

Hibernate (@Entity, @Table): Hibernate dùng để ánh xạ class với database.

10. Sự khác biệt giữa reflection và annotation processors trong việc xử lý annotation?

-Reflection - Xử lý Annotation Lúc Runtime:
🔹 Cách hoạt động:

Dùng Java Reflection API (java.lang.reflect) để đọc metadata của annotation.

Chỉ hoạt động với annotation có RetentionPolicy.RUNTIME.

Thường được dùng trong các framework như Spring, Hibernate, JUnit.

-Annotation Processor - Xử lý Annotation Lúc Compile-Time:

🔹 Cách hoạt động:

Dùng Annotation Processor (javax.annotation.processing.Processor) để phân tích và xử lý annotation lúc biên dịch (compile-time).

Thường được dùng để tạo code tự động, kiểm tra code hoặc biến đổi mã nguồn trước khi biên dịch.

Không thể truy xuất lúc runtime, thường sử dụng RetentionPolicy.SOURCE hoặc RetentionPolicy.CLASS.
