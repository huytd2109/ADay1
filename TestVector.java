package OnTap.vector;

import java.util.Random;

public class TestVector {
    private MyVector vector;
    private Random random = new Random();

    public TestVector(MyVector vector) {
        this.vector = vector;
    }

    public static void main(String[] args) {
        /* TODO
           - Thực hiện các hàm test.
           - Lưu các kết quả chạy chương trình vào file text có tên <Ten_MaSinhVien_Vector>.txt
             (ví dụ NguyenVanA_123456_Vector.txt). Nén các file source code và file kết quả vào file zip có tên
             <Ten_MaSinhVien_Vector>.zip (ví dụ NguyenVanA_123456_Vector.zip), nộp lên classroom.
         */
        TestVector tester = new TestVector(null);

        System.out.println("=== VECTOR TESTING RESULTS ===\n");

        tester.testArrayVector();
        System.out.println("\n" + "=".repeat(30) + "\n");
        tester.testListVector();
    }

    public void testArrayVector() {
        /* TODO
         - Sinh ngẫu nhiên một số tự nhiên, lưu giá trị sinh ra vào biến n.
         - Tạo ra vector có kích thước n, với các phần tử được sinh ngẫu nhiên, lưu vào biến vector, sử dụng MyArrayVector.
         - Viết các các chứ năng của các vector, như thêm vào phần tử, xóa bớt phần tử, sửa giá trị các
           phần tử, cộng các vector, nhân vector với vô hướng, tích vô hướng 2 vector, chuẩn vector, ... Mỗi lần thay
           đổi vector hoặc tính toán, in các kết quả ra terminal.

         */
        System.out.println("TESTING MyArrayVector");
        System.out.println("====================");

        // Tạo vector với 5 phần tử ngẫu nhiên
        int n = 5;
        MyArrayVector vector = new MyArrayVector();
        System.out.println("Creating vector with " + n + " random elements:");

        for (int i = 0; i < n; i++) {
            double value = Math.round(random.nextDouble() * 10 * 100.0) / 100.0;
            vector.insert(value);
            System.out.println("Added: " + value);
        }
        System.out.println("Initial vector: " + vector);
        System.out.println("Size: " + vector.size());
        System.out.println("Norm: " + String.format("%.2f", vector.norm()));

        // Test các operations cơ bản
        System.out.println("\n--- Basic Operations ---");

        // Thêm phần tử
        vector.insert(3.5);
        System.out.println("After insert 3.5: " + vector);

        // Xóa phần tử
        vector.remove(0);
        System.out.println("After remove index 0: " + vector);

        // Sửa giá trị
        vector.set(9.9, 1);
        System.out.println("After set index 1 to 9.9: " + vector);

        // Cộng scalar
        MyVector added = vector.add(2.0);
        System.out.println("Add 2.0: " + added);

        // Nhân scalar
        vector.scale(2.0);
        System.out.println("Scale by 2.0: " + vector);
        System.out.println("New norm: " + String.format("%.2f", vector.norm()));

        // Tạo vector thứ 2 để test operations
        MyArrayVector vector2 = new MyArrayVector();
        for (int i = 0; i < vector.size(); i++) {
            vector2.insert(i + 1.0);
        }
        System.out.println("Second vector: " + vector2);

        // Vector operations
        System.out.println("Dot product: " + String.format("%.2f", vector.dot(vector2)));
        System.out.println("Sum: " + vector.add(vector2));
        System.out.println("Difference: " + vector.minus(vector2));

        // Power operation
        vector.pow(2);
        System.out.println("After power 2: " + vector);
    }

    public void testListVector() {
        /* TODO
         - Sinh ngẫu nhiên một số tự nhiên, lưu giá trị sinh ra vào biến n.
         - Tạo ra vector có kích thước n, với các phần tử được sinh ngẫu nhiên, lưu vào biến vector, sử dụng MyListVector.
         - Viết các các chứ năng của các vector, như thêm vào phần tử, xóa bớt phần tử, sửa giá trị các
           phần tử, cộng các vector, nhân vector với vô hướng, tích vô hướng 2 vector, chuẩn vector, ... Mỗi lần thay
           đổi vector hoặc tính toán, in các kết quả ra terminal.
         */
        System.out.println("TESTING MyListVector");
        System.out.println("===================");

        // Tạo vector với 4 phần tử ngẫu nhiên
        int n = 4;
        MyListVector vector = new MyListVector();
        System.out.println("Creating vector with " + n + " random elements:");

        for (int i = 0; i < n; i++) {
            double value = Math.round(random.nextDouble() * 10 * 100.0) / 100.0;
            vector.insert(value);
            System.out.println("Added: " + value);
        }
        System.out.println("Initial vector: " + vector);
        System.out.println("Size: " + vector.size());
        System.out.println("Norm: " + String.format("%.2f", vector.norm()));

        // Test các operations cơ bản
        System.out.println("\n--- Basic Operations ---");

        // Thêm và xóa
        vector.insert(5.5, 1);
        System.out.println("Insert 5.5 at index 1: " + vector);

        vector.remove(vector.size()-1);
        System.out.println("Remove last element: " + vector);

        // Cộng và trừ
        vector.addTo(1.0);
        System.out.println("Add 1.0 to all: " + vector);

        vector.minusFrom(0.5);
        System.out.println("Subtract 0.5 from all: " + vector);

        // Tạo vector copy để test equals
        MyListVector copy = new MyListVector();
        for (int i = 0; i < vector.size(); i++) {
            copy.insert(vector.coordinate(i));
        }
        System.out.println("Original: " + vector);
        System.out.println("Copy: " + copy);
        System.out.println("Are equal? " + vector.equals(copy));

        // Extract test
        int[] indices = {0, 2};
        MyVector extracted = vector.extract(indices);
        System.out.println("Extract indices [0,2]: " + extracted);

        System.out.println("Final norm: " + String.format("%.2f", vector.norm()));
    }
}
