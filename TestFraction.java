package OnTap.fraction;

import java.util.Random;

public class TestFraction {
    private MyDataSet myDataSet;

    public TestFraction(MyDataSet myDataSet) {
        /* TODO */
        this.myDataSet = myDataSet;
    }

    public static void main(String[] args) {
        /* TODO:
         - Viết code cho các hàm test.
         - Chạy chương trình và lưu kết quả chạy chương trình và file text được đặt tên
           là <TenSinhVien_MaSinhVien_MyFractions>.txt (Ví dụ, NguyenVanA_123456_MyFractions.txt).
         - Nén các file source code và file text kết quả chạy chương trình vào file zip có tên
           <TenSinhVien_MaSinhVien_MyFractions>.zip (Ví dụ, NguyenVanA_123456_MyFractions.zip),
           nộp lên classroom.
         */
        TestFraction tester = new TestFraction(null);

        System.out.println("=== Testing MyArrayDataSet ===");
        tester.testMyArrayDataSet();

        System.out.println("\n=== Testing MyListDataSet ===");
        tester.testMyListDataSet();
    }

    public void testMyArrayDataSet() {
        /* TODO
        1. Sinh ngẫu nhiên một số tự nhiên nằm trong đoạn [30, 50], lưu giá trị vào biến numbers.
        2. Tạo ra numbers phân số, trong đó tử số và mẫu số được sinh ngẫu nhiên là các số tự nhiên nằm trong đoạn [1, 100].
           Lưu các phân số vào trong một tập dữ liệu myDataSet dùng MyArrayDataSet.

        3. Sắp xếp và in ra tập dữ liệu đã tạo ra theo các tiêu chí sau:
             - In ra các phân số theo thứ tự có giá trị tăng dần, nếu phân số bằng nhau thì được sắp xếp theo thứ tự
               có giá trị tăng dần của mẫu số.
             - In ra các phân số theo thứ tự có giá trị giảm dần, nếu phân số bằng nhau thì được sắp xếp theo thứ tự
               có giá trị giảm dần của mẫu số.

         4. In ra các dữ liệu sau:
             - In ra các phân số tối giản của các phân số đã tạo ra theo thứ tự trong dữ liệu gốc.
             - In ra các phân số tối giản theo thứ tự tăng dần.
             - In ra các phân số tối giản theo thứ tự giảm dần.
        */
        Random random = new Random();
        int numbers = random.nextInt(21) + 30; // [30, 50]

        System.out.println("Generated " + numbers + " fractions");

        // Tạo MyArrayDataSet
        MyArrayDataSet arrayDataSet = new MyArrayDataSet();

        // Sinh ngẫu nhiên các phân số
        for (int i = 0; i < numbers; i++) {
            int numerator = random.nextInt(100) + 1; // [1, 100]
            int denominator = random.nextInt(100) + 1; // [1, 100]
            MyFraction fraction = new MyFraction(numerator, denominator);
            arrayDataSet.append(fraction);
        }

        System.out.println("\nOriginal fractions:");
        arrayDataSet.print();

        // Sắp xếp tăng dần
        System.out.println("\nSorted increasing (by value, then by denominator):");
        MyArrayDataSet sortedIncreasing = arrayDataSet.sortIncreasing();
        sortedIncreasing.print();

        // Sắp xếp giảm dần
        System.out.println("\nSorted decreasing (by value, then by denominator):");
        MyArrayDataSet sortedDecreasing = arrayDataSet.sortDecreasing();
        sortedDecreasing.print();

        // Phân số tối giản theo thứ tự gốc
        System.out.println("\nSimplified fractions (original order):");
        MyArrayDataSet simplified = arrayDataSet.toSimplify();
        simplified.print();

        // Phân số tối giản sắp xếp tăng dần
        System.out.println("\nSimplified fractions sorted increasing:");
        MyArrayDataSet simplifiedIncreasing = simplified.sortIncreasing();
        simplifiedIncreasing.print();

        // Phân số tối giản sắp xếp giảm dần
        System.out.println("\nSimplified fractions sorted decreasing:");
        MyArrayDataSet simplifiedDecreasing = simplified.sortDecreasing();
        simplifiedDecreasing.print();
    }

    public void testMyListDataSet() {
        /* TODO
        1. Sinh ngẫu nhiên một số tự nhiên nằm trong đoạn [30, 50], lưu giá trị vào biến numbers.
        2. Tạo ra numbers phân số, trong đó tử số và mẫu số được sinh ngẫu nhiên là các số tự nhiên nằm trong đoạn [1, 100].
           Lưu các phân số vào trong một tập dữ liệu myDataSet dùng MyListDataSet.

        3. Sắp xếp và in ra tập dữ liệu đã tạo ra theo các tiêu chí sau:
             - In ra các phân số theo thứ tự có giá trị tăng dần, nếu phân số bằng nhau thì được sắp xếp theo thứ tự
               có giá trị tăng dần của mẫu số.
             - In ra các phân số theo thứ tự có giá trị giảm dần, nếu phân số bằng nhau thì được sắp xếp theo thứ tự
               có giá trị giảm dần của mẫu số.

         4. In ra các dữ liệu sau:
             - In ra các phân số tối giản của các phân số đã tạo ra theo thứ tự trong dữ liệu gốc.
             - In ra các phân số tối giản theo thứ tự tăng dần.
             - In ra các phân số tối giản theo thứ tự giảm dần.
        */
        Random random = new Random();
        int numbers = random.nextInt(21) + 30; // [30, 50]

        System.out.println("Generated " + numbers + " fractions");

        // Tạo MyListDataSet
        MyListDataSet listDataSet = new MyListDataSet();

        // Sinh ngẫu nhiên các phân số
        for (int i = 0; i < numbers; i++) {
            int numerator = random.nextInt(100) + 1; // [1, 100]
            int denominator = random.nextInt(100) + 1; // [1, 100]
            MyFraction fraction = new MyFraction(numerator, denominator);
            listDataSet.append(fraction);
        }

        System.out.println("\nOriginal fractions:");
        listDataSet.print();

        // Sắp xếp tăng dần
        System.out.println("\nSorted increasing (by value, then by denominator):");
        MyListDataSet sortedIncreasing = listDataSet.sortIncreasing();
        sortedIncreasing.print();

        // Sắp xếp giảm dần
        System.out.println("\nSorted decreasing (by value, then by denominator):");
        MyListDataSet sortedDecreasing = listDataSet.sortDecreasing();
        sortedDecreasing.print();

        // Phân số tối giản theo thứ tự gốc
        System.out.println("\nSimplified fractions (original order):");
        MyListDataSet simplified = listDataSet.toSimplify();
        simplified.print();

        // Phân số tối giản sắp xếp tăng dần
        System.out.println("\nSimplified fractions sorted increasing:");
        MyListDataSet simplifiedIncreasing = simplified.sortIncreasing();
        simplifiedIncreasing.print();

        // Phân số tối giản sắp xếp giảm dần
        System.out.println("\nSimplified fractions sorted decreasing:");
        MyListDataSet simplifiedDecreasing = simplified.sortDecreasing();
        simplifiedDecreasing.print();
    }
}
