package learning1;

public class LinkedList {

    LinkedList(int val) {
        this.val = val;
    }

    int val;
    LinkedList next;

    public static void main(String[] args) {
        LinkedList n0 = new LinkedList(1);
        LinkedList n1 = new LinkedList(3);
        LinkedList n2 = new LinkedList(2);
        n0.next = n1;
        insert(n0, n2);
        System.out.println(access(n0, 1).val);
        System.out.println(find(n0, 3));
        remove(n0);
        config(n0);
    }

    // 插入节点
    public static void insert(LinkedList n, LinkedList P) {
        P.next = n.next;
        n.next = P;
    }

    // 删除节点
    public static void remove(LinkedList n) {
        if (n.next == null) return;
        n.next = n.next.next;
    }

    // 访问节点
    public static LinkedList access(LinkedList Head, int index) {
        for (int i = 0; i < index; i++) {
            if (Head == null) return null;
            Head = Head.next;
        }
        return Head;
    }

    // 查找值的首个节点
    public static int find(LinkedList n, int target) {
        int index = 0;
        while (n != null) {
            if (n.val == target) return index;
            n = n.next;
            index++;
        }
        return -1;
    }

    // 打印节点
    public static void config(LinkedList n) {
        while (n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }

}
