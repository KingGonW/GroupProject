
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        //initialise the scanner
        Scanner sc = new Scanner(System.in);

        //initialise the bank
        Bank theBank = new Bank("Novus Banking Services");

        Customer  currentCustomer = new Customer("", "", theBank);

        //add customers to the bank, for testing purposes
        Customer Tamara = theBank.addCustomer("Novus","Tamara");
        Customer Mohsen = theBank.addCustomer("Novus","Mohsen");
        Customer Andy = theBank.addCustomer("Novus","Andy");
        Customer King = theBank.addCustomer("Novus","King");

        //create several accounts for testing purposes


        BankAccount tamaraAccount1 = new BankAccount("ISA",Tamara,theBank);
        Tamara.addAccount(tamaraAccount1);
        theBank.addAccount(tamaraAccount1);
        BankAccount tamaraAccount2 = new BankAccount("Business",Tamara,theBank);
        Tamara.addAccount(tamaraAccount2);
        theBank.addAccount(tamaraAccount2);
        BankAccount tamaraAccount3 = new BankAccount("Current",Tamara,theBank);
        Tamara.addAccount(tamaraAccount3);
        theBank.addAccount(tamaraAccount3);

        BankAccount andyAccount1 = new BankAccount("ISA",Andy,theBank);
        Andy.addAccount(andyAccount1);
        theBank.addAccount(andyAccount1);
        BankAccount andyAccount2 = new BankAccount("Business",Andy,theBank);
        Andy.addAccount(andyAccount2);
        theBank.addAccount(andyAccount2);
        BankAccount andyAccount3 = new BankAccount("Current",Andy,theBank);
        Andy.addAccount(andyAccount3);
        theBank.addAccount(andyAccount3);

        BankAccount mohsenAccount1 = new BankAccount("ISA",Mohsen,theBank);
        Mohsen.addAccount(mohsenAccount1);
        theBank.addAccount(mohsenAccount1);
        BankAccount mohsenAccount2 = new BankAccount("Business",Mohsen,theBank);
        Mohsen.addAccount(mohsenAccount2);
        theBank.addAccount(mohsenAccount2);
        BankAccount mohsenAccount3 = new BankAccount("Current",Mohsen,theBank);
        Mohsen.addAccount(mohsenAccount3);
        theBank.addAccount(mohsenAccount3);

        BankAccount kingAccount1 = new BankAccount("ISA",King,theBank);
        King.addAccount(kingAccount1);
        theBank.addAccount(kingAccount1);
        BankAccount kingAccount2 = new BankAccount("Business",King,theBank);
        King.addAccount(kingAccount2);
        theBank.addAccount(kingAccount2);
        BankAccount kingAccount3 = new BankAccount("Current",King,theBank);
        King.addAccount(kingAccount3);
        theBank.addAccount(kingAccount3);
        //'sc' below refers to the scanner - defined above - which gets passed to the methods

           Main.printStartMenu(theBank, sc, currentCustomer);
    }

//==============================Main (Start) Menu & Methods=========================================
    private static void printStartMenu(Bank theBank, Scanner  sc, Customer currentCustomer){
        int userChoice;

        do {
            System.out.printf("\nWelcome  to %s, what would you like to do?:", theBank.getName());
            System.out.println();
            System.out.println(" 1. View All Customers");
            System.out.println(" 2. View All Accounts");
            System.out.println(" 3. Exit the App");
            System.out.println();
            System.out.println(" Enter your choice");
            userChoice = sc.nextInt();
            if (userChoice <1 || userChoice > 3){
                System.out.println("Invalid choice, please choose 1 - 3.");
            }


        } while(userChoice <1 || userChoice > 3);
        switch (userChoice) {
            case 1 -> Main.viewCustomers(theBank, sc, currentCustomer);
            case 2 -> Main.viewAccounts(theBank, sc, currentCustomer);
            case 3 -> Main.exitApp();


            //in the video there is no default case, but I think we need one
            //also, put something for exit option, e.g. a goodbye message
        }

    }

    private static void viewCustomers(Bank theBank, Scanner sc, Customer currentCustomer) {
        theBank.printCustomerSummary();
        Main.customerMenu(theBank, sc, currentCustomer);
    }

    //below to display all accounts
    private static void viewAccounts(Bank theBank, Scanner sc, Customer currentCustomer) {
theBank.printAccountsSummary();

Main.transactionsMenu(theBank, sc, currentCustomer);
    }

    //below to exit the app
    private static void exitApp() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

//==============================Main Number Two & Methods=========================================
    //the below menu initialises when the admin has chosen option 1 -View All Customers - in printStartMenu

    private static void customerMenu(Bank theBank, Scanner sc, Customer currentCustomer){

        int userChoice;

        do {
            System.out.println("\nWelcome  to the customer Menu, what would you like to do?:");
            System.out.println();
            System.out.println(" 1. Add a Customer");
            System.out.println(" 2. Select a Customer");
            System.out.println(" 3. Go back to the start menu");
            System.out.println(" 4. Exit the App");
            System.out.println();
            System.out.println(" Enter your choice");
            userChoice = sc.nextInt();
            if (userChoice <1 || userChoice > 3){
                System.out.println("Invalid choice, please choose 1 - 3.");
            }

        } while(userChoice <1 || userChoice > 3);
        switch (userChoice) {
            case 1 -> Main.addCustomerAndAccount(theBank, sc);
            case 2 -> Main.selectCustomer(theBank, sc);
            case 3 -> Main.printStartMenu(theBank, sc, currentCustomer);
            case 4 -> Main.exitApp();

        }

    }
    //the below methods match the menu options above.
    private static void selectCustomer(Bank theBank, Scanner sc) {
        theBank.printCustomerSummary();

        //initialise
        String CustomerID;
        Customer currentCustomer;

        //user menu

        do {
            System.out.print("\nChoose one of the above Customer IDs:");
            CustomerID = sc.nextLine();


            //try to get the Customer object that corresponds to the ID
            currentCustomer = theBank.customerLogin(CustomerID);
            if (currentCustomer == null) {
                System.out.println("Incorrect User. \n Try again.");
            }
        } while (currentCustomer == null);//continues looping until successful login

        Main.printAccountsMenu(currentCustomer, sc, theBank);

    }

    private static void addCustomerAndAccount(Bank theBank, Scanner sc) {

        String firstName;
        String lastName;
        String AcctType;

        int userChoice;

        //Originally this just added a customer, but selectCustomer() would exit the app if a customer with no account was selected
        //Also, logically, every Bank Customer has an account in real life
        //Plus the Customer Class here has accounts linked

        System.out.println("Enter the first name of the new customer\n");
        firstName=sc.next();


        System.out.println("Enter the last name of the new customer");
        lastName = sc.next();
        Customer Customer = theBank.addCustomer(firstName, lastName);
        System.out.printf("New User: %s %s  created.", firstName, lastName);

        System.out.println("Now please add an account for this customer");
        System.out.println("Enter the Account Type:");
        System.out.println("1. ISA ");
        System.out.println("2. Business ");
        System.out.println("3. Current ");

        //I tried using a switch here, could not make it work!
        userChoice = sc.nextInt();
        if(userChoice == 1){
            AcctType = "ISA";
        } else if (userChoice == 2){
            AcctType = "Business";
        } else {
            AcctType = "Current";
        }
        
        BankAccount newBankAccount = new BankAccount(AcctType,Customer, theBank);
        theBank.addAccount(newBankAccount);
        Customer.addAccount(newBankAccount);
        System.out.printf("New Account %s created for %s %s  created.", AcctType, firstName,lastName);

      Main.selectCustomer(theBank,sc);

    }

    private static void printAccountsMenu(Customer currentCustomer, Scanner sc, Bank theBank) {

        //print a summary of user accounts
        //defined in Customer.java

        currentCustomer.printAccountsSummary();

        //initialise
        int userChoice;

        //user menu
        do
        {
            System.out.printf("\nWelcome to the accounts of %s %s, what would you like to do?:", currentCustomer.getFirstName(), currentCustomer.getLastName());

            System.out.println();
            System.out.println(" 1. Show Account Transaction History");
            System.out.println(" 2. Create An Account");
            System.out.println(" 3. Delete An Account");
            System.out.println(" 4. Make a withdrawal");
            System.out.println(" 5. Make a deposit");
            System.out.println(" 6. Make a Transfer");
            System.out.println(" 7. Exit to main menu");
            System.out.println(" 8. Exit the App");
            System.out.println();
            System.out.println(" Enter your choice");
            userChoice = sc.nextInt();

            if (userChoice <1 || userChoice > 8){
                System.out.println("Invalid choice, please choose 1 - 5.");
            }
        } while( userChoice <1 || userChoice > 8);

        //process the userChoice

        switch (userChoice) {
            case 1 -> Main.showTransactionHistory(currentCustomer, sc, theBank);
            case 2 -> Main.addAccountToThisCustomer(currentCustomer, sc, theBank);
            case 3 ->Main.deleteAccount(currentCustomer, sc,theBank);
            case 4 -> Main.withdrawFunds(currentCustomer, sc, theBank);
            case 5 -> Main.depositFunds(currentCustomer, sc, theBank);
            case 6 -> Main.transferFunds(currentCustomer, sc, theBank);
            case 7 -> Main.printStartMenu(theBank, sc, currentCustomer);
            case 8 -> Main.exitApp();
        }
    }

    private static void deleteAccount(Customer currentCustomer, Scanner sc, Bank theBank) {

        String AccountNumber;
        System.out.println("Enter the Account Number would you like to delete?:");
        System.out.println();
        currentCustomer.printAccountsSummary();

        AccountNumber = sc.nextLine();

        theBank.deleteAccount(AccountNumber);
        currentCustomer.deleteAccount(AccountNumber);
        System.out.println("Account Deleted");
        Main.printAccountsMenu(currentCustomer,sc, theBank);
    }

    private static void addAccountToThisCustomer(Customer currentCustomer, Scanner sc, Bank theBank) {

        int userChoice;
        String AcctType;
        System.out.println("Enter the Account Type:");
        System.out.println("1. ISA ");
        System.out.println("2. Business ");
        System.out.println("3. Current ");

        //I tried using a switch here, could not make it work!
        userChoice = sc.nextInt();
        if(userChoice == 1){
            AcctType = "ISA";
        } else if (userChoice == 2){
            AcctType = "Business";
        } else {
            AcctType = "Current";
        }

        BankAccount newBankAccount = new BankAccount(AcctType,currentCustomer, theBank);
        theBank.addAccount(newBankAccount);
        currentCustomer.addAccount(newBankAccount);
        System.out.printf("New Account %s created for %s %s  created.",
                AcctType, currentCustomer.firstName,currentCustomer.getLastName());

        Main.printAccountsMenu(currentCustomer,sc, theBank);

    }

    public static void showTransactionHistory(Customer currentCustomer, Scanner sc, Bank theBank) {

        int theAccount;
        //get the account for which we wish to show the history

        do{

            //account is chosen from the arraylist. Number refers to the account in the array list, not the account number
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "you wish to see the history of:",  currentCustomer.numAccounts());

            theAccount = sc.nextInt()-1;
            if(theAccount < 0  || theAccount >= currentCustomer.numAccounts()){
                System.out.println("Invalid Account chosen. Please try again.");
            }
        } while(theAccount < 0  || theAccount >= currentCustomer.numAccounts());

        //print transactions history
        currentCustomer.printAccountTransactionHistory(theAccount);

        //then takes the User back to the appropriate menu
        Main.printAccountsMenu(currentCustomer,sc,theBank);
    }

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

//==============================Main Number Three & Methods=========================================
    //the below menu initialises when the admin has chosen option 2 -View All Accounts -in printStartMenu

    private static void transactionsMenu(Bank theBank, Scanner sc, Customer currentCustomer) {

        int userChoice;

        System.out.printf("This is a list of all the %s Accounts\n",theBank.getName());
        System.out.println("What would you like to do?:");
        System.out.println();
        System.out.println(" 1. Show Account Transaction History");
        System.out.println(" 2. Create An Account");
        System.out.println(" 3. Delete An Account");
        System.out.println(" 4. Make a withdrawal");
        System.out.println(" 5. Make a deposit");
        System.out.println(" 6. Make a Transfer");
        System.out.println(" 7. Exit to main menu");
        System.out.println(" 8. Exit the App");
        System.out.println();
        System.out.println(" Enter your choice");
        userChoice = sc.nextInt();
        switch(userChoice){
            case 1 -> Main.showTransactionHistoryAllAccounts(currentCustomer, sc, theBank);
            case 2 -> Main.addCustomerAndAccount(theBank, sc);
            case 3 -> Main.deleteAccount(currentCustomer, sc,theBank);
          case 4 -> Main.withdrawFundsAllAccounts(currentCustomer, sc, theBank);
            case 5 -> Main.depositFundsAllAccounts(currentCustomer, sc, theBank);
            case 6 -> Main.transferFundsAllAccounts(currentCustomer, sc,theBank);
            case 7 -> Main.printStartMenu(theBank, sc, currentCustomer);
            case 8 -> Main.exitApp();
        }



    }
//the below methods are the same as the Customer versions, only retooled to make the "Bank" entity do them instead of a selected Customer
    private static void showTransactionHistoryAllAccounts(Customer currentCustomer, Scanner sc, Bank theBank) {
        int theAccount;
        //get the account for which we wish to show the history
        //this time it cycles through all accounts, not just the accounts of the selected customer

        do{

            //account is chosen from the arraylist. Number refers to the account in the array list, not the account number
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "you wish to see the history of:",  theBank.numAccounts());

            theAccount = sc.nextInt()-1;
            if(theAccount < 0  || theAccount >= theBank.numAccounts()){
                System.out.println("Invalid Account chosen. Please try again.");
            }
        } while(theAccount < 0  || theAccount >= theBank.numAccounts());

        //print transactions history
       theBank.printAccountTransactionHistory(theAccount);

        //then takes the User back to the appropriate menu
        Main.viewAccounts(theBank, sc, currentCustomer);
    }

    public static void transferFundsAllAccounts(Customer currentCustomer, Scanner sc, Bank theBank){
        //initialise
        //we need a source account, destination account and amount to transfer
        //some logic to ensure amount sent accords with balance
        //this time it cycles through all accounts, not just the accounts of the selected customer

        int fromAccount;
        int toAccount;
        double transferAmount;
        double accountBalance;

        //get the source account - same logic, i.e. accessing an array of accounts, as showTransactionHistory();
        do{
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "to transfer from:", theBank.numAccounts());
            fromAccount=sc.nextInt()-1;
            if(fromAccount < 0 || fromAccount >= theBank.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while(fromAccount < 0 || fromAccount >= theBank.numAccounts());

        //now we need the account balance, from the account just chosen

        accountBalance = theBank.getAccountBalance(fromAccount);

        //get the destination account
        do{
            System.out.printf("Enter the number  (1-%d) of the account\n"
                    + "to transfer to:",  currentCustomer.numAccounts());
            toAccount=sc.nextInt()-1;
            if(toAccount < 0 || toAccount >= currentCustomer.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while(toAccount < 0 || toAccount >= theBank.numAccounts());

        //get the amount to transfer
        do{
            System.out.printf("Enter the amount to transfer( max %.02f): ", accountBalance);
            transferAmount= sc.nextDouble();

            //logic below stops transferring negative funds, or exceeding account balance.
            //remember to factor in ISA minimum account balance for group project
            if(transferAmount <0){
                System.out.println("Amount must be greater than zero!");
            } else if (transferAmount > accountBalance){
                System.out.printf("You cannot transfer more than the balance\nof %.02f.\n", accountBalance);
            }
        } while(transferAmount < 0 || transferAmount > accountBalance);

        //now transfer can occur
        //note addAccountTransaction + getAcctId are defined in Customer
       theBank.addAccountTransaction(fromAccount, -1*transferAmount, String.format("Origin account %s",
                theBank.getAccountNumber(toAccount)));
        theBank.addAccountTransaction(toAccount, transferAmount, String.format("Destination account %s",
                theBank.getAccountNumber(fromAccount)));

        //a brief log/confirmatory message
        System.out.printf("%s transferred", transferAmount);

        //then takes the User back to the appropriate menu
        Main.viewAccounts(theBank, sc, currentCustomer);

    }

    private static void withdrawFundsAllAccounts(Customer currentCustomer, Scanner sc, Bank theBank) {

        //the logic here is similar to transferFunds();
        //this time it cycles through all accounts, not just the accounts of the selected customer

        int fromAccount;
        double withdrawAmount;
        double accountBalance;
        String memo;

        //get the account to withdraw from
        do{
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to withdraw from: ", theBank.numAccounts());
            fromAccount = sc.nextInt()-1;
            if(fromAccount < 0 || fromAccount >= theBank.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while (fromAccount < 0 || fromAccount >= theBank.numAccounts());

        //now we need the account balance, from the account just chosen
        accountBalance= theBank.getAccountBalance(fromAccount);

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
        theBank.addAccountTransaction(fromAccount,-1*withdrawAmount, memo);

        //a brief log/confirmatory message
        System.out.printf("%s withdrawn from %s ", withdrawAmount,fromAccount);

        //then takes the User back to the appropriate menu
        Main.viewAccounts(theBank, sc, currentCustomer);
    }

    private static void depositFundsAllAccounts(Customer currentCustomer, Scanner sc, Bank theBank) {

        //the logic here is basically an inversion of withdrawFunds();
        //this time it cycles through all accounts, not just the accounts of the selected customer
        int toAccount;
        double depositAmount;
        String memo;

        //get the account to transfer from
        do{
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to deposit to: ", theBank.numAccounts());
            toAccount = sc.nextInt()-1;
            if(toAccount < 0 || toAccount >= theBank.numAccounts())
            {
                System.out.println("Invalid Account chosen. Please try again.");
            }

        } while (toAccount < 0 || toAccount >= theBank.numAccounts());

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
        theBank.addAccountTransaction(toAccount,depositAmount, memo);

        //a brief log/confirmatory message
        System.out.printf("%s deposited", depositAmount);

        //then takes the User back to the appropriate menu
        Main.viewAccounts(theBank, sc, currentCustomer);
    }
}

