
public class CSVStruct {

    String brand;
    String type;
    String fullName;
    String shortName;
    String weight;
    float cost;

    /* Default constructor for a CSVStruct object
     * @param
     * @return void
     */
    public CSVStruct(){
        brand = "";
        type = "";
        weight = "";
        cost = -99999;
    }

    public CSVStruct(String id, String size){
        brand = id;
        type = "";
        fullName = id;
        weight = size;
        cost = 0;
    }

    /* General set fucntion for assigning array data to CSVStruct properties.
     * Note that this is specific to an array of data given by a particular
     * layout of csv. Any other format other than brand,type,weight,cost will
     * break this.
     * @param String[] data, the array of data read from the csv file
     * @return void
     */
    public void set(String[] data){
        brand = data[0];
        type = data[1];
        weight = data[2];
        cost = Float.parseFloat(data[3]);

        fullName = brand + " " + type + " " + weight;
        shortName = brand + " " + weight;
    }

    /* The following four functions are get functions. Note that these aren't
     * really implemented properly since I didn't make the data private so they
     * aren't really useful
     * @param
     * @return String, float
     */
    public String getBrand(){
        return this.brand;
    }

    public String getType(){
        return this.type;
    }

    public String getWeight(){
        return this.weight;
    }

    public float getCost(){
        return this.cost;
    }

    /* General print function for printing a CSVStruct object
     * @param
     * @return void
     */
    public void printStruct(){
        System.out.println("CSV STRUCT");
        System.out.println("Brand: " + brand + " | Type: " + type + " | Weight: " + weight + " | Cost: " + cost);
    }

}