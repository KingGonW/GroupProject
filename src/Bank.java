import java.util.ArrayList;
import java.util.Random;

//this class is intended to hold data and methods that are not really part of the account or customer stuff
// it mimics the bank as an entity, e.g. being able to create customers etc.
// it also interacts with other classes, as per Object Orientated Programming
public class Bank {
    private String name;

    private ArrayList<Customer> customers;

    private ArrayList<BankAccount> bankAccounts;

    //in group project Account = BankAcc and the array accounts here = bankAccounts;

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

        //create a savings account for the customer - in group project we have separate logic for this
        //note that inside the Main, another addAccount is called, for an ISA.
        BankAccount newAccount = new BankAccount("Savings",newCustomer,this);
        // add to customer and bank lists
        newCustomer.addAccount(newAccount);
        this.bankAccounts.add(newAccount);
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
}
