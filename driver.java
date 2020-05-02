/* @file driver
 * @author Marcus Bradlee
 */

import java.io.*;
import java.util.*;

public class driver extends Service{

    public static void main(String[] args) {

        //[1] create file for config
        File config = new File("config.txt");

        //[2] create ArrayList to store all possible services
        ArrayList<Service> services = new ArrayList<Service>();

        //[3] load services from config into services ArrayList

        System.out.println("LOADING SERVICES...");

        readConfig(config, services);

        System.out.println();
        System.out.println("SERVICES LOADED");
        System.out.println();

        //[4] load each services CSV into data structure (commit to memory)

        System.out.println("LOADING CSV FILES...");

        for(int j = 0; j < services.size(); j++){
            services.get(j).readCSV();
            services.get(j).sortList();
        }

        System.out.println();
        System.out.println("CSV FILES LOADED");
        System.out.println();

        //[5] begin building client list (user input)
        //[5.5] remove services as necessary?

        System.out.println("BUILDING CLIENT LIST...");
        System.out.println();
        System.out.println("Welcome!");

        String itemName;
        String itemSize;
        String search;
        int clientQuantity;
        int clientItemCount = 0;

        while(true){

            Scanner client = new Scanner(System.in);

            try{
                System.out.println("Enter your next item or \"done\""); //can you enter done at any time?
                System.out.print("> \t");
                itemName = client.nextLine();
                if(itemName.equals("done")){
                    break;
                }

                System.out.println("Enter item SIZE");
                System.out.print("> \t");
                itemSize = client.nextLine();

                System.out.println("Enter item QUANTITY");
                System.out.print("> \t");
                clientQuantity = client.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Invalid input, enter again.");
                continue;
            }

            search = itemName + " " + itemSize;
            int index;

            for(int k = 0; k < services.size(); k++) {

                index = services.get(k).itemList.find(search, services.get(k).itemList, 0, services.get(k).itemList.size());

                if (index == -1) {
                    System.out.println("Invalid item, enter again.");
                    continue;
                }else{
                    services.get(k).runningTotal += services.get(k).itemList.get(index).cost * clientQuantity;
                    System.out.println(services.get(k).serviceName + " new total: " + services.get(k).runningTotal);
                    clientItemCount++;
                }
            }

        }

        //[6] output final (best) price
        if(clientItemCount > 0) {
            float bestPrice = 0;
            int bestIndex = 0;
            int m = 0;
            for (; m < services.size(); m++) {
                bestIndex = m;
                bestPrice = services.get(m).runningTotal + services.get(m).deliveryCost;
                m++;
                break;
            }
            int n = m;
            for (; n < services.size(); n++) {
                if (services.get(n).runningTotal + services.get(n).deliveryCost < bestPrice) {
                    bestIndex = n;
                    bestPrice = services.get(n).runningTotal;
                }
            }

            System.out.println("The best delivery service for you is " + services.get(bestIndex).serviceName);
            System.out.println("With a total cost of: $" + services.get(bestIndex).runningTotal + " plus a delivery fee of: $" + services.get(bestIndex).deliveryCost);
        }else{
            System.out.println("Your list was empty. Goodbye!");
        }
    }

    /* readConfig is a function that receives the config file and loads each line into its own service item.
     * After calling this function, all services provided by the config will have its own object stored inside
     * an arrayList called services.
     * @param File config, the file containing the initial service info, ArrayList<Service> services, the arrayList to be populated
     * @return void
     */

    public static void readConfig(File config, ArrayList<Service> services){
        try{
            Scanner s = new Scanner(config);

            while(s.hasNextLine()){
                String[] serviceProperties = s.nextLine().split(",");

                String[] serviceName = serviceProperties[0].split("=");
                serviceProperties[0] = serviceName[1];

                Service service  = new Service(serviceProperties[0], serviceProperties[1], Float.parseFloat(serviceProperties[2]));
                services.add(service);

            }

        }catch(FileNotFoundException f){
            System.out.println("Config file not found.");
        }
    }

}
