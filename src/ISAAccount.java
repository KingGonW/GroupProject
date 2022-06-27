import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class ISAAccount extends BankAcc {

    public static String accountType = "ISA Account";
    public static String ISAsortCode = "22-66-44";
    private final float interestRate = 0.02f;
    private int ISAAccountNumber;




    public ISAAccount() {
        setISAAccountNumber(super.getAccNum());

    }

    public void setISAAccountNumber(int ISAAccountNumber) {
        this.ISAAccountNumber = ISAAccountNumber;
    }

  //overriding parent class withdraw method 
    @Override
    public void withdrawMoney(double withdrawAmount) {
        //override withdraw method from BankAcc class
        if (this.getOpeningBalance() - withdrawAmount < 100) {
            System.out.println("Your account balance will be too low, " +
                    "to keep your ISA account, please keep balance over 100");
            System.out.println("Would you like to deposit money?");
        } else {
            withdrawMoney(withdrawAmount);
            System.out.println("You have withdrawn " + withdrawAmount + " from your account" + "\n" +
                    "Your Balance is now: " + this.getClosingBalance());
        }
    }



    // method to calculate interest on customers yearly average balance
    public void calcInterest()
    {
        //initialise array to carry yearly balances (366 includes leap year)
        //initialise variables used in interest calculation
        float[] yearlyBalance = new float[366];
        float yearlyInterest = 0f;
        float finalBalance = 0f;

        //use random function to populate array with random balances
        Random rand = new Random();

        //this function is used to format variables to only contain 2 decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        //declare and initialise sum variable to be used to calculate the average
        float sum = 0.0f;

        //create for loop to iterate through the array
        for (int i = 0; i < yearlyBalance.length; i++) {

            //the for loop will go through each index of the array and assign it a random value
            yearlyBalance[i] = rand.nextFloat(100, 10000);

            //this formats the array to only display up tp 2 decimal points
            yearlyBalance[i] = Float.valueOf(decimalFormat.format(yearlyBalance[i]));

            //calculates the sum of the array using i
            //to add to each value of i that passes through the loop
            sum += yearlyBalance[i];
        }

        //declare and initialise variable to be used in the calculation of interest
        float yearlyAverage = 0.0f;

        //calculates yearly average by dividing the sum of the array by its length
        yearlyAverage = sum / yearlyBalance.length;
        yearlyAverage = Float.valueOf(decimalFormat.format(yearlyAverage));

        //calculates customer yearly average by using the average found in prev calculation
        //and multiplying that by the interest rate which is 0.02;
        yearlyInterest = yearlyAverage * interestRate;
        yearlyInterest = Float.valueOf(decimalFormat.format(yearlyInterest));

        //calculates final balance by adding the average balance to
        // the yearly interest calculated previously
        finalBalance = yearlyAverage + yearlyInterest;
        finalBalance = Float.valueOf(decimalFormat.format(finalBalance));

        System.out.println("Your balance average over the last financial year was: $" + yearlyAverage + '\n' +
                "Interest accumulated on your balance over the past year was: $" + yearlyInterest + '\n' +
                "Your final balance was: $" + finalBalance);

    }



    @Override
    public String toString() {
        return "Account Type:" + accountType + " Sort Code: " +
                ISAsortCode + " Account Number: " + ISAAccountNumber
                + " Balance" + super.getClosingBalance() +
                " Interest Rate: " + interestRate + "%%";


    }

}
