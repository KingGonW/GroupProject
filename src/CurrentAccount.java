public class CurrentAccount extends BankAccount{
    public CurrentAccount(String AccountType, Customer holder, Bank theBank) {
        super(AccountType, holder, theBank);
    }

    private int annualCost = 0;
    private int interest = 2;

    private int minimumBalance = 0;
}
