import java.util.Random;

public class BankAcc {
    public int accNum;
    private double balance;
    private double transactionHistory;




    public BankAcc(){
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


    public void depositMoney(double depositAmount) {
        System.out.println("Please enter the amount you want to deposit: ");
        this.balance += depositAmount;
        System.out.println("You have deposited " + depositAmount +  " from your account." + "\n" +
                "Balance is now: " + this.balance);
    }

    public void withdrawMoney(double withdrawalAmount) {
        if(this.balance < withdrawalAmount) {
            System.out.println("Insufficient Funds. \n Please Deposit an Amount.");
        }
        else {
            this.balance -= withdrawalAmount;
            System.out.println("You have withdrawn " + withdrawalAmount + " from your account" + "\n" +
                    "Your Balance is now: " + this.balance);
        }

        //NB ISA accounts can't go below 100, so some logic above to check for an ISA account is needed
    }

    public void moneyTransfer(BankAcc thisAccount, BankAcc toAccount, double amountToTransfer){
        if(thisAccount.getBalance() > 0) {
            toAccount.setBalance(toAccount.balance += amountToTransfer);
            thisAccount.setBalance(this.balance -= amountToTransfer);
        } else {
            System.out.println("You don't have enough funds");
        }
    }
    //NB ISA accounts can't go below 100, so some logic above to check for an ISA account is needed
    /*
    https://codereview.stackexchange.com/questions/259695/simple-bank-application-in-java
*/


}

