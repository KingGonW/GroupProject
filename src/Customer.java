

import java.util.ArrayList;

public class Customer {

    private long id;
    private String name;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    private static long counter = 0;

    private ArrayList<BankAcc> bankAccounts;


    public Customer() {
    }

    public Customer(String name, String lastName, String emailAddress, String phoneNumber) {
        setId(generateUniqueId());
        setEmailAddress(emailAddress);
        setName(name);
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
    public String getListOfBankAccounts() {
        String temp = "";
        for (int i = 0; i < bankAccounts.size(); i++) {
            temp += bankAccounts.get(i).toString() + "\n";
        }
        System.out.println(temp);
        return temp;
    }

    ArrayList<BankAcc> getAccount() {
        return bankAccounts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isEmpty())
            this.name = name;
    }

//    public BankAcc getCurrentBankAcc() {
//        return currentBankAcc;
//    }
//
//    public void setCurrentBankAcc(BankAcc currentBankAcc) {
//        this.currentBankAcc = currentBankAcc;
//    }



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
        return "Customer ID: " + id + " Name: " +
                name + " Last Name: " + lastName + " Email: " + emailAddress +
                " Phone Number: " + phoneNumber;
    }


    public void showCustomer() {
        System.out.println("Customer ID: " + id);
        System.out.println("Customer Name: " + name + lastName);
        System.out.println("Customer Email: " + emailAddress);
        System.out.println("Customer Phone Number: " + phoneNumber);

    }

    //the below links to showTransactionHistory(), defined and called in Main.
    //think of this as a helper method
    public void printAccountTransactionHistory(int accountIndex) {
        getBankAccounts().get(accountIndex).printTransHistory();
    }

    //the below links to showTransactionHistory(), defined and called in Main.
    //think of this as a helper method
    public int numAccounts() {
        return  getBankAccounts().size();
    }

    //the below gets called in transfer, withdraw and deposit operations
    public  void addAccountTransaction(int accountIndex, double amount, String memo) {
        bankAccounts.get(accountIndex).addTransaction(amount, memo);
    }


    //the below gets called in transfer, withdraw and deposit operations
    public  int getAccountNumber(int accountIndex) {
        return bankAccounts.get(accountIndex).getAccNum();
    }



  public double getAccountBalance(int accountIndex) {
      return this.bankAccounts.get(accountIndex).getBalance();}



}
