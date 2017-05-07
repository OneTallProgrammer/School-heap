/**
 * Created by Joseph on 5/7/2017.
 */
import arrays.ProjectArray;
import heap.Heap;

public class HeapMain {
    public static void main(String[] args) {
        ProjectArray pArray = new ProjectArray(100);

        Heap heap = new Heap(pArray.getOrderedArray(), "o");

        System.out.println("Swaps: " + heap.getSwaps());


        heap.printByLevel(10);


    }
}
