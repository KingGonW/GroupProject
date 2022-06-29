import java.util.ArrayList;
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


    //the below array list relates to Transaction class
    //the idea is that each account holds an array list of the transactions
    //this will be called
    private ArrayList<Transaction> transactions;

    //the customer variable below is the Customer class. 
    // The idea here is that each account has a linked customer
    private Customer holder;

    //below is an array list of customers
    //this might make more sense held in another class,
    // e.g. "Bank" to mimic a bank having a list of customers
    private ArrayList<Customer> customers;

    //below is an array list of accounts
    //this might make more sense held in another class,
    // e.g. "Bank" to mimic a bank having a list of accounts
    private ArrayList<BankAcc> bankAccounts;

    BankAcc() {
    }

    //alternate constructor below, with Customer variable attached
    public BankAcc(String accType, String sortCode, Customer holder) {
        this.accType = accType;
        this.sortCode = sortCode;
        this.accNum = this.getNewAccountNumber();

    }


    //this method relates to the alternate constructor above
    private int getNewAccountNumber() {

        Random rand = new Random();
        int accountNumber = this.accNum;
        this.accNum = rand.nextInt(99999999);
        if (this.accNum == accountNumber) {
            this.accNum = rand.nextInt(99999999);
        }

        return accountNumber;
    }


    public BankAcc(String accType, String sortCode) {
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
        depositMoney(200);
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
      //  System.out.println("Please enter the amount you want to deposit:  ");
        //sets closing balance to balance after deposit was made
        setClosingBalance(openingBalance += depositAmount);
        //new opening balance is the last closing balance amount
        setOpeningBalance(closingBalance);
        this.numberDeposits++; // increment number of deposits each time one is made


        //the below posts this info to the Transactions class
        //for this to work a toAccount variable is needed
        // to test, let us call depositMoney() from one of the menus
        //scanner also needed if we want a memo to be added
        // commenting out the code until necessary things are added
        // System.out.println("Enter a memo");
        // memo= sc.nextLine();

        // ideally we want a way to choose the specific customer

        //Customer.addAccountTransaction(toAccount,depositAmount, memo);
        // ATM.printUserMenu(currentCustomer,sc);

    }


    //see comments in ISAAccount. I think nested if/else would work better here to check if account is an ISA

    //withdraw and deposit methods need redefining, in Main I think,
    // where they can take in the account number as an parameter, which the user enters
    public void withdrawMoney(double withdrawAmount) {

        if (openingBalance < withdrawAmount) {
            System.out.println("Insufficient Funds. \n Please Deposit an Amount.");
        } else {
            setClosingBalance(openingBalance -= withdrawAmount);
            setOpeningBalance(closingBalance);
            numberWithdrawals++; // increment number of withdrawals made each time money is withdrawn
        }

        //the below posts this info to the Transactions class
        //for this to work a fromAccount variable is needed
        // to test, let us call withdrawMoney() from one of the menus
        //scanner also needed if we want a memo to be added
        // ideally we want a way to choose the specific customer
        // commenting out the code until necessary things are added

        // System.out.println("Enter a memo");
        // memo= sc.nextLine();
        //Customer.addAccountTransaction(fromAccount,-1*withdrawAmount, memo);

    }

    public void moneyTransfer(BankAcc fromAccount, BankAcc toAccount, double amountToTransfer) {

        if (fromAccount.accType == "ISAAccount") {
            if (ISAAccount.getOpeningBalance() - amountToTransfer < 100) {
                System.out.println("Your transfer will make your new openingBalance less than 100 +" +
                        "please keep 100 and above at all times");
                return;
            }
        }
        if (fromAccount.openingBalance >= amountToTransfer) {
            //sets the receivers closing balance to include new deposit
            toAccount.setClosingBalance(toAccount.openingBalance += amountToTransfer);
            // new opening balance of receiver is last closing balance amount
            toAccount.setOpeningBalance(closingBalance);

            //sets the senders closing balance to include withdrawal made
            fromAccount.setClosingBalance(fromAccount.openingBalance -= amountToTransfer);
            //sets senders opening to reflect closing balance
            fromAccount.setOpeningBalance(closingBalance);
            System.out.println("Funds successfully transferred.");
        } else {
            System.out.println("Insufficient Funds, Transfer Unsuccessful");
        }

    }

    public void transactionHistory() {
        this.numberWithdrawals = 0;
        this.numberDeposits = 0;
    }


    //below relates to PrintAccountTransactionHistory() defined and called in Customer class
    public void printTransHistory() {

        System.out.printf("\nTransaction History for account %s\n", this.accNum);

        //this prints the transactions in reverse order, i.e. the most recent first
        for (int t = this.transactions.size() - 1; t >= 0; t--) {
            System.out.printf(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo) {
        Transaction newTransaction = new Transaction(amount, memo, this);
        this.transactions.add(newTransaction);
    }
}

