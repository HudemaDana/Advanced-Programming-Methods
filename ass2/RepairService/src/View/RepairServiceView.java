package View;

import Controller.RepairServiceController;
import Model.Motocycle;
import Model.Bicycle;
import Model.Car;
import Model.Abstraction.Item;
import java.util.Scanner;

public class RepairServiceView {

    private RepairServiceController _controller;

    public RepairServiceView(RepairServiceController controller){
        _controller = controller;
    }

    public void menu(){
        System.out.println("----------------------------------");
        System.out.println("Welcome to Auto Service");
        System.out.println("----------------------------------");
        System.out.println();
        System.out.println("1. Add a new vehicle");
        System.out.println("2. Remove a vehicle");
        System.out.println("3. Show vehicles with red color");
        System.out.println("4. Show all vehicles");
        System.out.println("0. Exit");
    }

    public void menuAdd(){
        System.out.println("Choose the type of vehicle you want to create:");
        System.out.println();
        System.out.println("1. Car");
        System.out.println("2. Bicycle");
        System.out.println("3. Motorcycle");
        System.out.println("0. Exit");

        System.out.println();
        System.out.print("Type your choice: ");

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        switch(command){
            case "1":
                menuAddVehicleRepairCost("car");
                break;
            case "2":
                menuAddVehicleRepairCost("bicycle");
                break;
            case "3":
                menuAddVehicleRepairCost("motorcycle");
                break;
            case "0":
                break;
            default:
                System.out.println("Wrong input");
                break;
        }

    }

    public void menuAddVehicleRepairCost(String vehicle){
        Scanner in = new Scanner(System.in);
            try{
                System.out.print("Type the color of your vehicle: ");
                String color = in.nextLine();
                switch(vehicle){
                    case "car":
                        Item newCar = new Car(color);
                        _controller.addItem(newCar);
                        break;
                    case "bicycle":
                        Item newBicycle = new Bicycle(color);
                        _controller.addItem(newBicycle);
                        break;
                    case "motorcycle":
                        Item newMotorcycle = new Motocycle(color);
                        _controller.addItem(newMotorcycle);
                        break;
                }
            }
            catch(Exception e){
                System.err.println(e);
                return;
            }

    }

    public void menuRemoveItem(){
        Scanner in = new Scanner(System.in);

            try{
                System.out.print("Type the index of the item you want to remove: ");
                int index = in.nextInt();
                _controller.removeItem(index);
            }
            catch(NumberFormatException e){
                System.err.println("Invalid format for index");
            }
            catch(Exception e){
                System.out.println(e);
            }

    }

    public void filterByColor(){
        try {
            Item[] filteredArray = _controller.filterByColor();
            for(Item i:filteredArray){
                if(i!=null) {
                    System.out.println(i.DisplayObject());
                }
                else{
                    break;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void displayAll(){
        for(Item i:_controller.getAllItems()){
            if(i!=null) {
                System.out.println(i.DisplayObject());
            }
            else{
                break;
            }
        }
    }

    public void run(){
        Scanner in = new Scanner(System.in);

        while(true){
           menu();
           String command = in.nextLine();
           switch (command){
               case "1":
                   menuAdd();
                   break;
               case "2":
                   menuRemoveItem();
                   break;
               case "3":
                   filterByColor();
                   break;
               case "4":
                   displayAll();
                   break;
               case "0":
                   return;
               default:
                   System.out.println("Wrong input. Please try again.");
                   break;

           }
        }
    }

}
