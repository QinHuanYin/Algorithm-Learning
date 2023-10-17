package learning1;

public class HashPractice {
    public static void main(String[] args) {
        System.out.println(Integer.hashCode(1));

        System.out.println("-------------");

        System.out.println(Double.hashCode(3.14));

        System.out.println("-------------");

        System.out.println(Boolean.hashCode(true));

        System.out.println("-------------");

        System.out.println("hello".hashCode());

        System.out.println("-------------");

        Object arr[] = {"hello", 123};

        System.out.println(arr.hashCode());

        System.out.println("-------------");

        Pair p = new Pair(1, "-1");

        System.out.println(p.hashCode());
    }
}
