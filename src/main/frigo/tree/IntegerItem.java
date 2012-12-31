
package frigo.tree;

public class IntegerItem implements ListItem<Integer, IntegerItem> {

    private int key;
    private IntegerItem right;

    public IntegerItem (int key) {
        this.key = key;
    }

    @Override
    public Integer getKey () {
        return key;
    }

    @Override
    public IntegerItem getRight () {
        return right;
    }

    @Override
    public void setRight (IntegerItem item) {
        right = item;
    }
}
