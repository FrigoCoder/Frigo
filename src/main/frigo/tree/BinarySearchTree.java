
package frigo.tree;

public class BinarySearchTree<Key extends Comparable<Key>, T extends TreeNode<Key, T>> {

    private T root;

    public T find (Key key) {
        BinarySearchTreeFinder<Key, T> finder = new BinarySearchTreeFinder<>(root, key);
        finder.run();
        if( finder.found() ){
            return finder.current;
        }
        throw new IllegalArgumentException();
    }

    public void insert (T node) {
        if( isEmpty() ){
            insertAsRoot(node);
        }else{
            insertUnderClosest(node);
        }
    }

    public boolean isEmpty () {
        return root == null;
    }

    public int size () {
        return size(root);
    }

    private void insertAsRoot (T node) {
        root = node;
    }

    private void insertUnderClosest (T node) {
        BinarySearchTreeFinder<Key, T> finder = new BinarySearchTreeFinder<>(root, node.getKey());
        finder.run();
        if( finder.found() ){
            throw new IllegalArgumentException();
        }
        if( finder.cmp > 0 ){
            finder.current.setLeft(node);
        }else{
            finder.current.setRight(node);
        }
    }

    private int size (T parent) {
        return parent == null ? 0 : 1 + size(parent.getLeft()) + size(parent.getRight());
    }
}
