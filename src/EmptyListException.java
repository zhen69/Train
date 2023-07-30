/**
 * The EmptyListException class inherit all properties and behaviors
 * from the Exception class.
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
