package hus.oop.statistics;
import java.util.Random;


public class TestStatistics {
    private Statistics statistics;

    public TestStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public static void main(String[] args) {
        /* TODO
           - Thực hiện từng hàm test, lưu kết quả chạy chương trình và file text được đặt tên
             là <TenSinhVien_MaSinhVien_Statistics>.txt (Ví dụ, NguyenVanA_123456_Statistics.txt).
           - Nén các file source code và file text kết quả chạy chương trình vào file zip có tên
             <TenSinhVien_MaSinhVien_Statistics>.zip (Ví dụ, NguyenVanA_123456_Statistics.zip),
             nộp lên classroom.
         */
        TestStatistics test = new TestStatistics(null);
        test.testMyArrayList();
        test.testMyLinkedList();
    }

    public void testMyArrayList() {
        /* TODO
           - Sinh ngẫu nhiên một số tự nhiên nằm trong đoạn [30, 50], lưu vào biến length.
           - Tạo một list kiểu MyArrayList, có các phần tử dữ liệu kiểu double được sinh ngẫu nhiên
             nằm trong đoạn [1, 20]. Tạo Statistics có dữ liệu là list dữ liệu vừa tạo, lưu vào statistics.
           - Sử dụng Statistics để tính các đại lượng thống kê cơ bản (max, min, kỳ vọng, phương sai, rank, sắp xếp, tìm kiếm).
             In ra terminal tập dữ liệu, tập dữ liệu được sắp xếp, các đại lượng thống kê và kết quả chức năng tìm kiếm.
         */
        int length = 30 + (int)(Math.random() * 21);
        System.out.println("Generated length for MyArrayList: " + length);

        MyArrayList list = new MyArrayList();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            list.add(1 + 19 * rand.nextDouble());
        }

        statistics = new Statistics(list);

        printStatisticsResults("MyArrayList", list);
    }

    public void testMyLinkedList() {
        /* TODO
           - Sinh ngẫu nhiên một số tự nhiên nằm trong đoạn [30, 50], lưu vào biến length.
           - Tạo một list kiểu MyLinkedList, có các phần tử lưu dữ liệu kiểu double được sinh ngẫu nhiên
             nằm trong đoạn [1, 20]. Tạo Statistics có dữ liệu là list dữ liệu vừa tạo, lưu vào statistics.
           - Sử dụng Statistics để tính các đại lượng thống kê cơ bản (max, min, kỳ vọng, phương sai, rank, sắp xếp, tìm kiếm).
             In ra terminal tập dữ liệu, tập dữ liệu được sắp xếp, các đại lượng thống kê và kết quả chức năng tìm kiếm.
         */
        int length = 30 + (int)(Math.random() * 21);
        System.out.println("Generated length for MyLinkedList: " + length);

        MyLinkedList list = new MyLinkedList();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            list.add(1 + 19 * rand.nextDouble());
        }

        statistics = new Statistics(list);

        printStatisticsResults("MyLinkedList", list);
    }

    private void printStatisticsResults(String listType, MyList list) {
        System.out.println("Original data: " + list);

        MyList sortedList = list.sortIncreasing();
        System.out.println("Sorted data: " + sortedList);

        System.out.printf("Maximum value: %.2f\n", statistics.max());
        System.out.printf("Minimum value: %.2f\n", statistics.min());
        System.out.printf("Mean value: %.2f\n", statistics.mean());
        System.out.printf("Variance: %.2f\n", statistics.variance());

        double[] ranks = statistics.rank();
        System.out.print("Ranks: [");
        for (int i = 0; i < Math.min(10, ranks.length); i++) {
            System.out.printf("%.1f", ranks[i]);
            if (i < Math.min(9, ranks.length - 1)) {
                System.out.print(", ");
            }
        }
        if (ranks.length > 10) {
            System.out.print(", ...");
        }
        System.out.println("]");


        double valueToSearch = 0.0;
        MyIterator iterator = sortedList.iterator(sortedList.size() / 2);
        if (iterator.hasNext()) {
            valueToSearch = ((Number)iterator.next()).doubleValue();
        }

        int index = statistics.search(valueToSearch);
        System.out.printf("Searching for %.2f: %s\n", valueToSearch,
                index == -1 ? "Not found" : "Found at index " + index);

        double nonExistingValue = 100;
        index = statistics.search(nonExistingValue);
        System.out.printf("Searching for %.2f: %s\n", nonExistingValue,
                index == -1 ? "Not found" : "Found at index " + index);
    }
}
