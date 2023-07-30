/**
 * The InputMisMatchException class inherit all properties and behaviors
 * from the Exception class.
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
