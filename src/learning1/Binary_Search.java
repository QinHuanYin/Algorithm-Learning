package learning1;

public class Binary_Search {
    // 二分查找
    // 返回的是索引值
    public int Binary_Search(int[] arr, int num) {
        int i = 0, j = arr.length - 1;
        // 这里是双闭合数组，当 i=j 的时候，可以继续循环，因为此时j存在
        while (i <= j) {
            int m = (i + j) / 2;
            if (arr[m] > num) {
                j = m - 1;
            } else if (arr[m] < num) {
                i = m + 1;
            } else return m;
        }
        return -1;
    }

     /*二分查找索引
     根据推理所得，如果当一直循环下去时
     当中间值大于 num，i 朝着大于 num 的地方移动
     当中间值小于 num，j 朝着小于 num 的地方移动
     而最后 i 会打过 j，那么最终的地址就是i
     因为无重复的较简单，所以选择有重复的*/
    public int Binary_NewSearch(int[] arr, int num) {
        int i = 0, j = arr.length - 1;
        // 这里是双闭合数组，当 i=j 的时候，可以继续循环，因为此时j存在
        while (i <= j) {
            int m = (i + j) / 2;
            if (arr[m] > num) {
                j = m - 1;
            } else if (arr[m] < num) {
                i = m + 1;
            } else {
                // 这里也是 m+1，因为当存在重复元素时
                // 最小的那个 num 一定是在 [i, m - 1] 范围内
                j = m - 1;
            }
        }
        return i;
    }
}
