﻿import java.util.Random;

public class BankAcc {
    private int accNum;
    private double balance;
    private double transactionHistory;
    private double interest;

    public BankAcc(){
        Random rand= new Random();
        int nextNum = accNum;
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

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
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
    }

    public void moneyTransfer(BankAcc thisAccount, BankAcc toAccount, double amountToTransfer){
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

