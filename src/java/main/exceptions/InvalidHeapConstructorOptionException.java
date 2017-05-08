package exceptions;

/**
 * Created by Joseph on 5/7/2017.
 * exception used to ensure that a heap cannot be constructed with an invalid option
 */
public class InvalidHeapConstructorOptionException extends Exception{
    private String option;

    public InvalidHeapConstructorOptionException(String option){
        this.option = option;
    }

    /*
    prints error message to console
     */
    public void printErrorMessage(){
        System.out.println("Invalid heap constructor option: " + option + "\nConstruction Aborted");
    }
}
