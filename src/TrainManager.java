/**
 * The TrainManager class allows user to enter commands
 * to perform specific operations on the list of TrainCar.
 *
 * @author Zhen Wei Liao
 **/
import java.util.Scanner;

public class TrainManager {

    private static TrainLinkedList train;
    private static Scanner input;
    private static boolean run;

    /**
     * Checks if the user is entering y/n for dangerousness of the load and returning the correct
     * boolean value for the corresponding input.
     *
     * @param input
     * 		User input.
     *
     * @throws InputMisMatchException
     * 		when the user input is not "y" or "n".
     *
     * @return
     * 		True if user enter "y".
     * 		False if user enter "n".
     * */
    private static boolean isDangerous(String input) throws InputMisMatchException {
        if(input.equals("y"))
            return true;
        else if(input.equals("n"))
            return false;
        else
            throw new InputMisMatchException("Invalid input. Please only enter [y|n].\n");
    }

    /**
     * Prints the given prompt and return user input.
     *
     * @param prompt
     *      String asking the user to enter something.
     *
     * @return
     *      User input.
     */
    private static String userInput(String prompt){
        System.out.print(prompt);
        return input.nextLine();
    }

    /**
     * Enable user to add a TrainCar to the list.
     *
     */
    private static void insertTrain(){
        double length, weight;

        length = Double.parseDouble(userInput("Enter car length in meters: "));

        weight = Double.parseDouble(userInput("Enter car weight in tons: "));

        TrainCar car = new TrainCar(weight, length);
        train.insertAfterCursor(car);

        System.out.println("New train car (" + car.getCarLength() + " meters, " + car.getCarWeight()
                + " tons) connected to the train.\n");
    }

    /**
     * Enable user to remove a TrainCar from the list.
     */
    private static void removeTrain() throws EmptyListException {
        TrainCar car = train.removeCursor();
        ProductLoad load = car.getProductLoad();
        System.out.println("Car successfully unlinked. The following load has been removed from the train: ");

        if(load == null)
            TrainLinkedList.printLoad("Empty", 0.0, 0.00, false);
        else
            TrainLinkedList.printLoad(load.getName(), load.getWeight(), load.getValue(), load.isDangerous());

    }

    /**
     * Takes user input and creates a new ProductLoad.
     *
     * @return
     *      A new ProductLoad based on user input.
     */
    private static ProductLoad createLoad() throws InputMisMatchException {
        ProductLoad load = new ProductLoad();
        load.setName(userInput("Enter product name: "));
        load.setWeight(Double.parseDouble(userInput("Enter product weight in tons: ")));
        load.setValue(Double.parseDouble(userInput("Enter product value in dollar: ")));
        load.setIsDangerous(isDangerous(userInput("Enter is product dangerous? (y/n): ")));

        return load;
    }

    /**
     * Enable user to modify the ProductLoad of the current TrainCar.
     *
     */
    private static void setLoad() throws IllegalArgumentException, EmptyListException, InputMisMatchException {
        if(train.getCursorData() == null)
            throw new EmptyListException("Please insert a train car first.\n");
        else {
            ProductLoad load = createLoad();

            System.out.println(load.getWeight() + " tons of " + load.getName() + " added to the current car.\n");

            TrainCar car = train.getCursorData();
            train.removeProductLoad(car);
            car.setProductLoad(load);
            train.addTotal(car);
        }
    }

    /**
     * Terminated the program.
     */
    private static void terminate(){
        System.out.print("Program terminating successfully...");
        run = false;
        input.close();
    }

    /**
     * Ask for user input and perform operation based on the input command.
     *
     */
    private static void commands() throws EmptyListException, InputMisMatchException {
        switch (input.nextLine().toUpperCase().trim()) {
            case "F" -> train.cursorForward();
            case "B" -> train.cursorBackward();
            case "I" -> insertTrain();
            case "R" -> removeTrain();
            case "L" -> setLoad();
            case "S" -> {
                System.out.print("Enter product name: ");
                train.findProduct(input.nextLine());
            }
            case "T" -> System.out.print(train);
            case "M" -> train.printManifest();
            case "D" -> {
                train.removeDangerousCars();
                System.out.println("Dangerous cars successfully removed from the train.\n");
            }
            case "Q" -> terminate();
            default -> System.out.println("Please only enter the following choices: \n");
        }
    }

    /**
     * The main method runs a menu-driven application which first creates
     * an empty TrainLinkedList object. The program prompts the user for a command
     * to execute an operation. Once a command has been chosen, the program may ask
     * the user for additional information if necessary, and perform the operation.
     *
     */
    public static void main(String[] args) {
        train = new TrainLinkedList();
        run = true;
        input = new Scanner(System.in);
        while(run) {
                System.out.println("""
                        (F) Cursor Forward
                        (B) Cursor Backward
                        (I) Insert Car After Cursor
                        (R) Remove Car At Cursor
                        (L) Set Product Load
                        (S) Search For Product
                        (T) Display Train
                        (M) Display Manifest
                        (D) Remove Dangerous Cars
                        (Q) Quit
                        """);
                System.out.print("Enter a selection: ");
            try {
                commands();
            } catch(NumberFormatException e){
                System.out.println("Invalid input. Please only enter a number.\n");
            }
            catch(IllegalArgumentException | InputMisMatchException | EmptyListException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
