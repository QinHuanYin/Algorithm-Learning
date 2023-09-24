package learning1;

import java.util.Arrays;

public class NewList {

    int num[];
    int capacity;
    int size = 0;
    int extend = 2;

    // 构造函数
    public NewList(int number) {
        this.capacity = number;
        num = new int[this.capacity];
    }

    // 获取列表当前元素长度
    public int getSize() {
        return size;
    }

    // 获取列表的容量
    public int getCapacity() {
        return capacity;
    }

    // 访问元素
    public int getSum(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        return num[index];
    }

    // 更新元素
    public void setSum(int index, int sum) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        num[index] = sum;
    }

    // 向尾部添加元素
    public void add(int sum) {
        if (getSize() == getCapacity()) extendCapacity();
        num[getSize()] = sum;
        size++;
    }

    // 中间插入元素
    public void inset(int index, int sum) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("索引越界");
        }

        if (getSize() == getCapacity()) extendCapacity();

        for(int i = index; i <= getSize() - 1; i++) {
            num[i + 1] = num[i];
        }

        num[index] = sum;
        size++;
    }

    // 删除元素
    public int remove(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("索引越界");
        }

        if (getSize() == getCapacity()) extendCapacity();

        int sum = num[index];

        for (int i = index; i < getSize() - 1; i++) {
            num[i] = num[i + 1];
        }

        size--;
        return sum;
    }

    // 扩容列表
    public void extendCapacity() {
        num = Arrays.copyOf(num, getCapacity() * extend);
        capacity = num.length;
    }

    // 将列表转换为数组
    public int[] toArray() {
        int arr[] = new int [getSize()];
        for(int i = 0; i < getSize(); i++) {
            arr[i] = num[i];
        }
        return arr;
    }
}
