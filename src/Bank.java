

//this class created to hold various methods etc that aren't directly related to the actual accounts
//for example creating a customer
//also serves as an object to interact with
import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;

    private ArrayList<Customer> customers;

    private ArrayList<BankAcc> bankAccounts;

    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<Customer>();
        this.bankAccounts = new ArrayList<BankAcc>();
    }

    public String getNewUserID() {
        //initialise
        String CustomerId;
        Random rng = new Random();
        int len=4;
        boolean nonUnique = false;

        //continues loop until a Unique id is created
        do {
            //generate the number and put it in a string
            CustomerId="";
            for (int c = 0; c < len; c++){
                CustomerId += ((Integer)rng.nextInt(10)).toString();
            }

            //check to make sure it is unique, by going through the customer array list
            nonUnique = false;
            for(Customer u : this.customers){
                if (CustomerId.compareTo(u.getID()) == 0){
                    nonUnique=true;
                    break;
                }
            }
        } while(nonUnique);
        return CustomerId;

    }

    //this below is an alternate way to generate an account number
    public String getNewAcctNumber() {

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
            for(BankAcc a : this.bankAccounts){
                if (id.compareTo(a.getID()) == 0){
                    nonUnique=true;
                    break;
                }
            }
        } while(nonUnique);
        return id;

    }

    public void addAccount(BankAcc anAcct){
        this.bankAccounts.add(anAcct);
    }

    public Customer addCustomer(String firstName, String lastName){

        //the 'this' below makes it refer to the class it is in, i.e "Bank"
        Customer newCustomer = new Customer(firstName,lastName,this);
        this.customers.add(newCustomer);

        //create a savings account for the customer - in group project we have separate logic for this
        BankAcc newAccount = new bankAcc("Current",newCustomer,this);
        // add to customer and bank lists
        newCustomer.addAccount(newAccount);
        this.bankAccounts.add(newAccount);
        return newCustomer;

    }

    //the below lets users log in, ignoring the PIN stuff from the video
    public Customer customerLogin(String customerID){
        for(Customer u : this.customers){
            if (u.getID().compareTo(customerID)==0){
                return u;
            }
        }
        //if users are not found
        return null;
    }

    public Object getName() {

        return this.name;
    }
}