package sample.solver;

public enum Operator {
    add, minus, times, divide;

    public static boolean isCalculationOperator(char c){
        return (c == '+' || c == '-' || c == '×' || c == '÷');
    }

    public static boolean isTimesOrDivide(Operator operator){
        return (operator == times || operator == divide);
    }
    public static boolean isTimesOrDivide(char c){
        return ( c == '×' || c == '÷' );
    }
    public static boolean isAddOrMinus(Operator operator){
        return (operator == add || operator == minus);
    }

    public static Operator getOperator(char c){
        switch (c){
            case '+': return add;
            case '-': return minus;
            case '×': return times;
            case '÷': return divide;
            default: throw new IllegalArgumentException();
        }
    }
}
