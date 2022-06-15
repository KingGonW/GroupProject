

public class BankTransactions {

    private double balance, prevTransaction;

    public BankTransactions(double balance, double prevTransaction) {
        this.balance = 0.0;
        this.prevTransaction = prevTransaction;
    }

    public double getBalance() {
        return this.balance;
    }
    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getPrevTransaction() {
        return this.prevTransaction;
    }
    // array list to store history of transactions
    // use for later



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
    }
    public void moneyTransfer(BankTransactions thisAccount, BankTransactions toAccount, double amountToTransfer){
        if(thisAccount.getBalance() > 0) {
            toAccount.setBalance(toAccount.balance += amountToTransfer);
            thisAccount.setBalance(this.balance -= amountToTransfer);
        } else {
            System.out.println("You don't have enough funds");
        }
    }
    /*
    https://codereview.stackexchange.com/questions/259695/simple-bank-application-in-java
*/


}
