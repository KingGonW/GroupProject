public class CurrentAccount extends BankAcc{

    public static String accountType = "Current Account";
    public static String currentSortCode = "00-04-01";
    private int currentAccountNumber;

    public CurrentAccount() {
        super(accountType,currentSortCode);
        super.getAccNum();
    }

    public String getCurrentSortCode() {

        return currentSortCode;
    }

    public int getCurrentAccountNumber() {
        return currentAccountNumber;
    }


    //commenting out below as seems unneeded
    //public void withdraw(double withdrawAmount) {
        //override withdraw method from BankAcc class
        //if (withdrawAmount > super.getOpeningBalance()) {
          //  System.out.println("Insufficient funds, please withdraw a lower amount than your balance");
          //  System.out.println("Would you like to deposit money?");
        //} else {
           // super.withdrawMoney(withdrawAmount);
           // System.out.println("You have withdrawn " + withdrawAmount + " from your account" + "\n" +
                  //  "Your Balance is now: " + getClosingBalance());
       // }
   // }

    //public void deposit(double depositAmount) {  //override deposit method from BankAcc class
       // super.depositMoney(depositAmount);
        //System.out.println("You have deposited " + depositAmount + " from your account." + "\n" +
               // "Balance is now: " + getClosingBalance());
    //}


}
