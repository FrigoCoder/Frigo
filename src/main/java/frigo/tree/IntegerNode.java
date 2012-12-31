
package frigo.tree;

public class IntegerNode implements TreeNode<Integer, IntegerNode> {

    public final int value;
    private IntegerNode left;
    private IntegerNode right;

    public IntegerNode (int value) {
        this.value = value;
    }

    @Override
    public Integer getKey () {
        return value;
    }

    @Override
    public IntegerNode getLeft () {
        return left;
    }

    @Override
    public IntegerNode getRight () {
        return right;
    }

    @Override
    public void setLeft (IntegerNode node) {
        left = node;
    }

    @Override
    public void setRight (IntegerNode node) {
        right = node;
    }

}
