import java.time.*;

public class BusinessAccount extends BankAcc {

    private static String accountType = "Business Account";
    private final String businessSortCode = "12-23-24";
    private int businessAccountNumber;
    private double balance;
    private double subscription;
    private double afterSub;
    private LocalDate CreationDate;

    public BusinessAccount(double balance, int accNum) {
        super(balance, accNum);
        this.businessAccountNumber = accNum;
        this.CreationDate = LocalDate.now();
        /*this.setBalance(initialDeposit);
        if(initialDeposit < 25) {
            System.out.println("must deposit 25 or more to create an Business account");
        }*/
    }
    public void subscription(){
        LocalDate date = LocalDate.now();
        if(this.CreationDate.getMonth() == date.getMonth() && this.CreationDate.getDayOfMonth() == date.getDayOfMonth()){
            this.subscription = 25;
            this.afterSub = this.balance - this.subscription;
        }
        System.out.println("The balance after annual subscription is"+ this.afterSub);
    }
    public void withdraw(double withdrawAmount) {  //override withdraw method from BankAcc class
        if(super.getBalance() < 25) {
            System.out.println("Your account balance is too low, " +
                    "to keep your Business account, please keep balance over 25");
            System.out.println("Would you like to deposit money?");
        }
        else {
            super.withdrawMoney(withdrawAmount);
            System.out.println("You have withdrawn " + withdrawAmount + " from your account" + "\n" +
                    "Your Balance is now: " + this.balance);
        }
    }
    public String getBusinessSortCode() {

        return businessSortCode;
    }

    public int getBusinessAccountNumber() {

        return businessAccountNumber;
    }

}