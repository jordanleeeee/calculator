package solver.arithmetic;

import org.junit.jupiter.api.Test;
import exception.MathException;
import exception.SyntaxErrorException;

import static org.junit.jupiter.api.Assertions.*;

class SolverTester {
    @Test
    void AddTest() throws MathException {
        ArithmeticSolver solver;
        solver = new ArithmeticSolver("+1");
        assertEquals(solver.getAnswer(), "1");

        solver = new ArithmeticSolver("1+1");
        assertEquals(solver.getAnswer(), "2");

        solver = new ArithmeticSolver("+1+2+++3");
        assertEquals(solver.getAnswer(), "6");

        solver = new ArithmeticSolver("1++2+3.3");
        assertEquals(solver.getAnswer(), "6.3");

        assertThrows(SyntaxErrorException.class, ()->{
                ArithmeticSolver slover = new ArithmeticSolver("1+2+");
        });

        assertThrows(SyntaxErrorException.class, ()->{
            ArithmeticSolver slover = new ArithmeticSolver("+");
        });

        assertThrows(SyntaxErrorException.class, ()->{
            ArithmeticSolver slover = new ArithmeticSolver("");
        });
    }

    @Test
    void minusTest() throws MathException {
        ArithmeticSolver solver;
        solver = new ArithmeticSolver("-1");
        assertEquals(solver.getAnswer(), "-1");

        solver = new ArithmeticSolver("1-1");
        assertEquals(solver.getAnswer(), "0");

        solver = new ArithmeticSolver("10-9+-8");
        assertEquals(solver.getAnswer(), "-7");

        solver = new ArithmeticSolver("-10-5.5-1");
        assertEquals(solver.getAnswer(), "-16.5");

        solver = new ArithmeticSolver("--10-5.5-1");
        assertEquals(solver.getAnswer(), "3.5");

        assertThrows(SyntaxErrorException.class, ()->{
            ArithmeticSolver slover = new ArithmeticSolver("7-");
        });

        assertThrows(SyntaxErrorException.class, ()->{
            ArithmeticSolver slover = new ArithmeticSolver("-");
        });
    }

    @Test
    void timesTest() throws MathException {
        ArithmeticSolver solver;
        solver = new ArithmeticSolver("2×-3");
        assertEquals(solver.getAnswer(), "-6");

        solver = new ArithmeticSolver("3×0.5");
        assertEquals(solver.getAnswer(), "1.5");

        solver = new ArithmeticSolver("-1×-2×3×--4");
        assertEquals(solver.getAnswer(), "24");

        assertThrows(SyntaxErrorException.class, ()->{
            ArithmeticSolver slover = new ArithmeticSolver("×6");
        });

        assertThrows(SyntaxErrorException.class, ()->{
            ArithmeticSolver slover = new ArithmeticSolver("89×");
        });

        assertThrows(SyntaxErrorException.class, ()->{
            ArithmeticSolver slover = new ArithmeticSolver("×");
        });
    }

    @Test
    void divideTest() throws MathException {
        ArithmeticSolver solver;
        solver = new ArithmeticSolver("10÷2");
        assertEquals(solver.getAnswer(), "5");

        solver = new ArithmeticSolver("-10÷4");
        assertEquals(solver.getAnswer(), "-2.5");

        solver = new ArithmeticSolver("+10÷-2÷-4");
        assertEquals(solver.getAnswer(), "1.25");

        assertThrows(SyntaxErrorException.class, () -> {
            ArithmeticSolver slover = new ArithmeticSolver("÷6");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            ArithmeticSolver slover = new ArithmeticSolver("89÷");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            ArithmeticSolver slover = new ArithmeticSolver("÷");
        });
    }

    @Test
    void mixCalculationTest() throws MathException {
        ArithmeticSolver solver;
        solver = new ArithmeticSolver("1+2× 3");
        assertEquals(solver.getAnswer(), "7");

        solver = new ArithmeticSolver("1-4");
        assertEquals(solver.getAnswer(), "2.5");

        solver = new ArithmeticSolver("10÷2÷4");
        assertEquals(solver.getAnswer(), "1.25");

        assertThrows(SyntaxErrorException.class, () -> {
            ArithmeticSolver slover = new ArithmeticSolver("÷6");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            ArithmeticSolver slover = new ArithmeticSolver("89÷");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            ArithmeticSolver slover = new ArithmeticSolver("÷");
        });
    }

    @Test
    void invalidTest(){
        assertThrows(SyntaxErrorException.class, () -> {
            ArithmeticSolver slover = new ArithmeticSolver("1+÷6");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            ArithmeticSolver slover = new ArithmeticSolver("12÷÷4");
        });
    }
}
