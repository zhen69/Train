/**
 * The ProductLoad class contains basic product information for a specific train car,
 * such as the name of the product, the weight, the value, and whether the product
 * itself is dangerous or not.
 *
 * @author Zhen Wei Liao
 **/
public class ProductLoad {
    private String name;
    private double weight, value;
    private boolean isDangerous;

    /**
     * Constructor creates an empty ProductLoad object
     */
    public ProductLoad(){
    }

    /**
     * Constructor creates a new product load with specified information.
     *
     * @param name
     * 		Name of the product.
     *
     * @param weight
     * 		Weight of the product.
     *
     * @param value
     * 		Value of the product.
     *
     * @param isDangerous
     * 		Whether the product is dangerous.
     *
     */
    public ProductLoad(String name, double weight, double value, boolean isDangerous){
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.isDangerous = isDangerous;
    }

    /*
     * This getter method gives the name of the product
     *
     * @return
     * 		name of the product
     * */
    public String getName() {
        return name;
    }

    /**
     * Accessor. Returns the weight of the product.
     *
     * @return
     * 		Weight of the product
     *
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Accessor. Returns the value of the product.
     *
     * @return
     * 		Value of the product.
     *
     */
    public double getValue() {
        return value;
    }

    /**
     * Accessor. Tells if the product is dangerous or not.
     *
     * @return
     * 		True if the product is dangerous, false otherwise.
     *
     */
    public boolean isDangerous() {
        return isDangerous;
    }

    /**
     * Modifier. Modifies the name of the product
     *
     * @param name
     * 		Name of the product
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Modifier. Modifies the weight of the product
     *
     * @param weight
     * 		Weight of the product
     *
     * @throws IllegalArgumentException
     * 		when weight is set to a negative number
     *
     */
    public void setWeight(double weight) throws IllegalArgumentException{
        if(weight < 0)
            throw new IllegalArgumentException("Invalid inputs. Weight can't be negative.\n");

        this.weight = weight;
    }

    /**
     * Modifier. Modifies the value of the product
     *
     * @param value
     * 		Value of the product
     *
     * @throws IllegalArgumentException
     * 		when value is set to a negative number
     *
     */
    public void setValue(double value) throws IllegalArgumentException{
        if(value < 0)
            throw new IllegalArgumentException("Invalid inputs. Value can't be negative.\n");

        this.value = value;
    }

    /**
     * This setter method modifies the dangerousness of the product
     *
     * @param isDangerous
     * 		Dangerousness of the product.
     *
     */
    public void setIsDangerous(boolean isDangerous) {
        this.isDangerous = isDangerous;
    }

    /**
     * This method overrides the toString() method to obtain a string representation
     * of the product load with all necessary information.
     *
     * @return
     * 		String representation of the product load
     *
     */
    @Override
    public String toString() {
        return "Product Name: " + name + "\n"
                +"Weight: " + weight + "t \n"
                +"Value: " + value + "$ \n"
                +"Dangerous: " + ((isDangerous)? "Yes" : "No") + "\n";
    }

    /**
     * Determine if two ProductLoad objects contain the same information.
     *
     * @param obj
     * 		The object that is being used to compare with the current ProductLoad object.
     *
     * @return
     * 		The boolean value or truth value after comparing all the information.
     * 		If obj is not a ProductLoad object, this method would always return false.
     *
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof ProductLoad)) return false;

        ProductLoad objAsPL = (ProductLoad) obj;

        return objAsPL.name.equals(this.name) && objAsPL.isDangerous == this.isDangerous
                && Double.compare(objAsPL.value, this.value) == 0 && Double.compare(objAsPL.weight, this.weight) == 0;
    }
}
