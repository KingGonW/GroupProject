import java.util.ArrayList;

//Andy comments:
//
// this class created to host the addCustomer method
//as a separate class, this would, logically, hold thing belonging to a bank, namely a list of accounts and customers
//I tried placing the addCustomer() inside Customer class, but it threw up some scary errors
public class Bank {

    private String bankName;

    private ArrayList<Customer> customers;

    private ArrayList<BankAcc> bankAccounts;

    public Bank(String name) {
        this.bankName = name;

        this.customers = new ArrayList<Customer>();
        this.bankAccounts = new ArrayList<BankAcc>();
    }

    public void addAccount(BankAcc anAcct){
        this.bankAccounts.add(anAcct);
    }

    public Customer addCustomer(String firstName, String lastName, String emailAddress, String phoneNumber) {
        Customer newCustomer = new Customer(firstName,lastName,emailAddress, phoneNumber);
        this.customers.add(newCustomer);

        return newCustomer;
    }

}
