public class CurrentAccount extends BankAcc{

    final String currentSortCode;
    private int currentAccountNumber;

    public CurrentAccount(String currentSortCode, int currentAccountNumber) {
        this.currentSortCode = currentSortCode;
        this.currentAccountNumber = currentAccountNumber;
    }

    public String getCurrentSortCode() {
        return currentSortCode;
    }

    public int getCurrentAccountNumber() {
        return currentAccountNumber;
    }
}
