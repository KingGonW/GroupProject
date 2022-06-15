
public class ISAAccount extends BankAcc{

final int interestRate, timePeriod;

    public ISAAccount(int balance, int interestRate, int timePeriod) {
        super(balance); // implement balance from bankAcc
        this.interestRate = interestRate;
        this.timePeriod = timePeriod;
    }
}
