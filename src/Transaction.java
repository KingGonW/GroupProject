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
    public String getSummaryLine() {
        if(this.amount >= 0){
            return String.format("%s : £%.02f : %s",
                    this.timestamp.toString(),
                    this.amount, this.memo);
        } else {
            return String.format("%s : £(%.02f) : %s",
                    this.timestamp.toString(),
                    this.amount, this.memo);
        }
    }
}
