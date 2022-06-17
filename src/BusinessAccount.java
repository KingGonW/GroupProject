public class BusinessAccount extends BankAcc {

    private static String accountType = "Business Account";
    final String businessSortCode;
    private int businessAccountNumber;
    private double balance;

    public BusinessAccount(double balance,double initialDeposit, int accNum, String businessSortCode, int businessAccountNumber) {
        super(balance, accNum);
        this.businessSortCode = "12-23-24";
        this.businessAccountNumber = accNum;
        this.setBalance(initialDeposit);
        if(initialDeposit <= 100) {
            System.out.println("must deposit 25 or more to create an Business account");
        }
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