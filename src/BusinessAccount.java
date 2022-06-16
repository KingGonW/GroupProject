public class BusinessAccount extends BankAcc {


    final String businessSortCode;
    private int businessAccountNumber;

    public BusinessAccount(String businessSortCode, int businessAccountNumber) {
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
