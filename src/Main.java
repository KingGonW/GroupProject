import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private String input;
    private ArrayList<Customer> customers;

    Customer currentCustomer = new Customer();


    public static void main(String[] args) {

        Main main = new Main();
        main.customers = new ArrayList<>();


        main.validCustomer(new Customer("Mohsen", "M", "mohsen@gmail.com", "07374829121"));
        main.validCustomer(new Customer("King", "K", "king@gmail.com", "07664738222"));
        main.validCustomer(new Customer("Tamara", "T", "tamara@gmail.com", "07776652499"));
        main.validCustomer(new Customer("Andy", "A", "andy@gmail.com", "07542323451"));
        main.generateAccounts(main.customers.get(0));
        main.generateAccounts(main.customers.get(1));
        main.generateAccounts(main.customers.get(2));
        main.generateAccounts(main.customers.get(3));

       /* for (int i = 0; i < main.customers.size(); i++) {
            main.printCustomerAccounts(i + 1l);
        }*/

        try {
            FileWriter writer = new FileWriter("customerArray.txt");
            Writer output = new BufferedWriter(writer);
            int sz = main.customers.size();
            for (int i = 0; i < sz; i++) {
                output.write(main.customers.get(i).toString() + "\n");
            }
            output.close();

        } catch (IOException e) {
            System.out.println("File Creation Unsuccessful");
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("customerAccounts.txt");
            Writer output = new BufferedWriter(writer);
            for (int i = 0; i < main.customers.size(); i++) {
                output.write(main.printCustomerAccounts(Long.valueOf(i) + 1));
            }
            output.close();

        } catch (IOException e) {
            System.out.println("File Creation Unsuccessful");
            e.printStackTrace();
        }


        main.mainMenu();


    }

    public void generateAccounts(Customer customer) {
        Random random = new Random();
        int randomInt = random.nextInt(1, 3);

        for (int i = 0; i < randomInt; i++) {
            createCurrentAccount(customer.getId());
        }


    }

    public String printCustomerAccounts(Long customerID) {
        int customerNumberInInt = customerID.intValue();
        System.out.println(customers.get(customerNumberInInt - 1).getName());
        return customers.get(customerNumberInInt - 1).getListOfBankAccounts();


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
            System.out.println("""
                    1: Admin Access\s
                    2: Exit Program\n""");


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
                3: Exit to Main Menu\n""");
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


    //adding methods in order that it shows up in the admin menu
    public void findAllCustomers() {
        //set up variables to read file
        String filename = "customerArray.txt";
        String line;
        ArrayList aList = new ArrayList<>();

        //read the lines of text into an array list
        try {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            if (!input.ready()) { // check whether the file can be read
                throw new IOException();
            }
            while ((line = input.readLine()) != null) { //read a line of text
                aList.add(line); //add the line of text to the array list
            }
            input.close();
        } catch (IOException e) {  // catch any problems found e.g. file not found
            e.printStackTrace();
        }

        //print out each item in the array list
        int sz = aList.size();
        for (int i = 0; i < sz; i++) {
            System.out.println(aList.get(i).toString());
        }
    }


    public void customerLogin() {
        long temp = loginValidation();
        if (temp != 0) {
            yourBank(temp);
        } else {
            System.out.println("\nPlease Enter A Valid Name\n");
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
        System.out.println("\nPlease Enter A Customer ID");
        int cusName = scanner.nextInt();
        //this changes the first letter of users input to capital letter
        //so no matter what a lower or upper case input, the user will be found by their name
        for (Customer customer : customers) {
            if (customer.getId() == cusName) {
                System.out.println("\n======= Welcome To " + customer.getName() + "'s Bank Account =======\n");
                temp = customer.getId();
            }
        }
        return temp;

    }


    public void yourBank(long customerNumber) {
        System.out.println("\nPlease Select an Option");
        System.out.println("""
                1: Create A New Bank Account\s
                2: View Accounts                
                3: Exit to Admin Menu
                4: Exit to Main Menu\n""");

        input = scanner.next();
        switch (input) {
            case "1":
                printCustomerAccounts(customerNumber);
                createNewBankAccount(customerNumber);
                break;
            case "2":
               /*for (int i = 0; i < customers.size(); i++) {

                    System.out.println(customers.get(i).getName() + customers.get(i).getBankAccounts().toString());
                }*/
                printCustomerAccounts(customerNumber);
                //allCustomerAccounts(customerNumber);

                //the below menu gets called when Option 2 of yourBank() is selected.
                accountMenu(customerNumber);
                break;
            case "3":
                adminMenu();
                break;
            case "4":
                mainMenu();
                break;
            default:
                if (!input.equals("4")) {
                    System.out.println("Please enter a valid number");
                    adminMenu();
                }
                break;
        }
        if (!input.equals("6"))
            yourBank(customerNumber);
    }

    //the menu below is initialised in yourBank(), when the user chooses Option 2 "View All Accounts"
    private void accountMenu(long customerNumber) {

        System.out.println("Please Select an Option");
        System.out.println("""
                               
                1: View Account Transaction History
                2: View Balance
                3: Deposit Funds
                4: Withdraw Funds
                5: Transfer Funds
                6: Back to Account Menu
                7: Back to Admin Menu
                8: Back to Main Menu""");

        input = scanner.next();

        switch (input) {
            case "1":

                showTransactionHistory(customerNumber);

                break;
            case "2":
                viewBalance(customerNumber);
                //
                break;
            case "3":
                deposit(customerNumber);
                //
                break;
            case "4":
                withdraw(customerNumber);
                break;
            case "5":
                transferMoney();
                break;
            case "6":
                yourBank(customerNumber);
            case "7":
                adminMenu();
                break;
            case "8":
                mainMenu();
            default:
                if (!input.equals("8")) {
                    System.out.println("Please enter a valid number");
                    accountMenu(customerNumber);
                }
                break;

        }
    }

    private void viewBalance(long customerNumber) {


        System.out.println("Nothing here yet");
    }

    private void createNewBankAccount(long customerNumber) {
        System.out.println("Choose Account Type to Create");
        System.out.println("""
                1: Create Current Account\s
                2: Create Business Account
                3: Create ISA Account:
                4: Back to Customer Accounts
                5: Back to Admin Menu
                6: Back to Main Menu""");
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
            case "4":
                yourBank(customerNumber);
                break;
            case "5":
                adminMenu();
                break;
            case "6":
                mainMenu();
                break;

            default:
                if (!input.equals("6")) {
                    System.out.println("Please enter a valid number");
                    yourBank(customerNumber);
                }
                break;
        }

    }

    private void allCustomerAccounts(long customerNumber) {
        for (Customer customer : customers) {
            if (customer.getId() == customerNumber) {
                customer.getListOfBankAccounts();
            }
        }

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
                //deposit(customerNumber);
                break;
            case "2":
                //withdraw();
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

    public void deposit(Long customerNumber) {

        int customerNumberInInt = customerNumber.intValue();
        int toTheAccount;
        double dMoney;
        String memo;
        double newBalance;
        do {
            printCustomerAccounts(customerNumber);
            System.out.println("Please Enter The Account to Deposit Into: ");
            toTheAccount = scanner.nextInt();
            if (toTheAccount < 0 || toTheAccount >= customers.get(customerNumberInInt - 1).getBankAccounts().size()) {
                System.out.println("Invalid Account Chosen. Please Try Again.");
            }
        } while (toTheAccount < 0 || toTheAccount >= customers.get(customerNumberInInt - 1).getBankAccounts().size());
        do {
            System.out.println("Please Enter The Amount to Deposit:");
            dMoney = scanner.nextDouble();
            System.out.println("Enter A Memo:");
            memo = scanner.next();
            customers.get(customerNumberInInt - 1).getBankAccounts().get(toTheAccount - 1).depositMoney(dMoney);
        } while (dMoney < 0);
        newBalance = customers.get(customerNumberInInt - 1).getBankAccounts().get(toTheAccount - 1).getBalance();
        System.out.println("Deposit Successful!");
        System.out.println("You Have Deposited: " + dMoney + '\n' +
                "Your New Balance: " + newBalance + '\n' +
                "Memo: " + memo);
        customers.get(customerNumberInInt - 1).addAccountTransaction(toTheAccount - 1, dMoney, memo);

    }

    public void withdraw(Long customerNumber) {
        int customerNumberInInt = customerNumber.intValue();
        int fromTheAccount;
        double wMoney;
        String memo;
        double newBalance;
        do {
            printCustomerAccounts(customerNumber);
            System.out.println("Please Choose An Account to Withdraw From: ");
            fromTheAccount = scanner.nextInt();
            if (fromTheAccount < 0 || fromTheAccount > customers.get(customerNumberInInt - 1).getBankAccounts().size()) {
                System.out.println("Invalid Account Chosen. Please Try Again.");
            }
        } while (fromTheAccount < 0 || fromTheAccount >= customers.get(customerNumberInInt - 1).getBankAccounts().size());
        do {
            System.out.println("Please Enter The Amount to Withdraw");
            wMoney = scanner.nextDouble();
            System.out.println("Enter A Memo");
            memo = scanner.nextLine();
            customers.get(customerNumberInInt - 1).getBankAccounts().get(fromTheAccount - 1).withdrawMoney(wMoney);
            newBalance = customers.get(customerNumberInInt - 1).getBankAccounts().get(fromTheAccount - 1).getBalance();
            System.out.println("Withdrawal Successful!");
            System.out.println("You Have Withdrawn: " + wMoney + '\n' +
                    "Your New Balance: " + newBalance + '\n' +
                    "Memo: " + memo);
        } while (wMoney < 0);


        customers.get(customerNumberInInt - 1).addAccountTransaction(fromTheAccount - 1, wMoney, memo);
    }


    public void createCurrentAccount(Long customerNumber) {
        BankAcc currentAcc = new CurrentAccount();
        int customerNumberInInt = customerNumber.intValue();
        customers.get(customerNumberInInt - 1).getBankAccounts().add(currentAcc);
    }

    public void createBusinessAccount(Long customerNumber) {
        //create variable to allow creation of business account
        double initialDeposit = 0;
        //variable to get the customers ID
        int customerNumberInInt = customerNumber.intValue();

        //while loop here to continue running until the deposit has been made
        while (initialDeposit == 0) {
            System.out.println("To Open a Business Account, There is a Yearly Fee of $25\n");
            System.out.println("How Much Would You Like to Deposit");
            initialDeposit = scanner.nextDouble();


            //require customer to make an initial deposit of $25 to open Business Account
            // will only be created when user has deposited 25 or more
            if (initialDeposit < 25) {//if less than 25, prompt user to deposit 25 or more
                System.out.println("\nInitial Deposit Must Be $25 or More\n");
            } else {
                //once deposit statement has been satisfied, create business account object
                BankAcc businessAcc = new BusinessAccount(initialDeposit);
                //add business account to desired user in the array customers
                customers.get(customerNumberInInt - 1).getBankAccounts().add(businessAcc);
                //display message to user to signify successful account creation
                System.out.println("\nISA Account Successfully Created\n");

            }
        }


    }

    public void createISAAccount(Long customerNumber) {
        double initialDeposit = 0;
        int customerNumberInInt = customerNumber.intValue();

        while (initialDeposit == 0) {
            System.out.println("To Open An ISA Account, Please Deposit $100 or More\n");
            System.out.println("How Much Would You Like to Deposit");
            initialDeposit = scanner.nextDouble();

            if (initialDeposit < 100) {
                System.out.println("\nInitial Deposit Must Be $100 or More\n");
            } else {
                BankAcc ISAAcc = new ISAAccount(initialDeposit);
                System.out.println("\nISA Account Successfully Created\n");
                customers.get(customerNumberInInt - 1).getBankAccounts().add(ISAAcc);

                //require customer to deposit and keep the balance above 100
                //only the account will be created and add to customer array when the balance is above 100
            }
        }

    }

    //below method shows transaction history
    //it takes the Customer selected, shown as currentCustomer
    //loops through the accounts associated with the Customer
    //and displays the information
    //that information needs to be generated in the withdraw(), deposit() and transfer() methods.
    public void showTransactionHistory(Long customerNumber) {

        printCustomerAccounts(customerNumber);
        int theAccount;
        int customerNumberInInt = customerNumber.intValue();

        //get the account for which we wish to show the history
        do {
            //account is chosen from the arraylist. Number refers to the account in the array list,
            // not the account number
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "you wish to see the history of:", customers.get(customerNumberInInt - 1).numAccounts());

            // -1 to get to the 0 index position
            theAccount = scanner.nextInt() - 1;
            if (theAccount < 0 || theAccount >= customers.get(customerNumberInInt - 1).numAccounts()) {
                System.out.println("Invalid Account chosen. Please try again.");
            }
        } while (theAccount < 0 || theAccount >= customers.get(customerNumberInInt - 1).numAccounts());

        //print transactions history
        customers.get(customerNumberInInt - 1).printAccountTransactionHistory(theAccount);
    }
/*the below copied from the other branch.

getAccountBalance() has been defined, in Customer class


printAccountsMenu does not need to be defined. Delete it and use the existing menu options above. Its purpose is to bring the user back one level. I think
accountMenu - line 381 - is the matching menu

    public static void transferFunds(Customer currentCustomer, Scanner sc, Bank theBank){
        //initialise
        //we need a source account, destination account and amount to transfer
        //some logic to ensure amount sent accords with balance

        int fromAccount;
        int toAccount;
        double transferAmount;
        double accountBalance;

        //get the source account - same logic, i.e. accessing an array of accounts, as showTransactionHistory();
        do{
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "to transfer from:", currentCustomer.numAccounts());
            fromAccount=sc.nextInt()-1;
            if(fromAccount < 0 || fromAccount >= currentCustomer.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while(fromAccount < 0 || fromAccount >= currentCustomer.numAccounts());

        //now we need the account balance, from the account just chosen

        accountBalance = currentCustomer.getAccountBalance(fromAccount);

        //get the destination account
        do{
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "to transfer to:",  currentCustomer.numAccounts());
            toAccount=sc.nextInt()-1;
            if(toAccount < 0 || toAccount >= currentCustomer.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while(toAccount < 0 || toAccount >= currentCustomer.numAccounts());

        //get the amount to transfer
        do{
            System.out.printf("Enter the amount to transfer( max %.02f): ", accountBalance);
            transferAmount= sc.nextDouble();

            //logic below stops transferring negative funds, or exceeding account balance.
            //remember to factor in ISA minimum account balance for group project
            if(transferAmount <0){
                System.out.println("Amount must be greater than zero!");
            } else if (transferAmount > accountBalance){
                System.out.printf("You cannot transfer more than your balance\nof %.02f.\n", accountBalance);
            }
        } while(transferAmount < 0 || transferAmount > accountBalance);

        //now transfer can occur
        //note addAccountTransaction + getAcctId are defined in Customer
        currentCustomer.addAccountTransaction(fromAccount, -1*transferAmount, String.format("Origin account %s",
                currentCustomer.getAccountNumber(toAccount)));
        currentCustomer.addAccountTransaction(toAccount, transferAmount, String.format("Destination account %s",
                currentCustomer.getAccountNumber(fromAccount)));

        //a brief log/confirmatory message
        System.out.printf("%s transferred", transferAmount);

        //then takes the User back to the appropriate menu
        Main.printAccountsMenu(currentCustomer,sc,theBank);

    }

    private static void withdrawFunds(Customer currentCustomer, Scanner sc, Bank theBank) {

        //the logic here is similar to transferFunds();
        int fromAccount;
        double withdrawAmount;
        double accountBalance;
        String memo;

        //get the account to withdraw from
        do{
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to withdraw from: ", currentCustomer.numAccounts());
            fromAccount = sc.nextInt()-1;
            if(fromAccount < 0 || fromAccount >= currentCustomer.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while (fromAccount < 0 || fromAccount >= currentCustomer.numAccounts());

        //now we need the account balance, from the account just chosen
        accountBalance= currentCustomer.getAccountBalance(fromAccount);

        //get the amount to transfer
        do{
            System.out.printf("Enter the amount to withdraw( max %.02f): ", accountBalance);
            withdrawAmount= sc.nextDouble();

            //logic below stops withdrawing negative funds, as that would be basically a deposit
            // or exceeding account balance.
            //remember to factor in ISA minimum account balance for group project
            if(withdrawAmount <0){
                System.out.println("Amount must be greater than zero!");
            } else if (withdrawAmount > accountBalance){
                System.out.printf("You cannot withdraw more than your balance\nof %.02f.\n", accountBalance);
                Main.printAccountsMenu(currentCustomer,sc, theBank);
            }
        } while(withdrawAmount < 0 || withdrawAmount > accountBalance);



        sc.nextLine();

        //now the memo - this is useful for the customer/admin to add a note about the withdrawal.
        // It can safely be dispensed with, ergo consider removing for group project,
        //that would mean redefining addAccountTransaction();
        System.out.println("Enter a memo");
        memo= sc.nextLine();

        //finally allow the withdrawal
        currentCustomer.addAccountTransaction(fromAccount,-1*withdrawAmount, memo);

        //a brief log/confirmatory message
        System.out.printf("%s withdrawn ", withdrawAmount);

        //then takes the User back to the appropriate menu
        Main.printAccountsMenu(currentCustomer,sc,theBank);
    }

    private static void depositFunds(Customer currentCustomer, Scanner sc, Bank theBank) {

        //the logic here is basically an inversion of withdrawFunds();
        int toAccount;
        double depositAmount;
        String memo;

        //get the account to transfer from
        do{
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to deposit to: ", currentCustomer.numAccounts());
            toAccount = sc.nextInt()-1;
            if(toAccount < 0 || toAccount >= currentCustomer.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while (toAccount < 0 || toAccount >= currentCustomer.numAccounts());

        //get the amount to deposit
        do{
            System.out.println("Enter the amount to deposit");
            depositAmount= sc.nextDouble();

            //logic below stops users depositing a negative amount, as that would effectively be a withdrawal.
            //remember to factor in ISA minimum account balance for group project
            if(depositAmount <0){
                System.out.println("You cannot deposit less than zero");
            }

        } while(depositAmount < 0);

        sc.nextLine();

        //now the memo - this is useful for the customer/admin to add a note about the deposit.
        // It can safely be dispensed with, ergo consider removing for group project,
        //that would mean redefining addAccountTransaction();
        System.out.println("Enter a memo");
        memo= sc.nextLine();

        //finally allow the deposit
        currentCustomer.addAccountTransaction(toAccount,depositAmount, memo);

        //a brief log/confirmatory message
        System.out.printf("%s deposited", depositAmount);

        //then takes the User back to the appropriate menu
        Main.printAccountsMenu(currentCustomer,sc,theBank);
    }

    */

}