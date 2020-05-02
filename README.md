#ASSIGNMENT 2
This program is designed to create grocery delivery services which store their inventory (originally .csv file) in a data structure. Upon a live entry of the user/clients item list, the program will parse each service and calculate the best price for your list including the delivery cost. <br />

Use of Data Structures <br />
For this program, I used an ArrayList for both the storage of services and the storage of items read from the .csv files. There are major pros to this but there are also some downsides/better options which I will go over.<br />
A tree implementation would have a fast insertion time complexity while saving space. With an arrayList, each time you expand you may have a large number of empty cells which end up being a waste of space. However, space complexity is not as bad as it could be because in this program there is no removal of items in the arrayList so the maximum wasted space is n/2. <br />
In general I found It much easier to manipulate ArrayLists of objects. Also, when tested with 50,000 items across 10 services, the program held up. Reading .csv, sorting, and searching all had reasonable physical runtimes. <br />
ISSUES WITH IMPLEMENTATION <br />
Assumption 1: I wasn't quite sure what this was referring to, so there is nothing special calculated in regards to annual or monthly deliveries.
Assumption 3: Originally I was under the impression that when certain services would have items that other's don't, the program would remove that service as a valid one but keep building the client list. However I've changed this implementation so that if an item isn't found at a service, the client loop will tell you it's an invalid item and reset the loop. However there is a problem with this implementation as items found before the service that doesn't have it will have added to their running total, and items (not found) after the service that doesn't have it will not be added to their respective running totals, therefore creating an imbalance in the final price comparison. This was the biggest challenge for me in this assignment, which was how to deal with the order of actions upon building the client list. I thought about building another container for the client list but I seemed to run into the same issues either way since I'm checking services sequentially and adding the prices upon immediate return. I think the proper solution to this problem would be a data structure that specializes in backtracking so that once I have built a client list, I can check if its valid at the end, and thats when a increment totals or resent the client loop. That being said I didn't really have time to find a working implementation of this. <br />