
package frigo.tree;

public interface TreeNode<Key extends Comparable<Key>, T> {

    Key getKey ();

    T getLeft ();

    T getRight ();

    void setLeft (T node);

    void setRight (T node);
}
