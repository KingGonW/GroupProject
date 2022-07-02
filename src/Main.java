import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private String input;
    private ArrayList<Customer> customers;

    //why are we naming our instance of Customer "currentAccount"? This is confusing;
    private static Customer currentAccount;

    public static void main(String[] args) {
        Main main = new Main();
        main.customers = new ArrayList<>();


        main.currentAccount = new Customer();
        // temporary created to test the app

        //commenting out the below to test adding customers *with* accounts (see below)

        //main.validCustomer(new Customer("Mohsen", "-", "mohsen@gmail.com", "999999999"));
       // main.validCustomer(new Customer("King", "-", "king@gmail.com", "999999999"));
        //main.validCustomer(new Customer("Tamara", "-", "tamara@gmail.com", "999999999"));
        //main.validCustomer(new Customer("Andy", "-", "andy@gmail.com", "999999999"));


        // we ought to give these test customers some bank accounts as well, example below of an account with no attached customer

        BankAcc anAccount = new BankAcc("ISA", "22-66-44");

        //refer to comments in BankAcc class, we really ought to use the constructor that links the Customer to the Account...
        //trying this below, might break things

        Scanner sc = new Scanner(System.in);
        Customer currentCustomer;

        Bank theBank = new Bank("Novus Banking Services");

        //create the customer - accessed with Customer Class

        Customer Tamara = theBank.addCustomer("Tamara", "-", "tamara@gmail.com", "0123456789");

        //then give some bank accounts, which update the array lists of accounts and customers
        BankAcc tamaraAccount1 = new BankAcc("ISA", "22-66-44", Tamara);
        Tamara.addAccount(tamaraAccount1);
        theBank.addAccount(tamaraAccount1);
        BankAcc tamaraAccount2 = new BankAcc("Current", "00-04-01", Tamara);
        Tamara.addAccount(tamaraAccount2);
        theBank.addAccount(tamaraAccount2);
        BankAcc tamaraAccount3 = new BankAcc("Business", "12-23-24", Tamara);
        Tamara.addAccount(tamaraAccount3);
        theBank.addAccount(tamaraAccount3);

        //create the customer - accessed with Customer Class

        Customer Mohsen = theBank.addCustomer("Mohsen", "-", "mohsen@gmail.com", "0123456789");

        //then give some bank accounts, which update the array lists of accounts and customers
        BankAcc mohsenAccount1 = new BankAcc("ISA", "22-66-44", Mohsen);
        Mohsen.addAccount(mohsenAccount1);
        theBank.addAccount(mohsenAccount1);
        BankAcc mohsenAccount2 = new BankAcc("Current", "00-04-01", Mohsen);
        Mohsen.addAccount(mohsenAccount2);
        theBank.addAccount(mohsenAccount2);
        BankAcc mohsenAccount3 = new BankAcc("Business", "12-23-24", Mohsen);
        Mohsen.addAccount(mohsenAccount3);
        theBank.addAccount(mohsenAccount3);

        //create the customer - accessed with Customer Class
        Customer King = theBank.addCustomer("King", "-", "king@gmail.com", "0123456789");

        //then give some bank accounts, which update the array lists of accounts and customers
        BankAcc kingAccount1 = new BankAcc("ISA", "22-66-44", King);
        King.addAccount(kingAccount1);
        theBank.addAccount(kingAccount1);
        BankAcc kingAccount2 = new BankAcc("Current", "00-04-01", King);
        King.addAccount(kingAccount2);
        theBank.addAccount(kingAccount2);
        BankAcc kingAccount3 = new BankAcc("Business", "12-23-24", King);
        King.addAccount(kingAccount3);
        theBank.addAccount(kingAccount3);

        //create the customer - accessed with Customer Class
        Customer Andy = theBank.addCustomer("Andy", "-", "andy@gmail.com", "0123456789");

        //then give some bank accounts, which update the array lists of accounts and customers
        BankAcc andyAccount1 = new BankAcc("ISA", "22-66-44", Andy);
        Andy.addAccount(kingAccount1);
        theBank.addAccount(kingAccount1);
        BankAcc andyAccount2 = new BankAcc("Current", "00-04-01", Andy);
        Andy.addAccount(andyAccount2);
        theBank.addAccount(andyAccount2);
        BankAcc andyAccount3 = new BankAcc("Business", "12-23-24", Andy);
        Andy.addAccount(andyAccount3);
        theBank.addAccount(andyAccount3);




        main.mainMenu();
    }

    public void validCustomer(Customer customer) {
        Customer temp = customer;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmailAddress().equals(customer.getEmailAddress())) {
                System.out.println("ERROR: This email address already exists");
                return;
            }
        }
        customers.add(temp);
    }

    public void mainMenu() {


        System.out.println("MAIN MENU\nPlease select an option ... ");

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
                    }
                    break;
            }
        } while (!input.equals("3"));
    }

    private void adminMenu() {


        System.out.println("ADMIN MENU\nPlease select an option ... ");
        System.out.println(" 1.All customers \n 2.find a Customer \n3.delete a customer \n 4.Transfer money \n 5. Show Transaction History\n 6.Exit to main menu");
        input = scanner.next();
        switch (input) {
            case "1":
                findAllCustomers();
                break;
            case "2":
                System.out.println("Please enter the customer ID");
                Long cusID = scanner.nextLong();
                findCustomer(cusID);
                break;
            case "3":
                deleteCustomer();
                break;
            case "4":
                transferMoney();
                break;
            case "5":
                showTransactionHistory(currentAccount, scanner);
                default:
                if (!input.equals("6")) {
                    if (!input.equals("6")) {
                        System.out.println("Please enter a valid number");
                        adminMenu();
                    }
                }
                break;
        }
        if (!input.equals("5"))
            adminMenu();
        else
            mainMenu();

    }


    private void transferMoney() {
        findAllCustomers();
        System.out.println("Please enter the customer number that you would to transfer from ...");
        String tempCus1 = scanner.next();
        int cus1 = Integer.parseInt(tempCus1);
        customers.get(cus1).getListOfBankAccounts();
        System.out.println("please enter the account number to transfer from ....");
        String tempAcc1 = scanner.next();
        int acc1 = Integer.parseInt(tempAcc1);
        System.out.println("Please enter the customer number that you would to transfer from ...");
        String tempCus2 = scanner.next();
        int cus2 = Integer.parseInt(tempCus2);
        customers.get(cus2).getListOfBankAccounts();
        System.out.println("please enter the account number to transfer to ....");
        String tempAcc2 = scanner.next();
        int acc2 = Integer.parseInt(tempAcc1);
        System.out.println("please enter the the amount of money  ....");
        String tempAmount = scanner.next();
        double amount = Double.parseDouble(tempAcc1);
        currentAccount.getCurrentBankAcc().moneyTransfer(getTheAccountNumber(cus1, acc1), getTheAccountNumber(cus2, acc2), amount);
       //the below posts this info to the Transactions class
        //this should work
        // to test, let us call transferMoney() from one of the menus
        Customer.addAccountTransaction(acc1, -1*amount, String.format("Origin account %s",
                Customer.getAccountNumber(acc2)));
      Customer.addAccountTransaction(acc2, amount, String.format("Destination account %s",
                Customer.getAccountNumber(acc1)));
    }


    public BankAcc getTheAccountNumber(int customerId, int accNumber) {
        Long tempLong = Long.valueOf(customerId);
        Customer temp = findCustomer(tempLong);
        for (int i = 0; i < temp.getBankAccounts().size(); i++) {
            if (temp.getBankAccounts().get(i).getAccNum() == accNumber)
                return temp.getBankAccounts().get(i);
        }

        System.out.println("Cant find the the Account  ");
        return null;

    }


    private void customerMenu() {
        System.out.println("CUSTOMER MENU\nPlease select an option ... ");
        System.out.println(" 1.Register // 2.Login // 3.Help // 4.Exit to main menu");
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
                    if (!input.equals("4")) {
                        System.out.println("Please enter a valid number");
                        customerMenu();
                    }
                }
                break;
        }
        if (!input.equals("4"))
            customerMenu();
        else
            mainMenu();

    }


    public void deleteCustomer() {
        System.out.println("Please enter the customer ID");
        Long cusID = scanner.nextLong();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == cusID) {
                customers.remove(i);
                System.out.println("====> Selected customer has been successfully removed from the system");
                adminMenu();
            }
        }
        System.out.println("====> Customer does not exist");
        adminMenu();
    }

    public void findAllCustomers() {
        System.out.println("Please enter the customer ID");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).toString());
        }
        adminMenu();
    }

    public Customer findCustomer(Long customerID) {

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customerID) {
                System.out.println(customers.get(i).toString());
                return customers.get(i);
            }
        }
        System.out.println("====> Customer does not exist");
        return null;
    }

    public void newCustomer() {
        System.out.println("Please enter your name:");
        String tempName = scanner.next();
        System.out.println("Please enter your last name:");
        String tempLastName = scanner.next();
        System.out.println("Please enter your email address:");
        String tempEmailAddress = scanner.next();
        System.out.println("Please enter your phone number:");
        String tempPhone = scanner.next();
        validCustomer(new Customer(tempName, tempLastName, tempEmailAddress, tempPhone));
        customerMenu();
    }

    public long loginValidation() {
        long temp = 0;
        System.out.println("Please enter your name ");
        String cusName = scanner.next();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(cusName)) {
                System.out.println("====> WELCOME " + cusName);
                temp = customers.get(i).getId();
            }
        }
        return temp;

    }

    public void customerLogin() {
        long temp = loginValidation();
        if (temp != 0) {
            yourBank(temp);
        } else {
            System.out.println("Please enter a valid name ");
            customerMenu();
        }
    }

    public void yourBank(long customerNumber) {
        System.out.println("BANK MENU\nPlease select an option ... ");
        System.out.println("1.Create a new Bank account // 2.Your accounts // 3.Exit to main menu");
        input = scanner.next();
        switch (input) {
            case "1":
                createNewBankAccount(customerNumber);
                break;
            case "2":
                yourAccount(allCustomerAccounts(customerNumber));
                break;
            default:
                if (!input.equals("3")) {
                    System.out.println("Please enter a valid number");
                    customerMenu();
                }
                break;
        }
        if (!input.equals("3"))
            yourBank(customerNumber);
        else
            customerLogin();
    }

    private BankAcc allCustomerAccounts(long customerNumber) {
        Customer temp = null;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customerNumber) {
                temp = customers.get(i);
                break;
            }
        }
        temp.getListOfBankAccounts();
        System.out.println("Please select an account to continue ...  ");
        int accNum = scanner.nextInt();
        if (accNum - 1 <= temp.getBankAccounts().size())
            return temp.getBankAccounts().get(accNum - 1);

        System.out.println("please enter a valid number");
        return null;
    }

    public void yourAccount(BankAcc bankAcc) {
        System.out.println("Choose an Option");
        System.out.println(" 1.Deposit \n 2.Withdraw \n 3.Transfer \n 4.View Balance \n 5.Exit to Customer Menu");
    }

    private void createNewBankAccount(long customerNumber) {
        System.out.println("Choose an account to create");
        System.out.println(" 1.Create Current Account // 2.Create Business Account // 3.Create ISA Account // 4.Back to Customer Accounts");
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

    public void createCurrentAccount(Long customerNumber) {
        BankAcc currentAcc = new CurrentAccount();
        int customerNumberInInt = customerNumber.intValue();
        customers.get(customerNumberInInt).getBankAccounts().add(currentAcc);
        customers.get(customerNumberInInt).getListOfBankAccounts();
    }

    public void createBusinessAccount(Long customerNumber) {
        BankAcc businessAcc = new BusinessAccount();
        int customerNumberInInt = customerNumber.intValue();
        //ask user to deposit money to open ISA account
        //check if balance is less than 100, if yes
        //prompt user to make balance 100
        //only then when balance = 100 or more
        //add the created account to customer array
        customers.get(customerNumberInInt).getBankAccounts().add(businessAcc);
        customers.get(customerNumberInInt).getListOfBankAccounts();
        System.out.println("Choose an Option");
        System.out.println(" 1.Deposit \n 2.Withdrawn \n 3.Transfer \n 4.View Balance \n 5.Exit to Customer Menu");
    }

    public void createISAAccount(Long customerNumber) {
        BankAcc ISAAcc = new ISAAccount();
        int customerNumberInInt = customerNumber.intValue();
        customers.get(customerNumberInInt).getBankAccounts().add(ISAAcc);
        customers.get(customerNumberInInt).getListOfBankAccounts();
        System.out.println("Choose an Option");
        System.out.println(" 1.Deposit \n 2.Withdraw \n 3.Transfer \n 4.View Balance \n 5.Exit to Customer Menu");
    }

    //below method shows transaction history
    //it takes the Customer selected, shown as currentCustomer
    //loops through the accounts associated with the Customer
    //and displays the information
    //that information needs to be generated in the withdraw(), deposit() and transfer() methods.
    public static void showTransactionHistory(Customer currentCustomer, Scanner sc) {

        int theAccount;
        //get the account for which we wish to show the history

        do{
            //account is chosen from the arraylist. Number refers to the account in the array list,
            // not the account number
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "you wish to see the history of:",  currentAccount.numAccounts());

            // -1 to get to the 0 index position
            theAccount = sc.nextInt()-1;
            if(theAccount < 0  || theAccount >= currentCustomer.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }
        } while(theAccount < 0  || theAccount >= currentCustomer.numAccounts());

        //print transactions history
        currentCustomer.printAccountTransactionHistory(theAccount);
    }




}