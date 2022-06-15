import java.util.Random;

public class BankAccOtherTest {

    public static void main(String[] args) {


        Random rand = new Random();
        int sort_code = rand.nextInt(999999); // use split method to finalise sort code
        System.out.println(sort_code);
        //return String.format("%06d", sort_code);
        int accNum = rand.nextInt(99999999);
        System.out.println(accNum);

    }
    private double balance = 0, prevTransaction, interest;
    private int accountNumber;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getPrevTransaction() {
        return prevTransaction;
    }

    public void setPrevTransaction(double prevTransaction) {
        this.prevTransaction = prevTransaction;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

