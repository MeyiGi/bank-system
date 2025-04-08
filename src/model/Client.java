package model;

public class Client {
    private String firstName;
    private String secondName;
    private String dateOfBirth;
    private String inn;
    private String phoneNumber;
    private String bankAccount;
    private double balance;

    public Client(String firstName, String secondName, String dateOfBirth,
                  String inn, String phoneNumber, String bankAccount, double balance) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.inn = inn;
        this.phoneNumber = phoneNumber;
        this.bankAccount = bankAccount;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String[] toArray() {
        return new String[]{firstName, secondName, dateOfBirth, inn, phoneNumber, bankAccount, String.valueOf(balance)};
    }
}
