import java.util.Arrays;

public class IntList {
    public int[] arr;
    public int size;

    public IntList() {
        arr = new int[1];
        size = 0;
    }

    public void add(int a) {
        if (size == arr.length) {
            increase();
        }
        arr[size] = a;
        size++;
    }

    public int get(int i) {
        return arr[i];
    }

    private void increase() {
        arr = Arrays.copyOf(arr, arr.length * 2);
    }
}