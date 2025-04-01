package model;

public class ClientModel {
    private String firstName;
    private String secondName;
    private String dateOfBirth;
    private String inn;
    private String phoneNumber;
    private String bankAccount;
    private double balance;

    public ClientModel(String firstName, String secondName, String dateOfBirth,
                       String inn, String phoneNumber, String bankAccount, double balance) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.inn = inn;
        this.phoneNumber = phoneNumber;
        this.bankAccount = bankAccount;
        this.balance = balance;
    }

    public String[] toArray() {
        return new String[]{firstName, secondName, dateOfBirth, inn, phoneNumber, bankAccount, String.valueOf(balance)};
    }
}
