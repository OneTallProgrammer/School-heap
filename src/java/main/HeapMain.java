/**
 * Created by Joseph on 5/7/2017.
 */
import arrays.ProjectArray;
import heap.Heap;

import java.util.Scanner;

public class HeapMain {

    public static void main(String[] args) {

        boolean badInput = true;

        printMenu();
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        // main program loop to process user input
        while(badInput){
            String input = sc.next();

            try {
                choice = Integer.parseInt(input);

                if(choice > 0 && choice <= 2){
                    badInput = false;
                }
                else{
                    printInputErrorMessage();
                }

            } catch (NumberFormatException e) {
                printInputErrorMessage();
            }

        }

        switch(choice){
            case 1:
                testRandomIntegers(20);
                break;
            case 2:
                testFixedIntegers();
                break;
            default:
                break;
        }




    }

    /*
    tests both heap constructor methods using random integers between 1 - 100
    @param amountOfTests: the number of times the heap will be created and tested
     */
    public static void testRandomIntegers(int amountOfTests){
        ProjectArray pArray = new ProjectArray(100);
        int sequentialSwaps = 0;
        int optimalSwaps = 0;

        for(int i = 0; i < amountOfTests; i++){
            int[] testArray = pArray.getRandomizedArray();

            Heap optimalHeap = new Heap(testArray, "o");
            Heap sequentialHeap = new Heap(testArray, "s");

            sequentialSwaps += sequentialHeap.getSwaps();
            optimalSwaps += optimalHeap.getSwaps();
        }

        // calculate average number of swaps for both methods
        int averageSequentialSwaps = sequentialSwaps / amountOfTests;
        int averageOptimalSwaps = optimalSwaps / amountOfTests;

        // print the result of the tests to the console
        System.out.println("Average swaps for series of insertions: " + averageSequentialSwaps);
        System.out.println("Average swaps for optimal method: " + averageOptimalSwaps);
    }

    /*
    tests both heap constructor methods using fixed integers between 1 - 100
     */
    public static void testFixedIntegers(){
        ProjectArray pArray = new ProjectArray(100);
        int optimalSwaps = 0;
        int sequentialSwaps = 0;


        Heap sequentialHeap = new Heap(pArray.getOrderedArray(), "s");
        Heap optimalHeap = new Heap(pArray.getOrderedArray(), "o");

        // count number of swaps for each construction method
        optimalSwaps += optimalHeap.getSwaps();
        sequentialSwaps += sequentialHeap.getSwaps();

        // print the first 10 keys, and number of swaps for sequential method
        System.out.print("Heap built using series of insertion: ");
        sequentialHeap.printByLevel(10);
        System.out.print("\nNumber of swaps: " + sequentialSwaps);

        // remove 10 keys from the heap
        for(int i = 0; i < 10; i++){
            sequentialHeap.pop();
        }

        // reprint the first 10 keys after 10 deletions
        System.out.print("\nHeap after 10 removals: ");
        sequentialHeap.printByLevel(10);

        // print the first 10 keys, and the number of swaps for the optimal method
        System.out.print("\n\nHeap built using optimal method: ");
        optimalHeap.printByLevel(10);
        System.out.print("\nNumber of swaps: " + optimalSwaps);

        // remove 10 keys from the heap
        for(int i = 0; i < 10; i++){
            optimalHeap.pop();
        }

        // reprint the first 10 keys after 10 deletions
        System.out.print("\nHeap after 10 removals: ");
        optimalHeap.printByLevel(10);


    }

    /*
    menu error message alerting the user to invalid input
     */
    public static void printInputErrorMessage() {
        System.out.println("Invalid Input: expected integer values 1 or 2");
    }

    /*
    prints the menu of possible options the user can choose from
     */
    public static void printMenu(){
        System.out.print("Please select how to test the program: \n" +
                           "1) 20 sets of 100 randomly generated integers \n" +
                           "2) Fixed integer values 1-100 \n" +
                           "Enter Choice: ");
    }
}
