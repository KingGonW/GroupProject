//Shockingly, this class holds data and methods regarding Customers

import java.util.ArrayList;

public class Customer {

        private String firstName;

        private String lastName;

        private String CustomerId;

        private ArrayList<BankAccount> bankAccounts;

        public Customer(String firstName, String lastName, Bank theBank) {

            //set users name
            this.firstName = firstName;
            this.lastName = lastName;

            //get a new id for the user
            this.CustomerId = theBank.getNewUserID();

            //create an empty list of accounts
            this.bankAccounts=new ArrayList<BankAccount>();

            //Print log message
            System.out.printf("New User: %s %s with ID %s created.", firstName, lastName, this.CustomerId);
        }

        public void addAccount(BankAccount account) {
            this.bankAccounts.add(account);
        }

        public String getID() {

            return this.CustomerId;
        }

        public Object getFirstName() {
            return this.firstName;
        }


        //this prints a summary of the accounts of this user
        public void printAccountsSummary() {

            System.out.printf("\n\n%s's Accounts Summary:\n ", this.firstName);

            // sets our counter at 0,
            // as long as the counter is less than the size of the accounts array
            // adds one to the counter
            for (int a = 0; a <this.bankAccounts.size(); a++){

                //%d is the format for an integer
                //%s is the summary line - each account instance will generate one of these
                //getSummaryLine() is defined in Accounts.java
                System.out.printf("%d) %s\n", a+1,
                        this.bankAccounts.get(a).getSummaryLine());
            }
            System.out.println();
        }

        public int numAccounts() {

            return this.bankAccounts.size();
        }

        public void printAccountTransactionHistory(int accountIndex) {
            this.bankAccounts.get(accountIndex).printTransHistory();
        }
    }


