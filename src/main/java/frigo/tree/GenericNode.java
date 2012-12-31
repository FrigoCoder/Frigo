
package frigo.tree;

public class GenericNode<Key extends Comparable<Key>, Value> implements TreeNode<Key, GenericNode<Key, Value>> {

    public final Key key;
    public final Value value;

    private GenericNode<Key, Value> left;
    private GenericNode<Key, Value> right;

    public GenericNode (Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Key getKey () {
        return key;
    }

    @Override
    public GenericNode<Key, Value> getLeft () {
        return left;
    }

    @Override
    public GenericNode<Key, Value> getRight () {
        return right;
    }

    @Override
    public void setLeft (GenericNode<Key, Value> node) {
        left = node;
    }

    @Override
    public void setRight (GenericNode<Key, Value> node) {
        right = node;
    }

}
