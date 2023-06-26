package Lab05;

public class NodeAvl {

	int key;
    NodeAvl left, right;
    int height;

    public NodeAvl(int item) {
        key = item;
        height = 1;
    }
}
