public class CurrentAccount extends BankAcc {

    public static String accountType = "Current Account";
    public static String currentSortCode = "00-04-01";
    private int currentAccountNumber;

    public CurrentAccount() {
        super(accountType, currentSortCode);
        super.getAccNum();
        setCurrentAccountNumber(super.getAccNum());
    }

    public void setCurrentAccountNumber(int currentAccountNumber) {
        this.currentAccountNumber = currentAccountNumber;
    }

    @Override
    public String toString() {
        return "Account Type:" + accountType + " Sort Code: " +
                currentSortCode + " Account Number: " + currentAccountNumber
                + " Balance: " + this.getBalance();
    }

}
