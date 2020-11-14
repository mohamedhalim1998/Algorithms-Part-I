package analysisOflAgorithms;

public class BitonicArray {
    private int[] arr;
    private int bitonic;

    public BitonicArray(int[] arr) {
        this.arr = arr;
        bitonic = findBitonic();
    }

    public boolean search(int n) {
        return searchFirstHalf(n) || searchSecondHalf(n);
    }

    private boolean searchSecondHalf(int n) {
        int l = 0;
        int r = bitonic;
        int mid = l + (r - l) / 2;
        while (l < mid) {
            if (arr[mid] == n) {
                return true;
            } else if (arr[mid] > n) {
                r = mid;
                mid = l + (r - l) / 2;
            } else if (arr[mid] < n) {
                l = mid;
                mid = l + (r - l) / 2;
            }
        }
        return false;
    }

    private boolean searchFirstHalf(int n) {
        int l = 0;
        int r = bitonic;
        int mid = l + (r - l) / 2;
        while (l < mid) {
            if (arr[mid] == n) {
                return true;
            } else if (arr[mid] < n) {
                r = mid;
                mid = l + (r - l) / 2;
            } else if (arr[mid] > n) {
                l = mid;
                mid = l + (r - l) / 2;
            }
        }
        return false;
    }

    private int findBitonic() {
        int l = 0;
        int r = arr.length;
        int mid = l + r / 2;
        while (l < mid) {
            if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
                return mid;
            } else if (arr[mid] > arr[mid + 1] && arr[mid] < arr[mid - 1]) {
                l = mid;
                mid = l + r / 2;
            } else if (arr[mid] < arr[mid + 1] && arr[mid] > arr[mid - 1]) {
                r = mid;
                mid = l + r / 2;
            }

        }
        return mid;
    }
}
