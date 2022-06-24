import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private String input;
    private ArrayList<Customer> customers;
    private Customer currentAccount;

    public static void main(String[] args) {

        float[] yearlyBalance = new float[366];
        Random rand = new Random();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (int i = 0; i < yearlyBalance.length; i++) {
            yearlyBalance[i] = rand.nextFloat(100, 10000);
            yearlyBalance[i] = Float.valueOf(decimalFormat.format(yearlyBalance[i]));
        }
        System.out.println(Arrays.toString(yearlyBalance));


        Main main = new Main();
        main.customers = new ArrayList<>();
        main.currentAccount = new Customer();
        // temporary created to test the app
        main.validCustomer(new Customer("Mohsen", "-", "mohsen@gmail.com", "999999999"));
        main.validCustomer(new Customer("King", "-", "king@gmail.com", "999999999"));
        main.validCustomer(new Customer("Tamara", "-", "tamara@gmail.com", "999999999"));
        main.validCustomer(new Customer("Andy", "-", "andy@gmail.com", "999999999"));


        main.mainMenu();
    }

    public void validCustomer(Customer customer) {
        Customer temp = customer;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmailAddress().equals(customer.getEmailAddress())) {
                System.out.println("ERROR: This email address is already exist");
                return;
            }
        }
        customers.add(temp);
    }

    public void mainMenu() {
        System.out.println("MAIN MENU\nPlease select an option ... ");

        do {
            System.out.println("1.Admin\n2.Customer\n3.Exit");

            input = scanner.next();
            switch (input) {
                case "1":
                    adminMenu();
                    break;
                case "2":
                    customerMenu();
                    break;
                default:
                    if (!input.equals("3")) {
                        if (!input.equals("3"))
                            System.out.println("Please enter a valid number");
                    }
                    break;
            }
        } while (!input.equals("3"));
    }

// add feature to allow admin to transfer money between accounts
    //add feature to allow admin to create accounts for customers.

    private void adminMenu() {
        System.out.println("ADMIN MENU\nPlease select an option ... ");
        System.out.println(" 1.All customers // 2.create new customer // 3.find a Customer // 4.delete a customer // 5.Transfer money // 6.Exit to main menu");
        input = scanner.next();
        switch (input) {
            case "1":
                findAllCustomers();
                break;
            case "2":
                newCustomer();
                break;
            case "3":
                System.out.println("Please enter the customer ID");
                Long cusID = scanner.nextLong();
                findCustomer(cusID);
                break;
            case "4":
                deleteCustomer();
                break;
            case "5":
                transferMoney();
                break;
            default:
                if (!input.equals("6")) {
                    System.out.println("Please enter a valid number");
                    adminMenu();
                }
                break;
        }
        if (!input.equals("6"))
            adminMenu();
        else
            mainMenu();

    }

    private void transferMoney() {
        findAllCustomers();
        System.out.println("Please enter the customer number that you would to transfer from ...");
        String tempCus1 = scanner.next();
        int cus1 = Integer.parseInt(tempCus1);
        customers.get(cus1).getListOfBankAccounts();
        System.out.println("please enter the account number to transfer from ....");
        String tempAcc1 = scanner.next();
        int acc1 = Integer.parseInt(tempAcc1);
        System.out.println("Please enter the customer number that you would to transfer from ...");
        String tempCus2 = scanner.next();
        int cus2 = Integer.parseInt(tempCus2);
        customers.get(cus2).getListOfBankAccounts();
        System.out.println("please enter the account number to transfer from ....");
        String tempAcc2 = scanner.next();
        int acc2 = Integer.parseInt(tempAcc1);
        System.out.println("please enter the the amount of money  ....");
        String tempAmount = scanner.next();
        double amount = Double.parseDouble(tempAcc1);
        currentAccount.getCurrentBankAcc().moneyTransfer(getTheAccountNumber(cus1, acc1), getTheAccountNumber(cus2, acc2), amount);

    }


    public BankAcc getTheAccountNumber(int customerId, int accNumber) {
        Long tempLong = Long.valueOf(customerId);
        Customer temp = findCustomer(tempLong);
        for (int i = 0; i < temp.getBankAccounts().size(); i++) {
            if (temp.getBankAccounts().get(i).getAccNum() == accNumber)
                return temp.getBankAccounts().get(i);
        }

        System.out.println("Cant find the the Account  ");
        return null;

    }


    private void customerMenu() {
        System.out.println("CUSTOMER MENU\nPlease select an option ... ");
        System.out.println(" 1.Register // 2.Login // 3.Help // 4.Exit to main menu");
        input = scanner.next();
        switch (input) {
            case "1":
                newCustomer();
                break;
            case "2":
                customerLogin();
                break;
            case "3":
                System.out.println("please call +44123456789 to get help\n---------------------------");
                customerMenu();
                break;
            default:
                if (!input.equals("4")) {
                    if (!input.equals("4")) {
                        System.out.println("Please enter a valid number");
                        customerMenu();
                    }
                }
                break;
        }
        if (!input.equals("4"))
            customerMenu();
        else
            mainMenu();

    }


    public void deleteCustomer() {
        System.out.println("Please enter the customer ID");
        Long cusID = scanner.nextLong();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == cusID) {
                customers.remove(i);
                System.out.println("====> Selected customer is successfully removed from the system");
                adminMenu();
            }
        }
        System.out.println("====> Customer does not exist");
        adminMenu();
    }

    public void findAllCustomers() {
        System.out.println("Please enter the customer ID");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).toString());
        }
        adminMenu();
    }

    public Customer findCustomer(Long customerID) {

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customerID) {
                System.out.println(customers.get(i).toString());
                return customers.get(i);
            }
        }
        System.out.println("====> Customer does not exist");
        return null;
    }

    public void newCustomer() {
        System.out.println("Please enter your name:");
        String tempName = scanner.next();
        System.out.println("Please enter your last name:");
        String tempLastName = scanner.next();
        System.out.println("Please enter your email address:");
        String tempEmailAddress = scanner.next();
        System.out.println("Please enter your phone number:");
        String tempPhone = scanner.next();
        validCustomer(new Customer(tempName, tempLastName, tempEmailAddress, tempPhone));
        customerMenu();
    }

    public long loginValidation() {
        long temp = 0;
        System.out.println("Please enter your name ");
        String cusName = scanner.next();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(cusName)) {
                System.out.println("====> WELCOME " + cusName);
                temp = customers.get(i).getId();
            }
        }
        return temp;

    }

    public void customerLogin() {
        long temp = loginValidation();
        if (temp != 0) {
            yourBank(temp);
        } else {
            System.out.println("Please enter a valid name ");
            customerMenu();
        }
    }

    public void yourBank(long customerNumber) {
        System.out.println("BANK MENU\nPlease select an option ... ");
        System.out.println("1.Create a new Bank account // 2.Your accounts // 3.Exit to main menu");
        input = scanner.next();
        switch (input) {
            case "1":
                createNewBankAccount(customerNumber);
                break;
            case "2":
                allCustomerAccounts(customerNumber);
                break;
            default:
                if (!input.equals("3")) {
                    System.out.println("Please enter a valid number");
                    customerMenu();
                }
                break;
        }
        if (!input.equals("3"))
            yourBank(customerNumber);
        else
            customerLogin();
    }

    private void createNewBankAccount(long customerNumber) {
        System.out.println("Choose an account to create");
        System.out.println(" 1.Create Current Account // 2.Create Business Account // 3.Create ISA Account // 4.Back to Customer Accounts");
        input = scanner.next();
        switch (input) {
            case "1":
                createCurrentAccount(customerNumber);
                break;
            case "2":
                createBusinessAccount(customerNumber);
                break;
            case "3":
                createISAAccount(customerNumber);
                break;
            default:
                if (!input.equals("4")) {
                    System.out.println("Please enter a valid number");
                    allCustomerAccounts(customerNumber);
                }
                break;
        }
        if (!input.equals("4"))
            createNewBankAccount(customerNumber);
        else
            yourBank(customerNumber);
    }

    private void allCustomerAccounts(long customerNumber) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customerNumber) {
                customers.get(i).getListOfBankAccounts();;
            }
        }

        System.out.println("Please select an account to continue ...  ");
//        int accNum = scanner.nextInt();
//        if (accNum - 1 <= temp.getBankAccounts().size())
//            return temp.getBankAccounts().get(accNum - 1);
//
//        System.out.println("please enter a valid number");
    }

    public void yourAccount(BankAcc bankAcc) {
        System.out.println("Choose an Option");
        System.out.println(" 1.Deposit \n 2.Withdrawn \n 3.Transfer \n 4.View Balance \n 5.Exit to Customer Menu");
    }

    public void createCurrentAccount(Long customerNumber) {
        BankAcc currentAcc = new CurrentAccount();
        int customerNumberInInt = customerNumber.intValue();
        customers.get(customerNumberInInt - 1).getBankAccounts().add(currentAcc);
        customers.get(customerNumberInInt - 1 ).getListOfBankAccounts();
    }

    public void createBusinessAccount(Long customerNumber) {
        BankAcc businessAcc = new BusinessAccount();
        int customerNumberInInt = customerNumber.intValue();
        //ask user to deposit money to open ISA account
        //check if balance is less than 100, if yes
        //prompt user to make balance 100
        //only then when balance = 100 or more
        //add the created account to customer array
        customers.get(customerNumberInInt ).getBankAccounts().add(businessAcc);
        customers.get(customerNumberInInt).getListOfBankAccounts();
        System.out.println("Choose an Option");
        System.out.println(" 1.Deposit \n 2.Withdrawn \n 3.Transfer \n 4.View Balance \n 5.Exit to Customer Menu");
    }

    public void createISAAccount(Long customerNumber) {
        BankAcc ISAAcc = new ISAAccount();
        int customerNumberInInt = customerNumber.intValue();
        customers.get(customerNumberInInt ).getBankAccounts().add(ISAAcc);
        customers.get(customerNumberInInt ).getListOfBankAccounts();
        System.out.println("Choose an Option");
        System.out.println(" 1.Deposit \n 2.Withdrawn \n 3.Transfer \n 4.View Balance \n 5.Exit to Customer Menu");
    }

    //renamed the below, so we don't confuse the use of the word "current"


}