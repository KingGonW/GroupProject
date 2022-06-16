import java.util.Random;

public abstract class BankAcc {
    public int accNum;
    private double balance;
    private static double interestRate;
    private double numberDeposits;
    private double numberWithdrawals;
    private double transactionHistory;




    public BankAcc(double balance, int accNum){

        this.balance = balance;
        Random rand= new Random();
        int nextNum = this.accNum;
        this.accNum= rand.nextInt(99999999);
        if (this.accNum == nextNum)
        {
            this.accNum = rand.nextInt(99999999);
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(double transactionHistory) {
        this.transactionHistory = transactionHistory;
    }


    public double getNumberDeposits() {
        return numberDeposits;
    }

    public void setNumberDeposits(double numberDeposits) {
        this.numberDeposits = numberDeposits;
    }

    public double getNumberWithdrawals() {
        return numberWithdrawals;
    }

    public void setNumberWithdrawals(double numberWithdrawals) {
        this.numberWithdrawals = numberWithdrawals;
    }



    public void depositMoney(double depositAmount) {
        System.out.println("Please enter the amount you want to deposit: ");
        this.balance += depositAmount;
        this.numberDeposits++; // increment number of deposits each time one is made

    }

    public void withdrawMoney(double withdrawAmount) {
        if(balance < withdrawAmount) {
            System.out.println("Insufficient Funds. \n Please Deposit an Amount.");
        }
        else {
            balance -= withdrawAmount;
            numberWithdrawals++; // increment number of withdrawals made each time money is withdrawn

        }

        //NB ISA accounts can't go below 100, so some logic above to check for an ISA account is needed
    }

    public void moneyTransfer(BankAcc thisAccount, BankAcc toAccount, double amountToTransfer){
        if(thisAccount.getBalance() > 0) {
            toAccount.setBalance(toAccount.balance += amountToTransfer);
            thisAccount.setBalance(balance -= amountToTransfer);
        } else {
            System.out.println("You don't have enough funds");
        }
    }
    //NB ISA accounts can't go below 100, so some logic above to check for an ISA account is needed
    /*
    https://codereview.stackexchange.com/questions/259695/simple-bank-application-in-java
*/

    public void transactionHistory() {
    this.numberWithdrawals = 0;
    this.numberWithdrawals = 0;
    }



}

