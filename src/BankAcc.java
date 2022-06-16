import java.util.Random;

public class BankAcc {
    private final String sortCode;
    private int accNum;
    private double balance;
    private double transactionHistory;
    private double interest;

    public BankAcc(){
        Random rand= new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<3;i++){
            sb.append(rand.nextInt(99));
            sb.append("-");
        }
        sb.deleteCharAt(8);
        this.sortCode= sb.toString();
        this.accNum= rand.nextInt(99999999);
    //update another one
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(double transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}

