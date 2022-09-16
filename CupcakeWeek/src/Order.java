import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    public Order(ArrayList<Cupcake> cupcakeMenu, ArrayList<Drink> drinkMenu) {
        System.out.println("Hello customer. Would you like to place an order? (Y or N)");
        Scanner scan = new Scanner(System.in);
        String placeOrder = scan.nextLine();
        ArrayList<Object> order = new ArrayList<>();
        if (placeOrder.equalsIgnoreCase("Y")) {
            order.add(LocalDate.now());
            order.add(LocalTime.now());
            System.out.println("Here is the menu");
            System.out.println("CUPCAKES:");
            int itemNumber = 0;
            for (Cupcake cupcake: cupcakeMenu) {
                itemNumber++;
                System.out.println(itemNumber);
                cupcake.type();
                System.out.println("Price: " + cupcake.getPrice());
            }

            System.out.println("\n");
            System.out.println("DRINKS:");
            for (Drink drink: drinkMenu) {
                itemNumber++;
                System.out.println(itemNumber);
                drink.type();
                System.out.println("Price: " + drink.getPrice());
            }
            System.out.println("\n");
        } else {
            System.out.println("Have a nice day then");
        }

        boolean ordering = true;
        while (ordering) {
            System.out.println("what would you like to order? Please use the number associated with each item to order");
            int orderChoice = scan.nextInt();
            scan.nextLine();
            if (orderChoice > 0 && orderChoice < 4) {
                order.add(cupcakeMenu.get(orderChoice - 1));
                System.out.println("Item added to order");
            } else if (orderChoice > 3 && orderChoice < 7) {
                order.add(drinkMenu.get(orderChoice - 4));
            } else {
                System.out.println("Sorry, we don't seem to have that on the menu.");
            }
            System.out.println("Would you like to continue ordering? (Y/N");
            String continueOrder = scan.nextLine();
            if (!continueOrder.equalsIgnoreCase("Y")) {
                ordering = false;
            }
        }

        System.out.println(order.get(0));
        System.out.println(order.get(1));
        double subtotal = 0.0;
        Cupcake orderedCupcake;
        Drink orderedDrink;
        for (int i = 2; i < order.size(); i++) {
            if (order.get(i) == cupcakeMenu.get(0)) {
                orderedCupcake = cupcakeMenu.get(0);
                orderedCupcake.type();
                System.out.println(orderedCupcake.getPrice());
                subtotal = subtotal + orderedCupcake.getPrice();
            } else if (order.get(i) == cupcakeMenu.get(1)) {
                orderedCupcake = cupcakeMenu.get(1);
                orderedCupcake.type();
                System.out.println(orderedCupcake.getPrice());
                subtotal = subtotal + orderedCupcake.getPrice();
            } else if (order.get(i) == cupcakeMenu.get(1)) {
                orderedCupcake = cupcakeMenu.get(2);
                orderedCupcake.type();
                System.out.println(orderedCupcake.getPrice());
                subtotal = subtotal + orderedCupcake.getPrice();
            } else if (order.get(i) == drinkMenu.get(0)) {
                orderedDrink = drinkMenu.get(0);
                orderedDrink.type();
                System.out.println(orderedDrink.getPrice());
                subtotal = subtotal + orderedDrink.getPrice();
            } else if (order.get(i) == drinkMenu.get(1)) {
                orderedDrink = drinkMenu.get(1);
                orderedDrink.type();
                System.out.println(orderedDrink.getPrice());
                subtotal = subtotal + orderedDrink.getPrice();
            } else if (order.get(i) == drinkMenu.get(2)) {
                orderedDrink = drinkMenu.get(2);
                orderedDrink.type();
                System.out.println(orderedDrink.getPrice());
                subtotal = subtotal + orderedDrink.getPrice();
            }
        }
        System.out.println("$" + subtotal + "\n");
        new Chocolate();
        new WriteToFile(order);
    }
}

class CreateFile {

    public CreateFile() {
        try {
            File salesData = new File("salesData.txt");
            if (salesData.createNewFile()) {
                System.out.println("File created: " + salesData.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }
}

class WriteToFile {
    public WriteToFile(ArrayList<Object> order) {
        try {
            FileWriter fw = new FileWriter("salesData.txt", true);
            PrintWriter salesWriter = new PrintWriter(fw);
            for (int i = 0; i < order.size(); i++) {
                salesWriter.println(order.get(i));
            }
            salesWriter.close();
            System.out.println("Successfully wrote to the file");
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }
}
