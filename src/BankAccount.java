import java.util.ArrayList;

//this is the super class of bank accounts. ISAs etc inherit from here.
public class BankAccount {



    //name here = this used to be just name
    private String AccountType;

    private double balance;

    //this used to be AcctId
    private String AccountNumber;

    private Customer holder;


    //this array list will get called in showTransaction()
    private ArrayList<Transaction> transactions;

    public BankAccount(String AccountType, Customer holder, Bank theBank) {

        //set the account name and holder
        this.AccountType = AccountType;
        this.holder = holder;

        // get id for the account
        this.AccountNumber=theBank.getNewAccountNumber();

        //initialise transactions
        this.transactions = new ArrayList<Transaction>();
    }

    public String getID() {

        return this.AccountNumber;
    }

    public String getSummaryLine() {
        //get the balance
        double balance = this.getBalance();

        //format the summary line depending on whether the balance is overdrawn - consider if needed for Group Projects

        if (balance >= 0){

            //this tells the string to format as a decimal, with 2 points
            //%s is the account id, £% is the balance in brackets for negative values, then another %s for the account name
            return String.format(" Account Number: %s Balance: £%.02f Account Type: %s", this.AccountNumber, balance, this.AccountType);
        } else {
            return String.format("%s: £(%.02f ): %s", this.AccountNumber, balance, this.AccountType);
        }

    }

    //get the balance of the account
    public double getBalance() {
        double balance = 0;
        //run a loop to go through the transactions array list - defined above - and return the balance
        for(Transaction t: this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }

    //print the transaction history of the account
    public void printTransHistory() {
        System.out.printf("\nTransaction History for account %s\n", this.AccountNumber);
        for(int t = this.transactions.size()-1; t>=0; t--){
            System.out.printf(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }
}


