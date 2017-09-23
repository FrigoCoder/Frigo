
package frigo.util;

public enum Operator {
    ADD ("+") {

        @Override
        public double evaluate (double left, double right) {
            return left + right;
        }
    },

    DIV ("/") {

        @Override
        public double evaluate (double left, double right) {
            return left / right;
        }
    },

    MUL ("*") {

        @Override
        public double evaluate (double left, double right) {
            return left * right;
        }
    },

    SUB ("-") {

        @Override
        public double evaluate (double left, double right) {
            return left - right;
        }
    };

    public static boolean isOperator (String token) {
        for( Operator operator : Operator.values() ){
            if( operator.token.equals(token) ){
                return true;
            }
        }
        return false;
    }

    public static Operator parse (String token) {
        for( Operator operator : Operator.values() ){
            if( operator.token.equals(token) ){
                return operator;
            }
        }
        throw new EnumConstantNotPresentException(Operator.class, token);
    }

    public final String token;

    Operator (String token) {
        this.token = token;
    }

    public abstract double evaluate (double left, double right);
}
