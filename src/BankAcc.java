import java.util.Random;
import java.util.Scanner;

public class BankAcc {
    private String sortCode;
    private int accNum;

    public BankAcc(){
        Random rand= new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<3;i++){
            sb.append(rand.nextInt(99));
            sb.append("-");
        }
        sb.deleteCharAt(8);
        this.sortCode= sb.toString();
        this.accNum= rand.nextInt(99999999);
    //update another one
    }
}

