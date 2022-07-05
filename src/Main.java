import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private String input;
    private ArrayList<Customer> customers;
    private Customer currentAccount;

    //i hope this works
    public static void main(String[] args) {

        Main main = new Main();
        main.customers = new ArrayList<>();
        main.currentAccount = new Customer();
        // temporary created to test the app
        main.validCustomer(new Customer("Mohsen", "M", "mohsen@gmail.com", "999999999"));
        main.validCustomer(new Customer("King", "K", "king@gmail.com", "999999999"));
        main.validCustomer(new Customer("Tamara", "T", "tamara@gmail.com", "999999999"));
        main.validCustomer(new Customer("Andy", "A", "andy@gmail.com", "999999999"));
        main.generateRandomAccounts(main.customers.get(0));
        main.generateRandomAccounts(main.customers.get(1));
        main.generateRandomAccounts(main.customers.get(2));
        main.generateRandomAccounts(main.customers.get(3));
        for (int i = 0; i < main.customers.size(); i++) {
            main.printCustomerAccounts(i + 1l);
        }
        main.mainMenu();


    }

    public void generateRandomAccounts(Customer customer){
        for (int i = 0; i < 2; i++) {
            createCurrentAccount(customer.getId());
            createBusinessAccount(customer.getId());
            createISAAccount(customer.getId());
        }


    }
    
    public void printCustomerAccounts(Long customerID){
        int customerNumberInInt = customerID.intValue();
        System.out.println(customers.get(customerNumberInInt - 1).getName());
        customers.get(customerNumberInInt - 1).getListOfBankAccounts();
        
    }

    public void validCustomer(Customer customer) {
        for (Customer value : customers) {
            if (value.getEmailAddress().equals(customer.getEmailAddress())) {
                System.out.println("ERROR: This email address already exists");
                return;
            }
        }
        customers.add(customer);
    }

    public void mainMenu() {
        System.out.println("\n");
        System.out.println("==================================================");
        System.out.println("====== Welcome to Novus Banking Application ======");
        System.out.println("==================================================");
        System.out.println("\n");

        do {
            System.out.println("Please choose an option");
            System.out.println("1: Admin Access\n2: Exit Program");


            input = scanner.next();
            if ("1".equals(input)) {
                adminMenu();
            } else {
                if (!input.equals("2")) {
                    System.out.println("Please enter a valid number");
                }
            }
        } while (!input.equals("2"));
    }

    // add feature to allow admin to transfer money between accounts
    //add feature to allow admin to create accounts for customers.
    private void adminMenu() {
        System.out.println("\nPlease choose an option");
        System.out.println("""
                1: View All Customers\s
                2: Login to Customer Account
                3: Exit to Main Menu""");
        input = scanner.next();
        switch (input) {
            case "1":
                findAllCustomers();

                adminMenu();
                break;
            case "2":
                for (Customer customer : customers) {
                    System.out.println(customer.toString());
                }
                customerLogin();
                break;
            case "3":
                mainMenu();
                break;
            default:
                if (!input.equals("4")) {
                    System.out.println("Please enter a valid number");
                    adminMenu();
                }
                break;
        }
        if (!input.equals("4"))
            adminMenu();
        else
            mainMenu();

    }

    // created default back option to return to previous menu or initial main menu
    //this can be used in any section of the menu, from viewing customers to creating accounts
    public void returnMenus() {

        System.out.println("\nChoose an Option");
        System.out.println("1: Back to Admin Menu\n" +
                "2: Back to Main Menu");

        input = scanner.next();
        switch (input) {
            case "1":
                adminMenu();
            case "2":
                mainMenu();

        }

    }

    //adding methods in order that it shows up in the admin menu
    public void findAllCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }


    public void customerLogin() {
        long temp = loginValidation();
        if (temp != 0) {
            yourBank(temp);
        } else {
            System.out.println("Please enter a valid name ");
            adminMenu();
        }
    }

    public Customer findCustomer(Long customerID) {

        for (Customer customer : customers) {
            if (customer.getId() == customerID) {
                System.out.println(customer);
                return customer;
            }
        }
        System.out.println("====> Customer does not exist");
        return null;
    }

   public BankAcc getTheAccountNumber(int customerId, int accNumber) {
       Long tempLong = (long) customerId;
       Customer temp = null;
       for (int i = 0; i < customers.size(); i++) {
           temp = customers.get(customerId);
       }
       for (int i = 0; i < temp.getBankAccounts().size(); i++) {
           return temp.getBankAccounts().get(accNumber);
       }

       System.out.println("Cant find the the Account  ");
       return null;

   }
    private void transferMoney() {
        findAllCustomers();

        System.out.println("Please enter the Customer ID that you would to transfer from ...");
        String tempCus1 = scanner.next();
        int cus1 = Integer.parseInt(tempCus1);
        customers.get(cus1 - 1).getListOfBankAccounts();

        System.out.println("Please enter the account ID to transfer from ....");
        String tempAcc1 = scanner.next();
        int acc1 = Integer.parseInt(tempAcc1);

        System.out.println("Please enter the Customer ID that you would to transfer to ...");
        String tempCus2 = scanner.next();
        int cus2 = Integer.parseInt(tempCus2);
        customers.get(cus2 - 1).getListOfBankAccounts();

        System.out.println("Please enter the account ID to transfer from ....");
        String tempAcc2 = scanner.next();
        int acc2 = Integer.parseInt(tempAcc2);

        System.out.println("Please enter the the amount of money  ....");
        String tempAmount = scanner.next();
        double amount = Double.parseDouble(tempAmount);
        customers.get(cus1 - 1).getBankAccounts().get(0).moneyTransfer(getTheAccountNumber(cus1 - 1, acc1 - 1), getTheAccountNumber(cus2 - 1, acc2 - 1), amount);

        //the below posts this info to the Transactions class
        // this should work
        // to test, let us call transferMoney() from one of the menus
        // customers.get(cus1-1).addAccountTransaction(acc1, -1 * amount, String.format("Origin account %s",
        // customers.get(cus1-1).getAccountNumber(acc2)));
        //customers.get(cus1-1).addAccountTransaction(acc2, amount, String.format("Destination account %s",
        //customers.get(cus1-1).getAccountNumber(acc1)));

    }





    /*public void deleteCustomer() {
        System.out.println("Please enter the customer ID");
        long cusID = scanner.nextLong();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == cusID) {
                customers.remove(i);
                System.out.println("====> Selected customer is successfully removed from the system");
                adminMenu();
            }
        }
        System.out.println("====> Customer does not exist");
        adminMenu();
    }*/




    /*public void newCustomer() {
        System.out.println("Please enter your name:");
        String tempName = scanner.next();
        System.out.println("Please enter your last name:");
        String tempLastName = scanner.next();
        System.out.println("Please enter your email address:");
        String tempEmailAddress = scanner.next();
        System.out.println("Please enter your phone number:");
        String tempPhone = scanner.next();
        validCustomer(new Customer(tempName, tempLastName, tempEmailAddress, tempPhone));
        adminMenu();
    }*/

    public long loginValidation() {
        long temp = 0;
        System.out.println("Please enter customer name ");
        String cusName = scanner.next();
        //this changes the first letter of users input to capital letter
        //so no matter what a lower or upper case input, the user will be found by their name
        cusName = cusName.substring(0, 1).toUpperCase() + cusName.substring(1);
        for (Customer customer : customers) {
            if (customer.getName().equals(cusName)) {
                System.out.println("====> WELCOME " + cusName);
                temp = customer.getId();
            }
        }
        System.out.println(temp);
        return temp;

    }


    public void yourBank(long customerNumber) {
        System.out.println("Please Select an Option");
        System.out.println("""
                1: Create a new Bank account\s
                2: View accounts
                3: View Account Transaction History: 
                4: Exit to Admin Menu
                5: Exit to Main Menu""");

        input = scanner.next();
        switch (input) {
            case "1":
                createNewBankAccount(customerNumber);
                break;
            case "2":
                /*for (int i = 0; i < customers.size(); i++) {
                    System.out.println(customers.get(i).getName() + customers.get(i).getBankAccounts().toString());
                }*/
                allCustomerAccounts(customerNumber);
                break;
            case "3":
                // showTransactionHistory(Customer currentCustomer, Scanner sc);
                // how can we get or define the currentCustomer variable - which is supposed to represent the selected customer?- see notes
                break;
            case "4":
                adminMenu();
                break;
            case "5":
                mainMenu();
            default:
                if (!input.equals("6")) {
                    System.out.println("Please enter a valid number");
                    adminMenu();
                }
                break;
        }
        if (!input.equals("6"))
            yourBank(customerNumber);
    }

    private void createNewBankAccount(long customerNumber) {
        System.out.println("Choose Account Type to Create");
        System.out.println("""
                1: Create Current Account\s
                2: Create Business Account
                3: Create ISA Account:
                4: Back to Customer Accounts""");
        input = scanner.next();
        switch (input) {
            case "1":
                createCurrentAccount(customerNumber);
                printCustomerAccounts(customerNumber);
                break;
            case "2":
                createBusinessAccount(customerNumber);
                printCustomerAccounts(customerNumber);
                break;
            case "3":
                createISAAccount(customerNumber);
                printCustomerAccounts(customerNumber);
                break;
            default:
                if (!input.equals("4")) {
                    System.out.println("Please enter a valid number");
                    allCustomerAccounts(customerNumber);
                }
                break;
        }
        if (!input.equals("4"))
            createNewBankAccount(customerNumber);
        else
            yourBank(customerNumber);
    }

    private void allCustomerAccounts(long customerNumber) {
        for (Customer customer : customers) {
            if (customer.getId() == customerNumber) {
                customer.getListOfBankAccounts();
            }
        }

        System.out.println("Please select an account to continue ...  ");
//        int accNum = scanner.nextInt();
//        if (accNum - 1 <= temp.getBankAccounts().size())
//            return temp.getBankAccounts().get(accNum - 1);
//
//        System.out.println("please enter a valid number");
    }

    public void yourAccount(BankAcc bankAcc) {
        System.out.println("Choose an Option\n");
        System.out.println("""
        1: Make a deposit\s
        2: Make a withdrawal
        3: View Balance
        4: View Account Transaction History
        5: Exit to Customer Menu""");
        input = scanner.next();
        switch (input) {
            case "1":
                deposit();
                break;
            case "2":
                withdrawal();
                break;
            /*case "3":
                //viewbalance();
                break;*/
            case "4":
                //showTransactionHistory(Customer currentCustomer, Scanner sc); - see notes in yourBank()

                break;
            case "5":
                mainMenu();
                break;
            default:
                if (!input.equals("5")) {
                    System.out.println("Please enter a valid number");
                    adminMenu();
                }
                break;
        }
    }

    public void deposit() {
        BankAcc BA = new BankAcc();
        System.out.println("Please enter the amount to deposit");
        double dmoney = scanner.nextDouble();
        BA.depositMoney(dmoney);
    }

    public void withdrawal() {
        BankAcc BA = new BankAcc();
        System.out.println("Please enter the amount to withdrawal");
        double wmoney = scanner.nextDouble();
        BA.withdrawMoney(wmoney);
    }

    public void createCurrentAccount(Long customerNumber) {
        BankAcc currentAcc = new CurrentAccount();
        int customerNumberInInt = customerNumber.intValue();
        customers.get(customerNumberInInt - 1).getBankAccounts().add(currentAcc);
    }

    public void createBusinessAccount(Long customerNumber) {
        BankAcc businessAcc = new BusinessAccount();
        int customerNumberInInt = customerNumber.intValue();
        //ask user to deposit money to open ISA account
        //check if balance is less than 100, if yes
        //prompt user to make balance 100
        //only then when balance = 100 or more
        //add the created account to customer array
        customers.get(customerNumberInInt - 1).getBankAccounts().add(businessAcc);


    }

    public void createISAAccount(Long customerNumber) {
        BankAcc ISAAcc = new ISAAccount();
        int customerNumberInInt = customerNumber.intValue();
        customers.get(customerNumberInInt - 1).getBankAccounts().add(ISAAcc);

    }

    //below method shows transaction history
    //it takes the Customer selected, shown as currentCustomer
    //loops through the accounts associated with the Customer
    //and displays the information
    //that information needs to be generated in the withdraw(), deposit() and transfer() methods.
    public static void showTransactionHistory(Customer currentCustomer, Scanner sc) {

        int theAccount;
        //get the account for which we wish to show the history

        do {
            //account is chosen from the arraylist. Number refers to the account in the array list,
            // not the account number
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "you wish to see the history of:", currentCustomer.numAccounts());

            // -1 to get to the 0 index position
            theAccount = sc.nextInt() - 1;
            if (theAccount < 0 || theAccount >= currentCustomer.numAccounts()) {
                System.out.println("Invalid Account chosen. Please try again.");
            }
        } while (theAccount < 0 || theAccount >= currentCustomer.numAccounts());

        //print transactions history
        currentCustomer.printAccountTransactionHistory(theAccount);
    }


}