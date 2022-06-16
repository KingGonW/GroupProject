public class CurrentAccount extends BankAcc{

    private int currentSortCode;
    private int currentAccountNumber;

    public CurrentAccount(int currentSortCode, int currentAccountNumber) {
        this.currentSortCode = currentSortCode;
        this.currentAccountNumber = currentAccountNumber;
    }
}
