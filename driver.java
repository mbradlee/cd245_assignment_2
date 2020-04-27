import java.io.*;
import java.lang.reflect.Array;
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
//        for(int i = 0; i < services.size(); i++) {
//            services.get(i).printService();
//        }

        System.out.println();
        System.out.println("SERVICES LOADED");
        System.out.println();

        //[4] load each services CSV into data structure (commit to memory)

        System.out.println("LOADING CSV FILES...");

        for(int j = 0; j < services.size(); j++){
            services.get(j).readCSV();
            services.get(j).sortList();
//            services.get(j).printCSV();

//            System.out.println();
//            System.out.println("----------BREAK------------");
//            System.out.println();
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
            int index = 0;
            int invalidServices = services.size();
            int invalidIndices[] = new int[invalidServices];
            int serviceReset;

            for(int p = 0; p < invalidServices; p++) {
                invalidIndices[p] = -1;
            }

            for(int k = 0; k < services.size(); k++){

                serviceReset = invalidServices;

                if(services.get(k).valid){
                    index = services.get(k).itemList.find(search, services.get(k).itemList, 0, services.get(k).itemList.size());
                }
                if(index == -1){
                    //bug? price will be added to proceeding services?
                    System.out.println("Couldn't find at: " + services.get(k).serviceName);
                    invalidIndices[k] = k;
                    invalidServices--;
                    if(invalidServices == 0){
                        System.out.println("Invalid item, enter again.");
                        invalidServices = serviceReset;
                        continue;
                    }
                }else{
                    services.get(k).runningTotal += services.get(k).itemList.get(index).cost * clientQuantity;
                    System.out.println(services.get(k).serviceName + " new total: " + services.get(k).runningTotal);
                }
            }

            for(int q = 0; q < invalidServices; q++){
                if(invalidIndices[q] != -1){
                    services.get(invalidIndices[q]).valid = false;
                    invalidIndices[q] = -1;
                }
            }

        }

        //[6] output final (best) price
        float bestPrice = 0;
        int bestIndex = 0;
        int m = 0;
        for(; m < services.size(); m++){
            if(services.get(m).valid) {
                bestIndex = m;
                bestPrice = services.get(m).runningTotal + services.get(m).deliveryCost;
                m++;
                break;
            }
        }
        for(int n = m; n < services.size(); n++){
            if(services.get(m).valid){
                if(services.get(n).runningTotal + services.get(n).deliveryCost < bestPrice){
                    bestIndex = n;
                    bestPrice = services.get(n).runningTotal;
                }
            }
        }

        System.out.println("The best delivery service for you is " + services.get(bestIndex).serviceName);
        System.out.println("With a total cost of: $" + services.get(bestIndex).runningTotal + " and a delivery fee of: $" + services.get(bestIndex).deliveryCost);

    }

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
