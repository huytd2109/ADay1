package OnTap.fraction;

public class MyArrayDataSet implements MyDataSet {
    private static int DEFAULT_CAPACITY = 16;
    private MyFraction[] fractions;
    private int length;

    /**
     * Hàm dựng khởi tạo mảng chứa các phân số có kích thước là DEFAULT_CAPACITY.
     */
    public MyArrayDataSet() {
        fractions = new MyFraction[DEFAULT_CAPACITY];
        length = 0;
    }

    /**
     * Hàm dựng khởi tạo mảng chứa các phân số truyền vào.
     * @param fractions
     */
    public MyArrayDataSet(MyFraction[] fractions) {
        this.fractions = new MyFraction[fractions.length];
        for (int i = 0; i < fractions.length; i++) {
            this.fractions[i] = new MyFraction(fractions[i]);
        }
        this.length = fractions.length;
    }

    /**
     * Phương thức chèn phân số fraction vào vị trí index.
     * Nếu index nằm ngoài đoạn [0, length] thì không chèn được vào.
     * Nếu mảng hết chỗ để thêm dữ liệu, mở rộng kích thước mảng gấp đôi.
     * @param fraction là một phân số.
     * @return true nếu chèn được số vào, false nếu không chèn được số vào.
     */
    @Override
    public boolean insert(MyFraction fraction, int index) {
        if (!checkBoundaries(index, length)) {
            return false;
        }

        if (length >= fractions.length) {
            allocateMore();
        }

        // Dịch chuyển các phần tử về phía sau
        for (int i = length; i > index; i--) {
            fractions[i] = fractions[i - 1];
        }

        fractions[index] = new MyFraction(fraction);
        length++;
        return true;
    }

    /**
     * Phương thức thêm phân số fraction vào vị trí cuối cùng chưa có dứ liệu của mảng data.
     * Nếu mảng hết chỗ để thêm dữ liệu, mở rộng kích thước mảng gấp đôi.
     * @param fraction
     * @return true nếu chèn được số vào, false nếu không chèn được số vào.
     */
    @Override
    public boolean append(MyFraction fraction) {
        return insert(fraction, length);
    }

    @Override
    public MyArrayDataSet toSimplify() {
        MyArrayDataSet result = new MyArrayDataSet();
        for (int i = 0; i < length; i++) {
            MyFraction simplified = new MyFraction(fractions[i]);
            simplified.simplify();
            result.append(simplified);
        }
        return result;
    }

    @Override
    public MyArrayDataSet sortIncreasing() {
        MyFraction[] sortedArray = new MyFraction[length];
        for (int i = 0; i < length; i++) {
            sortedArray[i] = new MyFraction(fractions[i]);
        }

        // Bubble sort với comparator tùy chỉnh
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                int cmp = sortedArray[j].compareTo(sortedArray[j + 1]);
                if (cmp > 0 || (cmp == 0 && sortedArray[j].getDenominator() > sortedArray[j + 1].getDenominator())) {
                    MyFraction temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }

        return new MyArrayDataSet(sortedArray);
    }

    @Override
    public MyArrayDataSet sortDecreasing() {
        MyFraction[] sortedArray = new MyFraction[length];
        for (int i = 0; i < length; i++) {
            sortedArray[i] = new MyFraction(fractions[i]);
        }

        // Bubble sort với comparator tùy chỉnh (giảm dần)
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                int cmp = sortedArray[j].compareTo(sortedArray[j + 1]);
                if (cmp < 0 || (cmp == 0 && sortedArray[j].getDenominator() < sortedArray[j + 1].getDenominator())) {
                    MyFraction temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }

        return new MyArrayDataSet(sortedArray);
    }

    @Override
    public String myDataSetToString() {
        if (length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length; i++) {
            sb.append(fractions[i].toString());
            if (i < length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void print() {
        System.out.println(myDataSetToString());
    }

    /**
     * Phương thức mở rộng kích thước mảng gấp đôi, bằng cách tạo ra mảng mới có kích thước
     * gấp đôi, sau đó copy dự liệu từ mảng cũ vào.
     */
    private void allocateMore() {
        MyFraction[] newArray = new MyFraction[fractions.length * 2];
        for (int i = 0; i < length; i++) {
            newArray[i] = fractions[i];
        }
        fractions = newArray;
    }

    /**
     * Phương thức kiểm tra xem index có nằm trong khoảng [0, upperBound] hay không.
     * @param index
     * @param upperBound
     * @return true nếu index nằm trong khoảng [0, upperBound], false nếu ngược lại.
     */
    private boolean checkBoundaries(int index, int upperBound) {
        return index >= 0 && index <= upperBound;
    }
}
