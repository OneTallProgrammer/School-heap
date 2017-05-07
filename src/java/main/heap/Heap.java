package heap;

/**
 * Created by Joseph on 5/7/2017.
 */
import exceptions.*;
public class Heap {
    private int[] nodes;
    private int openIndex;
    private int swaps; // total number of swaps performed on heap

    public Heap(int[] data, String option){
        this.openIndex = 0;
        this.nodes = new int[data.length];

        try {
            //sequential insertion
            if(option.equals("s")){
                for(int element : data){
                    insertNewNode(element);
                }
            }
            //optimal insertion
            else if(option.equals("o")){

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

    public void insertNewNode(int node){
        this.nodes[this.openIndex] = node;
        reheapifyUp(this.openIndex);
        this.openIndex++;
    }

    public int pop(){
        int frontNode = this.nodes[0];

        this.openIndex--;
        this.nodes[0] = this.nodes[this.openIndex];
        reheapifyDown(0);

        return frontNode;
    }

    public void reheapifyUp(int index){
        if(index == 0){
            return;
        }
        else{
            int parent = (index - 1)/2;
            System.out.println("Parent = " + parent);
            if(this.nodes[index] > this.nodes[parent]){
                swapNodes(index, parent);
                reheapifyUp(parent);
            }
        }
    }

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

    private void swapNodes(int a, int b){
        int temp = this.nodes[a];
        this.nodes[a] = this.nodes[b];
        this.nodes[b] = temp;

        this.swaps++;
    }

    //insert optimally

    //remove

    public void printByLevel(int range) {
        if(range > this.openIndex){
            range = this.openIndex;
        }

        for(int i = 0; i < range; i++){
            System.out.println(this.nodes[i]);
        }
    }
}
