package learning1;

public class AllSort {
    /**
     * 选择排序
     * 一开始还以为和冒泡是一个性质
     * 结果是找到每一轮的最小值，最后再和开头换
     */
    public void selectionSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            // 用k来记录当前最小的索引
            int k = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[k]) {
                    k = j;
                }
            }
            int temp;
            temp = nums[k];
            nums[k] = nums[i];
            nums[i] = temp;
        }
    }

    /**
     * 冒泡优化
     * 设置一个flag
     * 当整个流程并没有交换仍和一个元素时
     * 这个时候就表明了，已经排序完成
     * 所以说最佳的情况下只需要遍历一次
     * 最佳时间复杂度也就为 O(n)
     */
    public void bubbleSortWithFlag(int nums[]) {
        int n = nums.length;
        boolean flag = false;
        int temp;
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                    flag = true;
                }
                // 当没有仍和交换时，直接结束
                if (!flag) break;
            }
        }
    }

    /**
     * 插入排序
     * 插入排序的思想就是
     * 一个个用插入的方式进行排序
     * 比如说排列第二个
     * 将其和第一进行对比，如果比第一个小就插入到第一个的前面
     * 思考了一下，好像没有必要用一个flag来记录其是否有序
     * 因为如果它是一个完全有序的数组
     * 那就意味着前面的元素都是小于base的
     * 那么内层循环根本不会进行
     * 也就意味着其最理想的情况下时间复杂度为 O(n)
     */
    public void insertionSort(int[] arr) {
        int n = arr.length;
        // 这里是从第二个开始排列，因为对第一个排列没有意义
        for (int i = 1; i < n; i++) {
            int base = arr[i];
            // 从i-1开始，往前面循环
            int j = i - 1;
            // 前面如果有比base小的情况，那么就进行往后一位
            // 要注意的时前面已经是一个排序完了的数组
            while (arr[j] < base && j >= 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = base;
        }
    }

    /**
     * 快速排序
     * 其核心思想时找到一个基准点
     * 让左边的都小于基准点，右边的都大于基准点
     * 然后重新再对左边和右边进行重新排序
     */
    public void swap(int[] arr, int i, int j) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 采用异或的方式来判定基准数的选取，基于中位数判断
    // 注意if的规则是0假1真
    // 然后1^1为0，0^1为1
    public int medianThree(int[] arr, int left, int right, int mid) {
        if ((arr[left] < arr[mid]) ^ (arr[left] < arr[right])) {
            return left;
        } else if ((arr[mid] < arr[left]) ^ (arr[mid] < arr[right])) {
            return mid;
        } else return right;
    }

    // 将哨兵挪到正确的位置，也就是左边的都小于哨兵，右边的都大于哨兵
    // 返回哨兵的索引，方便递归调用
    public int partition(int[] arr, int left, int right){
        int mid = medianThree(arr, left, right, (left + right) / 2);
        // 先将哨兵和最左边的数进行一个交换
        // 注意这里是索引，所以不存在mid不在的情况
        swap(arr, mid, left);
        int i = left, j = right;
        while(i < j) {
            // 循环找，一直找到大于哨兵的点为止
            while (arr[i] <= arr[left] && i < j) {
                i++;
            }
            while (arr[j] >= arr[left] && i < j) {
                j--;
            }
            swap(arr, i, j);
        }
        // 已经找到哨兵该到的位置了
        // 交换哨兵和最左边的
        swap(arr, left, i);
        return i;
    }

    // 递归调用
    public void quickSort(int[] arr, int left, int right) {
        // 子数组长度为 1 时终止递归
        if (left >= right)
            return;
        // 先找到最中间的那个哨兵
        // 在完成找哨兵的时候，已经将左右两侧都安排好了
        // 左边的小于哨兵，右边的大于哨兵
        int mid = partition(arr, left, right);
        quickSort(arr, mid + 1, right);
        quickSort(arr, left, mid - 1);
    }

}
