public class BusinessAccount extends BankAccount{
    public BusinessAccount(String AccountType, Customer holder, Bank theBank) {
        super(AccountType, holder, theBank);
    }

    private int annualCost = 25;
    private int interest = 0;

    private int minimumBalance = 0;
}
