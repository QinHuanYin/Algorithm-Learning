package learning1;

import java.util.Arrays;

public class NewListTest {
    public static void main(String[] args) {
        NewList list = new NewList(10);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(Arrays.toString(list.toArray()));
    }
}
