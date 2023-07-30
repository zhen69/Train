/**
 * The Train class contains basic information of a specific train car,
 * such as the length, the weight, and the product load of the train.
 *
 * @author Zhen Wei Liao
 *
 */
public class TrainCar {
    private double carLength, carWeight;
    private ProductLoad load;

    /**
     * Constructor creates an empty TrainCar object.
     */
    public TrainCar() {

    }

    /**
     * Constructor creates a TrainCar object with specified information.
     *
     * @param carWeight
     * 		Weight of the train car.
     *
     * @param carLength
     * 		Length of the train car.
     *
     */
    public TrainCar(double carWeight, double carLength) {
        if(carLength < 0)
            throw new IllegalArgumentException("Invalid. Length can't be negative.\n");
        if(carWeight < 0)
            throw new IllegalArgumentException("Invalid. Weight can't be negative.\n");

        this.carLength = carLength;
        this.carWeight = carWeight;
    }

    /**
     * Accessor. Returns the length of the train car.
     *
     * @return
     * 		Length of the train car.
     *
     */
    public double getCarLength() {
        return carLength;
    }

    /**
     * Accessor. Returns the weight of the train car.
     *
     * @return
     * 		Weight of the train car.
     *
     */
    public double getCarWeight() {
        return carWeight;
    }

    /**
     * Accessor. Returns the product load of the train car.
     *
     * @return
     * 		Product load of the train car.
     *
     */
    public ProductLoad getProductLoad() {
        return load;
    }

    /**
     * Modifier. Modifies the product load of the train car
     *
     * @param load
     * 		Product load of the train car
     *
     */
    public void setProductLoad(ProductLoad load) {
        this.load = load;
    }

    /**
     * Tells if the product load of the train car is empty or not
     *
     * @return
     * 		true if the product load is empty, false otherwise;
     *
     */
    public boolean isEmpty() {
        return (load == null);
    }

    /**
     * Returns a string representation of the train car with all necessary information.
     *
     * @return
     * 		string representation of the train car
     *
     */
    @Override
    public String toString() {
        return "Car Length: " + carLength + "m \n"
                +"Car Weight: " + carWeight + "t \n"
                +"Load: \n" + load.toString();
    }

    /**
     * Determine if two TrainCar objects contain the same information.
     *
     * @param obj
     * 		The object that is being used to compare with the current TrainCar object
     *
     * @return
     * 		The boolean value or truth value after comparing all the information.
     * 		If obj is not a TrainCar object, this method would always return false.
     *
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof TrainCar)) return false;

        TrainCar objAsTrain = (TrainCar) obj;

        return Double.compare(objAsTrain.carLength, this.carLength) == 0
                && Double.compare(objAsTrain.carWeight, this.carWeight) == 0 && objAsTrain.load.equals(this.load);
    }
}
