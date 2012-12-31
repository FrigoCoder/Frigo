
package frigo.tree;

public class BinarySearchTreeFinder<Key extends Comparable<Key>, T extends TreeNode<Key, T>> {

    public int cmp;
    public T current;
    public T down;
    public Key key;

    public BinarySearchTreeFinder (T root, Key key) {
        this.current = null;
        this.down = root;
        this.key = key;
    }

    public boolean found () {
        return current != null && cmp == 0;
    }

    public void run () {
        while( stepPossible() ){
            step();
        }
    }

    private void step () {
        current = down;
        cmp = current.getKey().compareTo(key);
        down = cmp == 0 ? null : cmp > 0 ? current.getLeft() : current.getRight();
    }

    private boolean stepPossible () {
        return down != null;
    }
}
