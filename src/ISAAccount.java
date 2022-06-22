public class ISAAccount extends BankAcc {

    private final String accountType = "ISA Account";
    private final String ISAsortCode = "22-66-44";
    private final double interestRate = 0.02;
    private int ISAAccountNumber;
    private double yearlyInterest;


    public ISAAccount(double openingBalance, double closingBalance, int accNum) {
        super(openingBalance, closingBalance, accNum);
        this.ISAAccountNumber = accNum;
        /*this.setBalance(initialDeposit);
        if(initialDeposit >= 100) {
            this.setInterest(0.02);
        }
        else if(initialDeposit < 100) {
            System.out.println("must deposit 100 or more to create an ISA account");
        }*/
    }

    public double getInterestRate() {
        return interestRate;
    }


    public String getISAsortCode() {

        return ISAsortCode;
    }

    public int getISAAccountNumber() {
        return ISAAccountNumber;
    }


    //I think the withdraw below can be folded into the BankAcc parent class method, with an if/else targetting ISA.
    // Method morphology etc.
    public void withdraw(double withdrawAmount) {
        //override withdraw method from BankAcc class
        if (super.getOpeningBalance() - withdrawAmount < 100) {
            System.out.println("Your account balance will be too low, " +
                    "to keep your ISA account, please keep balance over 100");
            System.out.println("Would you like to deposit money?");
        } else {
            super.withdrawMoney(withdrawAmount);
            System.out.println("You have withdrawn " + withdrawAmount + " from your account" + "\n" +
                    "Your Balance is now: " + getClosingBalance());
        }
    }


    //Why is there a separate deposit()?
    public void deposit(double depositAmount) {  //override deposit method from BankAcc class
        super.depositMoney(depositAmount);
        System.out.println("You have deposited " + depositAmount + " from your account." + "\n" +
                "Balance is now: " + getClosingBalance());
    }


    public void calcInterest() // method to calculate interest on customers most recent balance
    {

        yearlyInterest = (1 + (interestRate * 1));
        yearlyInterest = getClosingBalance() * yearlyInterest;
        setClosingBalance(yearlyInterest);
        System.out.print("Interest accumulated on your balance is: " +
                yearlyInterest + '\n' + "Your balance is now: " + getClosingBalance());


    }

    @Override
    public String toString() {
        return "Account Type:" + accountType + " Sort Code: " +
                this.getISAsortCode() + " Account Number: " + this.getISAAccountNumber() +
                " Interest Rate: " + this.getInterestRate() + "%%";


    }

}
