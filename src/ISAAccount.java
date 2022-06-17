public class ISAAccount extends BankAcc{

    private static String accountType = "ISA Account";
    final String ISAsortCode;
    private double interestRate;
    private int ISAAccountNumber;
    private double interest;
    private double balance;

    ISAAccount (double balance, int accNum, double interestRate, double initialDeposit,
                String sortCode, int ISAAccountNumber) {
        super(balance, accNum);
        this.ISAsortCode = "22-66-44";
        this.ISAAccountNumber = accNum;
        this.setBalance(initialDeposit);
        if(initialDeposit >= 100) {
            this.setInterest(0.02);
        }
        else if(initialDeposit < 100) {
            System.out.println("must deposit 100 or more to create an ISA account");
        }
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    @Override
    public void setBalance(double savingsBalance) {
        balance = savingsBalance;
    }

    public String getSortCode() {

        return ISAsortCode;
    }


    public void withdraw(double withdrawAmount) {  //override withdraw method from BankAcc class
        if(super.getBalance() < 100) {
            System.out.println("Your account balance is too low, " +
                    "to keep your ISA account, please keep balance over 100");
            System.out.println("Would you like to deposit money?");
        }
        else {
            super.withdrawMoney(withdrawAmount);
            System.out.println("You have withdrawn " + withdrawAmount + " from your account" + "\n" +
                    "Your Balance is now: " + this.balance);
        }
    }

    public void deposit(double depositAmount) {  //override deposit method from BankAcc class
        super.depositMoney(depositAmount);
        System.out.println("You have deposited " + depositAmount +  " from your account." + "\n" +
                "Balance is now: " + this.balance);
    }

    public void calInterest() // method to calculate interest on customers most recent balance
    {
        interestRate = 0.02;
        double yearlyInterest = interestRate*(365/365);
        yearlyInterest = balance * yearlyInterest;
        this.balance += yearlyInterest;
        System.out.print("Interest accumalated on your balance is: " +
                yearlyInterest + '\n' + "Your balance is now: " + this.balance);


    }


}
