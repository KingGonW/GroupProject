import java.text.DecimalFormat;
import java.util.Random;

public class ISAAccount extends BankAcc {

    public static String accountType = "ISA Account";
    public static String ISAsortCode = "22-66-44";
    private static final float[] yearlyBalance = new float[366];
    private static final float interestRate = 0.02f;
    private int ISAAccountNumber;


    public ISAAccount() {
        //super used to get variables from parent
        super(accountType, ISAsortCode);
        super.getAccNum();
        //set method used here to set account number using the
        //random account number method in parent class constructor
        setISAAccountNumber(super.getAccNum());

    }

    //used to set account number
    //made private as it is only to be used within this class to generate an account number
    private void setISAAccountNumber(int ISAAccountNumber) {
        this.ISAAccountNumber = ISAAccountNumber;
    }

    //overriding parent class withdraw method
    @Override
    public void withdrawMoney(double withdrawAmount) {
        //this checks to make withdraw amount doesn't move balance below 100
        if (this.getOpeningBalance() - withdrawAmount < 100) {
            System.out.println("Your account balance will be too low, " +
                    "to keep your ISA account, please keep balance over 100");
            System.out.println("Would you like to deposit money?");
        } else {
            //after checking withdrawal doesn't go below 100 balance
            //will execute withdrawal method from parent class
            withdrawMoney(withdrawAmount);
            System.out.println("You have withdrawn " + withdrawAmount + " from your account" + "\n" +
                    "Your Balance is now: " + this.getClosingBalance());
        }
    }


    /*
     method to calculate interest on customers yearly average balance
    to access interest calculation from main menu use calcInterest(calcYearlyAverage())
    */
    public static float[] calcYearlyAverage() {

        //use random function to populate array with random balances
        Random arrRandom = new Random();
        //this function is used to format variables to only contain 2 decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.##");


        //create for loop to iterate through the array
        for (int i = 0; i < yearlyBalance.length; i++) {
            //the for loop will go through each index of the array and assign it a random value
            yearlyBalance[i] = arrRandom.nextFloat(100, 10000);
            //this formats the array to only display up to 2 decimal points
            yearlyBalance[i] = Float.parseFloat(decimalFormat.format(yearlyBalance[i]));
        }

        //return yearly balance to be used in calcInterest method
        return yearlyBalance;

    }

    public static void calcInterest(float[] yearlyBalance) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        //initialise variables used in interest calculation
        float sum = 0.0f;
        float yearlyAverage = 0.0f;
        float yearlyInterest;

        //foreach loop to calculate yearly average
        for (float value : yearlyBalance) {
            //calculate sum by adding each value to the previous
            sum += value;
            //yearly average assigned value of equation
            yearlyAverage = sum / yearlyBalance.length;
            //uses format function to only include 2 decimal places
            yearlyAverage = Float.parseFloat(decimalFormat.format(yearlyAverage));
        }

        //calculate interest by multiplying average by the interest rate
        yearlyInterest = yearlyAverage * interestRate;
        //using format function to only include 2 decimal places in result
        yearlyInterest = Float.parseFloat(decimalFormat.format(yearlyInterest));

        System.out.println("$" + yearlyAverage);
        System.out.println("$" + yearlyInterest);
    }


    //The toString method is used to return a string representation of the ISA object.
    @Override
    public String toString() {
        return "Account Type:" + accountType + " Sort Code: " +
                ISAsortCode + " Account Number: " + ISAAccountNumber
                + " Balance " + super.getClosingBalance() +
                " Interest Rate: " + interestRate + "%%";


    }

}
