import java.time.LocalDate;

public class BusinessAccount extends BankAcc {

    public static String accountType = "Business Account";
    public static String businessSortCode = "12-23-24";
    private int businessAccountNumber;
    private double subscription;
    private double afterSub;
    private LocalDate CreationDate;

    public BusinessAccount() {
        super(accountType, businessSortCode);
        super.getAccNum();
        setBusinessAccountNumber(super.getAccNum());
        this.CreationDate = LocalDate.now();
    }

    public void setBusinessAccountNumber(int businessAccountNumber) {
        this.businessAccountNumber = businessAccountNumber;
    }

    public void subscription() {
        LocalDate date = LocalDate.now();
        if (this.CreationDate.getMonth() == date.getMonth() && this.CreationDate.getDayOfMonth() == date.getDayOfMonth()) {
            this.subscription = 25;
            this.afterSub = this.getBalance() - this.subscription;
        }
        System.out.println("The balance after annual subscription is" + this.afterSub);
    }


    @Override
    public String toString() {
        return "Account Type:" + accountType + " Sort Code: " +
                businessSortCode + " Account Number: " + businessAccountNumber
                + " Balance: " + super.getBalance();
    }

}