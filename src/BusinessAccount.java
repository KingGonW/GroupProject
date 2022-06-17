import java.time.*;

public class BusinessAccount extends BankAcc {

    private static String accountType = "Business Account";
    private final String businessSortCode = "12-23-24";
    private int businessAccountNumber;
    private double balance;
    private double subscription;

    private double afterSub;

    public BusinessAccount(double balance, int accNum) {
        super(balance, accNum);
        this.businessAccountNumber = accNum;
        /*this.setBalance(initialDeposit);
        if(initialDeposit <= 100) {
            System.out.println("must deposit 25 or more to create an Business account");
        }*/
    }
    public void subscription(){
        LocalDate date = LocalDate.parse("2022-1-1");
        LocalDate nextYear = date.plusDays(365);
        this.subscription = 25;
        for (int i=0; i<365; i++){
            LocalDate nextDate = date.plusDays(1);
            if (nextYear == nextDate){
                this.afterSub = this.balance - this.subscription;
            }
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
    public String getSortCode() {

        return businessSortCode;
    }

    public int getAccountNumber() {
        return businessAccountNumber;
    }

}