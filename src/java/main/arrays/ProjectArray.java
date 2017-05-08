package arrays;

/**
 * Created by Joseph on 5/7/2017.
 * a special array class with added operations that are benefitial to the project
 */

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class ProjectArray {
    private int[] orderedArray;

    public ProjectArray(int size){
        this.orderedArray = new int[size];

        for(int i = 0; i < this.orderedArray.length; i++){
            this.orderedArray[i] = i + 1;
        }
    }

    /*
    @return: an ordered array with elements 1 - size of project array
     */
    public int[] getOrderedArray() {
        return this.orderedArray;
    }

    /*
    prints the elements of this.orderedArray
     */
    public static void printArray(int[] array){
        for(int element : array) {
            System.out.println(element);
        }
    }

    /*
    uses the class variable 'orderedArray' to generate a randomly shuffed array of the same size
    @return: a shuffled array with elements 1 - this.orderedArray.length
     */
    public int[] getRandomizedArray(){
        int[] randomizedArray = Arrays.copyOf(this.orderedArray, this.orderedArray.length);

        // shuffling with a Fisher - Yates shuffle
        for(int i = 0; i < randomizedArray.length; i++){
            int rand = ThreadLocalRandom.current().nextInt(0, randomizedArray.length);

            // swaps the two array elements at index i and index rand
            int temp = randomizedArray[i];
            randomizedArray[i] = randomizedArray[rand];
            randomizedArray[rand] = temp;
        }

        return randomizedArray;
    }



}
