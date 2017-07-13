
package frigo.graph;

import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;

public class DirectedGraph<T> {

    private Set<T> nodes = newHashSet();
    private Map<T, Set<T>> inEdges = newHashMap();

    public void addNode (T node) {
        nodes.add(node);
        inEdges.put(node, Sets.<T>newHashSet());
    }

    @SuppressWarnings("unchecked")
    public void addNodes (T... nodesToAdd) {
        for( T node : nodesToAdd ){
            addNode(node);
        }
    }

    public void addEdge (T source, T target) {
        inEdges.get(target).add(source);
    }

    public List<Set<T>> topologicalSort () {
        Set<T> candidates = newHashSet(nodes);
        Set<T> reachable = newHashSet();
        List<Set<T>> topology = newLinkedList();
        while( !candidates.isEmpty() ){
            Set<T> newlyReachable = getNewlyReachable(candidates, reachable);
            candidates.removeAll(newlyReachable);
            reachable.addAll(newlyReachable);
            topology.add(newlyReachable);
        }
        return topology;
    }

    private Set<T> getNewlyReachable (Set<T> candidates, Set<T> reachable) {
        Set<T> newlyReachable = newHashSet();
        for( T candidate : candidates ){
            if( reachable.containsAll(inEdges.get(candidate)) ){
                newlyReachable.add(candidate);
            }
        }
        if( newlyReachable.isEmpty() ){
            throw new IllegalStateException("Cycle detected");
        }
        return newlyReachable;
    }
}
