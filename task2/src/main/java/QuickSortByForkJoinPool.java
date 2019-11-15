import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class QuickSortByForkJoinPool extends RecursiveAction {

    private List<Integer> list;
    private int startIndex;
    private int endIndex;

    public QuickSortByForkJoinPool(List<Integer> list, int startIndex, int endIndex) {
        this.list = list;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected void compute() {
        if (startIndex < endIndex) {
            int pivotIndex = startIndex + ((endIndex - startIndex) / 2);
            pivotIndex = partition(pivotIndex);
            QuickSortByForkJoinPool t1 = new QuickSortByForkJoinPool(list, startIndex, pivotIndex - 1);
            QuickSortByForkJoinPool t2 = new QuickSortByForkJoinPool(list, pivotIndex + 1, endIndex);
            t1.fork();
            t2.compute();
            t1.join();
        }
    }

    private int partition(int pivotIndex) {
        int value = list.get(pivotIndex);
        Collections.swap(list, pivotIndex, endIndex);
        int storeIndex = startIndex;
        for (int i = startIndex; i < endIndex; i++) {
            if (list.get(i) < value) {
                Collections.swap(list, i, storeIndex);
                storeIndex++;
            }
        }
        Collections.swap(list, storeIndex, endIndex);

        return storeIndex;
    }

    List<Integer> getList() {
        return list;
    }

}

