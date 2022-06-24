import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        //initialise the scanner
        Scanner sc = new Scanner(System.in);

        //initialise the bank
        Bank theBank = new Bank("Novus Banking Services");

        //add a customer to the bank, which also creates a savings account - for Group Project this is handled separately
        //this is hard coded here for testing purposes
        //for Group Project we need an interface, i.e. menus, to create customers and accounts

        Customer aCustomer = theBank.addCustomer("Mister","Test");

        //create an ISA for testing purposes
        BankAccount anAccount = new BankAccount("ISA",aCustomer,theBank);
        aCustomer.addAccount(anAccount);
        theBank.addAccount(anAccount);

        Customer currentCustomer;
        while(true){

            //'sc' below refers to the scanner - defined above - which gets passed to the methods

            //stay in the login prompt until successful login
            currentCustomer = Main.mainMenuPrompt(theBank, sc);

            //stay in main menu until user quits
            Main.printUserMenu(currentCustomer, sc);

        }

    }

    private static void printUserMenu(Customer currentCustomer, Scanner sc) {

        //print a summary of user accounts
        //defined in Customer.java

        //adapt this to display a list of customers + a list of account?
        currentCustomer.printAccountsSummary();

        //initialise
        int userChoice;

        //user menu
        do
        {
            System.out.printf("Welcome %s, what would you like to do?:", currentCustomer.getFirstName());
            System.out.println();
            System.out.println(" 1. Show Account Transaction History");
            System.out.println(" 2. Make a withdrawal");
            System.out.println(" 3. Make a deposit");
            System.out.println(" 4. Make a Transfer");
            System.out.println(" 5. Exit");
            System.out.println();
            System.out.println(" Enter your choice");
            userChoice = sc.nextInt();
            //for the group project we should also have a "go back to menu option
            if (userChoice <1 || userChoice > 5){
                System.out.println("Invalid choice, please choose 1 - 5.");
            }
        } while( userChoice <1 || userChoice > 5);

        //process the userChoice

        switch(userChoice){
            case 1:
                Main.showTransactionHistory(currentCustomer, sc);
                break;
            case 2:
                // ATM.withdrawFunds(currentCustomer, sc);
                break;
            case 3:
                // ATM.depositFunds(currentCustomer, sc);
                break;
            case 4:
                //  ATM.transferFunds(currentCustomer, sc);
                break;

            //in the video there is no default case, but I think we need one

        }

        //redisplay the menu unless the user wants to quit

        //the below is recursion, calling a method within a method. Time to watch Inception again.
        if(userChoice !=5){
            Main.printUserMenu(currentCustomer, sc);
        }

    }

    public static void showTransactionHistory(Customer currentCustomer, Scanner sc) {

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
    }

    private static Customer mainMenuPrompt(Bank theBank, Scanner sc) {
        //initialise
        String CustomerID;
        Customer AuthCustomer;

        do{
            //prompt user for log in details until correct input is achieved

            System.out.printf("Welcome to %s\n\n", theBank.getName());
            System.out.print("Enter Customer ID:");
            CustomerID = sc.nextLine();

            //ignoring PIN stuff from the video

            //try to get the Customer object that corresponds to the ID
            AuthCustomer = theBank.customerLogin(CustomerID);
            if (AuthCustomer == null){
                System.out.println("Incorrect User. \n Try again.");
            }
        } while( AuthCustomer == null);//continues looping until successful login

        return AuthCustomer;
    }


}
