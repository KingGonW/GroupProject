import java.util.ArrayList;
import java.util.Random;

//this class is intended to hold data and methods that are not really part of the account or customer stuff
// it mimics the bank as an entity, e.g. being able to create customers etc.
// it also interacts with other classes, as per Object Orientated Programming
public class Bank {
    private final String name;

    private final ArrayList<Customer> customers;

    final ArrayList<BankAccount> bankAccounts;



    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<Customer>();
        this.bankAccounts = new ArrayList<BankAccount>();
    }


    //this gets a CustomerID
    public String getNewUserID() {
        //initialise
        String id;
        Random rng = new Random();
        int len=4;
        boolean nonUnique = false;

        //continues loop until a Unique id is created
        do {
            //generate the number and put it in a string
            id="";
            for (int c = 0; c < len; c++){
                id += ((Integer)rng.nextInt(10)).toString();
            }

            //check to make sure it is unique, by going through the customer array list
            nonUnique = false;
            for(Customer u : this.customers){
                if (id.compareTo(u.getID()) == 0){
                    nonUnique=true;
                    break;
                }
            }
        } while(nonUnique);
        return id;

    }


    // this generates an account number
    public String getNewAccountNumber() {

        String id;
        Random rng = new Random();
        int len=8;
        boolean nonUnique = false;

        //continues loop until a Unique id is created
        do {
            //generate the number and put it in a string
            id="";
            for (int c = 0; c < len; c++){
                id += ((Integer)rng.nextInt(10)).toString();
            }

            //check to make sure it is unique, by going through the customer array list
            nonUnique = false;
            for(BankAccount a : this.bankAccounts){
                if (id.compareTo(a.getID()) == 0){
                    nonUnique=true;
                    break;
                }
            }
        } while(nonUnique);
        return id;

    }

    public void addAccount(BankAccount anAcct){
        this.bankAccounts.add(anAcct);
    }



    public Customer addCustomer(String firstName, String lastName){

        //the 'this' below makes it refer to the class it is in, i.e "Bank"
        Customer newCustomer = new Customer(firstName,lastName,this);
        this.customers.add(newCustomer);
        return newCustomer;
    }

    //the below lets users log in, but we probably will not use this as Trevor does not need to log in :)
    public Customer customerLogin(String customerID){
        for(Customer u : this.customers){
            if (u.getID().compareTo(customerID)==0){
                return u;
            }
        }
        //if users are not found
        return null;
    }


    //name here refers to the bank name
    public Object getName() {
        return this.name;
    }

    //below prints out a list of customers

    public void printCustomerSummary() {

        System.out.printf("\n\n%s' List of Customers:", this.name);

        // sets our counter at 0,
        // as long as the counter is less than the size of the accounts array
        // adds one to the counter
        for (int a = 0; a <this.customers.size(); a++){

            //%d is the format for an integer
            //%s is the summary line - each account instance will generate one of these
            //getSummaryLine() is defined in Accounts.java
            System.out.printf("\n%d) %s", a+1,
                    this.customers.get(a).getSummaryLine());
        }
        System.out.println();
    }

    //below to find out how many customers there are
    public int numCustomers() {
        return this.customers.size();
    }

    //below prints out a list of accounts
    public void printAccountsSummary() {
        System.out.printf("\n Accounts held by %s :", this.name);

        // sets our counter at 0,
        // as long as the counter is less than the size of the accounts array
        // adds one to the counter
        for (int a = 0; a <this.bankAccounts.size(); a++){

            //%d is the format for an integer
            //%s is the summary line - each account instance will generate one of these
            //getSummaryLine() is defined in Accounts.java
            System.out.printf("\n%d) %s", a+1,
                    this.bankAccounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    //the below to allow get all the accounts in the Banks array of accounts
    public int numAccounts() {
        return this.bankAccounts.size();
    }
    //the below to print the account history of a chosen account
    public void printAccountTransactionHistory(int accountIndex) {
        this.bankAccounts.get(accountIndex).printTransHistory();
    }
    //the below to print the account balance of a chosen account
    public double getAccountBalance(int accountIndex) {
        return this.bankAccounts.get(accountIndex).getBalance();
    }

    //the below to allow Bank Entity to make Account Transactions, same as how Customers can do it
    public void addAccountTransaction(int accountIndex, double amount, String memo) {
        this.bankAccounts.get(accountIndex).addTransaction(amount, memo);
    }
    //the below to allow Bank Entity to retrieve the Account Number, same as in the Customer class

    public Object getAccountNumber(int accountIndex) {
        return this.bankAccounts.get(accountIndex).getID();
    }
}
