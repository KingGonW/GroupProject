import java.util.Random;

public class BankAcc {
    public static void main (String[] args){
        Random rand= new Random();
        int sort_code = rand.nextInt(999999); // use split method to finalise sort code
        System.out.println(sort_code);
        //return String.format("%06d", sort_code);
        int accnum = rand.nextInt(99999999);
        System.out.println(accnum);
    }
}
