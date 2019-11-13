
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;


public class FactorialTaskTest {

    private static final int FACTORIAL_ARGUMENT = 1000;
    private static final Logger LOG = Logger.getLogger(FactorialTaskTest.class);

    @Test
    public void test() {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(FACTORIAL_ARGUMENT);
        long start = System.currentTimeMillis();
        BigInteger result = (BigInteger) forkJoinPool.invoke(factorialTask);
        LOG.info("result by fork join:\n" + result + "\nTime: " + (System.currentTimeMillis() - start));
    }
}