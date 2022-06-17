import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static String input;

    private ArrayList<Customer> customers;

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("Please select an option ... ");
        do {
            System.out.println("1.Admin\n2.Customer\n3.Exit\n-------------\n");
            input = scanner.next();
            switch (input) {
                case "1":
                    adminMenu();
                    break;
                case "2":
                    customerMenu();
                    break;
                default:
                    if (!input.equals("3"))
                        System.out.println("Please enter a valid number");
                    break;
            }
        } while (!input.equals("3"));
    }

    private static void adminMenu() {
        System.out.println("ADMIN MANU\nPlease select an option ... ");
        do {
            System.out.println("1.All customers\n2.find a Customer\n3.delete a customer\n4.Exit to main menu");
            input = scanner.next();
            switch (input) {
                case "1":
                    System.out.println("All customers\n---------------------------");
                    break;
                case "2":
                    System.out.println("please enter the customer number\n---------------------------");
                    break;
                case "3":
                    System.out.println("Select a customer number to be deleted\n---------------------------");
                    break;
                default:
                    if (!input.equals("4"))
                        System.out.println("Please enter a valid number");
                    break;
            }
        } while (!input.equals("4"));
    }

    private static void customerMenu() {
        System.out.println("CUSTOMER MANU\nPlease select an option ... ");
        do {
            System.out.println("1.Register\n2.Login\n3.Help\n4.Exit to main menu");
            input = scanner.next();
            switch (input) {
                case "1":
                    System.out.println("create a new customer\n---------------------------");
                    break;
                case "2":
                    System.out.println("please enter your username and password\n---------------------------");
                    break;
                case "3":
                    System.out.println("please call +44123456789 to get help\n---------------------------");
                    break;
                default:
                    if (!input.equals("4"))
                        System.out.println("Please enter a valid number");
                    break;
            }
        } while (!input.equals("4"));

    }





}