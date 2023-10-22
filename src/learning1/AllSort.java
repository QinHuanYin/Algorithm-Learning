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
}
