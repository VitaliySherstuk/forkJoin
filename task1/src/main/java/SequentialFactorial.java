import java.math.BigInteger;

public class SequentialFactorial {

    private int number;

    SequentialFactorial(int number) {
        this.number = number;
    }

    public BigInteger compute() {
        if (number <= 1) {
            return BigInteger.valueOf(1L);
        }
        return BigInteger.valueOf(number).multiply(new SequentialFactorial(number - 1).compute());
    }

}
