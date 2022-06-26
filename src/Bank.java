import java.util.ArrayList;
import java.util.Random;

//this class is intended to hold data and methods that are not really part of the account or customer stuff
// it mimics the bank as an entity, e.g. being able to create customers etc.
// it also interacts with other classes, as per Object Orientated Programming
public class Bank {
    private final String name;

    private final ArrayList<Customer> customers;

    private final ArrayList<BankAccount> bankAccounts;



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

    public int numCustomers() {
        return this.customers.size();
    }
}
