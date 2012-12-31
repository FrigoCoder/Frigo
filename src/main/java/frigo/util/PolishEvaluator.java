
package frigo.util;

public class PolishEvaluator {

    private int actual;
    private String[] tokens;

    public double evaluate (String expression) {
        actual = 0;
        tokens = expression.split(" ");
        return evaluateInner();
    }

    private double evaluateInner () {
        String token = tokens[actual++];
        if( Operator.isOperator(token) ){
            Operator operator = Operator.parse(token);
            double left = evaluateInner();
            double right = evaluateInner();
            return operator.evaluate(left, right);
        }
        return Double.valueOf(token);
    }
}
