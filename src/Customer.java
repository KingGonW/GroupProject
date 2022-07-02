

import java.util.ArrayList;

public class Customer {

    private long id;
    private String firstName;
    private String lastName;
    private static String emailAddress;
    private static String phoneNumber;

    private static long counter = 0;

    private BankAcc currentBankAcc;
    private static ArrayList<BankAcc> bankAccounts;

    private static ArrayList<Customer> customers;



    // Actions a customer is expected to do should be here, but I don't want to add new classes at this point (Andy)


    public void addAccount(BankAcc account) {
        this.bankAccounts.add(account);
    }

    public Customer() {
    }

    public Customer(String firstName, String lastName, String emailAddress, String phoneNumber) {
        currentBankAcc = new BankAcc();
        setId(generateUniqueId());
        setEmailAddress(emailAddress);
        setName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);

        //creates an empty list of accounts
        this.bankAccounts = new ArrayList<BankAcc>();
    }




    public boolean checkISAAcc(Object obj) {
        if (obj != null) {
            System.out.println("You  already have an ISA Account ");
            return false;
        } else
            return true;
    }

    public long generateUniqueId() {
        counter++;
        return counter;
    }

    public Customer findCustomer(int id, Customer[] customers) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].getId() == id)
                return customers[i];
        }
        System.out.println("Customer does not exist ");
        return null;

    }

    public ArrayList<BankAcc> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAcc> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void getListOfBankAccounts() {
        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.println(i + 1 + " = " + bankAccounts.get(i).toString());
            System.out.println();
        }
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        if (!name.isEmpty())
            this.firstName = firstName;
    }

    public BankAcc getCurrentBankAcc() {
        return currentBankAcc;
    }

    public void setCurrentBankAcc(BankAcc currentBankAcc) {
        this.currentBankAcc = currentBankAcc;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (!lastName.isEmpty())
            this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        // System.out.println("Please enter your email address using one of the following email providers : gmail,hotmail,outlook");
        if (emailAddress.contains("@gmail.com") || emailAddress.contains("@outlook.com") || emailAddress.contains("@hotmail.com")) //add email endings
            this.emailAddress = emailAddress;
        else {
            this.emailAddress = null;
            System.out.println("Please enter a valid email address");

        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isNumeric(String phoneNumber) {
        try {
            Double.parseDouble(phoneNumber);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid phone number");
            return false;
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isNumeric(phoneNumber))
            this.phoneNumber = phoneNumber;
        else
            this.phoneNumber = null;

    }

    @Override
    public String toString() {
        return "Customer ID:" + id + " Customer Name: " +
                firstName + " Customer lastName: " + lastName + " Customer Email: " + emailAddress +
                " Customer Phone Number: " + firstName;
    }


    public void showCustomer() {
        System.out.println("Customer ID: " + id);
        System.out.println("Customer Name: " + firstName + lastName);
        System.out.println("Customer Email: " + firstName);
        System.out.println("Customer Phone Number: " + firstName);

    }

    //the below links to showTransactionHistory(), defined and called in Main.
    //think of this as a helper method
    public void printAccountTransactionHistory(int accountIndex) {
        this.bankAccounts.get(accountIndex).printTransHistory();
    }

    //the below links to showTransactionHistory(), defined and called in Main.
    //think of this as a helper method
    public int numAccounts() {
        return  this.bankAccounts.size();
    }

    //the below gets called in transfer, withdraw and deposit operations
    public static void addAccountTransaction(int accountIndex, double amount, String memo) {
        Customer.bankAccounts.get(accountIndex).addTransaction(amount, memo);
    }

    //the below gets called in transfer, withdraw and deposit operations
    public static int getAccountNumber(int accountIndex) {
        return Customer.bankAccounts.get(accountIndex).getAccNum();
    }
}