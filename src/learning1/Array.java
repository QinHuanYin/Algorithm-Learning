package learning1;

public class Array {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};
        delete(arr, 1);
        // insert(arr, 1, 2);
        config(arr);
    }

    // 插入
    public static void insert(int arr[], int num, int index) {
        for (int i = arr.length - 1; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = num;
    }

    // 删除
    public static void delete(int arr[], int index) {
        for (int i = index; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = 0;
    }

    // 循环打印
    public static void config(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
