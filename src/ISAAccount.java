import static java.lang.Math.*;

public class ISAAccount extends BankAcc{


    ISAAccount (double initialDeposit) {
        super();
        this.setBalance(initialDeposit);
        if(initialDeposit > 100) {
            this.setInterest(0.02);
        }
    }
    public static void calInterest(double PAmount, double ROI, double TimePeriod)
    {
        double simpleInterest;

        simpleInterest = (PAmount * ROI * TimePeriod) / 100;

        System.out.println("\n Interest accumulated for your amount  " + PAmount + " is = " + simpleInterest);
    }






}
