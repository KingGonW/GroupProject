

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

public class Customer {

    private long id;
    private String name;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    private static long counter = 0;

    private BankAcc currentBankAcc;
    private ArrayList<BankAcc> bankAccounts;


    public Customer() {
    }

    public Customer(String name, String lastName, String emailAddress, String phoneNumber) {
        currentBankAcc = new BankAcc();
        setId(generateUniqueId());
        setEmailAddress(emailAddress);
        setName(name);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        bankAccounts = new ArrayList<>();
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
        System.out.println("Customer does not exist");
        return null;

    }

    public ArrayList<BankAcc> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAcc> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void getListofBankAccounts() {
        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.println("Account type : " + bankAccounts.get(i).getAccType() + " Account number : "
                    + bankAccounts.get(i).getAccNum() +
                    " Sort code : " + bankAccounts.get(i).getSortCode() + "Balance : " + bankAccounts.get(i).getClosingBalance()
            );
        }
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
                name + " Customer lastName: " + lastName + " Customer Email: " + emailAddress +
                " Customer Phone Number: " + name;
    }


    public void showCustomer() {
        System.out.println("Customer ID: " + id);
        System.out.println("Customer Name: " + name + lastName);
        System.out.println("Customer Email: " + name);
        System.out.println("Customer Phone Number: " + name);

    }
}