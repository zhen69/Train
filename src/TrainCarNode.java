/**
 * The TrainCarNode class represent a specific node in the double-linked list in
 * which it contains information of the train car in the node, the train car in
 * the previous node, and the train car in the next node;
 *
 * @author Zhen Wei Liao
 **/
public class TrainCarNode {
    private TrainCarNode prev;
    private TrainCarNode next;
    private TrainCar car;

    /**
     * Constructor creates an empty TrainCarNode object.
     **/
    public TrainCarNode() {

    }

    /**
     * Constructor creates a TrainCarNode object with a specified TrainCar object in it.
     *
     * @param car
     * 		TrainCar object.
     * */
    public TrainCarNode(TrainCar car) {
        this.car = car;
    }

    /**
     * Accessor. Returns the previous TrainCarNode object in the list/chain.
     *
     * @return
     * 		Previous TrainCarNode object in the list/chain.
     * */
    public TrainCarNode getPrev() {
        return prev;
    }

    /**
     * Accessor. Returns the next TrainCarNode object in the list/chain.
     *
     * @return
     * 		Next TrainCarNode object in the list/chain.
     * */
    public TrainCarNode getNext() {
        return next;
    }

    /**
     * Accessor. Returns the TrainCar object currently in the node.
     *
     * @return
     * 		TrainCar object currently in the node.
     * */
    public TrainCar getCar() {
        return car;
    }

    /**
     * Modifier. Modifies the previous TrainCarNode object in the list/chain
     *
     * @param prev
     * 		Previous TrainCarNode object in the list/chain
     * */
    public void setPrev(TrainCarNode prev) {
        this.prev = prev;
    }

    /**
     * Modifier. Modifies the next TrainCarNode object in the list/chain.
     *
     * @param next
     * 		Next TrainCarNode object in the list/chain.
     * */
    public void setNext(TrainCarNode next) {
        this.next = next;
    }

    /**
     * This setter method modifies the TrainCar object currently in the node.
     *
     * @param car
     * 		TrainCar object currently in the node.
     * */
    public void setCar(TrainCar car) {
        this.car = car;
    }

    /**
     * Returns a string representation of the TrainCarNode object with all necessary information.
     *
     * @return
     * 		String representation of the train car.
     * */
    @Override
    public String toString() {
        String info = "";
        if(prev != null)
            info += "Previous Train Car: \n\n" + prev.getCar().toString() + "\n";

        info += "\nCurrent Train Car: \n\n" + car.toString() + "\n";

        if(next != null)
            info += "\n" + "Next Train Car: \n\n" + next.getCar().toString();

        return info;
    }

}
