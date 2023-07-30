/**
 * The TrainLinkedList class implements a Double-Linked List ADT, which creates a
 * Double-Linked list representing the train and contains methods that enable user to
 * modify the train.
 *
 * @author Zhen Wei Liao
 **/
public class TrainLinkedList {
    private TrainCarNode head;
    private TrainCarNode tail;
    private TrainCarNode cursor;

    private int numOfTrainCar = 0, numOfDangerousCar = 0;
    private double totalLength = 0, totalValue = 0, totalWeight = 0;

    /**
     * Constructor creates an empty TrainLinkedList object
     * <p>
     * Post conditions:
     * 		TrainLinkedList has been initialized to an empty linked list.
     * 		Head, tail, and cursor are all set to null.
     * */
    public TrainLinkedList() {

    }

    /**
     * Returns the TrainCar object currently referenced by the cursor.
     * <p>
     * Preconditions:
     * 		The list is not empty and the cursor is not null.
     *
     * @return
     * 		TrainCar currently referenced by the cursor.
     * */
    public TrainCar getCursorData() {
        if(cursor != null)
            return cursor.getCar();

        return null;
    }

    /**
     * Modifies the TrainCar object currently represent by the cursor.
     *
     * @param car
     * 		TrainCar that will be referenced by the cursor.
     * <p>
     * Preconditions:
     * 		The list is not empty and the cursor is not null.
     * <p>
     * Post conditions:
     * 		Car is now referenced by the cursor.
     * */
    public void setCursorData(TrainCar car) {
        if(cursor != null)
            cursor.setCar(car);
    }

    /**
     * Moves the cursor to the next TrainCarNode.
     * <p>
     * Preconditions:
     * 		The list is not empty and the tail of the list is not currently referenced
     * 		by the cursor.
     * <p>
     * Post conditions:
     * 		The cursor has been moved to the next TrainCarNode, or has remained at the tail.
     * */
    public void cursorForward() throws EmptyListException {
        if(isEmpty())
            throw new EmptyListException("Invalid: No train exist.\n");
        else if(cursor != tail) {
            cursor = cursor.getNext();
            System.out.println("Cursor moved forward.\n");
        } else
            System.out.println("No next car; cannot move cursor forward.\n");
    }

    /**
     * This method moves the cursor to the previous TrainCarNode.
     * <p>
     * Preconditions:
     * 		The list is not empty and the head of the list is not currently referenced
     * 		by the cursor.
     * <p>
     * Post conditions:
     * 		The cursor has been moved to the previous TrainCarNode, or has remained at the head.
     * */
    public void cursorBackward() throws EmptyListException {
        if(isEmpty())
            throw new EmptyListException("Invalid. No train exist.\n");
        else if(cursor != head) {
            cursor = cursor.getPrev();
            System.out.println("Cursor moved backward.\n");
        } else
            System.out.println("No previous car, cannot move cursor backward.\n");
    }

    /**
     * Inserts a TrainCar into the train after the cursor position.
     *
     * @param newCar
     * 		The new TrainCar that is inserted into the train.
     * <p>
     * Preconditions:
     * 		The new TrainCar has been instantiated.
     * <p>
     * Post conditions:
     * 		The new TrainCar has been inserted into the train after the position of the cursor.
     * 		All TrainCar objects previously on the train are still on the train, and their order
     * 		has been preserved.
     * 		The cursor now points to the inserted car.
     *
     * @throws IllegalArgumentException
     * 		when the new TrainCar is null.
     * */
    public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException {
        TrainCarNode newCarNode = new TrainCarNode(newCar);

        if(newCar == null)
            throw new IllegalArgumentException("The inserted train car can't be null.");

        if(isEmpty()) {
            head = newCarNode;
            tail = newCarNode;
        } else {
            if(cursor != tail && cursor.getNext() != null) {
                newCarNode.setNext(cursor.getNext());
                cursor.getNext().setPrev(newCarNode);
            }
            newCarNode.setPrev(cursor);
            cursor.setNext(newCarNode);
        }
        cursor = newCarNode;

        if(cursor.getNext() == null)
            tail = cursor;

        numOfTrainCar++;
        totalLength += newCar.getCarLength();
        totalWeight += newCar.getCarWeight();

    }

    /**
     * Set the current list to an empty linked list.
     */
    public void emptyList(){
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * Remove the head of the linked list.
     *
     * @param node
     *      Current head.
     */
    public void removeHead(TrainCarNode node){
        node.getNext().setPrev(null);
        head = node.getNext();
    }

    /**
     * Remove the tail of the linked list.
     *
     * @param node
     *      Current tail.
     */
    public void removeTail(TrainCarNode node){
        node.getPrev().setNext(null);
        tail = node.getPrev();
    }

    /**
     * Remove the node from the linked list.
     *
     * @param node
     *      Current node.
     */
    public void removeNode(TrainCarNode node){
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
    }

    /**
     * Returns the TrainCar in the node currently referenced by the cursor and then remove
     * it from the train.
     * <p>
     * Preconditions:
     * 		Cursor is not null
     * <p>
     * Post conditions:
     * 		The TrainCarNode referenced by the cursor will be removed from the train.
     * 		The cursor now references to the next node, or the previous node if the
     * 		next node does not exist.
     * */
    public TrainCar removeCursor() throws IllegalArgumentException, EmptyListException {
        if(isEmpty())
            throw new EmptyListException("Invalid: No train exist.\n");

        TrainCar removedCar = cursor.getCar();

        if(cursor == head && cursor == tail)
            emptyList();
        else if(cursor == tail) {
            removeTail(cursor);
            cursor = tail;
        }
        else if(cursor == head) {
            removeHead(cursor);
            cursor = tail;
        }
        else {
            removeNode(cursor);
            cursor = cursor.getNext();
        }

        subtractTotal(removedCar);
        return removedCar;
    }

    /**
     * Increase the total weight, value, and number of dangerous cars of the train whenever a
     * product load is added to a TrainCar.
     *
     * @param car
     * 		The TrainCar that has been inserted a product load.
     * */
    public void addTotal(TrainCar car) {

        ProductLoad load = car.getProductLoad();

        if(load != null) {
            totalValue += load.getValue();
            totalWeight += load.getWeight();

            if(load.isDangerous())
                numOfDangerousCar++;
        }

    }

    /**
     * Decrease the total weight, value, and number of dangerous cars of the train whenever a
     * TrainCar is removed from the train.
     *
     * @param car
     * 		The TrainCar that has been removed from the train.
     * */
    public void subtractTotal(TrainCar car) {
        numOfTrainCar -= 1;
        totalLength -= car.getCarLength();

        if (!car.isEmpty())
            removeProductLoad(car);

        totalWeight -= car.getCarWeight();
    }

    /**
     * Decrement the weight and value of the current car whenever the product load is
     * removed or changed.
     *
     * @param car
     * 		The TrainCar being modified.
     */
    public void removeProductLoad(TrainCar car) {
        ProductLoad load = car.getProductLoad();

        if(load != null) {
            totalWeight -= load.getWeight();
            totalValue -= load.getValue();

            if(load.isDangerous())
                numOfDangerousCar--;
        }
    }

    /**
     * Returns the number of TrainCar.
     *
     * @return
     * 		Number of TrainCar objects on the train.
     * */
    public int size() {
        return numOfTrainCar;
    }

    /**
     * Accessor. Returns the total length of the train in meters.
     *
     * @return
     * 		Total length of the train.
     * */
    public double getLength() {
        return totalLength;
    }

    /**
     * Accessor. Returns the total value of products carried by the train.
     *
     * @return
     * 		Sum of each TrainCar values.
     * */
    public double getValue() {
        return totalValue;
    }

    /**
     * Accessor. Returns the total weight of the train in ton.
     *
     * @return
     * 		Sum each TrainCar weights plus the sum of the ProductLoad carried by that car.
     * */
    public double getWeight() {
        return totalWeight;
    }

    /**
     * Tells whether there's a dangerous product on the train.
     *
     * @return
     * 		True if at least one TrainCar is carrying a dangerous product, false otherwise.
     * */
    public boolean isDangerous() {
        return (numOfDangerousCar > 0);
    }

    /**
     * Prints a neatly formatted table of a specific product load.
     *
     * @param name
     * 		Name of the product
     *
     * @param weight
     * 		Weight of the product
     *
     * @param value
     * 		value of the product
     *
     * 	@param isDangerous
     * 		Whether the product is dangerous or not
     * */
    public static void printLoad(String name, double weight, double value, boolean isDangerous) {
        String table = "\n" + String.format("%-4s%-10s%-13s%-12s%-12s",
                " ", "Name", "Weight (t)", "Value ($)", "Dangerous") + "\n" + "=".repeat(51) +
                "\n" + String.format("%-4s%-10s%-13.1f%-18s%-3s", " ", name, weight,
                String.format("%,.2f", value), (isDangerous)? "Yes" : "No") + "\n";

        System.out.println(table);
    }

    /**
     * Prints the results obtained from findProduct().
     *
     * @param name
     *      Name of the product.
     *
     * @param numOfCar
     *      Number of TrainCars that carries the desired product.
     *
     * @param sumOfWeight
     *      Sum of all product load weights.
     *
     * @param sumOfValue
     *      Sum of all product load values.
     *
     * @param isDangerous
     *      Determine if the product load is dangerous or not.
     */
    private static void printProductFound(String name, int numOfCar, double sumOfWeight, double sumOfValue, boolean isDangerous){
        if(numOfCar == 0)
            System.out.println("No record of " + name + " on board train.\n");
        else {
            System.out.println("The following products were found on " + numOfCar + " cars:");

            printLoad(name, sumOfWeight, sumOfValue, isDangerous);
        }
    }

    /**
     * Search through the entire list to find the product load that has
     * the same name as the desired name. For each found product, their weights and
     * values are sum together. Then, the method will print a single ProductLoad
     * record to the console.
     *
     * @param name
     * 		Name of the ProductLoad to find on the train.
     * */
    public void findProduct(String name) {
        double sumOfWeight = 0, sumOfValue = 0;
        boolean isDangerous = false;
        int numOfCar = 0;

        TrainCarNode search = head;
        while(search != null) {

            ProductLoad load = search.getCar().getProductLoad();

            if(load != null && name.equals(load.getName())) {
                numOfCar++;
                sumOfWeight += load.getWeight();
                sumOfValue += load.getValue();
                if(load.isDangerous())
                    isDangerous = true;
            }
            search = search.getNext();
        }

        printProductFound(name, numOfCar, sumOfWeight, sumOfValue, isDangerous);
    }

    /**
     * Removes all the dangerous car from the train, while maintaining the order of the TrainCar objects
     * in the train.
     * <p>
     * Post conditions:
     * 		All dangerous cars have been removed from this train.
     * 		The order of all non-dangerous cars is maintained.
     * */
    public void removeDangerousCars() throws EmptyListException {

        if(isEmpty())
            throw new EmptyListException("Invalid. No train exist.\n");

        TrainCarNode search = head;
        while(search != null) {
            if(search.getCar().getProductLoad().isDangerous()) {
                if(search == cursor)
                    removeCursor();
                else{
                    if(search == tail)
                        removeTail(search);
                    else if(search == head)
                        removeHead(search);
                    else
                        removeNode(search);
                    subtractTotal(search.getCar());
                }
            }
            search = search.getNext();
        }
    }



    /**
     * Prints a neatly formatted table of the car number, car length, car weight,
     * load name, load weight, load value, and load dangerousness for all the car on the
     * train. If the car is empty, the load would be "Empty", the load weight and value
     * would be 0, and the dangerousness of the car would be "No".
     * */
    public void printManifest() {
        StringBuilder header = new StringBuilder();
        header.append(String.format("%-4s%-36s%-34s", " ", "CAR:", "LOAD:")).append("\n").append(
                String.format("%-6s%-6s%-14s%-13s%-5s%-10s%-13s%-12s%-9s", " ", "Num", "Length (m)",
                        " Weight (t)", "|", "Name", "Weight (t)", "Value ($)",
                        "Dangerous")).append("\n").append(" ".repeat(4)).append(
                                "=".repeat(35)).append("+").append("=".repeat(51)).append("\n");


        if(isEmpty())
            System.out.println(header);
        else
            printManifestRows(head, header);
    }

    /**
     * Helper of the printManifest method. Prints the rows of the table.
     *
     * @param node
     *      Node pointer being used to traverse through the train or linked list.
     *
     * @param table
     *      A neatly formatted table.
     *
     */
    public void printManifestRows(TrainCarNode node, StringBuilder table){
        int carNum = 1;

        while(node != null) {
            String arrow = " ", productName = "Empty", dangerousness = "No";
            double loadWeight = 0, loadValue = 0;

            if(cursor == node)
                arrow = "->";

            TrainCar car = node.getCar();
            ProductLoad load = car.getProductLoad();

            if(load != null) {
                productName = load.getName();
                dangerousness = load.isDangerous() ? "Yes" : "No";
                loadWeight = load.getWeight();
                loadValue = load.getValue();
            }

            table.append(String.format("%-6s%-6d%-15.1f%-12.1f%-5s%-10s%-13.1f%-18.2f%-3s", arrow,
                    carNum, car.getCarLength(), car.getCarWeight(), "|", productName, loadWeight,
                    loadValue, dangerousness)).append("\n");

            carNum++;
            node = node.getNext();
        }
        System.out.println(table);
    }

    /**
     * Checks if a current list is empty.
     *
     * @return
     *      True if the list is empty, false otherwise.
     */
    public boolean isEmpty(){
        return head == null && tail == null && cursor == null;
    }

    /**
     * Returns a neatly formatted String representation of the train, indicating the size (number of cars),
     * length (in meters), weight (in tons), value (in dollars), and dangerousness of the train.
     *
     * @return
     * 		String representation of the train.
     * */
    @Override
    public String toString() {
        return "Train: " + this.size() + " cars, "
                + String.format("%.1f", this.getLength()) + " meters, "
                + String.format("%.1f", this.getWeight()) + " tons, "
                + "$" + String.format("%,.2f", this.getValue()) + " value, "
                + ((isDangerous())? "DANGEROUS." : "Not Dangerous.") + "\n\n";
    }


}

