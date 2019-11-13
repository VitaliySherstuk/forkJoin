import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.testng.Assert.assertTrue;

public class QuickSortByForkJoinPoolTest {

    private Logger LOG = Logger.getLogger(QuickSortByForkJoinPoolTest.class);
    private static final int LIST_SIZE = 100000;
    private static final int BOUND = 100;

    @Test
    public void test() {
        List<Integer> unsortedList = Collections.unmodifiableList(createUnsortedList());
        List<Integer> sortedListByCollections =sortedByCollections(new ArrayList<>(unsortedList));

        QuickSortByForkJoinPool quickSortByForkJoinPool = new QuickSortByForkJoinPool(new ArrayList<>(unsortedList), 0, unsortedList.size() - 1);
        long start = System.currentTimeMillis();
        quickSortByForkJoinPool.compute();
        List<Integer> sortedListByForkJoin = quickSortByForkJoinPool.getList();
        LOG.info("Sort by fork join: " + (System.currentTimeMillis() - start));
        assertTrue(compareCollections(sortedListByCollections, sortedListByForkJoin));
    }

    private boolean compareCollections(List<Integer> firstList, List<Integer> secondList) {
        if(firstList.size() != secondList.size()){
            return false;
        }

        for(int i=0; i < firstList.size(); i++) {
            if(firstList.get(i) != secondList.get(i)) {
                return false;
            }
        }

        return true;
    }

    private List<Integer> sortedByCollections(List<Integer> list) {
        long start = System.currentTimeMillis();
        Collections.sort(list);
        LOG.info("Sort by Collections: " + (System.currentTimeMillis() - start));
        return list;
    }

    private List<Integer> createUnsortedList() {
        return IntStream.iterate(0, i -> i + 1)
                .limit(LIST_SIZE)
                .mapToObj(item -> new Random().nextInt(BOUND))
                .collect(Collectors.toList());
    }

}