import java.util.Date;


//this class holds information regarding transactions, making them easier to retrieve and use
public class Transaction {

    //the amount of this transaction
    private double amount;

    //the time and date of the transaction
    private Date timestamp;

    //a memo for this transaction. This is an optional thing :)
    private String memo;

    //the account in which the transaction was performed
    private BankAccount inAccount;

    //one with the memo, and one without. This is overloading.
    // this means we can safely skip the memo part in the Main.java class, in the withdrawFunds() and depositFunds()
    public Transaction( double amount, BankAccount inAccount){

        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo="";

    }

    public Transaction( double amount,String memo, BankAccount inAccount){
        //call the 2 argument constructor first, i.e. the one above
        this(amount,inAccount);


        //then set the memo
        this.memo = memo;
    }



    //this is a helper method of getBalance() - inside BankAccount
    public double getAmount() {
        return this.amount;
    }
    //see printAccountSummary in Customer.java for explanation
    //£ is not recognised!
    public String getSummaryLine() {
        if(this.amount >= 0){
            return String.format("Date and time :%s\n Amount in pounds : %.02f\n Memo: %s\n",
                    this.timestamp.toString(),
                    this.amount, this.memo);
        } else {
            return String.format("Date and time :%s\n Amount in pounds: (%.02f)\n Memo: %s\n",
                    this.timestamp.toString(),
                    this.amount, this.memo);
        }
    }
}
