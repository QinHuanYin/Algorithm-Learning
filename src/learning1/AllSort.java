package learning1;

import java.util.*;

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
    public int partition(int[] arr, int left, int right) {
        int mid = medianThree(arr, left, right, (left + right) / 2);
        // 先将哨兵和最左边的数进行一个交换
        // 注意这里是索引，所以不存在mid不在的情况
        swap(arr, mid, left);
        int i = left, j = right;
        while (i < j) {
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

    /**
     * 归并排序
     * 其思路和快速排序的思路差不多
     * 也就是分开的一个思想
     * 它是将左右两边的进行分开
     * 然后一步步递归达到左右两边都符合排序的规则
     * 然后再进行合并
     * 如同我们知道的那样，它合并的规则是先合并左边再右边再合并处理
     * 符合左右根的遍历次序
     * 也就是后序遍历
     */
    public void merge(int[] arr, int left, int right, int mid) {
        // 先创建辅助数组
        int[] arrTemp = Arrays.copyOfRange(arr, left, right + 1);
        int leftStart = left - left, leftEnd = mid - left;
        int rightStart = mid + 1 - left, rightEnd = right - left;
        int i = leftStart, j = rightStart;
        // 安排指针k，一步步来填充重新排序后的数组
        for (int k = left; k <= right; k++) {
            // 当左边的全部合并之后，就开始合并右边的了
            if (i > leftEnd) {
                arr[k] = arr[j++];
            }
            // 当右边的全部合并之后，就开始合并左边的
            // 或者还有一种情况，就是左边的小于右边的
            else if (j > rightEnd || arr[i] < arr[j]) {
                arr[k] = arr[i++];
            }
            // 剩下的一种情况就是左边的大于右边的，合并右边的
            else arr[k] = arr[j++];
        }
    }

    public void mergeSort(int[] nums, int left, int right) {
        // 当数组长度为1时
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(nums, mid, right);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, right, mid);
    }

    /**
     * 堆排序
     * 其核心思想是堆自上而下的堆化操作
     * 首先对传进来的数组进行堆化处理
     * 然后将最右子节点和顶节点相交换
     * 这样最大的那个节点就会出去放在最末尾
     * 这样也就会有排序的功能
     * 请注意建堆的时间复杂度为O(n)，详情见k神堆那一章
     * 而最大的节点还在数组中，只是放在了最右子节点的位置
     */
    public void siftDown(int[] nums, int n, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int ma = i;
        int temp;
        while (true) {
            if (nums[left] > nums[ma] && left < n) {
                ma = left;
            } else if (nums[right] > nums[ma] && right < n) {
                ma = right;
            }
            temp = nums[left];
            nums[left] = nums[ma];
            nums[ma] = temp;
            if (ma == i) return;
            i = ma;
        }
    }

    public void heapSort(int[] nums) {
        // 建堆操作
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            siftDown(nums, nums.length, i);
        }

        // 循环提取最大的元素
        // 第一个元素无需处理，因为已经排序好了
        for (int i = nums.length - 1; i > 0; i--) {
            // 交换堆顶和堆底的元素
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            // 每一次数组都会减少，所以长度就是i
            siftDown(nums, i, 0);
        }
    }

    /**
     * 桶排序
     * 桶排序的核心是输入对应数组后
     * 对每一个数据进行相应的映射操作
     * 然后内部再采用算法排序
     * 最后再将桶中的每一个元素储存起来
     */
    public void bucketSort(float[] nums) {
        // 每一个桶中存放两个元素，有个k个桶
        int k = nums.length / 2;
        // 创建列表
        List<List<Float>> list = new ArrayList<>();
        // 对每一个元素进行映射，让其映射的范围是[0, k - 1]
        // 因为f的范围是[0, 1)，相乘之后最大也就是逼近于k
        // 但此时取整，所以是最大时k-1
        for (float f : nums) {
            int i = (int) f * k;
            list.get(i).add(f);
        }

        // 桶内排序
        for (List<Float> lists : list) {
            // 建议还是用Collection.sort方法
            lists.sort(new Comparator<Float>() {
                @Override
                public int compare(Float o1, Float o2) {
                    return Float.compare(o1, o2);
                }
            });
        }

        // 遍历桶
        int i = 0;
        for (List<Float> lists : list) {
            for (float f : lists) {
                nums[i++] = f;
            }
        }
    }
}
