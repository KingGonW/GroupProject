public class BusinessAccount extends BankAcc {


    final String businessSortCode;
    private int businessAccountNumber;

    public BusinessAccount(double balance, int accNum, String businessSortCode, int businessAccountNumber) {
        super(balance, accNum);
        this.businessSortCode = businessSortCode;
        this.businessAccountNumber = businessAccountNumber;
    }

    public String getBusinessSortCode() {
        return businessSortCode;
    }

    public int getBusinessAccountNumber() {
        return businessAccountNumber;
    }
}