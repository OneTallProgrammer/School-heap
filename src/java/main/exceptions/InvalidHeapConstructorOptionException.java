package exceptions;

/**
 * Created by Joseph on 5/7/2017.
 */
public class InvalidHeapConstructorOptionException extends Exception{
    private String option;

    public InvalidHeapConstructorOptionException(String option){
        this.option = option;
    }

    public void printErrorMessage(){
        System.out.println("Invalid heap constructor option: " + option + "\nConstruction Aborted");
    }
}
