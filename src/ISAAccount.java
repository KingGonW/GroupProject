public class ISAAccount extends BankAcc{

    private final String accountType = "ISA Account";
    private final String ISAsortCode = "22-66-44";
    private final double interestRate = 0.02;
    private int ISAAccountNumber;
    private double yearlyInterest;


    public ISAAccount(double balance, int accNum) {
        super(balance, accNum);
        this.ISAAccountNumber = accNum;
        /*this.setBalance(initialDeposit);
        if(initialDeposit >= 100) {
            this.setInterest(0.02);
        }
        else if(initialDeposit < 100) {
            System.out.println("must deposit 100 or more to create an ISA account");
        }*/
    }

    public double getInterest() {
        return yearlyInterest;
    }


    public String getSortCode() {

        return ISAsortCode;
    }

    public int accountNumber() {
        return ISAAccountNumber;
    }


    public void withdraw(double withdrawAmount) {
        //override withdraw method from BankAcc class
        if(super.getBalance() - withdrawAmount < 100) {
            System.out.println("Your account balance will be too low, " +
                    "to keep your ISA account, please keep balance over 100");
            System.out.println("Would you like to deposit money?");
        }
        else {
            super.withdrawMoney(withdrawAmount);
            System.out.println("You have withdrawn " + withdrawAmount + " from your account" + "\n" +
                    "Your Balance is now: " + this.getBalance());
        }
    }

    public void deposit(double depositAmount) {  //override deposit method from BankAcc class
        super.depositMoney(depositAmount);
        System.out.println("You have deposited " + depositAmount +  " from your account." + "\n" +
                "Balance is now: " + this.getBalance());
    }



    public void calInterest() // method to calculate interest on customers most recent balance
    {

        yearlyInterest = interestRate*(365/365);
        yearlyInterest = this.getBalance() * yearlyInterest;
        this.setBalance(yearlyInterest);
        System.out.print("Interest accumulated on your balance is: " +
                yearlyInterest + '\n' + "Your balance is now: " + this.getBalance());


    }


}
