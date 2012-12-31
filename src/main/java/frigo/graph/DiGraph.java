
package frigo.graph;

public interface DiGraph extends Graph {

    Node getSource (Edge x);

    Node getTarget (Edge x);

    void setSource (Edge x, Node y);

    void setTarget (Edge x, Node y);
}
