package sample.solver;

import sample.exception.MathException;
import sample.exception.SyntaxErrorException;
import sample.solver.arithmetic.ArithmeticOperator;


public class Solver {
    private String equation;

    private String answer;
    private MathException exception = null;

    public Solver(String equation){
        this.equation = equation;
        solve();
    }

    private void solve(){
        addingTimesSign(); //convert a(b) to a×(b)
        try {
            calculate();
        } catch (MathException e) {
            exception = e;
        }
    }

    private void addingTimesSign(){
        for (int i = 1; i < equation.length(); i++) {
            char oneChar = equation.charAt(i);
            if(oneChar == '('){
                char previousChar = equation.charAt(i-1);
                if(Character.isDigit(previousChar) || previousChar == ')'){
                    String former = equation.substring(0, i);   //[0, i)
                    String latter = equation.substring(i);      //[i, end]
                    equation = former + '×' + latter;
                }
            }
        }
    }

    private void calculate() throws MathException{
        killAllParenthesis();
        ArithmeticOperator solver = new ArithmeticOperator(equation);
        answer = solver.getAnswer();
    }

    private boolean isCorrectUseOfParenthesis(String str){
        int numOfOpenParenthesis = 0;
        int numOfCloseParenthesis = 0;
        for(int i=0; i<str.length(); i++){
            char oneChar = str.charAt(i);
            if(oneChar == '('){
                numOfOpenParenthesis++;
            } else if (oneChar == ')') {
                numOfCloseParenthesis++;
            }
        }
        return (numOfCloseParenthesis == numOfOpenParenthesis);
    }

    private void killAllParenthesis() throws MathException {
        if(!isCorrectUseOfParenthesis(equation)){
            throw new SyntaxErrorException();
        }
        int indexOfOpenParenthesis = -1;
        for(int i=0; i<equation.length(); i++){
            char oneChar = equation.charAt(i);
            if(oneChar == '('){
                indexOfOpenParenthesis = i;
            }
            if (oneChar == ')') {
                String arithmeticEquation = equation.substring(indexOfOpenParenthesis+1, i);
                ArithmeticOperator solver = new ArithmeticOperator(arithmeticEquation);
                String answer = solver.getAnswer();
                String former = equation.substring(0, indexOfOpenParenthesis);
                String latter = equation.substring(i+1);
                equation = former + answer + latter;
                i = -1;
            }
        }
    }

    public String getAnswer(){
        if(exception == null){
            return answer;
        }
        return exception.getWarning();
    }
}
