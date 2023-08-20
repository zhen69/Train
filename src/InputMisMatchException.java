/**
 * The InputMisMatchException class inherits all properties and behaviors of the Exception class.
 * This exception is thrown when the user does not enter an expected input.
 *
 * @author Zhen Wei Liao
 **/
public class InputMisMatchException extends Exception{
    /**
     * Constructor method calls the constructor of the Exception class
     */
    public InputMisMatchException(){
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
    public InputMisMatchException(String error){
        super(error);
    }
}
