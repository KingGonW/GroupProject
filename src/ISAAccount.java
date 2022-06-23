public class ISAAccount extends BankAcc {

    public static String accountType = "ISA Account";
    public static String ISAsortCode = "22-66-44";
    private final double interestRate = 0.02;
    private int ISAAccountNumber;
    private double yearlyInterest;


    public ISAAccount() {
     //   super(openingBalance, closingBalance, accNum,accountType,ISAsortCode);
        setISAAccountNumber(super.getAccNum());
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


    public void setISAAccountNumber(int ISAAccountNumber) {
        this.ISAAccountNumber = ISAAccountNumber;
    }

  //overriding parent class method
    @Override
    public void withdrawMoney(double withdrawAmount) {
        //override withdraw method from BankAcc class
        if (getOpeningBalance() - withdrawAmount < 100) {
            System.out.println("Your account balance will be too low, " +
                    "to keep your ISA account, please keep balance over 100");
            System.out.println("Would you like to deposit money?");
        } else {
            withdrawMoney(withdrawAmount);
            System.out.println("You have withdrawn " + withdrawAmount + " from your account" + "\n" +
                    "Your Balance is now: " + getClosingBalance());
        }
    }

//removed deposit method

    public void calcInterest() // method to calculate interest on customers yearly balance
    {

        yearlyInterest = (1 + (interestRate));
        yearlyInterest = getClosingBalance() * yearlyInterest;
        setClosingBalance(yearlyInterest);
        System.out.print("Interest accumulated on your balance is: " +
                yearlyInterest + '\n' + "Your balance is now: " + getClosingBalance());


    }

    @Override
    public String toString() {
        return "Account Type:" + accountType + " Sort Code: " +
                ISAsortCode + " Account Number: " + ISAAccountNumber +
                " Interest Rate: " + this.getInterestRate() + "%%";


    }

}
