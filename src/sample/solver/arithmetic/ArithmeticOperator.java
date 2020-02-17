package sample.solver.arithmetic;

import sample.exception.DivideByZeroException;
import sample.exception.MathException;
import sample.exception.SyntaxErrorException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArithmeticOperator {
    private String equation;

    //using double is cannot do correct calculation...
    //why? please refer to https://dzone.com/articles/never-use-float-and-double-for-monetary-calculatio
    private BigDecimal answer;

    private ArrayList<BigDecimal> numbers = new ArrayList<>();
    private ArrayList<Operator> operators = new ArrayList<>();

    public ArithmeticOperator(String equation) throws MathException{
        this.equation = equation;
        solve();
    }

    private void solve() throws MathException{
        System.out.println("at the beginning "+ equation);
        simplifyPlusAndMinusSign();
        simplifyPercentageSign();

        extractEquationToNumbersAndOperators();
        System.out.println("numbers: " + numbers.toString());
        System.out.println("operator: " + operators.toString());
        calculate();
    }

    private void simplifyPlusAndMinusSign(){
        for (int i = 0; i < equation.length() - 1; i++) {
            char firstChar = equation.charAt(i);
            char secondChar = equation.charAt(i + 1);

            if (Operator.isCalculationOperator(firstChar) && Operator.isCalculationOperator(secondChar)) {
                String str = new String(new char[]{firstChar, secondChar});
                // 0, 1, 2, i-1, i, i+1, i+2, ....
                String former = equation.substring(0, i);   // [0, i)
                String latter = equation.substring(i + 2);  // [i+2, end]

                if (str.equals("+-") || str.equals("-+")) {
                    equation = former + "-" + latter;
                    i = -1;
                } else if (str.equals("++") || str.equals("--")) {
                    equation = former + "+" + latter;
                    i = -1;
                }
            }
        }
    }

    /**
     * convert % to ÷100
     */
    private void simplifyPercentageSign() {
        for (int i = 0; i < equation.length(); i++) {
            if(equation.charAt(i) == '%') {
                String former = equation.substring(0, i);   // [0, i)
                String latter = equation.substring(i + 1);  // [i+1, end]
                equation = former + "÷100" + latter;
            }
        }
    }

    private void extractEquationToNumbersAndOperators() throws SyntaxErrorException{
        System.out.println("extracting "+ equation);

        boolean isNegative = false;
        Integer numStartIndex = null;

        for (int i = 0; i < equation.length(); i++) {
            char oneChar = equation.charAt(i);
            if(Character.isDigit(oneChar) || oneChar == '.'){
                if(numStartIndex == null){
                    numStartIndex = i;
                }
            }
            else if(Operator.isCalculationOperator(oneChar)){       //the char is an operator
                if (i == 0) {
                    if(Operator.isTimesOrDivide(oneChar)){
                        throw new SyntaxErrorException();
                    }
                }
                if(i != 0) {
                    char previousChar = equation.charAt(i - 1);
                    if (Operator.isCalculationOperator(previousChar)) {
                        if (oneChar == '-') {
                            isNegative = true;
                            continue;
                        } else if (oneChar == '+') {
                            continue;
                        } else {
                            throw new SyntaxErrorException();
                        }
                    }
                }
                assert numStartIndex != null;
                BigDecimal number = new BigDecimal(equation.substring(numStartIndex, i));
                numbers.add((isNegative)? (number.multiply(new BigDecimal(-1))): number);
                isNegative = false;
                numStartIndex = null;
                operators.add(Operator.getOperator(oneChar));
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        assert numStartIndex != null;
        BigDecimal number = new BigDecimal(equation.substring(numStartIndex));
        numbers.add((isNegative)? (number.multiply(new BigDecimal(-1))): number);
    }

    private void calculation(List<Operator> allowedOperator) throws DivideByZeroException {
        for (int i = 0; i < operators.size(); i++) {
            Operator operator = operators.get(i);
            if(allowedOperator.contains(operator)){
                numbers.set(i, operator.calculation(numbers.get(i), numbers.get(i + 1)));
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }
    }

    private void storeAns(){
        if(numbers.size() != 1){
            throw new IllegalStateException("program have bug(s)...");
        }
        answer = numbers.get(0);
    }

    private void calculate() throws DivideByZeroException {
        calculation(Arrays.asList(Operator.times, Operator.divide));
        calculation(Arrays.asList(Operator.add, Operator.minus));
        storeAns();
    }

    public String getAnswer(){
        return answer.toString();
    }
}
