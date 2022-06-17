public class BusinessAccount extends BankAcc {

    private static String accountType = "Business Account";
    final String businessSortCode;
    private int businessAccountNumber;

    public BusinessAccount(double balance,double initialDeposit, int accNum, String businessSortCode, int businessAccountNumber) {
        super(balance, accNum);
        this.businessSortCode = "12-23-24";
        this.businessAccountNumber = accNum;
        this.setBalance(initialDeposit);
        if(initialDeposit <= 100) {
            System.out.println("must deposit 25 or more to create an Business account");
        }
    }

    public String getBusinessSortCode() {
        return businessSortCode;
    }

    public int getBusinessAccountNumber() {
        return businessAccountNumber;
    }
}