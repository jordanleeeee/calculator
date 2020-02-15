package sample.solver;

import sample.exception.SyntaxErrorException;

import java.util.ArrayList;

public class Solver {
    private String equation;
    private double answer;
    private boolean isInvalidEquation = false;

    private ArrayList<Double> numbers = new ArrayList<>();
    private ArrayList<Operator> operators = new ArrayList<>();

    public Solver(String equation){
        this.equation = equation;
        solve();
    }

    private void solve(){
        System.out.println("at the beginning "+ equation);
        simplifyPlusAndMinusSign();
        simplifyPercentageSign();
        try {
            extractEquationToNumbersAndOperators();
        }
        catch (SyntaxErrorException e){
            isInvalidEquation = true;
            return;
        }
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

        double number = 0;
        boolean isInDecimalPlace = false;
        boolean isNegative = false;
        int decCount = 0;

        for (int i = 0; i < equation.length(); i++) {
            char oneChar = equation.charAt(i);
            if(Character.isDigit(oneChar)){
                if(isInDecimalPlace){
                    number += Character.getNumericValue(oneChar)* Math.pow(0.1, ++decCount);
                }
                else{
                    number = number* 10 + Character.getNumericValue(oneChar);
                }

            }
            else if(oneChar == '.'){
                isInDecimalPlace = true;
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
                numbers.add((isNegative)? (number*-1): number);
                isNegative = false;
                isInDecimalPlace = false;
                decCount = 0;
                number = 0;
                operators.add(Operator.getOperator(oneChar));
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        numbers.add((isNegative)? (number*-1): number);
    }

    private void calculateTimerOrDivide(){
        for (int i = 0; i < operators.size(); i++) {
            Operator operator = operators.get(i);
            if(Operator.isTimesOrDivide(operator)) {
                if (operator == Operator.times) {
                    numbers.set(i, Operation.times(numbers.get(i), numbers.get(i + 1)));
                }
                else {
                    numbers.set(i, Operation.divide(numbers.get(i), numbers.get(i + 1)));
                }
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }
    }

    private void calculateAddOrMinus(){
        for (int i = 0; i < operators.size(); i++) {
            Operator operator = operators.get(i);
            if (operator == Operator.add) {
                numbers.set(i, Operation.add(numbers.get(i), numbers.get(i + 1)));
            }
            else {
                numbers.set(i, Operation.minus(numbers.get(i), numbers.get(i + 1)));
            }
            numbers.remove(i + 1);
            operators.remove(i);
            i--;
        }
    }

    private void storeAns(){
        answer = numbers.get(0);
    }

    private void calculate(){
        calculateTimerOrDivide();
        calculateAddOrMinus();
        storeAns();
    }

    public String getAnswer(){
        if(!isInvalidEquation){
            if(answer%1 == 0){
                return Integer.toString(((int)answer));
            }
            return Double.toString(answer);
        }
        return SyntaxErrorException.warning;
    }
}
