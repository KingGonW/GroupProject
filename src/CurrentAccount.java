public class CurrentAccount extends BankAcc{

    private int currentSortCode;
    private int currentAccountNumber;

    public CurrentAccount(double balance, int accNum, int currentSortCode, int currentAccountNumber) {
        super(balance, accNum);
        this.currentSortCode = currentSortCode;
        this.currentAccountNumber = currentAccountNumber;
    }
}
