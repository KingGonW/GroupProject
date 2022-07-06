import java.util.ArrayList;
import java.util.Random;

public class BankAcc {
    private int accNum;
    private double balance;
    BankAcc ISAAccount;

    private String accType;

    private String sortCode;

//the below array list relates to Transaction class
    //the idea is that each account holds an array list of the transactions
    //this will be called
    private ArrayList<Transaction> transactions = new ArrayList<>();

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
        Random rand = new Random();
        int nextNum = this.accNum;
        this.accNum = rand.nextInt(99999999);
        if (this.accNum == nextNum) {
            this.accNum = rand.nextInt(99999999);
        }
        setBalance(200);
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccNum() {
        return accNum;
    }
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


    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void depositMoney(double depositAmount) {
        //  System.out.println("Please enter the amount you want to deposit:  ");
        //sets closing balance to balance after deposit was made

            balance += depositAmount;
            setBalance(balance);
            //new opening balance is the last closing balance amount
            System.out.println("You Have Deposited $" + depositAmount + "\n" +
                    "Your Balance is Now: $" + getBalance());
        }




    public void withdrawMoney(double withdrawAmount) {

        if (balance < withdrawAmount) {
            System.out.println("Insufficient Funds");
        } else {
            balance -= withdrawAmount;
            System.out.println("You have withdrawn $" + withdrawAmount + " from your account" + "\n" +
                    "Your Balance is now: " + getBalance());
        }


    }

    public void moneyTransfer(BankAcc fromAccount, BankAcc toAccount, double amountToTransfer) {

        if (fromAccount.accType == "ISAAccount") {
            if (ISAAccount.getBalance() - amountToTransfer < 100) {
                System.out.println("Your transfer will make your new openingBalance less than 100 +" +
                        "please keep 100 and above at all times");
                return;
            }
        }
        if (fromAccount.getBalance() >= amountToTransfer) {
            //sets the receivers closing balance to include new deposit
            toAccount.setBalance(toAccount.balance += amountToTransfer);
            // new opening balance of receiver is last closing balance amount

            //sets the senders closing balance to include withdrawal made
            fromAccount.setBalance(fromAccount.balance -= amountToTransfer);
            //sets senders opening to reflect closing balance
            System.out.println("Funds successfully transferred.");
        } else {
            System.out.println("Insufficient Funds, Transfer Unsuccessful");
        }

    }


    //below relates to PrintAccountTransactionHistory() defined and called in Customer class
    public void printTransHistory() {

        System.out.printf("\nTransaction History for account %s\n", accNum);

        //this prints the transactions in reverse order, i.e. the most recent first
        for (int t = transactions.size() - 1; t >= 0; t--) {
            System.out.printf(transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo) {
        Transaction newTransaction = new Transaction(amount, memo, this);
        getTransactions().add(newTransaction);
    }

/*    public double getBalance() {
        double balance;
        //run a loop to go through the transactions array list - defined above - and return the balance
        for(Transaction t: this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }*/


}

