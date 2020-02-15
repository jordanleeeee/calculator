package sample.solver;

public class Operation {
    private Operation(){};

    public static double add(double x, double y){
        return x+y;
    }
    public static double minus(double x, double y){
        return x-y;
    }
    public static double times(double x, double y){
        return x*y;
    }
    public static double divide(double x, double y){
        return x/y;
    }
}
