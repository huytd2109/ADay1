package OnTap.fraction;

import java.util.List;
import java.util.ArrayList;

public class MyListDataSet implements MyDataSet {
    private List<MyFraction> fractions;

    /**
     * Hàm dựng khởi tạo list chứa các phân số.
     */
    public MyListDataSet() {
        fractions = new ArrayList<>();
    }

    /**
     * Hàm dựng khởi tạo list chứa các phân số theo truyền vào.
     * @param fractions
     */
    public MyListDataSet(List<MyFraction> fractions) {
        this.fractions = new ArrayList<>();
        for (MyFraction fraction : fractions) {
            this.fractions.add(new MyFraction(fraction));
        }
    }

    @Override
    public boolean insert(MyFraction fraction, int index) {
        if (index < 0 || index > fractions.size()) {
            return false;
        }
        fractions.add(index, new MyFraction(fraction));
        return true;
    }

    @Override
    public boolean append(MyFraction fraction) {
        fractions.add(new MyFraction(fraction));
        return true;
    }

    @Override
    public MyListDataSet toSimplify() {
        MyListDataSet result = new MyListDataSet();
        for (MyFraction fraction : fractions) {
            MyFraction simplified = new MyFraction(fraction);
            simplified.simplify();
            result.append(simplified);
        }
        return result;
    }

    public MyListDataSet sortIncreasing() {
        List<MyFraction> sortedList = new ArrayList<>();
        for (MyFraction fraction : fractions) {
            sortedList.add(new MyFraction(fraction));
        }

        // Bubble sort với comparator tùy chỉnh
        for (int i = 0; i < sortedList.size() - 1; i++) {
            for (int j = 0; j < sortedList.size() - i - 1; j++) {
                int cmp = sortedList.get(j).compareTo(sortedList.get(j + 1));
                if (cmp > 0 || (cmp == 0 && sortedList.get(j).getDenominator() > sortedList.get(j + 1).getDenominator())) {
                    MyFraction temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }

        return new MyListDataSet(sortedList);
    }

    public MyListDataSet sortDecreasing() {
        List<MyFraction> sortedList = new ArrayList<>();
        for (MyFraction fraction : fractions) {
            sortedList.add(new MyFraction(fraction));
        }

        // Bubble sort với comparator tùy chỉnh (giảm dần)
        for (int i = 0; i < sortedList.size() - 1; i++) {
            for (int j = 0; j < sortedList.size() - i - 1; j++) {
                int cmp = sortedList.get(j).compareTo(sortedList.get(j + 1));
                if (cmp < 0 || (cmp == 0 && sortedList.get(j).getDenominator() < sortedList.get(j + 1).getDenominator())) {
                    MyFraction temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }

        return new MyListDataSet(sortedList);
    }

    @Override
    public String myDataSetToString() {
        if (fractions.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < fractions.size(); i++) {
            sb.append(fractions.get(i).toString());
            if (i < fractions.size() - 1) {
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
}
