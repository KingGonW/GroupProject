import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private String input;
    private ArrayList<Customer> customers;

    public static void main(String[] args) {

        Main main = new Main();
        main.customers = new ArrayList<>();
        //insert the customer detail
        main.validCustomer(new Customer("Mohsen", "M", "mohsen@gmail.com", "07374829121"));
        main.validCustomer(new Customer("King", "K", "king@gmail.com", "07664738222"));
        main.validCustomer(new Customer("Tamara", "T", "tamara@gmail.com", "07776652499"));
        main.validCustomer(new Customer("Andy", "A", "andy@gmail.com", "07542323451"));
        main.generateAccounts(main.customers.get(0));
        main.generateAccounts(main.customers.get(1));
        main.generateAccounts(main.customers.get(2));
        main.generateAccounts(main.customers.get(3));

//save the customer details into a .txt
        try {
            FileWriter writer = new FileWriter("customerArray.txt");
            Writer output = new BufferedWriter(writer);
            int sz = main.customers.size();
            for (int i = 0; i < sz; i++) {
                output.write(main.customers.get(i).toString() + "\n");
            }
            output.close();

        } catch (IOException e) {
            System.out.println("File Creation Unsuccessful");
            e.printStackTrace();
        }

        for (Customer customer : main.customers) {
            String temp = customer.getName();
            try {
                FileWriter writer = new FileWriter(temp + "'s Accounts.txt");
                Writer output = new BufferedWriter(writer);
                output.write(customer.getListOfBankAccounts());
                output.close();

            } catch (IOException e) {
                System.out.println("File Creation Unsuccessful");
                e.printStackTrace();
            }

        }


        main.mainMenu();

    }

    //method to write accounts of each customer to a file
    public void writeFileForAccounts(Long customerNumber) {
        String temp = "";
        int customerNumberInInt = customerNumber.intValue();

        //for each to interate through customers array
        //assign customer id to variable temp to be used as file contents
        for (Customer customer : customers) {
            if (customer.getId() == customerNumber - 1) {
                temp = customer.getName();
            }


            //check to make sure temp isnt empty
            if (!temp.equals("")) {
                try {
                    //using temp to add to file creation name
                    FileWriter writer = new FileWriter(temp + ".txt");
                    Writer output = new BufferedWriter(writer);
                    //writes each customer's bank accounts
                    output.write(customers.get(customerNumberInInt - 1).getListOfBankAccounts());
                    output.close();

                } catch (IOException e) {
                    System.out.println("File Creation Unsuccessful");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Customer not found");
            }
        }
    }

    //generate random number of current account for each customer, max 3 for each
    public void generateAccounts(Customer customer) {
        Random random = new Random();
        int randomInt = random.nextInt(1, 4);

        for (int i = 0; i < randomInt; i++) {
            createCurrentAccount(customer.getId());
        }


    }

    //print all the accounts that the chosen customer has
    public void printCustomerAccounts(Long customerID) {
        //   int customerNumberInInt = customerID.intValue();
//        System.out.println(customers.get(customerNumberInInt - 1).getName());
//        return customers.get(customerNumberInInt - 1).getListOfBankAccounts();


        //here we tried to read the file but didn't seem to print anything
        // so have left the original print accounts code

        String temp = "";
        String line = "";
        ArrayList<Object> aList = new ArrayList<>();
        int customerNumberInInt = customerID.intValue();

        for (Customer customer : customers) {
            if (customer.getId() == customerNumberInInt) {
                temp = customer.getName();
            }
        }
        //read the lines of text into an array list
        if (!temp.equals("")) {
            try {
                BufferedReader input = new BufferedReader(new FileReader(temp + "'s Accounts.txt"));
                System.out.println(temp);
                if (!input.ready()) { // check whether the file can be read
                    throw new IOException();
                }

                do {
                    line = input.readLine();
                    if (line != null)
                        System.out.println(line);
                }
                while (line != null);//read a line of text
                //*aList.add(input.readLine());*//* //add the line of text to the array list

                input.close();
            } catch (
                    IOException e) {  // catch any problems found e.g. file not found
                e.printStackTrace();
            }
        }


    }

    //check the validation of the customer details
    public void validCustomer(Customer customer) {
        for (Customer value : customers) {
            if (value.getEmailAddress().equals(customer.getEmailAddress())) {
                System.out.println("ERROR: This email address already exists");
                return;
            }
        }
        customers.add(customer);
    }

    public void mainMenu() {
        System.out.println("\n");
        System.out.println("==================================================");
        System.out.println("====== Welcome to Novus Banking Application ======");
        System.out.println("==================================================");
        System.out.println("\n");

        do {
            System.out.println("Please choose an option");
            System.out.println("""
                    1: Admin Access\s
                    2: Exit Program
                    """);

            input = scanner.next();
            if ("1".equals(input)) {
                adminMenu();
            } else {
                if (!input.equals("2")) {
                    System.out.println("Please enter a valid number");
                }
            }
        } while (!input.equals("2"));
    }


    private void adminMenu() {
        System.out.println("\nPlease choose an option");
        System.out.println("""
                1: View All Customers\s
                2: Login to Customer Account
                3: Exit to Main Menu
                """);
        input = scanner.next();
        switch (input) {
            case "1":
                findAllCustomers();

                adminMenu();
                break;
            case "2":
                for (Customer customer : customers) {
                    System.out.println(customer.toString());
                }
                customerLogin();
                break;
            case "3":
                mainMenu();
                break;
            default:
                if (!input.equals("4")) {
                    System.out.println("Please enter a valid number");
                    adminMenu();
                }
                break;
        }
        if (!input.equals("4")) adminMenu();
        else mainMenu();

    }


    //adding methods in order that it shows up in the admin menu
    public void findAllCustomers() {
        //set up variables to read file
        String filename = "customerArray.txt";
        String line;
        ArrayList<Object> aList = new ArrayList<>();

        //read the lines of text into an array list
        try {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            if (!input.ready()) { // check whether the file can be read
                throw new IOException();
            }
            while ((line = input.readLine()) != null) { //read a line of text
                aList.add(line); //add the line of text to the array list
            }
            input.close();
        } catch (
                IOException e) {  // catch any problems found e.g. file not found
            e.printStackTrace();
        }

        //print out each item in the array list
        for (Object o : aList) {
            System.out.println(o.toString());
        }
    }


    public void customerLogin() {
        long temp = loginValidation();
        if (temp != 0) {
            bankMenu(temp);
        } else {
            System.out.println("\nPlease Enter A Valid ID\n");
            adminMenu();
        }
    }

    //use to get the account number of the customer
    public BankAcc getTheAccountNumber(int customerId, int accNumber) {
        Long tempLong = (long) customerId;
        Customer temp = null;
        for (int i = 0; i < customers.size(); i++) {
            temp = customers.get(customerId);
        }
        for (int i = 0; i < temp.getBankAccounts().size(); i++) {
            return temp.getBankAccounts().get(accNumber);
        }

        System.out.println("Cant find the the Account  ");
        return null;
    }

    //login to a customer menu
    public long loginValidation() {
        long temp = 0;
        int cusName = 0;
        boolean stop = false;

        System.out.println("\nPlease Enter A Customer ID");
        do {
            try {
                cusName = scanner.nextInt();
                stop = true;
            } catch (Exception e) {
                System.out.println("Error: " + e + '\n' +
                        "Input Must Be Numerical");
            }
        } while (!stop);

        //this changes the first letter of users input to capital letter
        //so no matter what a lower or upper case input, the user will be found by their name
        for (Customer customer : customers) {
            if (customer.getId() == cusName) {
                System.out.println("\n======= Welcome To " + customer.getName() + "'s Bank Account =======\n");
                temp = customer.getId();
            }
        }
        return temp;
    }


    public void bankMenu(long customerNumber) {
        System.out.println("\nPlease Select an Option");
        System.out.println("""
                1: Create A New Bank Account\s
                2: View Accounts
                3: Exit to Admin Menu
                4: Exit to Main Menu
                """);

        input = scanner.next();
        switch (input) {
            case "1":
                printCustomerAccounts(customerNumber);
                createNewBankAccount(customerNumber);
                break;
            case "2":
                printCustomerAccounts(customerNumber);
                //the below menu gets called when Option 2 of bankMenu() is selected.
                accountMenu(customerNumber);
                break;
            case "3":
                adminMenu();
                break;
            case "4":
                mainMenu();
                break;
            default:
                if (!input.equals("6")) {
                    System.out.println("Please enter a valid number");
                    adminMenu();
                }
                break;
        }
        if (!input.equals("6")) bankMenu(customerNumber);
    }

    private void createNewBankAccount(long customerNumber) {
        System.out.println("Choose Account Type to Create");
        System.out.println("""
                1: Create Current Account\s
                2: Create Business Account
                3: Create ISA Account:
                4: Back to Customer Accounts
                5: Back to Admin Menu
                6: Back to Main Menu""");
        input = scanner.next();
        switch (input) {
            case "1":
                createCurrentAccount(customerNumber);
                printCustomerAccounts(customerNumber);
                break;
            case "2":
                createBusinessAccount(customerNumber);
                printCustomerAccounts(customerNumber);
                break;
            case "3":
                createISAAccount(customerNumber);
                printCustomerAccounts(customerNumber);
                break;
            case "4":
                bankMenu(customerNumber);
                break;
            case "5":
                adminMenu();
                break;
            case "6":
                mainMenu();
                break;

            default:
                if (!input.equals("6")) {
                    System.out.println("Please enter a valid number");
                    bankMenu(customerNumber);
                }
                break;
        }

    }

    public void createCurrentAccount(Long customerNumber) {
        //when method is called, create a current account object
        BankAcc currentAcc = new CurrentAccount();
        //getting the customer number that is logged in to associate with bank account
        int customerNumberInInt = customerNumber.intValue();
        //add bank account to customer array
        customers.get(customerNumberInInt - 1).getBankAccounts().add(currentAcc);

    }


    public void createBusinessAccount(Long customerNumber) {
        //create variable to allow creation of business account
        double initialDeposit = 0;
        //variable to get the customers ID
        int customerNumberInInt = customerNumber.intValue();

        boolean stop = false;

        //while loop here to continue running until the deposit has been made
        while (initialDeposit == 0) {
            System.out.println("To Open a Business Account, There is a Yearly Fee of $25\n");
            System.out.println("How Much Would You Like to Deposit");

            do {
                try {
                    initialDeposit = scanner.nextDouble();
                    stop = true;
                } catch (Exception e) {
                    System.out.println("Error: " + e + '\n' + "Input Must Be Numerical");
                    scanner.next();
                }
            } while (!stop);


            //require customer to make an initial deposit of $25 to open Business Account
            // will only be created when user has deposited 25 or more
            if (initialDeposit < 25) {//if less than 25, prompt user to deposit 25 or more

                System.out.println("\nInitial Deposit Must Be $25 or More\n");

            } else {
                //once deposit statement has been satisfied, create business account object
                BankAcc businessAcc = new BusinessAccount(initialDeposit);
                //add business account to desired user in the array customers
                customers.get(customerNumberInInt - 1).getBankAccounts().add(businessAcc);
                //display message to user to signify successful account creation
                System.out.println("Business Account Successfully Created\n");
            }
        }

    }


    public void createISAAccount(Long customerNumber) {

        double initialDeposit = 0;
        int customerNumberInInt = customerNumber.intValue();
        boolean stop = false;

        if (customers.get(customerNumberInInt - 1).checkISAAcc()) {
            while (initialDeposit == 0) {

                System.out.println("To Open An ISA Account, Please Deposit $100 or More\n");
                System.out.println("How Much Would You Like to Deposit");

                do {
                    try {
                        initialDeposit = scanner.nextDouble();
                        stop = true;
                    } catch (Exception e) {
                        System.out.println("Error: " + e + '\n' + "Input Must Be Numerical");
                        scanner.next();
                    }
                } while (!stop);

                if (initialDeposit < 100) {
                    System.out.println("\nInitial Deposit Must Be $100 or More\n");
                } else {

                    BankAcc ISAAcc = new ISAAccount(initialDeposit);
                    System.out.println("\nISA Account Successfully Created\n");
                    customers.get(customerNumberInInt - 1).getBankAccounts().add(ISAAcc);
                }
                //require customer to deposit and keep the balance above 100
                //only the account will be created and add to customer array when the balance is above 100
            }
        }

    }


    //the menu below is initialised in bankMenu(), when the user chooses Option 2 "View All Accounts"
    private void accountMenu(long customerNumber) {

        System.out.println("Please Select an Option");
        System.out.println("""
                               
                1: View Account Transaction History
                2: View Balance
                3: View Interest
                4: Deposit Funds
                5: Withdraw Funds
                6: Transfer Funds
                7: Back to Account Menu
                8: Back to Admin Menu
                9: Back to Main Menu""");

        input = scanner.next();

        switch (input) {
            case "1":
                showTransactionHistory(customerNumber);
                break;
            case "2":
                viewBalance(customerNumber);
                break;
            case "3":
                showInterest(customerNumber);
                break;
            case "4":
                deposit(customerNumber);
                break;
            case "5":
                withdraw(customerNumber);
                break;
            case "6":
                transferMoney(customerNumber);
                break;
            case "7":
                bankMenu(customerNumber);
            case "8":
                adminMenu();
                break;
            case "9":
                mainMenu();
            default:
                if (!input.equals("9")) {
                    System.out.println("Please enter a valid number");
                    accountMenu(customerNumber);
                }
                break;

        }
    }

    //methods displayed in order of menu options

    private void viewBalance(Long customerNumber) {
        int customerNumberInInt = customerNumber.intValue();
        System.out.println("Please choose an Account");
        int input = scanner.nextInt() - 1;
        customers.get(customerNumberInInt).getBankAccounts().get(input).getBalance();
        //once code has been executed return user to account menu
        accountMenu(customerNumber);
    }

    private void showInterest(Long customerNumber) {
        int customerNumberInInt = customerNumber.intValue();
        System.out.println("Please choose an Account");
        int input = scanner.nextInt() - 1;
        ISAAccount temp = new ISAAccount();
        for (int i = 0; i < customers.get(customerNumberInInt).getBankAccounts().size(); i++) {
            if (customers.get(customerNumberInInt).getBankAccounts().get(i).getAccType().equals("ISA Account")) {
                temp = (ISAAccount) customers.get(customerNumberInInt).getBankAccounts().get(i);
                temp.showInterest();
            }
        }
        accountMenu(customerNumber);
    }


    //below method shows transaction history
    //it takes the Customer selected, shown as customerNumberInInt
    //loops through the accounts associated with the Customer
    //and displays the information
    //that information needs to be generated in the withdraw(), deposit() and transfer() methods.
    public void showTransactionHistory(Long customerNumber) {

        printCustomerAccounts(customerNumber);
        int theAccount = 0;
        int customerNumberInInt = customerNumber.intValue();
        boolean stop = false;

        //get the account for which we wish to show the history
        do {
            //account is chosen from the arraylist. Number refers to the account in the array list,
            // not the account number
            System.out.printf("Enter the number  (1-%d) of the account\n" + "you wish to see the history of:", customers.get(customerNumberInInt - 1).numAccounts());

            // -1 to get to the 0 index position
            do {
                try {
                    theAccount = scanner.nextInt() - 1;
                    stop = true;
                } catch (Exception e) {
                    System.out.println("Error: " + e + '\n' + "Input Must Be Numerical");
                }
            } while (!stop);

            if (theAccount < 0 || theAccount >= customers.get(customerNumberInInt - 1).numAccounts()) {
                System.out.println("Invalid Account chosen. Please try again.");
            }
        } while (theAccount < 0 || theAccount >= customers.get(customerNumberInInt - 1).numAccounts());

        //print transactions history
        customers.get(customerNumberInInt - 1).printAccountTransactionHistory(theAccount);
        //once code has been executed return user to account menu
        accountMenu(customerNumber);
    }

    //gather user input to execute deposit method from BankAcc class
    public void deposit(Long customerNumber) {

        int customerNumberInInt = customerNumber.intValue();
        int toTheAccount = 0;
        int backMenu = 0;
        double dMoney = 0;
        String memo = "";
        double newBalance;
        boolean stop = false;

        System.out.println("Press 1 To Go Back To Account Menu \n" + "Press 2 to continue");
        //try catches used on inputs to catch any input mismatches
        //prompts the user to enter the correct input format to continue
        do {
            try {
                backMenu = scanner.nextInt();
                stop = true;
            } catch (Exception e) {
                System.out.println("Error: " + e + '\n' + "Please Input a Number");
                scanner.nextLine();
            }
        } while (!stop);

        if (backMenu == 1) {
            accountMenu(customerNumber);
        } else if (backMenu == 2) {
            ////use do while loop to choose the account to withdraw
            do {
                printCustomerAccounts(customerNumber);
                System.out.println("Please Choose An Account to Deposit to: ");
                //prompt user to enter which account they want to deposit from
                do {
                    try {
                        toTheAccount = scanner.nextInt();
                        stop = false;
                    } catch (Exception e) {
                        System.out.println("Error: " + e + '\n' + "Please Input a Number");
                        scanner.nextLine();
                    }
                } while (stop);
                //user prompt used to check condition
                if (toTheAccount < 0 || toTheAccount > customers.get(customerNumberInInt - 1).getBankAccounts().size()) {
                    System.out.println("Invalid Account Chosen. Please Try Again.");
                }


            } while (toTheAccount < 0 || toTheAccount > customers.get(customerNumberInInt - 1).getBankAccounts().size());
            //ise do while loop here again to enter the amount and memo
            do {
                //use do while loop here again to enter the amount and memo
                do {
                    try {
                        System.out.println("Please Enter An Amount to Deposit:");
                        dMoney = scanner.nextDouble();
                        stop = true;
                    } catch (Exception e) {
                        System.out.println("Error: " + e + '\n' + "Please Enter An Amount In Numbers");
                        scanner.nextLine();
                    }
                } while (!stop);

                do {
                    try {
                        System.out.println("Enter A Memo:");
                        memo = scanner.next();
                        stop = false;
                    } catch (Exception e) {
                        System.out.println("Error: " + e + '\n' + "Please Enter Text");
                        scanner.nextLine();
                        memo = scanner.next();
                    }
                } while (stop);

                customers.get(customerNumberInInt - 1).getBankAccounts().get(toTheAccount - 1).depositMoney(dMoney);

            } while (dMoney < 0);
            newBalance = customers.get(customerNumberInInt - 1).getBankAccounts().get(toTheAccount - 1).getBalance();
            System.out.println("Deposit Successful!");
            System.out.println("You Have Deposited: $" + dMoney + '\n' + "Your New Balance: $" + newBalance + '\n' + "Memo: " + memo);
            customers.get(customerNumberInInt - 1).addAccountTransaction(toTheAccount - 1, dMoney, memo);

            //once code has been executed return user to account menu
            accountMenu(customerNumber);
        }
    }


    public void withdraw(Long customerNumber) {
        int customerNumberInInt = customerNumber.intValue();
        int fromTheAccount = 0;
        int backMenu = 0;
        double wMoney = 0;
        String memo = "";
        double newBalance;
        boolean stop = false;
        System.out.println("Press 1 To Go Back To Account Menu \n" + " Press 2 to continue");
        //try catches used on inputs to catch any input mismatches
        //prompts the user to enter the correct input format to continue
        do {
            try {
                backMenu = scanner.nextInt();
                stop = true;
            } catch (Exception e) {
                System.out.println("Error: " + e + '\n' + "Please Input a Number");
                scanner.nextLine();
            }
        } while (!stop);

        if (backMenu == 1) {
            accountMenu(customerNumber);
        } else if (backMenu == 2) {
            //use do while loop to choose the account to deposit
            do {
                printCustomerAccounts(customerNumber);
                System.out.println("Please Choose An Account to Withdraw From: ");

                do {
                    try {
                        fromTheAccount = scanner.nextInt();
                        stop = false;
                    } catch (Exception e) {
                        System.out.println("Error: " + e + '\n' + "Please Input a Number");
                        scanner.nextLine();
                    }
                } while (stop);

                if (fromTheAccount < 0 || fromTheAccount > customers.get(customerNumberInInt - 1).getBankAccounts().size()) {
                    System.out.println("Invalid Account Chosen. Please Try Again.");
                }

            } while (fromTheAccount < 0 || fromTheAccount > customers.get(customerNumberInInt - 1).getBankAccounts().size());
            ////use do while loop again to the enter the amount and memo
            do {

                do {
                    try {
                        System.out.println("Please Enter The Amount to Withdraw:");
                        wMoney = scanner.nextDouble();
                        stop = true;

                    } catch (Exception e) {
                        System.out.println("Error: " + e + '\n' + "Please Enter An Amount In Numbers");
                        scanner.nextLine();
                    }
                } while (!stop);


                do {
                    try {
                        System.out.println("Enter A Memo:");
                        memo = scanner.next();
                        stop = false;
                    } catch (Exception e) {
                        System.out.println("Error: " + e + '\n' + "Please Enter Text");
                        scanner.nextLine();

                    }
                } while (stop);

                customers.get(customerNumberInInt - 1).getBankAccounts().get(fromTheAccount - 1).withdrawMoney(wMoney);

            } while (wMoney < 0 || customers.get(customerNumberInInt - 1).getBankAccounts().get(fromTheAccount - 1).getBalance() < wMoney);
            newBalance = customers.get(customerNumberInInt - 1).getBankAccounts().get(fromTheAccount - 1).getBalance();
            System.out.println("Withdrawal Successful!");
            System.out.println("You Have Withdrawn: $" + wMoney + '\n' + "Your New Balance: $" + newBalance + '\n' + "Memo: " + memo);

            customers.get(customerNumberInInt - 1).addAccountTransaction(fromTheAccount - 1, wMoney, memo);
        }
        //once code has been executed return user to account menu
        accountMenu(customerNumber);
    }


    private void transferMoney(Long customerNumber) {

        //variables initialised to be used in throughout loops
        int cus1 = customerNumber.intValue();
        int acc1 = 0;

        int cus2 = 0;
        int acc2 = 0;

        double amount = 0;
        boolean stop = false;

        customers.get(cus1 - 1).getListOfBankAccounts();

        do {
            try {
                System.out.println("Please Enter The Account ID to Transfer From: ");
                //prompt user to choose account after displaying bank accounts
                acc1 = scanner.nextInt();
                stop = true; //reassign variable from false
            } catch (Exception e) {
                System.out.println("Error: " + e + '\n' + "Input Must Be Numerical");
                scanner.next();
            }
        } while (!stop); //loops until user input matches the input type


        findAllCustomers();
        do {
            try {
                System.out.println("\nPlease Enter The Customer to Transfer to(1-4): ");
                cus2 = scanner.nextInt();
                customers.get(cus2 - 1).getListOfBankAccounts();
                stop = false; //variable has to be reassigned every time for while loop to fit condition
            } catch (Exception e) {
                System.out.println("Error: " + e + '\n' + "Input Must Be Numerical");
                scanner.next();
            }

        } while (stop);


        do {
            try {
                System.out.println("\nPlease Enter The Account ID to Transfer to");
                acc2 = scanner.nextInt();
                stop = true;
            } catch (Exception e) {
                System.out.println("Error: " + e + '\n' + "Input Must Be Numerical");
                scanner.next();
            }
        } while (!stop);


        do {
            try {
                System.out.println("\nPlease Enter The Amount");
                amount = scanner.nextDouble();
                stop = false;
            } catch (Exception e) {
                System.out.println("Error: " + e + '\n' + "Input Must Be Numerical");
                scanner.next();
            }
        } while (stop);

        //variable inputs taken from the user are used to get origin and destination account
        //inputs are then used to carry out money transfer method from BankAcc
        customers.get(cus1 - 1).getBankAccounts().get(0).moneyTransfer(getTheAccountNumber(cus1 - 1, acc1 - 1),
                getTheAccountNumber(cus2 - 1, acc2 - 1), amount);

        //user inputs used to add transaction to users transaction history
        customers.get(cus1 - 1).addAccountTransaction(acc1 - 1, amount, String.format("Money Transferred To: %s, %s, %s",
                customers.get(cus2 - 1).getName(), customers.get(cus2 - 1).getLastName(), customers.get(cus2 - 1).getAccountNumber(acc2 - 1)));

        accountMenu(customerNumber);
    }


}