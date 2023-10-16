package learning1;

public class Hash {
    // 扩容因子
    private double LoadNum = 2.0 / 3.0;
    // 扩容倍数
    private int load = 2;
    // 键值对数量
    private int size;
    // 桶容量
    private int capacity = 4;
    // 删除标记
    private Pair TROUBLESOME = new Pair(-1, "1");
    // 创建桶
    private Pair[] bucket;

    public Hash() {
        size = 0;
        bucket = new Pair[capacity];
    }

    // 哈希函数
    private int hashBucket(int key) {
        return key % 10;
    }

    // 扩展判定函数
    private int setLoadNum() {
        return size / capacity;
    }

    // 根据key找到桶索引
    public int findBucket(int key) {
        int index = hashBucket(key);
        // 未碰到删除标记就视为 -1
        int flag = -1;
        // 开始寻找
        while (bucket[index] != null) {
            // 记录第一个碰到的删除标记
            if (bucket[index] == TROUBLESOME && flag == -1) {
                flag = index;
            }
            // 如果找到了
            if (bucket[index].key == key) {
                // 之前碰到了删除标记，将删除标记和找到的索引位置调换
                if (flag != -1) {
                    bucket[flag] = bucket[index];
                    bucket[index] = TROUBLESOME;
                    return flag;
                }
                // 如果没有碰到删除标记，则返回index
                return index;
            }
        }
        // 如果没有找到索引
        return flag == -1 ? index : flag;
    }

    // 查找操作
    public int find(int key) {
        int index = findBucket(key);
        if (bucket[index] == null && bucket[index] == TROUBLESOME) {
            return -1;
        }
        return index;
    }

    // 删除操作
    public void delete(int key) {
        int index = findBucket(key);
        if (bucket[index] != null) {
            bucket[index] = TROUBLESOME;
        }
        size--;
    }

    // 增加操作
    public void put(Pair pair) {
        if (setLoadNum() > LoadNum) {
            extend();
        }
        int index = findBucket(pair.key);
        if (bucket[index] != null) {
            bucket[index].key = pair.key;
            bucket[index].val = pair.val;
        }
        size++;
    }

    // 扩容操作
    public void extend() {
        size = 0;
        Pair[] bucketTemp = bucket;
        bucket = new Pair[capacity * load];
        for (Pair pair : bucketTemp) {
            put(pair);
        }
    }

    // 打印操作
    public void print() {
        for (Pair pair : bucket) {
            if (pair == null) {
                System.out.println("null");
            } else if (pair == TROUBLESOME) {
                System.out.println("TROUBLESOME");
            } else {
                System.out.println(pair.key + "->" + pair.val);
            }
        }
    }


}
