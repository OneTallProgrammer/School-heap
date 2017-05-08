package heap;

/**
 * Created by Joseph on 5/7/2017.
 * basic implementation of a heap with integer keys
 */
import exceptions.*;

import java.util.Arrays;

public class Heap {
    private int[] nodes;
    private int openIndex;
    private int swaps; // total number of swaps performed on heap

    /*
    constructor can be called with sequential insertion and optimal insertion methods
    @param data: the data being inserted
    @param option: the method of insertion
     */
    public Heap(int[] data, String option){
        this.openIndex = 0;
        this.nodes = new int[data.length];

        try {
            //sequential insertion
            if(option.equals("s")){
                sequentialInsertion(data);
            }
            //optimal insertion
            else if(option.equals("o")){
                optimalInsertion(data);
            }
            else{
                throw new InvalidHeapConstructorOptionException(option);
            }
        } catch (InvalidHeapConstructorOptionException e) {
            e.printErrorMessage();
        }
    }

    public int getSwaps(){
        return this.swaps;
    }

    /*
    inserts an array of integers one at a time
    reheapify up after every insertion
    @param data: an array of integers being inserted into the heap
     */
    public void sequentialInsertion(int[] data){
        for(int element : data){
            insertNewNode(element);
        }
    }

    /*
    inserts an array of integers using the optimized method discussed in class
    @param data: an array of integers being inserted into the heap
     */
    public void optimalInsertion(int[] data){

        this.nodes = Arrays.copyOf(data, this.nodes.length); // insert all integers at once
        this.openIndex = this.nodes.length;                  // reset the openInteger metric
        int lastLeaf = this.openIndex - 1;                   // locate the last leaf

        // from the parent of the last leaf, start iterating backwards
        for(int i = (lastLeaf - 1)/2; i >= 0; i--){
            reheapifyDown(i);
        }

    }

    /*
    inserts a new key value into the next available spot in the backing array
    rebalances the tree after insertion
    @param node: the key value being inserted
     */
    public void insertNewNode(int node){
        this.nodes[this.openIndex] = node;
        reheapifyUp(this.openIndex);
        this.openIndex++;
    }

    /*
    remove root of the tree in the backing array (index 0) and replace root with last leaf
    reheapify downward
    @return: the root of the heap that was deleted
     */
    public int pop(){
        int frontNode = this.nodes[0];

        this.openIndex--;
        this.nodes[0] = this.nodes[this.openIndex];
        reheapifyDown(0);

        return frontNode;
    }

    /*
    compares child to parent node and swaps if parent < child
    if swapped, recursively repeats the same process starting at the parent
    @param index: the index in the backing array in which to start the comparisons
     */
    public void reheapifyUp(int index){
        if(index == 0){
            return;
        }
        else{
            int parent = (index - 1)/2;
            if(this.nodes[index] > this.nodes[parent]){
                swapNodes(index, parent);
                reheapifyUp(parent);
            }
        }
    }

    /*
    compares parent to both child nodes and places the largest of the 3 in the parent position
    if swapped, recursively repeats the same process starting with the child node that was changed
    @param index: the index in the backing array in which to start the comparisons
     */
    public void reheapifyDown(int index){
        int left = 2*index + 1;
        int right = 2*index + 2;

        // if right & left child exists
        if(right < this.openIndex){
            // check both
            if(this.nodes[left] < this.nodes[right]){
                if(this.nodes[index] < this.nodes[right]){
                    swapNodes(index, right);
                    reheapifyDown(right);
                }
            }
            else if(this.nodes[index] < this.nodes[left]){
                swapNodes(index, left);
                reheapifyDown(left);
            }
        }
        // if only left child exists
        else if(left < this.openIndex){
            // only check left
            if(this.nodes[index] < this.nodes[left]){
                swapNodes(index, left);
                reheapifyDown(left);
            }
        }
        // node at index has no children (base case)
        else {
            // neither child exists
            return;
        }

        // by the rules of a complete tree, if there is no left child,
        // the node has reached the maximum depth of the tree
    }

    /*
    basic operation to swap two indices of an array
    counts number of swaps
    @param a: first index to be swapped
    @param b: second index to be swapped
     */
    private void swapNodes(int a, int b){
        int temp = this.nodes[a];
        this.nodes[a] = this.nodes[b];
        this.nodes[b] = temp;

        this.swaps++;
    }

    /*
    prints backing array in order which produces a level order printing of the nodes in the heap
    @param range: how many nodes the caller would like to print starting at index 0 as range 1
     */
    public void printByLevel(int range) {
        // ensure that caller can only print until next open index is reached
        if(range > this.openIndex){
            range = this.openIndex;
        }

        for(int i = 0; i < range; i++){
            System.out.print(this.nodes[i] + ", ");
        }
    }
}
