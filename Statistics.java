package hus.oop.statistics;

public class Statistics {
    private MyList data;

    /**
     * Khởi tạo dữ liệu cho BasicStatistic.
     */
    public Statistics(MyList data) {
        this.data = data;
    }

    /**
     * Lấy giá trị lớn nhất trong list.
     * @return giá trị lớn nhất.
     */
    public double max() {
        if (data.size() == 0) {
            throw new IllegalStateException("Empty list has no maximum value");
        }

        MyIterator iterator = data.iterator(0);
        double max = (double) iterator.next();

        while (iterator.hasNext()) {
            double current = (double) iterator.next();
            if (current > max) {
                max = current;
            }
        }

        return max;    }

    /**
     * Lấy giá trị nhỏ nhất trong list.
     * @return giá trị nhỏ nhất.
     */
    public double min() {
        if (data.size() == 0) {
            throw new IllegalStateException("Empty list has no minimum value");
        }

        MyIterator iterator = data.iterator(0);
        double min = (double) iterator.next();

        while (iterator.hasNext()) {
            double current = (double) iterator.next();
            if (current < min) {
                min = current;
            }
        }
        return min;
    }

    /**
     * Tính kỳ vọng của mẫu theo dữ liệu trong list.
     * @return kỳ vọng.
     */
    public double mean() {
        if (data.size() == 0) {
            throw new IllegalStateException("Empty list has no mean");
        }

        double sum = 0;
        MyIterator iterator = data.iterator(0);

        while (iterator.hasNext()) {
            sum += (double) iterator.next();
        }

        return sum / data.size();    }

    /**
     * Tính phương sai của mẫu theo dữ liệu trong list.
     * @return phương sai.
     */
    public double variance() {
        if (data.size() <= 1) {
            throw new IllegalStateException("Cannot calculate variance for a sample of size <= 1");
        }

        double mean = mean();
        double sumSquaredDifferences = 0;
        MyIterator iterator = data.iterator(0);

        while (iterator.hasNext()) {
            double diff = (double) iterator.next() - mean;
            sumSquaredDifferences += diff * diff;
        }

        return sumSquaredDifferences / (data.size() - 1);    }

    /**
     * Tìm kiếm trong list có phẩn tử nào có giá trị bằng data không, sử dụng binarySearch trong list.
     * Trả về index một phần tử có giá trị bằng data, nếu không tìm thấy thì trả về -1.
     * @return
     */
    public int search(double data) {
        return this.data.binarySearch(data);
    }

    /**
     * Tính rank của các phần tử trong list.
     * @return rank của các phần tử trong list
     */
    public double[] rank() {
        int size = data.size();
        if (size == 0) {
            return new double[0];
        }

        // Extract values into an array
        double[] values = new double[size];
        MyIterator iterator = data.iterator(0);
        for (int i = 0; i < size; i++) {
            values[i] = (double) iterator.next();
        }

        // Calculate ranks (1-based)
        double[] ranks = new double[size];
        for (int i = 0; i < size; i++) {
            int lessThan = 0;
            int equalTo = 0;

            for (int j = 0; j < size; j++) {
                if (values[j] < values[i]) {
                    lessThan++;
                } else if (values[j] == values[i]) {
                    equalTo++;
                }
            }

            // Average rank for ties
            double sumRanks = 0;
            for (int k = 0; k < equalTo; k++) {
                sumRanks += (lessThan + 1 + k);
            }
            ranks[i] = sumRanks / equalTo;
        }

        return ranks;    }
}
