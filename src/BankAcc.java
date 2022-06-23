import java.util.Random;

public class BankAcc {
    private int accNum;
    private double openingBalance;
    private double closingBalance;
    BankAcc ISAAccount;
    private double numberDeposits;
    private double numberWithdrawals;

    private String accType;

    private String sortCode;

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    BankAcc(){}

    public BankAcc(double openingBalance, double closingBalance, int accNum,String accType,String sortCode) {
        this.accType = accType;
        this.sortCode = sortCode;
        // added opening and closing balance to constructor
        this.openingBalance = openingBalance;
        this.closingBalance = closingBalance;
        Random rand = new Random();
        int nextNum = this.accNum;
        this.accNum = rand.nextInt(99999999);
        if (this.accNum == nextNum) {
            this.accNum = rand.nextInt(99999999);
        }
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public double getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(double closingBalance) {
        this.closingBalance = closingBalance;
    }


    public int getAccNum() {
        return accNum;
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
        System.out.println("Please enter the amount you want to deposit:  ");
        //sets closing balance to balance after deposit was made
        setClosingBalance(openingBalance += depositAmount);
        //new opening balance is the last closing balance amount
        setOpeningBalance(getClosingBalance());
        this.numberDeposits++; // increment number of deposits each time one is made

    }


    //see comments in ISAAccount. I think nested if/else would work better here to check if account is an ISA
    public void withdrawMoney(double withdrawAmount) {

        if (openingBalance < withdrawAmount) {
            System.out.println("Insufficient Funds. \n Please Deposit an Amount.");
        } else {
            setClosingBalance(openingBalance -= withdrawAmount);
            setOpeningBalance(closingBalance);
            numberWithdrawals++; // increment number of withdrawals made each time money is withdrawn

        }

    }

    public void moneyTransfer(BankAcc fromAccount, BankAcc toAccount, double amountToTransfer) {

        if (fromAccount == ISAAccount) {
            if (ISAAccount.getOpeningBalance() - amountToTransfer < 100) {
                System.out.println("Your transfer will make your new openingBalance less than 100 +" +
                        "please keep 100 and above at all times");
                return;
            }
        }
        if (fromAccount.openingBalance > amountToTransfer) {
            //sets the receivers closing balance to include new deposit
            toAccount.setClosingBalance(toAccount.openingBalance += amountToTransfer);
            // new opening balance of receiver is last closing balance amount
            toAccount.setOpeningBalance(closingBalance);

            //sets the senders closing balance to include withdrawal made
            fromAccount.setClosingBalance(fromAccount.openingBalance -= amountToTransfer);
            //sets senders opening to reflect closing balance
            fromAccount.setOpeningBalance(closingBalance);
            System.out.println("Funds successfully .");
        } else {
            System.out.println("Insufficient Funds, Transfer Unsuccessful");
        }
// worth adding a try catch here for transferring between accounts

    /*
    https://codereview.stackexchange.com/questions/259695/simple-bank-application-in-java
*/


    }

    public void transactionHistory() {
        numberWithdrawals = 0;
        numberDeposits = 0;
        getOpeningBalance();
        getClosingBalance();

    }

}

