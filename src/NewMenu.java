import java.util.ArrayList;
import java.util.Scanner;

public class NewMenu {
    private final Scanner scanner = new Scanner(System.in);
    private String input;
    private ArrayList<Customer> customers;
    private Customer currentAccount;

    public static void main(String[] args) {

        NewMenu menu = new NewMenu();
        menu.customers = new ArrayList<>();
        menu.currentAccount = new Customer();
        // temporary created to test the app
        menu.validCustomer(new Customer("Mohsen", "M", "mohsen@gmail.com", "072812712"));
        menu.validCustomer(new Customer("King", "K", "king@gmail.com", "077435261"));
        menu.validCustomer(new Customer("Tamara", "T", "tamara@gmail.com", "074362811"));
        menu.validCustomer(new Customer("Andy", "A", "andy@gmail.com", "078547711"));

        menu.mainMenu();


    }

    public void validCustomer(Customer customer) {
        for (Customer value : customers) {
            if (value.getEmailAddress().equals(customer.getEmailAddress())) {
                System.out.println("ERROR: This email address is already exist");
                return;
            }
        }
        customers.add(customer);
    }

    public void mainMenu() {
        System.out.println("\n\n");
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
                2: Create New Customer
                3: Login to Customer Account
                4: Find Customer
                5: Delete Customer
                6: Transfer Money
                7: Exit to Main Menu""");
        input = scanner.next();
        switch (input) {
            case "1":
                findAllCustomers();
                for (Customer customer : customers) {
                    System.out.println(customer.getName() + customer.getBankAccounts().toString());
                }
                break;
            case "2":
                newCustomer();
                break;
            case "3":
                customerLogin();
                break;
            case "4":
                System.out.println("Please enter the customer ID");
                Long cusID = scanner.nextLong();
                findCustomer(cusID);
                returnMenus();
                break;
            case "5":
                deleteCustomer();
                break;
            case "6":
                transferMoney();
                break;
            default:
                if (!input.equals("7")) {
                    System.out.println("Please enter a valid number");
                    adminMenu();
                }
                break;
        }


    }

    public void returnMenus() {
        // created default back option to return to previous menu or initial main menu
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

    public void findAllCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
        returnMenus();


    }

    public void newCustomer() {
        System.out.println("Please Enter Your First Name:");
        String tempName = scanner.next();
        System.out.println("Please Enter Your Last Name:");
        String tempLastName = scanner.next();
        System.out.println("Please Enter Your Email Address");
        String tempEmailAddress = scanner.next();
        System.out.println("Please Enter Your Phone Number");
        String tempPhone = scanner.next();

        //if user inputs name in lower case, this method
        // turns first letter of name to upper case
        tempName = tempName.substring(0, 1).toUpperCase() + tempName.substring(1);
        tempLastName = tempLastName.substring(0, 1).toUpperCase() + tempLastName.substring(1);

        //checking to see if customer has put in right information
        //option to restart process if info is incorrect
        System.out.println("Is all your information correct? \n" +
                "Y to Continue, N to Restart");
        String userAnswer = scanner.next();

        //if correct creates new customer and prints greeting message
        if("Y".equals(userAnswer) || "y".equals(userAnswer)) {
            validCustomer(new Customer(tempName, tempLastName, tempEmailAddress, tempPhone));
            System.out.println( tempName + tempLastName + " Has Been Added As a Customer");
            returnMenus();
        }
        //else restarts process to create a new customer
        //if statement checks both upper and lower case of user prompt 
        else if("N".equals(userAnswer) || "n".equals(userAnswer)){
            newCustomer();
        }
    }

    public long loginValidation() {
        long temp = 0;
        String cusName = scanner.next();
        //this changes the first letter of users input to capital letter
        //so no matter what a lower or upper case input, the user will be found by their name
        cusName = cusName.substring(0, 1).toUpperCase() + cusName.substring(1);
        for (Customer customer : customers) {
            if (customer.getName().equals(cusName)) {
                System.out.println("======= Welcome " + cusName + " =======");
                temp = customer.getId();

            }
        }

        return temp;
    }

    public void customerLogin() {
        System.out.println("Please Enter a Customer Name");
        long temp = loginValidation();
        if (temp != 0) {
            yourBank(temp);
        } else {

            customerLogin();
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


    public void deleteCustomer() {
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
        returnMenus();


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
        //this should work
        // to test, let us call transferMoney() from one of the menus
//        customers.get(cus1-1).addAccountTransaction(acc1, -1 * amount, String.format("Origin account %s",
//                customers.get(cus1-1).getAccountNumber(acc2)));
//        customers.get(cus1-1).addAccountTransaction(acc2, amount, String.format("Destination account %s",
//                customers.get(cus1-1).getAccountNumber(acc1)));

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

        returnMenus();
        return null;

    }


    public void yourBank(long customerNumber) {
        System.out.println("Please select an option");
        System.out.println("""
                1: Create New Account
                2: View Accounts
                3: Return to Menus""");
        input = scanner.next();
        switch (input) {
            case "1":
                createNewBankAccount(customerNumber);
                break;
            case "2":
                allCustomerAccounts(customerNumber);
                break;
            default:
                if (!input.equals("3")) {
                    System.out.println("Please enter a valid number");
                    returnMenus();
                }
                break;
        }
        if (!input.equals("3"))
            yourBank(customerNumber);
        else
            returnMenus();
    }

    private void createNewBankAccount(long customerNumber) {
        System.out.println("Choose An Account to Create");
        System.out.println("""
                 1: Create Current Account
                2: Create Business Account
                3: Create ISA Account
                4: Back to Customer Accounts""");
        input = scanner.next();
        switch (input) {
            case "1":
                createCurrentAccount(customerNumber);
                break;
            case "2":
                createBusinessAccount(customerNumber);
                break;
            case "3":
                createISAAccount(customerNumber);
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
        System.out.println("Choose an Option");
        System.out.println(" 1: Deposit\n" +
                "2: Withdraw\n" +
                "3: Transfer\n" +
                "4: View Balance\n" +
                "5: Return to Menus");
    }

    public void createCurrentAccount(Long customerNumber) {
        BankAcc currentAcc = new CurrentAccount();
        int customerNumberInInt = customerNumber.intValue();
        customers.get(customerNumberInInt - 1).getBankAccounts().add(currentAcc);
        customers.get(customerNumberInInt - 1).getListOfBankAccounts();
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
        customers.get(customerNumberInInt - 1).getListOfBankAccounts();
        //System.out.println("Choose an Option");
        //System.out.println(" 1.Deposit \n 2.Withdrawn \n 3.Transfer \n 4.View Balance \n 5.Exit to Customer Menu");
    }

    public void createISAAccount(Long customerNumber) {
        BankAcc ISAAcc = new ISAAccount();
        int customerNumberInInt = customerNumber.intValue();
        customers.get(customerNumberInInt - 1).getBankAccounts().add(ISAAcc);
        customers.get(customerNumberInInt - 1).getListOfBankAccounts();
        //System.out.println("Choose an Option");
        //System.out.println(" 1.Deposit \n 2.Withdrawn \n 3.Transfer \n 4.View Balance \n 5.Exit to Customer Menu");
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

