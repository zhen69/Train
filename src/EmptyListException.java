/**
 * The EmptyListException class inherits all properties and behaviors of the Exception class.
 * This exception is thrown when searching or removing an empty train.
 *
 * @author Zhen Wei Liao
 **/
public class EmptyListException extends Exception {
    /**
     * Constructor method calls the constructor of the Exception class
     */
    public EmptyListException(){
        super();
    }

    /**
     * Constructor calls the constructor of the Exception class,
     * while changing the display error message to the given error variable.
     *
     * @param error
     * 		Error message to display when this exception is thrown.
     *
     */
    public EmptyListException(String error){
        super(error);
    }
}
