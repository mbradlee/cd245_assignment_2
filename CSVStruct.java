
public class CSVStruct {

    String brand;
    String type;
    String fullName;
    String shortName;
    String weight;
    float cost;

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

    public void set(String[] data){
        brand = data[0];
        type = data[1];
        weight = data[2];
        cost = Float.parseFloat(data[3]);

        fullName = brand + " " + type + " " + weight;
        shortName = brand + " " + weight;
    }

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

    public void printStruct(){
        System.out.println("CSV STRUCT");
        System.out.println("Brand: " + brand + " | Type: " + type + " | Weight: " + weight + " | Cost: " + cost);
    }

}