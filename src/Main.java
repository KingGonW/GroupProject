import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String input;

    private static ArrayList<Customer> customers;

    public static void main(String[] args) {
        customers = new ArrayList<>();
        // temporary created to test the app
        customers.add(new Customer("Mohsen","-","Mohsen@gmail.com", "999999999"));
        customers.add(new Customer("King","-","King@gmail.com", "999999999"));
        customers.add(new Customer("Tamara","-","Tamara@gmail.com", "999999999"));
        customers.add(new Customer("Andy","-","Andy@gmail.com", "999999999"));

        mainMenu();
    }

    public static void mainMenu(){
        System.out.println("MAIN MANU\nPlease select an option ... ");

        do {
            System.out.println("1.Admin\n2.Customer\n3.Exit");

            input = scanner.next();
            switch (input) {
                case "1":
                    adminMenu();
                    break;
                case "2":
                    customerMenu();
                    break;
                default:
                    if (!input.equals("3")) {
                        if (!input.equals("3"))
                            System.out.println("Please enter a valid number");
                    }break;
            }
        } while (!input.equals("3"));
    }



        private static void adminMenu() {
            System.out.println("ADMIN MANU\nPlease select an option ... ");
            System.out.println("1.All customers\n2.find a Customer\n3.delete a customer\n4.Transfer money\n5.Exit to main menu");

            do {
                input = scanner.next();
                switch (input) {
                    case "1":
                        findAllCustomers();
                        break;
                    case "2":
                        findCustomer();
                        break;
                    case "3":
                        deleteCustomer();
                        break;
                    case "4":
                        transferMoney();
                        break;
                    default:
                        if (!input.equals("5")) {
                            if (!input.equals("5")){
                                System.out.println("Please enter a valid number");
                                adminMenu();}
                        }break;
                }
            } while (!input.equals("5"));
        }

    private static void transferMoney() {


    }

    private static void customerMenu() {
            System.out.println("CUSTOMER MANU\nPlease select an option ... ");
            System.out.println("1.Register\n2.Login\n3.Help\n4.Exit to main menu");
            do {
                input = scanner.next();
                switch (input) {
                    case "1":
                        newCustomer();
                        break;
                    case "2":
                        customerLogin();
                        break;
                    case "3":
                        System.out.println("please call +44123456789 to get help\n---------------------------");
                        customerMenu();
                        break;
                    default:
                        if (!input.equals("4")) {
                            if (!input.equals("4")){
                                System.out.println("Please enter a valid number");
                                customerMenu();}
                        }break;
                }
            } while (!input.equals("4"));

        }

        public static void deleteCustomer(){
            System.out.println("Please enter the customer ID");
            Long cusID = scanner.nextLong();
            for (int i = 0; i < customers.size(); i++){
                if(customers.get(i).getId() == cusID){
                    customers.remove(i);
                    System.out.println("====> Selected customer is successfully removed from the system");
                    adminMenu();
                }}
            System.out.println("====> Customer does not exist");
            adminMenu();
        }

        public static void findAllCustomers(){
            System.out.println("Please enter the customer ID");
            for (int i = 0; i < customers.size(); i++){
                System.out.println(customers.get(i).toString());
            }
            adminMenu();
        }
        public static void findCustomer(){
            System.out.println("Please enter the customer ID");
            Long cusID = scanner.nextLong();
            for (int i = 0; i < customers.size(); i++){
                if(customers.get(i).getId() == cusID){
                    System.out.println(customers.get(i).toString());
                    adminMenu();
                }
            }
            System.out.println("====> Customer does not exist");
            adminMenu();
        }
        public static void newCustomer(){
            System.out.println("Please enter your name:");
            String tempName = scanner.next();
            System.out.println("Please enter your last name:");
            String tempLastName = scanner.next();
            System.out.println("Please enter your email address:");
            String tempEmailAddress = scanner.next();
            System.out.println("Please enter your phone number:");
            String tempPhone = scanner.next();
            customers.add(new Customer(tempName,tempLastName,tempEmailAddress,tempPhone));
            customerMenu();
        }

        public static long validation(){
            System.out.println("Please enter your name ");
            String cusName = scanner.next();
            for (int i = 0; i < customers.size(); i++){
                if(customers.get(i).getName().equals(cusName)){
                    System.out.println("====> WELCOME " + cusName );
                    return customers.get(i).getId();
                }
            }
            return 0;
        }
        public static void customerLogin(){
            if(validation() != 0){
                yourBank();
            }
             else
                System.out.println("Please enter a valid name ");
            customerMenu();

        }

        public static void yourBank(){
            System.out.println("BANK MANU\nPlease select an option ... ");
            System.out.println("1.create a new Bank account\n2.list of the current accounts\n3.Exit to main menu");
            do {
                input = scanner.next();
                switch (input) {
                    case "1":
                        createNewBankAccount();
                        break;
                    case "2":
                        currentBankAccounts();
                        break;
                    default:
                        if (!input.equals("3")) {
                            if (!input.equals("3")){
                                System.out.println("Please enter a valid number");
                                customerMenu();}
                        }break;
                }
            } while (!input.equals("3"));
        }
    private static void createNewBankAccount() {

    }
    private static void currentBankAccounts() {

    }



}