
package frigo.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Preconditions;

public class MedianOfMedians {

    private static final int SMALL_SIZE = 5;

    public static <T extends Comparable<T>> T median(List<T> list) {
        return select(list, (list.size() - 1) / 2);
    }

    public static <T extends Comparable<T>> T select(List<T> list, int k) {
        if (list.size() <= SMALL_SIZE) {
            return smallCase(list, 0, list.size() - 1, k);
        }

        T pivot = approximateMedian(list);

        List<T> smaller = new LinkedList<>();
        List<T> greater = new LinkedList<>();

        for (T elem : list) {
            int compare = elem.compareTo(pivot);
            if (compare < 0) {
                smaller.add(elem);
            }
            if (compare > 0) {
                greater.add(elem);
            }
        }

        if (k < smaller.size()) {
            return select(smaller, k);
        }

        if (k >= list.size() - greater.size()) {
            return select(greater, k - (list.size() - greater.size()));
        }

        return pivot;
    }

    public static <T extends Comparable<T>> T approximateMedian(List<T> list) {
        if (list.size() <= SMALL_SIZE) {
            int high = list.size() - 1;
            return smallCase(list, 0, high, (high - 0) / 2);
        }
        return approximateMedian(calculateMediansOfFive(list));
    }

    private static <T extends Comparable<T>> List<T> calculateMediansOfFive(List<T> list) {
        List<T> medians = new ArrayList<>((list.size() + SMALL_SIZE - 1) / SMALL_SIZE);
        for (int low = 0; low < list.size(); low += SMALL_SIZE) {
            int high = Math.min(low + SMALL_SIZE - 1, list.size() - 1);
            medians.add(smallCase(list, low, high, (high - low) / 2));
        }
        return medians;
    }

    private static <T extends Comparable<T>> T smallCase(List<T> list, int low, int high, int k) {
        Preconditions.checkArgument(low <= high);
        List<T> clone = new ArrayList<>(high - low + 1);
        for (int i = low; i <= high; i++) {
            clone.add(list.get(i));
        }
        Collections.sort(clone);
        return clone.get(k);
    }

}
