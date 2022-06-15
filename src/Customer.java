import java.util.Random;
import java.util.regex.Pattern;

public class Customer {

    private long id;
    private String name;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;


    public Customer(String name, String lastName, String emailAddress, String phoneNumber) {
        Random random = new Random();
        setId(random.nextLong(1000));
        setEmailAddress(emailAddress);
        setName(name);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
    }


    public boolean checkISAAcc(Object obj) {
        if (obj != null)
            return true;
        else{
            System.out.println("test");
            return false;
    }}

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
        if (emailAddress.contains("@") && emailAddress.contains("."))
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
}
