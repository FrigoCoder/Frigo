
package frigo.graph;


public class DepthFirstSearch {

    /**
     * Based on http://www.boost.org/doc/libs/1_63_0/libs/graph/doc/undirected_dfs.html
     */

    @SuppressWarnings("unused")
    public interface Visitor {

        default void initializeVertex (Node node) {}

        default void startVertex (Node node) {}

        default void discoverVertex (Node node) {}

        default void examineEdge (Edge edge) {}

        default void treeEdge (Edge edge) {}

        default void backEdge (Edge edge) {}

        default void finishEdge (Edge edge) {}

        default void finishVertex (Node node) {}

    }

    private enum Color {
        WHITE,
        GREY,
        BLACK
    }

    private Graph graph;
    private Visitor visitor;
    private NodeMap<Color> vertexColors = new NodeMap<>();
    private EdgeMap<Color> edgeColors = new EdgeMap<>();

    public DepthFirstSearch (Graph graph, Visitor visitor) {
        this.graph = graph;
        this.visitor = visitor;
    }

    public void run () {
        run(null);
    }

    public void run (Node root) {
        for( Node node : graph.nodes() ){
            vertexColors.put(node, Color.WHITE);
            visitor.initializeVertex(node);
        }
        for( Edge edge : graph.edges() ){
            edgeColors.put(edge, Color.WHITE);
        }
        if( root != null ){
            visitor.startVertex(root);
            dfsVisitRecursive(root);
        }
        for( Node node : graph.nodes() ){
            if( vertexColors.get(node) == Color.WHITE ){
                visitor.startVertex(node);
                dfsVisitRecursive(node);
            }
        }
    }

    private void dfsVisitRecursive (Node source) {
        vertexColors.put(source, Color.GREY);
        visitor.discoverVertex(source);

        for( Edge edge : graph.outEdges(source) ){
            visitor.examineEdge(edge);

            Node target = edge.getTarget(source);
            switch( vertexColors.get(target) ){

                case WHITE:
                    edgeColors.put(edge, Color.BLACK);
                    visitor.treeEdge(edge);
                    dfsVisitRecursive(target);
                    break;

                case GREY:
                    if( edgeColors.get(edge) == Color.WHITE ){
                        visitor.backEdge(edge);
                    }
                    break;

                case BLACK:
                default:
                    break;
            }

            visitor.finishEdge(edge);
        }

        vertexColors.put(source, Color.BLACK);
        visitor.finishVertex(source);
    }

}
