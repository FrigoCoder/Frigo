
package frigo.tree;

public class SplayList<Key, T extends ListItem<Key, T>> {

    private T root;

    public boolean contains (Key key) {
        for( T parent = null, current = root; current != null; parent = current, current = current.getRight() ){
            if( current.getKey().equals(key) ){
                unchain(parent, current);
                pushFront(current);
                return true;
            }
        }
        return false;
    }

    public T find (Key key) {
        if( !contains(key) ){
            throw new IllegalArgumentException();
        }
        return root;
    }

    public void insert (T item) {
        if( contains(item.getKey()) ){
            throw new IllegalArgumentException();
        }
        pushFront(item);
    }

    public boolean isEmpty () {
        return root == null;
    }

    public void remove (Key key) {
        if( !contains(key) ){
            throw new IllegalArgumentException();
        }
        unchain(null, root);
    }

    public int size () {
        int size = 0;
        for( T current = root; current != null; current = current.getRight() ){
            size++;
        }
        return size;
    }

    private void pushFront (T item) {
        item.setRight(root);
        root = item;
    }

    private void unchain (T parent, T current) {
        if( current == root ){
            root = root.getRight();
        }else{
            parent.setRight(current.getRight());
        }
        current.setRight(null);
    }

}
