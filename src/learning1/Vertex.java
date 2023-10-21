package learning1;

// 这是顶点类
public class Vertex {
    private int val;

    public Vertex(int val) {
        this.val = val;
    }

    public Vertex[] get (int[] arr) {
        Vertex[] vertex = new Vertex[arr.length];
        for (int i = 0; i < arr.length; i++) {
            vertex[i].val = arr[i];
        }
        return vertex;
    }
}
