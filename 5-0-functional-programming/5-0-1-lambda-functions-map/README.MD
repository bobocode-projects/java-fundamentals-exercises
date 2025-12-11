import java.util.HashMap;
import java.util.Map;
import java.util.function.IntUnaryOperator;

public class LambdaFunctionsMap {

    private final Map<String, IntUnaryOperator> functionMap = new HashMap<>();

    public LambdaFunctionsMap() {
        
        IntUnaryOperator abs = x -> x >= 0 ? x : -x;

        
        IntUnaryOperator sgn = x -> x > 0 ? 1 : (x < 0 ? -1 : 0);

        
        IntUnaryOperator increment = x -> x + 1;

        
        IntUnaryOperator decrement = x -> x - 1;

        
        IntUnaryOperator square = x -> x * x;

        
        functionMap.put("abs", abs);
        functionMap.put("sgn", sgn);
        functionMap.put("increment", increment);
        functionMap.put("decrement", decrement);
        functionMap.put("square", square);
    }

    
    public Map<String, IntUnaryOperator> getFunctionMap() {
        return functionMap;
    }

    
    public static void main(String[] args) {
        LambdaFunctionsMap lfm = new LambdaFunctionsMap();

        System.out.println("abs(-5) = " + lfm.getFunctionMap().get("abs").applyAsInt(-5));
        System.out.println("sgn(-5) = " + lfm.getFunctionMap().get("sgn").applyAsInt(-5));
        System.out.println("increment(7) = " + lfm.getFunctionMap().get("increment").applyAsInt(7));
        System.out.println("decrement(7) = " + lfm.getFunctionMap().get("decrement").applyAsInt(7));
        System.out.println("square(4) = " + lfm.getFunctionMap().get("square").applyAsInt(4));
    }
}
