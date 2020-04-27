import java.io.*;
import java.util.*;

public class Service extends QuickSort{
    public String serviceName;
    public String csvFile;
    public float deliveryCost;
    ArrayList<CSVStruct> itemList;
    public boolean valid;
    public float runningTotal;

    public Service(){
    }

    public Service(String name, String file, float cost){
        this.serviceName = name;
        this.csvFile = file;
        this.deliveryCost = cost;
        itemList = new ArrayList<CSVStruct>();
        valid = true;
        runningTotal = 0;
    }

    public void printService(){
        System.out.println("Service Name: " + serviceName);
        System.out.println(".csv File: " + csvFile);
        System.out.println("Delivery Cost: " + deliveryCost);
    }

    public void readCSV(){

        File csv = new File(this.csvFile);

        try{
            Scanner s = new Scanner(csv);

            while(s.hasNextLine()){
                CSVStruct struct = new CSVStruct();
                String[] csvProperties = s.nextLine().split(",");

                //[1] COMMIT TO MEMORY
                //populate a csv struct
                struct.set(csvProperties);

                //add it to the list
                itemList.add(struct);

            }

        }catch(FileNotFoundException f){
            System.out.println("CSV File not found.");
        }

    }

    public int findCost(String name){
        return -1;
    }

    public void sortList(){
        sort(itemList);
    }

    public void printCSV(){
        for(int i = 0; i < itemList.size(); i++){
            itemList.get(i).printStruct();
        }
    }

}