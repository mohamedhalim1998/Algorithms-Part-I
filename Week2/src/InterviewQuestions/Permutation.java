package InterviewQuestions;

import java.util.HashSet;

public class Permutation {
    public boolean checkPermutation(int[] a, int[] b) {
        boolean found = true;
        for (int i : a) {
            found = false;
            for (int j : b) {
                if (i == j) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return found;
    }

    public boolean checkPermutation2(int[] a, int[] b) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : a) {
            set.add(i);
        }
        for (int j : b) {
            if (!set.contains(j)) {
                return false;
            }
        }
        return true;
    }

}
