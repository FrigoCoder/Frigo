
package frigo.tree;

public interface ListItem<Key, T> {

    Key getKey ();

    T getRight ();

    void setRight (T item);

}
