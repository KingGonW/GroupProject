//Shockingly, this class holds data and methods regarding Customers

import java.util.ArrayList;

public class Customer {

        public final String firstName;

        private final String lastName;

        private final String CustomerId;



        private final ArrayList<BankAccount> bankAccounts;

        public Customer(String firstName, String lastName, Bank theBank) {

            //set users name
            this.firstName = firstName;
            this.lastName = lastName;

            //get a new id for the user
            this.CustomerId = theBank.getNewUserID();

            //create an empty list of accounts
            this.bankAccounts=new ArrayList<BankAccount>();

            //Print log message - not needed? Maybe call it, maybe not
            //System.out.printf("New User: %s %s with ID %s created.", firstName, lastName, this.CustomerId);
        }



    public void addAccount(BankAccount account) {
            this.bankAccounts.add(account);
        }

        public String getID() {

            return this.CustomerId;
        }

        public String getFirstName() {
            return this.firstName;
        }



        //this prints a summary of the accounts of this user
        public void printAccountsSummary() {

            System.out.printf("\n\n%s %s's Accounts Summary:", this.firstName,this.lastName);

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

        public int numAccounts() {

            return this.bankAccounts.size();
        }

    //note the below is something the *customer* does, hence being defined here
    //Account Index is the index of the account to use, which exists in the Accounts class
    public void printAccountTransactionHistory(int accountIndex) {
            this.bankAccounts.get(accountIndex).printTransHistory();
        }

    //note the below is something the *customer* does, hence being defined here
    //this gets the balance of a particular account
    public double getAccountBalance(int accountIndex){
        return this.bankAccounts.get(accountIndex).getBalance();
    }

    //note the below is something the *customer* does, hence being defined here
    //this adds to the Transaction class
    //note that addTransaction() is defined in BankAccount
    public void addAccountTransaction(int accountIndex, double amount, String memo) {
        this.bankAccounts.get(accountIndex).addTransaction(amount, memo);
    }

    //note the below is something the *customer* does, hence being defined here
    //this gets the Account Number of a particular account
    public String getAccountNumber(int accountIndex) {
        return this.bankAccounts.get(accountIndex).getID();
    }

    public Object getSummaryLine() {
            //%s is the customer name % is the 2nd name and the last  %s is the id
            return String.format("Customer Name: %s  %s, ID: %s", this.firstName, this.lastName, this.CustomerId);
    }

    public String getLastName() {
            return this.lastName;
    }
}


