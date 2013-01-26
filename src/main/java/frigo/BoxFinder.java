
package frigo;

public class BoxFinder {

    private final double[] source;
    private final BoxStatistics stat;

    public BoxFinder (double[] source) {
        this.source = source.clone();
        stat = new BoxStatistics(source);
    }

    public Box getMinimumWeightedVarianceSum () {
        Box bestBox = new Box(0, 0);
        for( int x = 0; x < source.length; x++ ){
            for( int y = x; y < source.length; y++ ){
                Box box = new Box(x, y);
                if( stat.getWeightedVarianceSum(bestBox) > stat.getWeightedVarianceSum(box) ){
                    bestBox = box;
                }
            }
        }
        return bestBox;
    }
}
