import java.util.ArrayList;

//this is the super class of bank accounts. ISAs etc inherit from here.
public class BankAccount {

    private String AccountType;

    private double balance;

    private String AccountNumber;

    private Customer holder;




    //interest and minimumBalance are shared by all accounts *but* only in ISA accounts will they have a value
    private int interest;

    private int minimumBalance;

    //similarly, all accounts have an annual cost, but only in Business Accounts will this have a value
    private int annualCost;


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

        //format the summary line depending on whether the balance is overdrawn - consider if needed for Group Project

        if (balance >= 0){

            //this tells the string to format as a decimal, with 2 points
            //%s is the account id, % is the balance in brackets for negative values, then another %s for the account name
            return String.format("Account Number: %s, Balance in pounds: %.02f, Account Type: %s, linked to %s %s",
                    this.AccountNumber, balance, this.AccountType, this.holder.firstName, this.holder.getLastName());
        } else {
            return String.format("Account Number: %s, Balance in pounds: (%.02f), Account Type: %s, linked to %s",
                    this.AccountNumber, balance, this.AccountType, this.holder);
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
        //this prints the transactions in reverse order, i.e. the most recent first
        for(int t = this.transactions.size()-1; t>=0; t--){
            System.out.printf(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    //below called by Customer but defined here, because it creates a new Transaction object,
    // and adds it to the array list, which then allows it to be accessed
    public void addTransaction(double amount, String memo) {

        Transaction newTransaction = new Transaction(amount, memo, this);
        this.transactions.add(newTransaction);
    }
}


