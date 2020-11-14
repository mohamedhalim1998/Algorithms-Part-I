package analysisOflAgorithms;

import java.util.Arrays;
import java.util.HashSet;

public class ThreeSum {
    private int[] arr;

    public ThreeSum(int[] arr) {
        this.arr = arr;
        Arrays.sort(arr);
    }

    public int countThreeSum() {
        int count = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i);
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] - arr[j];
                if (set.contains(-sum)) {
                    count++;
                }
            }
        }
        return count;
    }
}
