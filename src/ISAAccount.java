public class ISAAccount extends BankAcc{

final String sortCode;
    ISAAccount (double initialDeposit, String sortCode) {
        super();
        this.sortCode = "22-66-44";
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

    public String getSortCode() {
        return sortCode;
    }


}
