
package frigo.graph;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<T> {

    private Map<T, T> parent = new HashMap<>();
    private Map<T, Integer> rank = new HashMap<>();

    public void add (T element) {
        parent.put(element, element);
        rank.put(element, 0);
    }

    @SafeVarargs
    public final void add (T... elements) {
        for( T element : elements ){
            add(element);
        }
    }

    public T find (T x) {
        T xParent = parent.get(x);
        if( !xParent.equals(x) ){
            parent.put(x, find(xParent));
        }
        return parent.get(x);
    }

    public void union (T x, T y) {
        T xRoot = find(x);
        T yRoot = find(y);
        if( xRoot.equals(yRoot) ){
            return;
        }
        int xRank = rank.get(xRoot);
        int yRank = rank.get(yRoot);
        if( xRank < yRank ){
            parent.put(xRoot, yRoot);
        }else{
            parent.put(yRoot, xRoot);
        }
        if( xRank == yRank ){
            rank.put(xRoot, xRank + 1);
        }
    }

}
