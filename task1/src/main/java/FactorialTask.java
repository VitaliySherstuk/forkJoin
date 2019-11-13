import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask {

    private int number;

    FactorialTask(int number) {
        this.number = number;
    }

    @Override
    protected BigInteger compute() {
        if (number <= 1) {
            return BigInteger.valueOf(1L);
        }
        FactorialTask factorialTask = new FactorialTask(number - 1);
        factorialTask.fork();

        return BigInteger.valueOf(number).multiply((BigInteger) factorialTask.join());
    }

}
