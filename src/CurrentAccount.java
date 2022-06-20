public class CurrentAccount extends BankAcc{

    private static String accountType = "Current Account";
    private final String currentSortCode = "00-04-01";
    private int currentAccountNumber;

    public CurrentAccount(double balance, int accNum) {
        super(balance, accNum);
        this.currentAccountNumber = accNum;
    }

    public String getCurrentSortCode() {

        return currentSortCode;
    }

    public int getCurrentAccountNumber() {
        return currentAccountNumber;
    }

}
